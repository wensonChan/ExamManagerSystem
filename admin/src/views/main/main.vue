<template>
  <el-container class="layout-container-demo" style="height: 100%">
    <el-aside width="200px">
      <el-scrollbar>
        <router-link to="/main">
          <div class="logo">
            考试管理系统
          </div>
        </router-link>
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="2"
            text-color="#fff"
            :router="true"
            @open="handleOpen"
            @close="handleClose"
        >
          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <location/>
              </el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/menber/student">学生管理</el-menu-item>
            <el-menu-item index="/menber/teacher">老师管理</el-menu-item>
          </el-sub-menu>

        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px;line-height: 56px;" height="56px">
        <div class="toolbar">
          <el-dropdown>
            <el-icon style="margin-right: 8px;">
              <setting/>
            </el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>
                  <router-link to="/reg">修改密码</router-link>
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span>{{ adminName }}</span>
        </div>
      </el-header>

      <el-main>
        <el-scrollbar>
          <router-view></router-view>
        </el-scrollbar>
      </el-main>
    </el-container>
  </el-container>

</template>


<script>
import {Menu as IconMenu, Message, Setting, Location} from '@element-plus/icons-vue'

export default {
  name: "main",
  components: {
    IconMenu,
    Message,
    Setting,
    Location
  },
  data() {
    return {
      adminName: null,
      user: "xx",
    }
  },
  methods: {},
  created() {
    this.adminName = sessionStorage.getItem("adminName")
  },
}

</script>

<style scoped>

.layout-container-demo .el-header {
  position: relative;
  background-color: #3ecde1;
  color: #fff;
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: #69e3cd;
  height: 100%;
}

.layout-container-demo .el-aside .logo {
  height: 56px;
  line-height: 56px;
  text-align: center;
  color: #fff;
}

.layout-container-demo .el-aside .logo:hover {
  background-color: #e0a421;
}


.layout-container-demo .el-menu {
  border-right: none;
  background-color: #3ecde1;
}

.layout-container-demo .el-menu-item {
  border-right: none;
  background-color: #3ecde1;
}

.layout-container-demo .el-main {
  background-color: #ccfcf8;
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
  background-color: #3ecde1;
}

.viso {
  display: block;
}

.notviso {
  display: none;
}

.box .left {
  font-size: 100px;
  text-align: center;
  writing-mode: vertical-rl;
  text-orientation: mixed;
  padding-top: 80px;
  padding-right: 90px;
}
</style>
