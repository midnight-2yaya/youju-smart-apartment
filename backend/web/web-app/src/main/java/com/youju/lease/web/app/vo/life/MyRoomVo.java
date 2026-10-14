package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Schema(description = "我的房间信息")
public class MyRoomVo {
    private Long agreementId;
    private Long apartmentId;
    private Long roomId;
    private String apartmentName;
    private String roomNumber;
    private String imageUrl;
    private String address;
    private BigDecimal rent;
    private LocalDate leaseStartDate;
    private LocalDate leaseEndDate;
}
