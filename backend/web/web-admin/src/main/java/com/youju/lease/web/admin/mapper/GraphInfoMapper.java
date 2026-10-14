package com.youju.lease.web.admin.mapper;

import com.youju.lease.model.entity.GraphInfo;
import com.youju.lease.model.enums.ItemType;
import com.youju.lease.web.admin.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id);
}




