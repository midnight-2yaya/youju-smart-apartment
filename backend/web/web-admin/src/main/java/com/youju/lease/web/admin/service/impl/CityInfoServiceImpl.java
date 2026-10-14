package com.youju.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.CityInfo;
import com.youju.lease.web.admin.service.CityInfoService;
import com.youju.lease.web.admin.mapper.CityInfoMapper;
import org.springframework.stereotype.Service;


@Service
public class CityInfoServiceImpl extends ServiceImpl<CityInfoMapper, CityInfo>
    implements CityInfoService{

}




