package com.youju.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.ApartmentFeeValue;
import com.youju.lease.web.app.service.ApartmentFeeValueService;
import com.youju.lease.web.app.mapper.ApartmentFeeValueMapper;
import org.springframework.stereotype.Service;


@Service
public class ApartmentFeeValueServiceImpl extends ServiceImpl<ApartmentFeeValueMapper, ApartmentFeeValue>
    implements ApartmentFeeValueService{
}




