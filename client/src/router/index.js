import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Dashboard from '../components/Dashboard'
import Employee from '../components/Employee'
import Register from '../components/Register'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name:'Login',
      component: Login
    },
    {
      path: '/Dashboard',
      name:'Dashboard',
      component: Dashboard
    },
    {
      path: '/Employee',
      name:'Employee',
      component: Employee
    },
    {
      path: '/Register',
      name:'Register',
      component: Register
    }
  ]
})
