<template>
  <a-result
      status="error"
      title="操作失败!"
      sub-title="Please check and modify the following information before resubmitting."
  >
    <template #extra>
      <a-button key="console" type="primary">Go 首页</a-button>
      <a-button key="buy">返回</a-button>
    </template>

    <div class="desc">
      <p style="font-size: 16px">
        <strong>您提交的内容存在以下错误：</strong>
      </p>
      <p>
        <close-circle-outlined :style="{ color: 'red' }" />
        您的帐户已被冻结
        <a>立即解冻&gt;</a>
      </p>
      <p>
        <close-circle-outlined :style="{ color: 'red' }" />
        您的帐户不符合资格
        <a>申请解锁&gt;</a>
      </p>
    </div>
  </a-result>
</template>
<script>
import { CloseCircleOutlined } from '@ant-design/icons-vue';
import { defineComponent } from 'vue';
export default defineComponent({
  components: {
    CloseCircleOutlined,
  },
});
</script>
<style scoped>
.desc p {
  margin-bottom: 1em;
}
</style>