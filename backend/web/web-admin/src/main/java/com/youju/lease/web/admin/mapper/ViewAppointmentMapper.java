package com.youju.lease.web.admin.mapper;

import com.youju.lease.model.entity.ViewAppointment;
import com.youju.lease.web.admin.vo.appointment.AppointmentQueryVo;
import com.youju.lease.web.admin.vo.appointment.AppointmentVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


public interface ViewAppointmentMapper extends BaseMapper<ViewAppointment> {

    IPage<AppointmentVo> pageItem(Page<AppointmentVo> page, AppointmentQueryVo queryVo);

}




