import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

//导入重置样式 @等价src的意思
import '@/assets/style/css/reset.css'

createApp(App).use(store).use(router).use(ElementPlus).mount('#app')