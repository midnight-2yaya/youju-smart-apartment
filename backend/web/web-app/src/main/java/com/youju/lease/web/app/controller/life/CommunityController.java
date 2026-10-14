package com.youju.lease.web.app.controller.life;

import com.youju.lease.common.result.Result;
import com.youju.lease.web.app.service.TenantLifeService;
import com.youju.lease.web.app.vo.life.CommunityCommentVo;
import com.youju.lease.web.app.vo.life.CommunityPostSubmitVo;
import com.youju.lease.web.app.vo.life.CommunityPostVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app/community")
@Tag(name = "社区圈子")
public class CommunityController {

    @Autowired
    private TenantLifeService tenantLifeService;

    @GetMapping("list")
    @Operation(summary = "查询圈子动态")
    public Result<List<CommunityPostVo>> list() {
        return Result.ok(tenantLifeService.listPosts());
    }

    @GetMapping("page")
    @Operation(summary = "分页查询圈子动态")
    public Result<List<CommunityPostVo>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return Result.ok(tenantLifeService.listPostsByPage(page, size));
    }

    @PostMapping("publish")
    @Operation(summary = "发布圈子动态")
    public Result publish(@RequestBody CommunityPostSubmitVo submitVo) {
        tenantLifeService.publishPost(submitVo);
        return Result.ok();
    }

    @PostMapping("like")
    @Operation(summary = "点赞圈子动态")
    public Result like(@RequestParam Long id) {
        tenantLifeService.likePost(id);
        return Result.ok();
    }

    @PostMapping("unlike")
    @Operation(summary = "取消点赞")
    public Result unlike(@RequestParam Long id) {
        tenantLifeService.unlikePost(id);
        return Result.ok();
    }

    @GetMapping("comment/list")
    @Operation(summary = "查询评论列表")
    public Result<List<CommunityCommentVo>> comments(@RequestParam Long postId) {
        return Result.ok(tenantLifeService.listComments(postId));
    }

    @PostMapping("comment/add")
    @Operation(summary = "发表评论")
    public Result comment(@RequestParam Long postId, @RequestParam String content) {
        tenantLifeService.addComment(postId, content);
        return Result.ok();
    }

    @PostMapping("delete")
    @Operation(summary = "删除自己的动态")
    public Result delete(@RequestParam Long id) {
        tenantLifeService.deleteOwnPost(id);
        return Result.ok();
    }
}
