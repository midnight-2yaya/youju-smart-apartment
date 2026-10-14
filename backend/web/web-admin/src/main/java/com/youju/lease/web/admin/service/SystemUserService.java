package com.youju.lease.web.admin.service;

import com.youju.lease.model.entity.SystemUser;
import com.youju.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.youju.lease.web.admin.vo.system.user.SystemUserQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface SystemUserService extends IService<SystemUser> {

    IPage<SystemUserItemVo> pageItem(Page<SystemUserItemVo> page, SystemUserQueryVo queryVo);

    SystemUserItemVo getDetailById(Long id);

}
