package com.youju.lease.web.app.service;

import com.youju.lease.model.entity.ViewAppointment;
import com.youju.lease.web.app.vo.appointment.AppointmentDetailVo;
import com.youju.lease.web.app.vo.appointment.AppointmentItemVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ViewAppointmentService extends IService<ViewAppointment> {
    List<AppointmentItemVo> listItem(Long userId);

    AppointmentDetailVo getDetailById(Long id);
}
