import {createRouter, createWebHistory} from 'vue-router'
import ClassList from "@/views/class/classList.vue";
import exampage from "@/views/class/exampage.vue";
import Login from "@/views/login.vue";
import exammanage from "@/views/exam/exammanage.vue";
import main from "@/views/main/main.vue";
import elective from "@/views/exam/elective.vue";
import reg from "@/views/reg.vue";
import exampaper from "@/views/class/exampaper.vue";

const routes = [
    {
        path: '/main',
        name: 'main',
        component: main,
        children: [
            {
                path: "/",
                redirect:"/login"
            },
            {
                path: '/class/classlist',
                name: 'classList',
                component: ClassList
            },
            {
                path: '/exam/exampage',
                name: 'exampage',
                component: exampage
            },
            {
                path: '/exam/exampager',
                name: 'exampager',
                component: exampaper
            },
            {
                path: '/exam/exammanage',
                name: 'exammanage',
                component: exammanage
            },
            {
                path: '/exam/elective',
                name: 'elective',
                component: elective
            },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/reg',
        name: 'reg',
        component: reg
    }
]
const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
