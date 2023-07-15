import {createRouter, createWebHistory} from 'vue-router'
import Login from "@/views/login.vue";
import student from "@/views/menber/student.vue";
import main from "@/views/main/main.vue";
import reg from "@/views/reg.vue";
import teacher from "@/views/menber/teacher.vue";

const routes = [
    {
        path: '/main',
        name: 'main',
        component: main,
        children: [
            {
                path: "/",
                redirect: "/login"
            },
            {
                path: '/menber/student',
                name: 'student',
                component: student
            },
            {
                path: '/menber/teacher',
                name: 'teacher',
                component: teacher
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
