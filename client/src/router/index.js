import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Dashboard from '../components/Dashboard'
import Employee from '../components/Employee'
import PreOrder from '../components/PreOrder'
import AddProduct from '../components/AddProduct'
import Register from '../components/Register'
import CollectPoint from '../components/CollectPoint'

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
    },
    {
      path: '/PreOrder',
      name:'PreOrder',
      component: PreOrder
    },
    {
      path: '/AddProduct',
      name:'AddProduct',
      component: AddProduct
    },
    {
      path: '/CollectPoint',
      name:'CollectPoint',
      component: CollectPoint
    }
  ]
})
