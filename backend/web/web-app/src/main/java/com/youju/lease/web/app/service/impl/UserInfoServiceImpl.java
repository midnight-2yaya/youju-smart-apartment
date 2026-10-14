package com.youju.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.UserInfo;
import com.youju.lease.web.app.service.UserInfoService;
import com.youju.lease.web.app.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;


@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

}




