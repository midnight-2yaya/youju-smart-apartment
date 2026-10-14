package com.youju.lease.web.app.service.impl;

import com.youju.lease.common.exception.LeaseException;
import com.youju.lease.common.login.LoginUser;
import com.youju.lease.common.login.LoginUserHolder;
import com.youju.lease.common.result.ResultCodeEnum;
import com.youju.lease.web.app.mapper.TenantLifeMapper;
import com.youju.lease.web.app.service.TenantLifeService;
import com.youju.lease.web.app.vo.life.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class TenantLifeServiceImpl implements TenantLifeService {

    @Autowired
    private TenantLifeMapper tenantLifeMapper;

    private LoginUser currentUser() {
        return LoginUserHolder.getLoginUser();
    }

    @Override
    public MyRoomVo getMyRoom() {
        return tenantLifeMapper.selectMyRoom(currentUser().getUsername());
    }

    @Override
    @Transactional
    public List<BillVo> listBills(Integer billType) {
        ensureCurrentMonthBills();
        return tenantLifeMapper.selectBills(currentUser().getUserId(), billType);
    }

    private void ensureCurrentMonthBills() {
        LoginUser user = currentUser();
        String period = YearMonth.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        if (tenantLifeMapper.countBills(user.getUserId(), period) > 0) {
            return;
        }
        MyRoomVo room = tenantLifeMapper.selectMyRoom(user.getUsername());
        if (room == null) {
            return;
        }
        LocalDate dueDate = YearMonth.now().plusMonths(1).atDay(10);
        tenantLifeMapper.insertBill(user.getUserId(), room.getAgreementId(), room.getRoomId(),
                1, room.getRent(), period, dueDate);
        tenantLifeMapper.insertBill(user.getUserId(), room.getAgreementId(), room.getRoomId(),
                2, new BigDecimal("86.50"), period, dueDate);
        tenantLifeMapper.insertBill(user.getUserId(), room.getAgreementId(), room.getRoomId(),
                3, new BigDecimal("32.00"), period, dueDate);
    }

    @Override
    @Transactional
    public String simulatePay(Long billId) {
        Long userId = currentUser().getUserId();
        BillVo bill = tenantLifeMapper.selectBill(billId, userId);
        if (bill == null) {
            throw new LeaseException(ResultCodeEnum.APP_BILL_NOT_FOUND);
        }
        if (Integer.valueOf(1).equals(bill.getStatus())) {
            throw new LeaseException(ResultCodeEnum.APP_BILL_ALREADY_PAID);
        }
        String tradeNo = "SIM" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        if (tenantLifeMapper.payBill(billId, userId, tradeNo) == 0) {
            throw new LeaseException(ResultCodeEnum.APP_BILL_ALREADY_PAID);
        }
        tenantLifeMapper.insertMessage(userId, "缴费成功",
                bill.getBillTypeName() + " ¥" + bill.getAmount() + " 已通过模拟支付完成，交易号：" + tradeNo,
                1, billId);
        return tradeNo;
    }

    @Override
    @Transactional
    public void submitRepair(RepairSubmitVo submitVo) {
        if (submitVo == null || !StringUtils.hasText(submitVo.getDescription())) {
            throw new LeaseException(ResultCodeEnum.PARAM_ERROR);
        }
        LoginUser user = currentUser();
        MyRoomVo room = tenantLifeMapper.selectMyRoom(user.getUsername());
        if (room == null) {
            throw new LeaseException(ResultCodeEnum.APP_AGREEMENT_NOT_FOUND);
        }
        String contactPhone = StringUtils.hasText(submitVo.getContactPhone())
                ? submitVo.getContactPhone().trim() : user.getUsername();
        tenantLifeMapper.insertRepair(user.getUserId(), room.getAgreementId(), room.getRoomId(),
                submitVo.getDescription().trim(), contactPhone);
        tenantLifeMapper.insertMessage(user.getUserId(), "报修已提交",
                room.getApartmentName() + " " + room.getRoomNumber() + " 的报修申请已提交，物业将尽快处理。",
                2, null);
    }

    @Override
    public List<RepairVo> listRepairs() {
        return tenantLifeMapper.selectRepairs(currentUser().getUserId());
    }

    @Override
    public void publishPost(CommunityPostSubmitVo submitVo) {
        if (submitVo == null || !StringUtils.hasText(submitVo.getTitle()) || !StringUtils.hasText(submitVo.getContent())) {
            throw new LeaseException(ResultCodeEnum.PARAM_ERROR);
        }
        if (submitVo.getTitle().trim().length() > 100) {
            throw new LeaseException(ResultCodeEnum.PARAM_ERROR);
        }
        if (submitVo.getContent().trim().length() > 1000) {
            throw new LeaseException(ResultCodeEnum.PARAM_ERROR);
        }
        tenantLifeMapper.insertPost(currentUser().getUserId(), submitVo.getTitle().trim(), submitVo.getContent().trim());
    }

    @Override
    public List<CommunityPostVo> listPosts() {
        return tenantLifeMapper.selectPostsByPage(currentUser().getUserId(), 0, 100);
    }

    @Override
    public List<CommunityPostVo> listPostsByPage(Integer page, Integer size) {
        if (page == null || page < 1) page = 1;
        if (size == null || size < 1 || size > 50) size = 10;
        int offset = (page - 1) * size;
        return tenantLifeMapper.selectPostsByPage(currentUser().getUserId(), offset, size);
    }

    @Override
    public void deleteOwnPost(Long id) {
        int rows = tenantLifeMapper.deleteOwnPost(id, currentUser().getUserId());
        if (rows == 0) {
            throw new LeaseException(ResultCodeEnum.DATA_ERROR);
        }
    }

    @Override
    @Transactional
    public void likePost(Long id) {
        Long userId = currentUser().getUserId();
        //  先判断是否已点过
        if (tenantLifeMapper.countLike(id, userId) > 0) {
            throw new LeaseException(ResultCodeEnum.APP_LIKE_DUPLICATE);
        }
        // 插点赞记录
        tenantLifeMapper.insertLike(id, userId);
        //  点赞数 +1
        if (tenantLifeMapper.increaseLike(id) == 0) {
            throw new LeaseException(ResultCodeEnum.DATA_ERROR);
        }
    }

    @Override
    @Transactional
    public void unlikePost(Long id) {
        Long userId = currentUser().getUserId();
        // 删除点赞记录，返回影响行数
        int rows = tenantLifeMapper.deleteLike(id, userId);
        if (rows > 0) {
            //  只有确实删掉了才 -1
            tenantLifeMapper.decreaseLike(id);
        }
    }

    @Override
    public List<CommunityCommentVo> listComments(Long postId) {
        return tenantLifeMapper.selectComments(postId);
    }

    @Override
    @Transactional
    public void addComment(Long postId, String content) {
        if (!StringUtils.hasText(content)) {
            throw new LeaseException(ResultCodeEnum.PARAM_ERROR);
        }
        if (content.trim().length() > 500) {
            throw new LeaseException(ResultCodeEnum.PARAM_ERROR);
        }
        Long userId = currentUser().getUserId();
        tenantLifeMapper.insertComment(postId, userId, content.trim());
        tenantLifeMapper.increaseComment(postId);
    }

    @Override
    public List<MessageVo> listMessages() {
        return tenantLifeMapper.selectMessages(currentUser().getUserId());
    }

    @Override
    public Integer unreadCount() {
        return tenantLifeMapper.countUnread(currentUser().getUserId());
    }

    @Override
    public void markRead(Long id) {
        tenantLifeMapper.markRead(id, currentUser().getUserId());
    }

    @Override
    public void markAllRead() {
        tenantLifeMapper.markAllRead(currentUser().getUserId());
    }
}
