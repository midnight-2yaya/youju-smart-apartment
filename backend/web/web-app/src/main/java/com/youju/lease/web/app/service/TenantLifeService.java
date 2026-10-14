package com.youju.lease.web.app.service;
import com.youju.lease.web.app.vo.life.*;

import java.util.List;

public interface TenantLifeService {
    MyRoomVo getMyRoom();
    List<BillVo> listBills(Integer billType);
    String simulatePay(Long billId);
    void submitRepair(RepairSubmitVo submitVo);
    List<RepairVo> listRepairs();
    void publishPost(CommunityPostSubmitVo submitVo);
    List<CommunityPostVo> listPosts();
    void likePost(Long id);
    List<MessageVo> listMessages();
    Integer unreadCount();
    void markRead(Long id);
    void markAllRead();
    // 取消点赞
    void unlikePost(Long id);

    // 分页查询动态
    List<CommunityPostVo> listPostsByPage(Integer page, Integer size);

    // 删除自己的动态
    void deleteOwnPost(Long id);

    List<CommunityCommentVo> listComments(Long postId);
    void addComment(Long postId, String content);
}
