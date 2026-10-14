package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.LeaseAgreement;
import com.youju.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.youju.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface LeaseAgreementService extends IService<LeaseAgreement> {

    IPage<AgreementVo> pageItem(Page<AgreementVo> page, AgreementQueryVo queryVo);

    AgreementVo getAgreementById(Long id);
}
