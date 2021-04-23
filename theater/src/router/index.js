import Vue from 'vue'
import VueRouter from 'vue-router'

// page
import BoxOffice from '@/components/BoxOffice.vue'
import Index from '@/components/Index.vue'
import Ticketing from '@/components/Ticketing.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    component: Index
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path: '/boxOffice',
    name: 'box',
    component: BoxOffice,
    props: true
  },
  {
    path : '/ticketing',
    name : 'ticketing',
    component : Ticketing,
    props : true
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
