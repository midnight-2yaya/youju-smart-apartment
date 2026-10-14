package com.youju.lease.web.admin.mapper;

import com.youju.lease.model.entity.LeaseAgreement;
import com.youju.lease.web.admin.vo.agreement.AgreementQueryVo;
import com.youju.lease.web.admin.vo.agreement.AgreementVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    IPage<AgreementVo> pageItem(Page<AgreementVo> page, AgreementQueryVo queryVo);

}




