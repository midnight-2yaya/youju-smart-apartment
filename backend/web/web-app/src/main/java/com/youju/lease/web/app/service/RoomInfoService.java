package com.youju.lease.web.app.service;

import com.youju.lease.model.entity.RoomInfo;
import com.youju.lease.web.app.vo.room.RoomDetailVo;
import com.youju.lease.web.app.vo.room.RoomItemVo;
import com.youju.lease.web.app.vo.room.RoomQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface RoomInfoService extends IService<RoomInfo> {
    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    IPage<RoomItemVo> pageItemByApartmentId(Page<RoomItemVo> page, Long id);

    RoomDetailVo getDetailById(Long id);

}
