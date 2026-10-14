package com.youju.lease.web.app.service;

import com.youju.lease.model.entity.LeaseAgreement;
import com.youju.lease.web.app.vo.agreement.AgreementDetailVo;
import com.youju.lease.web.app.vo.agreement.AgreementItemVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface LeaseAgreementService extends IService<LeaseAgreement> {
    List<AgreementItemVo> listItem(String username);

    AgreementDetailVo getDetailById(Long id);
}
