package com.youju.lease.web.app.service.impl;

import com.youju.lease.common.constant.RedisConstant;
import com.youju.lease.common.exception.LeaseException;
import com.youju.lease.common.result.ResultCodeEnum;
import com.youju.lease.common.utils.CodeUtil;
import com.youju.lease.common.utils.JwtUtil;
import com.youju.lease.model.entity.UserInfo;
import com.youju.lease.model.enums.BaseStatus;
import com.youju.lease.web.app.mapper.UserInfoMapper;
import com.youju.lease.web.app.service.EmailService;
import com.youju.lease.web.app.service.LoginService;
import com.youju.lease.web.app.vo.user.LoginVo;
import com.youju.lease.web.app.vo.user.UserInfoVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    @Autowired
    private EmailService emailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void sendCode(String email) {
        if (!StringUtils.hasText(email)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_EMAIL_EMPTY);
        }
        email = email.trim().toLowerCase();
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_EMAIL_INVALID);
        }

        String key = RedisConstant.APP_LOGIN_PREFIX + "email:" + email;
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        if (Boolean.TRUE.equals(hasKey)) {
            Long ttl = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (ttl != null && ttl > RedisConstant.APP_LOGIN_CODE_TTL_SEC - RedisConstant.APP_LOGIN_CODE_RESEND_TIME_SEC) {
                throw new LeaseException(ResultCodeEnum.APP_SEND_SMS_TOO_OFTEN);
            }
        }

        String code = CodeUtil.getRandomCode(6);
        emailService.sendLoginCode(email, code);
        stringRedisTemplate.opsForValue().set(key, code, RedisConstant.APP_LOGIN_CODE_TTL_SEC, TimeUnit.SECONDS);
    }

    @Override
    public String login(LoginVo loginVo) {
        String phone = loginVo.getPhone() == null ? "" : loginVo.getPhone().trim();
        String email = loginVo.getEmail() == null ? "" : loginVo.getEmail().trim().toLowerCase();

        if (!StringUtils.hasText(phone)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_EMPTY);
        }
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_PHONE_INVALID);
        }
        if (!StringUtils.hasText(email)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_EMAIL_EMPTY);
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_EMAIL_INVALID);
        }
        if (!StringUtils.hasText(loginVo.getCode())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EMPTY);
        }

        String key = RedisConstant.APP_LOGIN_PREFIX + "email:" + email;
        String code = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.hasText(code)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_EXPIRED);
        }
        if (!loginVo.getCode().equals(code)) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_CODE_ERROR);
        }

        UserInfo phoneUser = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getPhone, phone));
        UserInfo emailUser = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getEmail, email));

        if (phoneUser != null && emailUser != null && !phoneUser.getId().equals(emailUser.getId())) {
            throw new LeaseException(ResultCodeEnum.APP_LOGIN_BIND_CONFLICT);
        }

        UserInfo userInfo;
        if (phoneUser == null && emailUser == null) {
            userInfo = new UserInfo();
            userInfo.setPhone(phone);
            userInfo.setEmail(email);
            userInfo.setNickname("悠居用户-" + phone.substring(7));
            userInfo.setStatus(BaseStatus.ENABLE);
            userInfoMapper.insert(userInfo);
        } else {
            userInfo = phoneUser != null ? phoneUser : emailUser;
            if (!phone.equals(userInfo.getPhone())) {
                throw new LeaseException(ResultCodeEnum.APP_LOGIN_BIND_CONFLICT);
            }
            if (StringUtils.hasText(userInfo.getEmail()) && !email.equalsIgnoreCase(userInfo.getEmail())) {
                throw new LeaseException(ResultCodeEnum.APP_LOGIN_BIND_CONFLICT);
            }
            if (!StringUtils.hasText(userInfo.getEmail())) {
                userInfo.setEmail(email);
                userInfoMapper.updateById(userInfo);
            }
            if (userInfo.getStatus() == BaseStatus.DISABLE) {
                throw new LeaseException(ResultCodeEnum.APP_ACCOUNT_DISABLED_ERROR);
            }
        }

        stringRedisTemplate.delete(key);
        // JWT中的username继续保存手机号，原有租约按phone查询的逻辑无需修改。
        return JwtUtil.createToken(userInfo.getId(), userInfo.getPhone());
    }

    @Override
    public UserInfoVo getLoginUserInfoById(Long userId) {
        UserInfo userInfo = userInfoMapper.selectById(userId);
        return new UserInfoVo(userInfo.getId(), userInfo.getNickname(), userInfo.getAvatarUrl(), userInfo.getPhone(), userInfo.getEmail());
    }
}
