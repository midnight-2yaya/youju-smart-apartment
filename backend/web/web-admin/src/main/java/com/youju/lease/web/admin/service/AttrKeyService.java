package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.AttrKey;
import com.youju.lease.web.admin.vo.attr.AttrKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface AttrKeyService extends IService<AttrKey> {

    List<AttrKeyVo> listAttrInfo();

}
