package com.youju.lease.web.admin.service;

import com.youju.lease.web.admin.vo.login.CaptchaVo;
import com.youju.lease.web.admin.vo.login.LoginVo;
import com.youju.lease.web.admin.vo.system.user.SystemUserInfoVo;

public interface LoginService {

    CaptchaVo getCaptcha();


    String login(LoginVo loginVo);

    SystemUserInfoVo getUserInfoById(Long userId);
}
