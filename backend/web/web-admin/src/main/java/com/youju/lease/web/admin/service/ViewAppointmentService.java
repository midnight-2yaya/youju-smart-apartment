package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.ViewAppointment;
import com.youju.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.youju.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ViewAppointmentService extends IService<ViewAppointment> {

    IPage<AppointmentVo> pageItem(Page<AppointmentVo> page, AppointmentQueryVo queryVo);
}
