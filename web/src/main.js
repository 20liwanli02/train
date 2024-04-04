import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios';
import './assets/js/enums';

//导入ant-design-vue组件架子
import Antd, {notification} from 'ant-design-vue';
//导入ant-design-vue组件样式
import 'ant-design-vue/dist/antd.css';
//导入ant-design-vue组件图标
import * as Icons from '@ant-design/icons-vue';

//导入，引用，使用

//引入ant-design-vue组件--.use(Antd)
const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app')

//全局引用ant-design-vue组件图标
const icons = Icons;
for(const i in icons){
    app.component(i,icons[i])
}

/**
 * axios拦截
 */
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    const _token = store.state.member.token;
    if (_token) {
        config.headers.token = _token;
        console.log("请求headers增加token:", _token);
    }
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    const response = error.response;
    const status = response.status;
    if (status === 401) {
        // 判断状态码是401 跳转到登录页
        console.log("未登录或登录超时，跳到登录页");
        store.commit("setMember", {});
        notification.error({ description: "未登录或登录超时" });
        router.push('/login');
    }
    return Promise.reject(error);
});

/**
 * 配置动态地址头
 */
axios.defaults.baseURL = process.env.VUE_APP_SERVER;
/**
 * 打印当前环境
 */
console.log('环境：', process.env.NODE_ENV);
console.log('服务端：', process.env.VUE_APP_SERVER);
