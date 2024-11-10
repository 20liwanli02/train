<template>
  <div class="header-content">
  <p style="font-size: 26px; font-weight: bold; text-align: center">欢迎使用12306售票系统</p>
  </div>
  <div class="body-content" style="width: 800px;">
  <a-carousel autoplay>
    <div><a-image src="https://img95.699pic.com/photo/50049/8440.jpg_wh860.jpg" /></div>
    <div><a-image src="https://ts1.cn.mm.bing.net/th/id/R-C.658709bed3fe1330b07ce36e1c935763?rik=WVOgyG7Ji3nYaw&riu=http%3a%2f%2fimg95.699pic.com%2fphoto%2f50056%2f1638.jpg_wh860.jpg&ehk=0aj5TVZSHOyfRqss4XfBbs%2fAD%2bISg5Fo8XxsgDvKTzk%3d&risl=&pid=ImgRaw&r=0" /></div>
    <div><a-image src="https://ts1.cn.mm.bing.net/th/id/R-C.d4de4e4632be5af00da46ca43dff7223?rik=u7Wxj%2bkC0dm1Yw&riu=http%3a%2f%2fimg95.699pic.com%2fphoto%2f50097%2f9303.jpg_wh860.jpg&ehk=LfyBUu1Lk5H9nCNhtY6uoCJAaa0coG%2ftg3EZRn8Vawo%3d&risl=&pid=ImgRaw&r=0" /></div>
    <div><a-image src="https://ts1.cn.mm.bing.net/th/id/R-C.9cc5d00364b5452838d9bc8e6d181b20?rik=2aOr4Ockc3%2baRw&riu=http%3a%2f%2fimg95.699pic.com%2fphoto%2f50063%2f8820.jpg_wh860.jpg&ehk=CwqJsUgIwkgYYYTiRyghqxhp1pvIO2pXxQN3owT0BfU%3d&risl=&pid=ImgRaw&r=0" /></div>
  </a-carousel>

<!--    <div :style="{ width: '300px', border: '1px solid #d9d9d9', borderRadius: '4px' }">-->
<!--    <a-calendar v-model:value="value" :fullscreen="false" @panelChange="onPanelChange" />-->
<!--    </div>-->
  </div>
</template>

<script>

import { defineComponent, ref } from 'vue';

export default defineComponent({
  name: "welcome-view",
  setup() {
    const value = ref();
    const onPanelChange = (value, mode) => {
      console.log(value, mode);
    };



    return {
      value,
      onPanelChange,
    };
  },
});
</script>

<style>
/* For demo */
.ant-carousel :deep(.slick-slide) {
  text-align: center;
  height: 160px;
  line-height: 160px;
  background: #364d79;
  overflow: hidden;
}

.ant-carousel :deep(.slick-slide h3) {
  color: #fff;
}

.body-content {
  float: left;
}

</style>
