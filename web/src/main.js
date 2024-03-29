import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//导入ant-design-vue组件架子
import Antd from 'ant-design-vue';
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