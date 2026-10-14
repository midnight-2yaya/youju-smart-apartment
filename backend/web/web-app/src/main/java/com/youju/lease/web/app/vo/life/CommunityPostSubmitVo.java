package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "圈子动态发布信息")
public class CommunityPostSubmitVo {
    private String title;
    private String content;
}
