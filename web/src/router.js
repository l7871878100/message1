import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:"/",
      redirect:"/list"
    },
    {
      path: '/list',
      name: 'messageList',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "messageList" */ './views/MessageList.vue')
    },
    {
      path: '/manage',
      name: 'messageManage',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "messageList" */ './views/MessageManage.vue')
    },
    {
      path: '/info/:id',
      name: 'messageInfo',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "messageList" */ './views/MessageInfo.vue')
    },
    {
      path: '/reply',
      name: 'messageReply',
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import(/* webpackChunkName: "messageList" */ './views/MessageReply.vue')
    }
  ]
})
