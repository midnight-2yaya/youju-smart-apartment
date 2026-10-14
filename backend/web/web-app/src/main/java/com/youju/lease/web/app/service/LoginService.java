package com.youju.lease.web.app.service;

import com.youju.lease.web.app.vo.user.LoginVo;
import com.youju.lease.web.app.vo.user.UserInfoVo;

public interface LoginService {
    void sendCode(String email);

    String login(LoginVo loginVo);

    UserInfoVo getLoginUserInfoById(Long userId);
}
