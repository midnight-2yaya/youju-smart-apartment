package com.youju.lease.web.admin.mapper;

import com.youju.lease.model.entity.FeeKey;
import com.youju.lease.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface FeeKeyMapper extends BaseMapper<FeeKey> {

    List<FeeKeyVo> listFeeInfo();

}




