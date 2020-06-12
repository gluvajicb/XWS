import Vue from 'vue'
import Router from 'vue-router'

import LoginPage from '@/components/homepage/LoginPage'

Vue.use(Router)

export default new Router({
    mode: 'history',
    
    routes: [
        {
            path: '/login',
            component: LoginPage
        }
    ]
})