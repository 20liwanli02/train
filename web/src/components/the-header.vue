<template>
  <a-layout-header class="header">
    <div class="logo" />
    <div style="float: right; color: white;">
      您好：{{member.mobile}} &nbsp;&nbsp;
      <router-link to="/login" style="color: white;">
        退出登录
      </router-link>
    </div>

    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/welcome">
        <router-link to="/welcome">
          <coffee-outlined/> &nbsp; 欢迎
        </router-link>
      </a-menu-item>
      <a-menu-item key="/passenger">
        <router-link to="/passenger">
          <coffee-outlined/> &nbsp; 乘车人管理
        </router-link>
      </a-menu-item>
    </a-menu>

  </a-layout-header>

</template>

<script>
import {defineComponent, ref} from 'vue';
import store from "@/store"

export default defineComponent({
  name: "the-header-view",
  setup() {
    let member = store.state.member;

    return {
      selectedKeys1: ref(['2']),
      member,
    }
  },
});
</script>


<style scoped>
</style>
