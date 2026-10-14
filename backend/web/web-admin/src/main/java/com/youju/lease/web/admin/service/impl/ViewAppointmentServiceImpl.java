package com.youju.lease.web.admin.service.impl;

import com.youju.lease.model.entity.ViewAppointment;
import com.youju.lease.web.admin.mapper.ViewAppointmentMapper;
import com.youju.lease.web.admin.service.ViewAppointmentService;
import com.youju.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.youju.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ViewAppointmentServiceImpl extends ServiceImpl<ViewAppointmentMapper, ViewAppointment>
        implements ViewAppointmentService {

    @Autowired
    private ViewAppointmentMapper viewAppointmentMapper;

    @Override
    public IPage<AppointmentVo> pageItem(Page<AppointmentVo> page, AppointmentQueryVo queryVo) {
        return viewAppointmentMapper.pageItem(page, queryVo);
    }
}




