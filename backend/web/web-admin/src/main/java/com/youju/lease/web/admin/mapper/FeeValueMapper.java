package com.youju.lease.web.admin.mapper;

import com.youju.lease.model.entity.FeeValue;
import com.youju.lease.web.admin.vo.fee.FeeValueVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface FeeValueMapper extends BaseMapper<FeeValue> {

    List<FeeValueVo> selectListByApartmentId(Long id);

}




