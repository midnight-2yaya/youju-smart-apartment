package com.youju.lease.web.app.mapper;

import com.youju.lease.model.entity.GraphInfo;
import com.youju.lease.model.enums.ItemType;
import com.youju.lease.web.app.vo.graph.GraphVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id);
}




