import { createRouter, createWebHistory } from 'vue-router'
import IntroduceView from '@/views/IntroduceView.vue'
import LoginView from '@/views/LoginView.vue'
import BoardView from '@/views/BoardView.vue'
import CorpView from '@/views/CorpView.vue'
import PaymentView from '@/views/PaymentView.vue'
import MainView from '@/views/MainView.vue'
import GymView from '@/views/GymlistView.vue'
import UserView from '@/views/UserView.vue'
import YoutubeView from '@/views/YoutubeView.vue'

import BoardWrite from '@/components/board/BoardWrite.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardList from '@/components/board/BoardList.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'

import CorpGymCreate from '@/components/corp/CorpGymCreate.vue'
import CorpGymUpdate from '@/components/corp/CorpGymUpdate.vue'

import UserMyInfo from '@/components/user/UserMyInfo.vue'
import UserCreate from '@/components/user/UserCreate.vue'
import UserPayment from '@/components/user/UserPayment.vue'
import KakaoLogin from '@/components/user/KakaoLogin.vue'
import UserUse from '@/components/user/UserUse.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/youtube',
      name: 'youtube',
      component: YoutubeView,
    },
    {
      path: '/pay',
      name: 'paymentView',
      component: PaymentView,
    },
    
    {
      path: '/main',
      name: 'mainView',
      component: MainView,
    },
    
    {
      path: '/',
      name: 'introduceView',
      component: IntroduceView,
    },
    
    {
      path: '/login',
      name: 'loginView',
      component: LoginView,
    },
    {
      path: "/kakaologin",
      name: "kakaologin",
      component: KakaoLogin
    },
    {
      path: '/gym',
      name: 'gymView',
      component: GymView,
    },
    {
      path: '/create',
      name: 'userCreate',
      component: UserCreate
    },
    {
      path: '/board',
      name: 'boardView',
      component: BoardView,
      children: [
        {
          path: '',
          name: 'boardList',
          component: BoardList
        },
        {
          path: 'write',
          name: 'boardWrite',
          component: BoardWrite
        },
        {
          path: 'update',
          name: 'boardUpdate',
          component: BoardUpdate
        },
        {
          path: ':id',
          name: 'boardDetail',
          component: BoardDetail
        },
      ]
    },

    {
      path: '/corp',
      name: 'corpView',
      component: CorpView,
      children: [
        {
          path: 'gymregist',
          name: 'corpGymCreate',
          component: CorpGymCreate
        },
        {
          path: 'gymupdate',
          name: 'corpGymUpdate',
          component: CorpGymUpdate
        },
      ]
    },

    
    {
      path: '/user',
      name: 'userView',
      component: UserView,
      children: [
        {
          path: 'payment',
          name: 'userPayment',
          component: UserPayment
        },
        {
          path: 'usegym',
          name: 'userUse',
          component: UserUse
        },
        {
          path: '',
          name: 'userMyInfo',
          component: UserMyInfo
        },
        
      ]
    },

  ]
})


export default router
