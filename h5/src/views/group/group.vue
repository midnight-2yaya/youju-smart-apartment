<template>
  <div class="group-page">
    <van-image class="banner" :src="bgImgUrl" fit="cover" />

    <!-- 发布动态 -->
    <div class="publish main-container">
      <van-field
        v-model.trim="form.title"
        placeholder="动态标题"
        maxlength="100"
      />
      <van-field
        v-model.trim="form.content"
        type="textarea"
        rows="2"
        placeholder="分享邻里生活、周边好店或居住小技巧"
        maxlength="1000"
        show-word-limit
      />
      <van-button
        size="small"
        type="primary"
        :loading="publishing"
        @click="publish"
      >
        发布
      </van-button>
    </div>

    <!-- 动态列表 -->
    <div class="main-container post-list">
      <van-empty
        v-if="!posts.length"
        description="还没有动态，来发布第一条吧"
      />

      <article v-for="post in posts" :key="post.id" class="post-card">
        <header>
          <van-image
            round
            width="42"
            height="42"
            :src="post.avatarUrl || defaultAvatarUrl"
          />
          <div>
            <strong>{{ post.nickname || "悠居用户" }}</strong>
            <small>{{ formatTime(post.createTime) }}</small>
          </div>
        </header>

        <h3>{{ post.title }}</h3>
        <p>{{ post.content }}</p>

        <footer>
          <!-- 点赞按钮 -->
          <button :class="{ liked: post.likedByMe }" @click="like(post)">
            <van-icon :name="post.likedByMe ? 'good-job' : 'good-job-o'" />
            {{ post.likeCount }}
          </button>

          <!-- 评论按钮 -->
          <button @click="openComment(post)">
            <van-icon name="comment-o" />
            {{ post.commentCount }}
          </button>

          <!-- 删除自己的动态 -->
          <button
            v-if="post.userId === currentUserId"
            class="delete-btn"
            @click="removePost(post)"
          >
            <van-icon name="delete-o" /> 删除
          </button>
        </footer>
      </article>
    </div>

    <!-- ★ 评论弹窗 -->
    <van-popup
      v-model:show="showComment"
      position="bottom"
      round
      :style="{ height: '65vh' }"
    >
      <div class="comment-panel">
        <h3>评论（{{ currentPost?.commentCount || 0 }}）</h3>

        <div class="comment-list">
          <van-empty v-if="!comments.length" description="还没有评论" />
          <div v-for="c in comments" :key="c.id" class="comment-item">
            <van-image
              round
              width="32"
              height="32"
              :src="c.avatarUrl || defaultAvatarUrl"
            />
            <div class="comment-body">
              <div class="comment-head">
                <strong>{{ c.nickname || "悠居用户" }}</strong>
                <small>{{ formatTime(c.createTime) }}</small>
              </div>
              <p>{{ c.content }}</p>
            </div>
          </div>
        </div>

        <div class="comment-input">
          <van-field
            v-model.trim="commentContent"
            placeholder="说点什么..."
            maxlength="500"
          />
          <van-button size="small" type="primary" @click="submitComment">
            发送
          </van-button>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script setup lang="ts" name="Group">
import { onMounted, onActivated, reactive, ref, computed } from "vue";
import dayjs from "dayjs";
import { showFailToast, showSuccessToast, showConfirmDialog } from "vant";
import bgImgUrl from "@/assets/group_bg.png";
import defaultAvatarUrl from "../../../public/favicon.ico";
import {
  getCommunityPosts,
  likeCommunityPost,
  unlikeCommunityPost,
  deleteCommunityPost,
  publishCommunityPost,
  getPostComments,
  addPostComment
} from "@/api/life";
import type { CommunityPostItem, CommunityCommentVo } from "@/api/life/types";
import { useUserStore } from "@/store/modules/user";

const posts = ref<CommunityPostItem[]>([]);
const publishing = ref(false);
const form = reactive({ title: "", content: "" });

const userStore = useUserStore();
const currentUserId = computed(() => userStore.userInfo?.id);

// 评论相关状态
const showComment = ref(false);
const currentPost = ref<CommunityPostItem | null>(null);
const comments = ref<CommunityCommentVo[]>([]);
const commentContent = ref("");

const formatTime = (value: string) => dayjs(value).format("YYYY-MM-DD HH:mm");

async function load() {
  posts.value = (await getCommunityPosts()).data;
}

