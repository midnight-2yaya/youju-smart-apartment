package com.youju.lease.web.app.controller.life;

import com.youju.lease.common.result.Result;
import com.youju.lease.web.app.service.TenantLifeService;
import com.youju.lease.web.app.vo.life.MessageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/message")
@Tag(name = "消息通知")
public class MessageController {

    @Autowired
    private TenantLifeService tenantLifeService;

    @GetMapping("list")
    @Operation(summary = "查询消息列表")
    public Result<List<MessageVo>> list() {
        return Result.ok(tenantLifeService.listMessages());
    }

    @GetMapping("unreadCount")
    @Operation(summary = "查询未读消息数")
    public Result<Integer> unreadCount() {
        return Result.ok(tenantLifeService.unreadCount());
    }

    @PostMapping("read")
    @Operation(summary = "标记单条消息已读")
    public Result read(@RequestParam Long id) {
        tenantLifeService.markRead(id);
        return Result.ok();
    }

    @PostMapping("readAll")
    @Operation(summary = "全部标记已读")
    public Result readAll() {
        tenantLifeService.markAllRead();
        return Result.ok();
    }
}
