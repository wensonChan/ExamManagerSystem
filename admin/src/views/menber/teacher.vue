<template>
  <div class="box">
    <el-button type="primary" style="margin-bottom: 10px;" @click="handleAdd">添加</el-button>

    <el-input v-model="findid" v-on:input="handleSelect" placeholder="输入教师号进行搜索" maxlength="10" clearable
              style="padding-bottom: 20px"/>
    {{ findid }}
    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="教师号" align="center">
        <template #default="scope">
          {{ scope.row.teacher_id }}
        </template>
      </el-table-column>

      <el-table-column label="姓名" align="center">
        <template #default="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>

      <el-table-column label="密码" align="center">
        <template #default="scope">
          {{ scope.row.password }}
        </template>
      </el-table-column>

      <el-table-column label="手机号码" align="center">
        <template #default="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>

      <el-table-column label="邮箱" align="center">
        <template #default="scope">
          {{ scope.row.mail }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.teacher_id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogTeacherVisible" title="学生信息">
    {{ teacher }}
    <el-form :model="teacher">
      <el-form-item label="学号">
        <el-input v-model="teacher.teacher_id" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="姓名">
        <el-input v-model="teacher.name" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="密码">
        <el-input v-model="teacher.password" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="手机号码">
        <el-input v-model="teacher.phone" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="teacher.mail" autocomplete="off"/>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogTeacherVisible = false">取消</el-button>
        <el-button type="primary" @click="save">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.box {
  padding: 10px;
}
</style>
<script>
import axios from "axios"
import qs from "qs"

export default {
  name: "List",
  data() {
    return {
      findid: null,
      temp: true,
      loading: false,
      dialogTeacherVisible: false,
      tableData: [{teacher_id: 1999010101, name: "test", password: "test", phone: "test", mail: "test"}],
      teacher: {
        teacher_id: null, name: null, password: null, phone: null, mail: null
      }

    }
  },
  methods: {
    loadTeacher() {
      axios.get('/edu/teachers')
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },

    handleAdd() {

      this.teacher = {
        teacher_id: null, name: null, password: null, phone: null, mail: null
      },
          this.dialogTeacherVisible = true;
      this.temp = true;
    },


    handleEdit(row) {
      this.teacher = JSON.parse(JSON.stringify(row));
      this.dialogTeacherVisible = true;
      this.temp = false;
    },

    save() {

      if (this.temp == false) {
        //编辑操作
        axios.post('/edu/teachers/' + this.teacher.teacher_id, "name=" + this.teacher.name
            + "&password=" + this.teacher.password + "&phone=" + this.teacher.phone
            + "&mail=" + this.teacher.mail + "&_method=put").then((response) => {
          if (response.data.code == 200) {
            this.dialogTeacherVisible = false;
            //重新加载信息
            this.loadTeacher();
          }
        }).catch((error) => {
          console.log(error);
        });
      } else {
        let param = qs.stringify(this.teacher);
        axios.post('/edu/teachers/', param).then((response) => {
          if (response.data.code == 200) {
            this.dialogTeacherVisible = false;
            //重新加载信息
            this.loadTeacher();
          }
        }).catch((error) => {
          console.log(error);
        });
      }
    },

    handleDelete(teacher_id) {
      //发送delete
      axios.delete('/edu/teachers/' + teacher_id).then((response) => {
        if (response.data.code == 200) {
          //重新加载信息
          this.loadTeacher();
        }
      }).catch((error) => {
        console.log(error);
      });
    },
    handleSelect() {//搜索
      axios.get('/edu/teachers/' + this.findid)
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },
  },
  created() {
    this.loadTeacher();
  }
}
</script>