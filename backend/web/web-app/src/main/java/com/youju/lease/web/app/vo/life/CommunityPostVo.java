package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "圈子动态")
public class CommunityPostVo {
    private Long id;
    private Long userId;
    private String nickname;
    private String avatarUrl;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer commentCount;
    private Date createTime;
    @Schema(description = "当前用户是否点赞过")
    private Boolean likedByMe;
}
