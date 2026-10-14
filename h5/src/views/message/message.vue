<template>
  <div class="message-page main-container">
    <div class="summary">
      <div>
        <strong>{{ unread }}</strong
        ><span>条未读消息</span>
      </div>
      <van-button size="small" plain type="primary" @click="readAll"
        >全部已读</van-button
      >
    </div>
    <van-empty v-if="!messages.length" description="暂无消息" />
    <div v-else class="message-list">
      <button
        v-for="item in messages"
        :key="item.id"
        class="message-card"
        :class="{ unread: item.isRead === 0 }"
        @click="read(item)"
      >
        <div class="icon" :class="`type-${item.messageType}`">
          <van-icon :name="iconName(item.messageType)" size="24" />
        </div>
        <div class="content">
          <div>
            <strong>{{ item.title }}</strong
            ><small>{{ formatTime(item.createTime) }}</small>
          </div>
          <p>{{ item.content }}</p>
        </div>
      </button>
    </div>
  </div>
</template>

<script setup lang="ts" name="Message">
import { onMounted, onActivated, ref } from "vue";
import dayjs from "dayjs";
import {
  getMessages,
  getUnreadCount,
  markAllMessagesRead,
  markMessageRead
} from "@/api/life";
import type { MessageItem } from "@/api/life/types";

const messages = ref<MessageItem[]>([]);
const unread = ref(0);
const formatTime = (value: string) => dayjs(value).format("MM-DD HH:mm");
const iconName = (type: number) =>
  type === 1 ? "balance-pay" : type === 2 ? "wap-home-o" : "volume-o";
async function load() {
  const [list, count] = await Promise.all([getMessages(), getUnreadCount()]);
  messages.value = list.data;
  unread.value = count.data;
}
async function read(item: MessageItem) {
  if (item.isRead === 1) return;
  await markMessageRead(item.id);
  item.isRead = 1;
  unread.value = Math.max(0, unread.value - 1);
}
async function readAll() {
  await markAllMessagesRead();
  await load();
}
onMounted(load);
onActivated(load);
</script>

<style lang="less" scoped>
.message-page {
  min-height: 100vh;
  background: #f5f6f7;
  padding-top: 14px;
  padding-bottom: 70px;
}
.summary {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: var(--van-primary-color);
  color: white;
  padding: 18px;
  border-radius: 16px;
}
.summary div {
  display: flex;
  align-items: baseline;
  gap: 7px;
}
.summary strong {
  font-size: 28px;
}
.message-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 14px;
}
.message-card {
  width: 100%;
  border: 0;
  text-align: left;
  background: #fff;
  border-radius: 14px;
  padding: 14px;
  display: flex;
  gap: 12px;
  position: relative;
}
.message-card.unread::after {
  content: "";
  position: absolute;
  right: 12px;
  top: 12px;
  width: 7px;
  height: 7px;
  border-radius: 50%;
  background: #ee5555;
}
.icon {
  flex: 0 0 44px;
  height: 44px;
  border-radius: 12px;
  display: grid;
  place-items: center;
  color: #fff;
  background: #5d9b78;
}
.icon.type-1 {
  background: #e7a84c;
}
.icon.type-2 {
  background: #4f9e87;
}
.content {
  flex: 1;
  min-width: 0;
}
.content > div {
  display: flex;
  justify-content: space-between;
  gap: 8px;
}
.content small {
  color: #aaa;
  font-size: 11px;
  white-space: nowrap;
}
.content p {
  color: #777;
  font-size: 13px;
  line-height: 1.5;
  margin: 6px 0 0;
}
</style>