async function publish() {
  if (!form.title || !form.content) {
    showFailToast("请填写标题和内容");
    return;
  }
  publishing.value = true;
  try {
    await publishCommunityPost(form);
    form.title = "";
    form.content = "";
    showSuccessToast("发布成功");
    await load();
  } finally {
    publishing.value = false;
  }
}

async function like(post: CommunityPostItem) {
  try {
    if (post.likedByMe) {
      await unlikeCommunityPost(post.id);
      post.likedByMe = false;
      post.likeCount = Math.max(0, post.likeCount - 1);
    } else {
      await likeCommunityPost(post.id);
      post.likedByMe = true;
      post.likeCount++;
    }
  } catch (e: any) {
    showFailToast(e?.message || "操作失败");
  }
}

async function openComment(post: CommunityPostItem) {
  currentPost.value = post;
  commentContent.value = "";
  const res = await getPostComments(post.id);
  comments.value = res.data || [];
  showComment.value = true;
}

async function submitComment() {
  if (!commentContent.value) {
    showFailToast("请输入评论内容");
    return;
  }
  if (!currentPost.value) return;
  try {
    await addPostComment(currentPost.value.id, commentContent.value);
    commentContent.value = "";
    // 刷新评论列表
    const res = await getPostComments(currentPost.value.id);
    comments.value = res.data || [];
    // 更新卡片上的评论数
    currentPost.value.commentCount = (currentPost.value.commentCount || 0) + 1;
    showSuccessToast("评论成功");
  } catch (e: any) {
    showFailToast(e?.message || "评论失败");
  }
}

async function removePost(post: CommunityPostItem) {
  try {
    await showConfirmDialog({ title: "提示", message: "确定删除这条动态吗？" });
  } catch {
    return;
  }
  try {
    await deleteCommunityPost(post.id);
    posts.value = posts.value.filter(p => p.id !== post.id);
    showSuccessToast("删除成功");
  } catch (e: any) {
    showFailToast(e?.message || "删除失败");
  }
}

onMounted(load);
onActivated(load);
</script>

<style lang="less" scoped>
.group-page {
  min-height: 100vh;
  background: #f5f6f7;
  padding-bottom: 70px;
}
.banner {
  width: 100%;
  height: 145px;
}
.publish {
  background: #fff;
  margin-top: -18px;
  position: relative;
  border-radius: 16px;
  padding: 8px 10px 12px;
  box-shadow: 0 5px 18px rgba(25, 76, 48, 0.08);
  text-align: right;
}
.post-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 15px;
}
.post-card {
  background: #fff;
  border-radius: 15px;
  padding: 14px;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.04);
}
.post-card header {
  display: flex;
  gap: 10px;
  align-items: center;
}
.post-card header div {
  display: flex;
  flex-direction: column;
}
.post-card small {
  color: #aaa;
  font-size: 11px;
  margin-top: 3px;
}
.post-card h3 {
  font-size: 16px;
  margin: 13px 0 7px;
}
.post-card p {
  color: #555;
  line-height: 1.65;
  white-space: pre-wrap;
}
.post-card footer {
  display: flex;
  gap: 22px;
  color: #777;
  margin-top: 12px;
  align-items: center;
}
.post-card footer button {
  border: 0;
  background: transparent;
  color: inherit;
  padding: 0;
  display: inline-flex;
  align-items: center;
  gap: 4px;
}
/* 已点赞高亮 */
.post-card footer button.liked {
  color: var(--van-primary-color);
  font-weight: 600;
}
/* 删除按钮靠右 */
.post-card footer .delete-btn {
  margin-left: auto;
  color: #ee5555;
}

/* 评论弹窗 */
.comment-panel {
  padding: 16px;
  display: flex;
  flex-direction: column;
  height: 100%;
}
.comment-panel h3 {
  margin: 0 0 12px;
  font-size: 16px;
}
.comment-list {
  flex: 1;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.comment-item {
  display: flex;
  gap: 10px;
}
.comment-body {
  flex: 1;
  min-width: 0;
}
.comment-head {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
}
.comment-head small {
  color: #aaa;
  font-size: 11px;
}
.comment-body p {
  margin: 4px 0 0;
  color: #555;
  font-size: 14px;
  line-height: 1.5;
  white-space: pre-wrap;
  word-break: break-all;
}
.comment-input {
  display: flex;
  gap: 8px;
  align-items: center;
  padding-top: 10px;
  border-top: 1px solid #eee;
}
.comment-input .van-field {
  flex: 1;
  background: #f5f6f7;
  border-radius: 18px;
}
</style>
