package com.youju.lease.web.app.mapper;

import com.youju.lease.web.app.vo.life.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface TenantLifeMapper {
    MyRoomVo selectMyRoom(@Param("phone") String phone);

    Integer countBills(@Param("userId") Long userId, @Param("billingPeriod") String billingPeriod);

    int insertBill(@Param("userId") Long userId, @Param("agreementId") Long agreementId,
                   @Param("roomId") Long roomId, @Param("billType") Integer billType,
                   @Param("amount") BigDecimal amount, @Param("billingPeriod") String billingPeriod,
                   @Param("dueDate") LocalDate dueDate);

    List<BillVo> selectBills(@Param("userId") Long userId, @Param("billType") Integer billType);

    BillVo selectBill(@Param("id") Long id, @Param("userId") Long userId);

    int payBill(@Param("id") Long id, @Param("userId") Long userId, @Param("tradeNo") String tradeNo);

    int insertRepair(@Param("userId") Long userId, @Param("agreementId") Long agreementId,
                     @Param("roomId") Long roomId, @Param("description") String description,
                     @Param("contactPhone") String contactPhone);

    List<RepairVo> selectRepairs(@Param("userId") Long userId);

    int insertPost(@Param("userId") Long userId, @Param("title") String title, @Param("content") String content);

    List<CommunityPostVo> selectPosts();

    int increaseLike(@Param("id") Long id);

    int insertMessage(@Param("userId") Long userId, @Param("title") String title,
                      @Param("content") String content, @Param("messageType") Integer messageType,
                      @Param("bizId") Long bizId);

    List<MessageVo> selectMessages(@Param("userId") Long userId);

    Integer countUnread(@Param("userId") Long userId);

    int markRead(@Param("id") Long id, @Param("userId") Long userId);

    int markAllRead(@Param("userId") Long userId);

    // 查询当前用户是否点过赞
    Integer countLike(@Param("postId") Long postId, @Param("userId") Long userId);

    // 插入点赞记录
    int insertLike(@Param("postId") Long postId, @Param("userId") Long userId);

    // 删除点赞记录（取消点赞）
    int deleteLike(@Param("postId") Long postId, @Param("userId") Long userId);

    // 点赞数 -1
    int decreaseLike(@Param("id") Long id);

    // 分页查询动态
    List<CommunityPostVo> selectPostsByPage(@Param("userId") Long userId,
                                            @Param("offset") Integer offset,
                                            @Param("size") Integer size);

    //查询动态总数（用于分页）
    Integer countPosts();

    // 删除自己的动态
    int deleteOwnPost(@Param("id") Long id, @Param("userId") Long userId);

    // 查询某条动态的评论列表
    List<CommunityCommentVo> selectComments(@Param("postId") Long postId);

    // 插入评论
    int insertComment(@Param("postId") Long postId,
                      @Param("userId") Long userId,
                      @Param("content") String content);

    // 评论数 +1
    int increaseComment(@Param("postId") Long postId);

    // 评论数 -1（可选）
    int decreaseComment(@Param("postId") Long postId);
}
