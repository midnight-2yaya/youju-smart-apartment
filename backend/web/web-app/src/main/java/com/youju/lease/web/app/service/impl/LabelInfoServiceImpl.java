package com.youju.lease.web.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.LabelInfo;
import com.youju.lease.web.app.service.LabelInfoService;
import com.youju.lease.web.app.mapper.LabelInfoMapper;
import org.springframework.stereotype.Service;


@Service
public class LabelInfoServiceImpl extends ServiceImpl<LabelInfoMapper, LabelInfo>
    implements LabelInfoService{

}




