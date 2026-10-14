package com.youju.lease.web.app.vo.life;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommunityCommentVo {
    private Long id;
    private Long postId;
    private Long userId;
    private String nickname;
    private String avatarUrl;
    private String content;
    private LocalDateTime createTime;
}
