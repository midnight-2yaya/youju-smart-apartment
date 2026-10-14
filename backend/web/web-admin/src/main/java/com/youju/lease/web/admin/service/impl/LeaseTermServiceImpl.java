package com.youju.lease.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youju.lease.model.entity.LeaseTerm;
import com.youju.lease.web.admin.service.LeaseTermService;
import com.youju.lease.web.admin.mapper.LeaseTermMapper;
import org.springframework.stereotype.Service;


@Service
public class LeaseTermServiceImpl extends ServiceImpl<LeaseTermMapper, LeaseTerm>
    implements LeaseTermService{

}




