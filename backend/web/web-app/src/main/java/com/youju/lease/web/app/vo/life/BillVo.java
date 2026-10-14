package com.youju.lease.web.app.vo.life;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@Schema(description = "生活账单")
public class BillVo {
    private Long id;
    private Integer billType;
    private String billTypeName;
    private BigDecimal amount;
    private String billingPeriod;
    private LocalDate dueDate;
    private Integer status;
    private Date paidTime;
    private String tradeNo;
}
