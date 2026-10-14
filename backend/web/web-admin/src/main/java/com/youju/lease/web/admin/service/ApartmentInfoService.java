package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.ApartmentInfo;
import com.youju.lease.web.admin.vo.apartment.ApartmentDetailVo;
import com.youju.lease.web.admin.vo.apartment.ApartmentItemVo;
import com.youju.lease.web.admin.vo.apartment.ApartmentQueryVo;
import com.youju.lease.web.admin.vo.apartment.ApartmentSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface ApartmentInfoService extends IService<ApartmentInfo> {

    void saveOrUpdateApartment(ApartmentSubmitVo apartmentSubmitVo);

    IPage<ApartmentItemVo> pageItem(Page<ApartmentItemVo> page, ApartmentQueryVo queryVo);

    ApartmentDetailVo getDetailById(Long id);

    void removeApartmentById(Long id);
}
