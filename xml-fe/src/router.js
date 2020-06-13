import Vue from 'vue'
import Router from 'vue-router'

/* Index Page (Login, Registration) */
import IndexPage from '@/components/homepage/IndexPage'
import LoginPage from '@/components/homepage/LoginPage'
import RegisterPage from '@/components/homepage/RegisterPage'

/* Author */
import AuthorHomePage from '@/components/author/HomePage'

Vue.use(Router)

export default new Router({
    mode: 'history',
    
    routes: [
        {
            path: '/',
            component: IndexPage,
        },
        {
            path: '/login',
            component: LoginPage
        },
        {
            path: '/register',
            component: RegisterPage
        },
        {
            path: '/author/:id/',
            component: AuthorHomePage,
            children: [
                {
                    path: 'homepage',
                    component: AuthorHomePage
                }
            ]
        }
    ]
})