<template>
<!--  <div class="header">-->
<!--    <div class="wrapper">-->
<!--      <div class="header-con">-->
<!--        <h1 class="logo">-->
<!--          <a href="http://10.157.75.165:9000/login">铁路12306</a>-->
<!--        </h1>-->
<!--        <span class="header-welcome">-->
<!--        欢迎登录12306-->
<!--        </span>-->
<!--      </div>-->
<!--    </div>-->
<!--    </div>-->
<!--  <ul class="header-welcome">-->
<!--    <li class="one"><a href="http://10.157.75.165:9000/login" style="color: black">铁路12306</a></li>-->
<!--    <li class="we">欢迎登录12306</li>-->
<!--  </ul>-->



  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center"><rocket-two-tone />&nbsp;12306售票系统</h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
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
        </a-form-item>

        <a-form-item :wrapper-col="{ span: 14, offset: 7}">
<!--          <a-button type="primary" block @click="login">登录</a-button>-->
          <a-button type="primary" @click="login">登录</a-button>
<!--          <a-button type="primary" @click="login" style="margin-left: 180px">登录</a-button>-->
          <a-button style="margin-left: 43px" @click="register">注册</a-button>
<!--          <span style="margin-left: 20px">Forgot your</span>-->
        </a-form-item>

      </a-form>
    </a-col>
  </a-row>


</template>

<script>
import { defineComponent, reactive } from 'vue';
import axios from 'axios';
//导入通知组件
import { notification } from 'ant-design-vue';
import { useRouter } from 'vue-router'
import store from "@/store";

export default defineComponent({
  name: "login-view",
  setup() {
    const router = useRouter();

    const loginForm = reactive({
      mobile: '13000000001',
      code: '',
      keyRedis: '',
    });

    const sendCode = () => {
      axios.post("/member/member/sendcode", {
        mobile: loginForm.mobile
      }).then(response => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '发送验证码成功！' });
          loginForm.code = data.content.code;
          loginForm.keyRedis = data.content.key;
          console.log("发送验证码返回结果：", data.content);
        } else {
          notification.error({ description: data.message });
        }
      });
    };

    //
    const login = () => {
      axios.post("/member/member/login", loginForm).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '登录成功！' });
          // 登录成功，跳到控台主页
          router.push("/welcome");
          store.commit("setMember", data.content);
          console.log(data.content);
        } else {
          notification.error({ description: data.message });
        }
      })
    };

    const register = () => {
      axios.post("/member/member/register", loginForm).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '注册成功！' });
          console.log(data.content);
          // store.commit("setMember", data.content);
        } else {
          notification.error({ description: data.message });
        }
      })
    };

    return {
      loginForm,
      sendCode,
      login,
      register
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
/*ul {*/
/*  list-style-type: none;*/
/*  margin: 0;*/
/*  padding: 0;*/
/*  overflow: hidden;*/
/*  border: 1px solid #e7e7e7;*/
/*  !*background-color: #f3f3f3;*!*/
/*  background-color: white;*/
/*}*/

/*li {*/
/*  float: left;*/
/*}*/

/*li a {*/
/*  display: block;*/
/*  color: #666;*/
/*  text-align: center;*/
/*  padding: 14px 16px;*/
/*  text-decoration: none;*/
/*}*/

/*li a:hover:not(.active) {*/
/*  !*background-color: #ddd;*!*/
/*  background-color: white;*/
/*}*/

/*li a.active {*/
/*  color: white;*/
/*  !*background-color: #4CAF50;*!*/
/*  background-color: white;*/
/*}*/

/*ul li.we {*/
/*  padding-top: 28px;*/
/*  padding-left: 20px;*/
/*  font-size: 20px;*/
/*}*/

/*.header-welcome .one {*/
/*  font-size: 35px;*/
/*}*/

</style>
