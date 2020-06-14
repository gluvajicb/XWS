import Vue from 'vue'
import Router from 'vue-router'

/* Index Page (Login, Registration) */
import IndexPage from '@/components/homepage/IndexPage'
import LoginPage from '@/components/homepage/LoginPage'
import RegisterPage from '@/components/homepage/RegisterPage'

/* Author */
import AuthorHomePage from '@/components/author/HomePage'
import AuthorAdvancedSearch from '@/components/author/AdvancedSearch'
import AuthorMyArticles from '@/components/author/MyArticles'
import AuthorAddArticle from '@/components/author/AddArticle'
import AuthorIndexPage from '@/components/author/IndexPage'


Vue.use(Router)

export default new Router({
    mode: 'history',
    
    routes: [
        
        /* Index Routes */
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


        /* Author Routes */
        {
            path: '/author/:id',
            component: AuthorIndexPage
        },
        {
            path: '/author/:id/homepage',
            component: AuthorHomePage
        },
        {
            path: '/author/:id/advanced-search',
            component: AuthorAdvancedSearch
        },
        {
            path: '/author/:id/my-articles',
            component: AuthorMyArticles
        },
        {
            path: '/author/:id/add-article',
            component: AuthorAddArticle
        }

        /* TREBALO BI DA BUDE OVAKO, ALI IMA BUG, JER PRIHVATA SAMO INDEXPAGE, NI JEDNU DRUGU
        {
            path: '/author/:id/',
            component: AuthorIndexPage,
            children: [
                {
                    path: 'homepage',
                    component: AuthorHomePage
                },
                {
                    path: 'advanced-search',
                    component: AuthorAdvancedSearch
                },
                {
                    path: 'my-articles',
                    component: AuthorMyArticles
                },
                {
                    path: 'add-article',
                    component: AuthorAddArticle
                }
            ]
        }
        */
    ]
})