<template>
  <el-container class="layout-container-demo" style="height: 100%">
    <el-aside width="200px">
      <el-scrollbar>
        <div class="logo">
          考试管理系统（学生端）
        </div>
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="2"
            text-color="#fff"
            :router="true"
            @open="handleOpen"
            @close="handleClose">

          <el-sub-menu index="1">
            <template #title>
              <el-icon><location /></el-icon>
              <span>课程管理</span>
            </template>
            <el-menu-item index="/electives/List">课程</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="2">
            <template #title>
              <el-icon><location /></el-icon>
              <span>选课管理</span>
            </template>
            <el-menu-item index="/subject/subjectList">选课</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="3">
            <template #title>
              <el-icon><location /></el-icon>
              <span>考试中心</span>
            </template>
            <el-menu-item index="/testAbout/mytestList">我的考试</el-menu-item>
          </el-sub-menu>

          <el-sub-menu index="4">
            <template #title>
              <el-icon><location /></el-icon>
              <span>信息设置</span>
            </template>
            <el-menu-item index="/setinf/setInf">修改信息</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-main>
      <el-scrollbar>
        <router-view></router-view>
      </el-scrollbar>
    </el-main>
  </el-container>
</template>


<script>
import { Menu as IconMenu, Message, Setting,Location } from '@element-plus/icons-vue'
import axios from "axios"
import qs from "qs"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "layout",
  components:{
    IconMenu,
    Message,
    Setting,
    Location
  },
}
</script>

<style scoped>

.layout-container-demo .el-header {
  position: relative;
  background-color: #545c64;
  color: #fff;
}
.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: #545c64;
  height: 100%;
}

.layout-container-demo .el-aside .logo {
  height:56px;
  line-height:56px;
  text-align:center;
  color:#fff;
}

.layout-container-demo .el-aside .logo:hover{
  background-color: rgb(67, 74, 80);
}

.layout-container-demo .el-menu {
  border-right: none;
}
.layout-container-demo .el-main {
  padding: 0;
}
.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}
</style>

<style>
#app{
  height: 100%;
}
</style>
