<template>
  <div class="page main-container">
    <van-cell-group inset title="提交报修">
      <van-field
        v-model.trim="form.contactPhone"
        label="联系电话"
        placeholder="默认使用登录手机号"
      />
      <van-field
        v-model.trim="form.description"
        label="故障描述"
        type="textarea"
        rows="3"
        maxlength="500"
        show-word-limit
        placeholder="请描述故障位置和现象"
      />
    </van-cell-group>
    <van-button
      class="submit"
      block
      round
      type="primary"
      :loading="submitting"
      @click="submit"
      >提交报修</van-button
    >
    <h3>报修记录</h3>
    <van-empty v-if="!records.length" description="暂无报修记录" />
    <van-cell-group v-else inset>
      <van-cell
        v-for="item in records"
        :key="item.id"
        :title="`${item.apartmentName} ${item.roomNumber}`"
        :label="item.description"
      >
        <template #value
          ><van-tag type="primary">{{ item.statusName }}</van-tag></template
        >
      </van-cell>
    </van-cell-group>
  </div>
</template>

<script setup lang="ts" name="Repair">
import { onMounted, reactive, ref } from "vue";
import { showFailToast, showSuccessToast } from "vant";
import { getRepairs, submitRepair } from "@/api/life";
import type { RepairItem } from "@/api/life/types";

const form = reactive({ contactPhone: "", description: "" });
const records = ref<RepairItem[]>([]);
const submitting = ref(false);
async function load() {
  records.value = (await getRepairs()).data;
}
async function submit() {
  if (!form.description) {
    showFailToast("请填写故障描述");
    return;
  }
  submitting.value = true;
  try {
    await submitRepair(form);
    showSuccessToast("报修已提交");
    form.description = "";
    await load();
  } finally {
    submitting.value = false;
  }
}
onMounted(load);
</script>

<style lang="less" scoped>
.page {
  min-height: 100vh;
  background: #f6f7f8;
  padding-top: 10px;
}
.submit {
  width: calc(100% - 32px);
  margin: 16px;
}
h3 {
  margin: 22px 16px 10px;
}
</style>
