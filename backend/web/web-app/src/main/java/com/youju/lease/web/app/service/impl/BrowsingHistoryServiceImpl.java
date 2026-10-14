package com.youju.lease.web.app.service.impl;

import com.youju.lease.model.entity.BrowsingHistory;
import com.youju.lease.web.app.mapper.BrowsingHistoryMapper;
import com.youju.lease.web.app.service.BrowsingHistoryService;
import com.youju.lease.web.app.vo.history.HistoryItemVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class BrowsingHistoryServiceImpl extends ServiceImpl<BrowsingHistoryMapper, BrowsingHistory>
        implements BrowsingHistoryService {

    @Autowired
    private BrowsingHistoryMapper browsingHistoryMapper;

    @Override
    public IPage<HistoryItemVo> pageItemByUserId(Page<HistoryItemVo> page, Long userId) {
        return browsingHistoryMapper.pageItemByUserId(page, userId);
    }

    @Override
    @Async
    public void saveHistory(Long userId, Long roomId) {

        System.out.println("保存浏览历史-" + Thread.currentThread().getName());

        LambdaQueryWrapper<BrowsingHistory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BrowsingHistory::getUserId, userId);
        queryWrapper.eq(BrowsingHistory::getRoomId, roomId);
        BrowsingHistory browsingHistory = browsingHistoryMapper.selectOne(queryWrapper);

        if (browsingHistory == null) {
            BrowsingHistory newHistory = new BrowsingHistory();
            newHistory.setUserId(userId);
            newHistory.setRoomId(roomId);
            newHistory.setBrowseTime(new Date());
            browsingHistoryMapper.insert(newHistory);
        } else {
            browsingHistory.setBrowseTime(new Date());
            browsingHistoryMapper.updateById(browsingHistory);
        }
    }
}