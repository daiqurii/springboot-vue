import {createApp} from 'vue'
import App from './App.vue'
import router from './router'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import { ElNotification } from 'element-plus'
import '@/assets/css/global.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App)
app.use(ElementPlus)
app.use(router).mount('#app')

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

router.beforeEach(async (to,from,next)=>{
    let isLogin = localStorage.getItem("token"); //判断是否登录了
    // needLogin  表示哪些路由需要在登录条件下才能访问
    console.log(to);
    let needLogin = to.matched.some(match=>match.meta.needLogin);
    if(needLogin){
        //需要登录
        if(isLogin){
            //登录过了
            next()
        }else{
            //没有登录
            // this.$message({
            //     type: 'error',
            //     message: '您尚未登录',
            // });
            ElNotification({
                title: 'Warning',
                message: '您尚未登录',
                type: 'warning',
            })
            next('/login')
        }
    }else{
        //不需要登录
        if(isLogin && to.path === '/login'){  //如果你访问login页面，则给你跳到首页面，因为不需要登录
            next('/')
        }else{
            next()
        }
    }
});

