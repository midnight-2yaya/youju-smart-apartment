package com.youju.lease.web.admin.mapper;

import com.youju.lease.model.entity.AttrKey;
import com.youju.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface AttrKeyMapper extends BaseMapper<AttrKey> {

    List<AttrKeyVo> listAttrInfo();

}




