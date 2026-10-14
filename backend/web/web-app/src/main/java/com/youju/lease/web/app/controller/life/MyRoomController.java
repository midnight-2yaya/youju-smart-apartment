package com.youju.lease.web.app.controller.life;

import com.youju.lease.common.result.Result;
import com.youju.lease.web.app.service.TenantLifeService;
import com.youju.lease.web.app.vo.life.BillVo;
import com.youju.lease.web.app.vo.life.MyRoomVo;
import com.youju.lease.web.app.vo.life.RepairSubmitVo;
import com.youju.lease.web.app.vo.life.RepairVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/myRoom")
@Tag(name = "我的房间")
public class MyRoomController {

    @Autowired
    private TenantLifeService tenantLifeService;

    @GetMapping("info")
    @Operation(summary = "获取当前租住的房间")
    public Result<MyRoomVo> info() {
        return Result.ok(tenantLifeService.getMyRoom());
    }

    @GetMapping("bill/list")
    @Operation(summary = "查询房租、水费和电费账单")
    public Result<List<BillVo>> billList(@RequestParam(required = false) Integer billType) {
        return Result.ok(tenantLifeService.listBills(billType));
    }

    @PostMapping("bill/simulatePay")
    @Operation(summary = "模拟支付账单")
    public Result<String> simulatePay(@RequestParam Long id) {
        return Result.ok(tenantLifeService.simulatePay(id));
    }

    @GetMapping("repair/list")
    @Operation(summary = "查询我的报修记录")
    public Result<List<RepairVo>> repairList() {
        return Result.ok(tenantLifeService.listRepairs());
    }

    @PostMapping("repair/submit")
    @Operation(summary = "提交报修")
    public Result repairSubmit(@RequestBody RepairSubmitVo submitVo) {
        tenantLifeService.submitRepair(submitVo);
        return Result.ok();
    }
}
