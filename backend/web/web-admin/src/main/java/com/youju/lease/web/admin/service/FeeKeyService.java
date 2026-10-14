package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.FeeKey;
import com.youju.lease.web.admin.vo.fee.FeeKeyVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface FeeKeyService extends IService<FeeKey> {

    List<FeeKeyVo> listFeeInfo();

}
