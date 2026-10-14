package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "用户消息")
public class MessageVo {
    private Long id;
    private String title;
    private String content;
    private Integer messageType;
    private Integer isRead;
    private Long bizId;
    private Date createTime;
}
