<template>
  <div class="room-page">
    <van-image class="hero" :src="bgImgUrl" fit="cover" />

    <div class="main-container quick-actions">
      <button
        v-for="item in navList"
        :key="item.path"
        @click="router.push(item.path)"
      >
        <SvgIcon :name="item.icon" size="42" />
        <span>{{ item.name }}</span>
      </button>
    </div>

    <div class="main-container mt-[18px]">
      <div v-if="room" class="room-card">
        <van-image
          width="105"
          height="82"
          radius="10"
          fit="cover"
          :src="room.imageUrl"
        />
        <div class="room-summary">
          <strong>{{ room.apartmentName }} {{ room.roomNumber }}房间</strong>
          <span>{{ room.leaseStartDate }} 至 {{ room.leaseEndDate }}</span>
          <span class="rent">¥ {{ room.rent }}/月</span>
          <small>{{ room.address }}</small>
        </div>
      </div>
      <van-empty v-else-if="!loading" description="暂无生效中的租约房间">
        <van-button
          size="small"
          type="primary"
          @click="router.push('/myAgreement')"
          >查看我的租约</van-button
        >
      </van-empty>
      <van-loading v-else class="loading" />
    </div>
  </div>
</template>

<script setup lang="ts" name="MyRoom">
import { onMounted, onActivated, ref } from "vue";
import bgImgUrl from "@/assets/my_room_bg.png";
import { useRouter } from "vue-router";
import { getMyRoom } from "@/api/life";
import type { MyRoomInfo } from "@/api/life/types";

const router = useRouter();
const room = ref<MyRoomInfo | null>(null);
const loading = ref(true);
const navList = [
  { icon: "物业费用出账", name: "交房租", path: "/bill?type=1" },
  { icon: "电费", name: "交电费", path: "/bill?type=2" },
  { icon: "水费", name: "交水费", path: "/bill?type=3" },
  { icon: "物业报修", name: "报修", path: "/repair" }
];

async function loadRoom() {
  try {
    const { data } = await getMyRoom();
    room.value = data;
  } finally {
    loading.value = false;
  }
}

onMounted(loadRoom);
onActivated(loadRoom);
</script>

<style lang="less" scoped>
.room-page {
  min-height: 100vh;
  background: #f6f7f8;
}
.hero {
  width: 100%;
  height: 210px;
}
.quick-actions {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 8px;
  margin-top: -22px;
  position: relative;
  z-index: 1;
  button {
    border: 0;
    background: #fff;
    border-radius: 14px;
    padding: 14px 4px 10px;
    box-shadow: 0 5px 18px rgba(20, 70, 45, 0.08);
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 4px;
  }
}
.room-card {
  background: #fff;
  border-radius: 16px;
  padding: 14px;
  display: flex;
  gap: 12px;
  box-shadow: 0 4px 16px rgba(28, 67, 50, 0.07);
}
.room-summary {
  display: flex;
  flex: 1;
  min-width: 0;
  flex-direction: column;
  gap: 5px;
  color: #777;
  font-size: 12px;
}
.room-summary strong {
  color: #222;
  font-size: 16px;
}
.room-summary .rent {
  color: var(--van-primary-color);
  font-size: 15px;
  font-weight: 700;
}
.room-summary small {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.loading {
  display: block;
  margin: 60px auto;
  width: 30px;
}
</style>
