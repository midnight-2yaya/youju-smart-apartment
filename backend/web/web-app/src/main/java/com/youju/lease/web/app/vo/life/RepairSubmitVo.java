package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "报修提交信息")
public class RepairSubmitVo {
    private String description;
    private String contactPhone;
}
