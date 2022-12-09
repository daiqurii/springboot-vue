import {createRouter, createWebHistory} from 'vue-router'

import Login from "@/views/Login";
import Layout from "@/layout/Layout";
import Home from "@/views/Home";
import Register from "@/views/Register";

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: Layout,
        meta:{
            needLogin:false
        },
        redirect:"/home",
        children:[
            {
                path: 'home',
                name: 'Home',
                component: Home,
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router