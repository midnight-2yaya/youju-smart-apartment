package com.youju.lease.web.app.service;

import com.youju.lease.model.entity.BrowsingHistory;
import com.youju.lease.web.app.vo.history.HistoryItemVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


public interface BrowsingHistoryService extends IService<BrowsingHistory> {
    IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId);

    void saveHistory(Long userId, Long roomId);
}
