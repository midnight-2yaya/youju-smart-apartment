package com.youju.lease.web.app.mapper;

import com.youju.lease.model.entity.ViewAppointment;
import com.youju.lease.web.app.vo.appointment.AppointmentItemVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    List<AppointmentItemVo> listItem(Long userId);
}




