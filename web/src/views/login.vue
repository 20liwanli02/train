<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center"><rocket-two-tone />&nbsp;12306售票系统</h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
          @finish="onFinish"
          @finishFailed="onFinishFailed"
      >
        <a-form-item
            label="手机号："
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="mobile"/>
        </a-form-item>

        <a-form-item
            label="验证码："
            name="code"
            :rules="[{ required: true, message: '请输入验证码!' }]"
        >
          <a-input v-model:value="loginForm.code" placeholder="code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>
          <!--<a-input v-model:value="loginForm.code" placeholder="验证码"/>-->
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>

      </a-form>
    </a-col>
  </a-row>
</template>

<script>
import { defineComponent, reactive } from 'vue';
// import axios from 'axios';
// import { notification } from 'ant-design-vue';
// import { useRouter } from 'vue-router'
// import store from "@/store";

export default defineComponent({
  name: "login-view",
  setup() {
    // const router = useRouter();

    const loginForm = reactive({
      mobile: '',
      code: '',
    });

    const onFinish = values => {
      console.log('Success:',values);
    }

    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };


    // const sendCode = () => {
    //   axios.post("/member/member/send-code", {
    //     mobile: loginForm.mobile
    //   }).then(response => {
    //     let data = response.data;
    //     if (data.success) {
    //       notification.success({ description: '发送验证码成功！' });
    //       loginForm.code = "8888";
    //     } else {
    //       notification.error({ description: data.message });
    //     }
    //   });
    // };
    //
    // const login = () => {
    //   axios.post("/member/member/login", loginForm).then((response) => {
    //     let data = response.data;
    //     if (data.success) {
    //       notification.success({ description: '登录成功！' });
    //       // 登录成功，跳到控台主页
    //       router.push("/welcome");
    //       store.commit("setMember", data.content);
    //     } else {
    //       notification.error({ description: data.message });
    //     }
    //   })
    // };

    return {
      loginForm,
      // sendCode,
      // login
      onFinish,
      onFinishFailed,
    };
  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}
.login-main {
  margin-top: 100px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>
