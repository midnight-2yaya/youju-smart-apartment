package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.RoomInfo;
import com.youju.lease.web.admin.vo.room.RoomDetailVo;
import com.youju.lease.web.admin.vo.room.RoomItemVo;
import com.youju.lease.web.admin.vo.room.RoomQueryVo;
import com.youju.lease.web.admin.vo.room.RoomSubmitVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface RoomInfoService extends IService<RoomInfo> {

    IPage<RoomItemVo> pageItem(Page<RoomItemVo> page, RoomQueryVo queryVo);

    void saveOrUpdateRoom(RoomSubmitVo roomSubmitVo);

    RoomDetailVo getRoomDetailById(Long id);

    void removeRoomById(Long id);
}
