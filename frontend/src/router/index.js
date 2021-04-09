import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Update from '../page/user/Update.vue'
import Profile from '../page/user/MyProfile.vue'

//blog
import MyList from '../page/blog/MyList.vue'
import searchList from "../page/blog/searchList"
import detail from "../page/blog/detail"
import BlogCreate from "../page/blog/blogCreate"
import Information from "../page/blog/Information"

// 포스트
import Frontpage from '../page/post/Frontpage.vue'

// ERROR
import ErrorPage from '../page/error/error.vue'


//관리자 페이지
import adminPage from "@/page/admin/adminPage";

//기업 리스트
import companyList from "@/page/blog/companyList";


Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
      // 관리자 페이지
    {
      path: '/admin',
      name: constants.URL_TYPE.ADMIN.ADMINPAGE,
      component: adminPage
    },
      // 기업 리스트
    {
      path: '/companylist',
      name: companyList,
      component: companyList
    },

    // 로그인/가입

    {
      path: '/user/update',
      name: 'update',
      component: Update
    },
    {
      path: '/user/profile',
      name: 'Profile',
      component: Profile
    },
    {
      path: '/blog/list',
      name: 'mylist',
      component: MyList
    },
    // 포스트
    {
      path: '/',
      name: constants.URL_TYPE.POST.MAIN,
      component: Frontpage,
    },

    // 블로그
    {
      path: '/blog/search',
      name: constants.URL_TYPE.BLOG.SEARCHLIST,
      component: searchList
    },
    {
      path: '/blog/detail/:bno',
      name: constants.URL_TYPE.BLOG.DETAIL,
      component: detail
    },
    {
      path: "/blog/blogcreate",
      name: "BlogCreate",
      component: BlogCreate,
      props: true
    },
    { path: "/blog/information",
      name: "Information",
      component: Information
    },
    //error
    {
      path: "/errorpage/:code",
      name: "ErrorPage",
      component: ErrorPage
    },
   

  ]
})
