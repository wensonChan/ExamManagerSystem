import { createRouter, createWebHistory } from 'vue-router'
import ElectivesListView from "@/views/electives/List"
import LayoutView from "@/views/layout/layout"
import LoginView from "@/views/login/login"
import SubjectListView from "@/views/subject/subjectList"
import MyTestView from "@/views/testAbout/myTest"
import MytestListView from "@/views/testAbout/mytestList"
import setInfView from "@/views/setinf/setInf"

const routes = [
  {
    path:'/',
    //重定向到login页面
    redirect:'/login/login'
  },
  {
    path:"/login/login",
    name:"loginView",
    component:LoginView,
  },
  {
    path:"/",
    name:"layoutView",
    component:LayoutView,
    children:[
      {
        path:"/",
        redirect:"/electives/List",
      },
      {
        path:"/electives/List",
        name:"electivesView",
        component:ElectivesListView
      },
      {
        path:"/subject/subjectList",
        name:"subjectView",
        component:SubjectListView
      },
      {
        path:"/testAbout/myTest/:id",
        name:"myTestView",
        component:MyTestView
      },
      {
        path:"/testAbout/mytestList",
        name:"myTestListView",
        component:MytestListView
      },
      {
        path:"/setinf/setInf",
        name:"setInfView",
        component:setInfView
      }
    ]
  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router