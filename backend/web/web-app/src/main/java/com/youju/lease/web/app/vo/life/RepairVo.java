package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "报修记录")
public class RepairVo {
    private Long id;
    private String apartmentName;
    private String roomNumber;
    private String description;
    private String contactPhone;
    private Integer status;
    private String statusName;
    private Date createTime;
    private Date updateTime;
}
