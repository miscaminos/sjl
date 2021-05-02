//import vue
import Vue from 'vue'

//import router
import VueRouter from 'vue-router'

//import bootstrap
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

//import vue components
import App from './App.vue'
import Home from '@/components/Home.vue'
import About from '@/components/About.vue'
import Portfolio from '@/components/Portfolio.vue'
import Contents from '@/components/Contents.vue'
import AddContents from '@/components/AddContents.vue'
import ContentsList from '@/components/ContentsList.vue'
import HelloWorld from '@/components/HelloWorld.vue'

//router사용
Vue.use(VueRouter);
//bootstrap사용
Vue.use(BootstrapVue)

//router 생성
//페이지 이동 설정
const router = new VueRouter({
  //routes collection은 경로(path)와 component로 이루어짐.
  //이 collection의 순서대로 router가 경로를 matching해서 component를 출력한다
  mode: "history",
  routes: [
    {
      path:'/',
      name:"home",
      component:Home
    },
    {
      path:'/about',
      component:About
    },
    {
      path:'/portfolio',
      component:Portfolio
    },
    {
      path:'/contents',
      name:'contents',
      component:ContentsList
    },
    {
      path:'/contents/:id',
      name: 'contents-details',
      component:Contents
    },
        {
      path:'/add',
      name:'add',
      component:AddContents
    },
    {
      path:'/helloWorld',
      component:HelloWorld
    }
  ]
});


Vue.config.productionTip = false

//vue object 생성
new Vue({
  router:router,
  render: h => h(App),
}).$mount('#app')
