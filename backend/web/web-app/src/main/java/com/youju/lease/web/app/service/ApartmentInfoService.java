package com.youju.lease.web.app.service;

import com.youju.lease.model.entity.ApartmentInfo;
import com.youju.lease.web.app.vo.apartment.ApartmentDetailVo;
import com.youju.lease.web.app.vo.apartment.ApartmentItemVo;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ApartmentInfoService extends IService<ApartmentInfo> {
    ApartmentItemVo selectApartmentItemVoById(Long id);

    ApartmentDetailVo getApartmentDetailById(Long id);

}
