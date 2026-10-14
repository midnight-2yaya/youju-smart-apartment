package com.youju.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.ApartmentFacility;
import com.youju.lease.web.app.service.ApartmentFacilityService;
import com.youju.lease.web.app.mapper.ApartmentFacilityMapper;
import org.springframework.stereotype.Service;


@Service
public class ApartmentFacilityServiceImpl extends ServiceImpl<ApartmentFacilityMapper, ApartmentFacility>
    implements ApartmentFacilityService{
}




