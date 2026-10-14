<template>
  <div class="page main-container">
    <van-tabs v-model:active="active" @change="loadBills">
      <van-tab title="房租" :name="1" />
      <van-tab title="电费" :name="2" />
      <van-tab title="水费" :name="3" />
    </van-tabs>
    <van-loading v-if="loading" class="center" />
    <van-empty v-else-if="!bills.length" description="暂无账单" />
    <div v-else class="bill-list">
      <div v-for="bill in bills" :key="bill.id" class="bill-card">
        <div>
          <strong>{{ bill.billTypeName }} · {{ bill.billingPeriod }}</strong>
          <p>应付 ¥{{ bill.amount }} · 截止 {{ bill.dueDate }}</p>
          <small v-if="bill.tradeNo">交易号 {{ bill.tradeNo }}</small>
        </div>
        <div class="bill-action">
          <span :class="{ paid: bill.status === 1 }">{{
            bill.status === 1 ? "已支付" : "待支付"
          }}</span>
          <van-button
            v-if="bill.status === 0"
            size="small"
            type="primary"
            @click="pay(bill.id)"
            >立即支付</van-button
          >
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="Bill">
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";
import { showSuccessToast } from "vant";
import { getBills, simulatePay } from "@/api/life";
import type { BillItem } from "@/api/life/types";

const route = useRoute();
const active = ref(Number(route.query.type || 1));
const bills = ref<BillItem[]>([]);
const loading = ref(false);
async function loadBills() {
  loading.value = true;
  try {
    bills.value = (await getBills(active.value)).data;
  } finally {
    loading.value = false;
  }
}
async function pay(id: number) {
  const { data } = await simulatePay(id);
  showSuccessToast(`支付成功：${data}`);
  await loadBills();
}
onMounted(loadBills);
</script>

<style lang="less" scoped>
.page {
  min-height: 100vh;
  background: #f6f7f8;
  padding-top: 8px;
}
.center {
  display: block;
  width: 30px;
  margin: 70px auto;
}
.bill-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-top: 16px;
}
.bill-card {
  display: flex;
  justify-content: space-between;
  gap: 10px;
  background: #fff;
  border-radius: 14px;
  padding: 16px;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.05);
}
.bill-card p,
.bill-card small {
  margin: 6px 0 0;
  color: #888;
  font-size: 12px;
}
.bill-action {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  justify-content: space-between;
  color: #e28a24;
  white-space: nowrap;
}
.bill-action .paid {
  color: var(--van-primary-color);
}
</style>
