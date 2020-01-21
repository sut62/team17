import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login'
import Dashboard from '../components/Dashboard'
import Employee from '../components/Employee'
import PreOrder from '../components/PreOrder'
import Product from '../components/Product'
import Customer from '../components/Customer'
import CollectPoint from '../components/CollectPoint'
import SearchSale from '../components/SearchSale'

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
      path: '/SearchSale',
      name:'SearchSale',
      component: SearchSale
    },
    {
      path: '/Product',
      name:'Product',
      component: Product
    },
    {
      path: '/Customer',
      name:'Customer',
      component: Customer
    },
    {
      path: '/CollectPoint',
      name:'CollectPoint',
      component: CollectPoint
    },
    {
      path: '/PreOrder',
      name:'PreOrder',
      component: PreOrder
    }
  ]
})
