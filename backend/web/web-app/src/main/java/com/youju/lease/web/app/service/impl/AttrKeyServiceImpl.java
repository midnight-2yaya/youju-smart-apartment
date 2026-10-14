package com.youju.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.AttrKey;
import com.youju.lease.web.app.service.AttrKeyService;
import com.youju.lease.web.app.mapper.AttrKeyMapper;
import org.springframework.stereotype.Service;


@Service
public class AttrKeyServiceImpl extends ServiceImpl<AttrKeyMapper, AttrKey>
    implements AttrKeyService{

}




