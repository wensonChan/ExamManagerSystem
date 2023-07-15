<template>
  <div class="box">
    <el-button type="primary" style="margin-bottom: 10px;" @click="handleAdd">添加</el-button>

    <el-input v-model="findid" v-on:input="handleSelect" placeholder="输入学号进行搜索" maxlength="25" clearable
              style="padding-bottom: 20px"/>
    <!--    {{ findid }}-->
    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="学号" align="center">
        <template #default="scope">
          {{ scope.row.student_id }}
        </template>
      </el-table-column>

      <el-table-column label="姓名" align="center">
        <template #default="scope">
          {{ scope.row.stu_name }}
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
          <el-button size="small" type="danger" @click="handleDelete(scope.row.student_id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogStudentVisible" title="学生信息">
    <!--    {{ student }}-->
    <el-form :model="student">
      <!--      <el-form-item label="学号">-->
      <!--        <el-input v-text="student.student_id"  v-model="student.student_id" autocomplete="off"/>-->
      <!--      </el-form-item>-->

      <el-form-item label="姓名">
        <el-input v-model="student.stu_name" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="密码">
        <el-input v-model="student.password" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="手机号码">
        <el-input v-model="student.phone" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="student.mail" autocomplete="off"/>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogStudentVisible = false">取消</el-button>
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
      loading: true,
      dialogStudentVisible: false,
      tableData: [],
      student: {
        student_id: null, stu_name: null, password: null, phone: null, mail: null
      }

    }
  },
  methods: {
    loadStudent() {
      axios.get('/edu/students')
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },

    handleAdd() {

      this.student = {
        student_id: null, stu_name: null, password: null, phone: null, mail: null
      },
          this.student.student_id = 123;
      this.dialogStudentVisible = true;
      this.temp = true;
    },


    handleEdit(row) {
      this.student = JSON.parse(JSON.stringify(row));
      this.dialogStudentVisible = true;
      this.temp = false;
    },

    save() {

      if (this.temp == false) {

        //编辑操作
        axios.post('/edu/students/' + this.student.student_id, "student_id=" + this.student.student_id + "&stu_name=" + this.student.stu_name
            + "&password=" + this.student.password + "&phone=" + this.student.phone
            + "&mail=" + this.student.mail + "&_method=put").then((response) => {
          if (response.data.code == 200) {
            this.dialogStudentVisible = false;
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
            //重新加载信息
            this.loadStudent();
          }
        }).catch((error) => {
          console.log(error);
        });
      } else {
        let param = qs.stringify(this.student);
        axios.post('/edu/students/', param).then((response) => {
          if (response.data.code == 200) {
            this.dialogStudentVisible = false;
            //重新加载信息
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
            this.loadStudent();
          }
        }).catch((error) => {
          console.log(error);
        });
      }
    },

    handleDelete(student_id) {
      this.$confirm('确定要删除该学生吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击了确定按钮，发送delete请求
        axios.delete('/edu/students/' + student_id)
            .then((response) => {
              if (response.data.code === 200) {
                this.$message({
                  message: response.data.msg,
                  type: 'success'
                });
                this.loadStudent();
              }
            })
            .catch((error) => {
              console.log(error);
            });
      }).catch(() => {
        // 用户点击了取消按钮，不执行任何操作
      });
    },
    handleSelect() {//搜索
      if (this.findid != "") {
        axios.get('/edu/students/' + this.findid)
            .then((response) => {
              if (response.data.data) {
                this.tableData = [];
                this.tableData.push(response.data.data)
              } else {
                this.tableData = [];
              }
              this.loading = false;
            }).catch((error) => {
        });
      } else this.loadStudent();
    },
  }
  ,
  created() {
    this.loadStudent();
  }
}
</script>