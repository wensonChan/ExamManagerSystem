<template>
  <div class="box">
    <el-button type="primary" style="margin-bottom: 10px;" @click="handleAdd">添加</el-button>

    <el-input v-model="findid" v-on:input="handleSelect" placeholder="输入教师号进行搜索" maxlength="25" clearable
              style="padding-bottom: 20px"/>
<!--    {{ findid }}-->
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

  <el-dialog v-model="dialogTeacherVisible" title="教师信息">
<!--    {{ teacher }}-->
    <el-form :model="teacher">
      <!--      <el-form-item label="老师编号">-->
      <!--&lt;!&ndash;        <el-text v-text="'******'" v-model="teacher.teacher_id" autocomplete="off"/>&ndash;&gt;-->
      <!--      </el-form-item>-->

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
      loading: true,
      dialogTeacherVisible: false,
      tableData: [],
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
          this.teacher.teacher_id = 123;
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

        axios.post('/edu/teachers/' + this.teacher.teacher_id, "teacher_id=" + this.teacher.teacher_id + "&name=" + this.teacher.name
            + "&password=" + this.teacher.password + "&phone=" + this.teacher.phone
            + "&mail=" + this.teacher.mail + "&_method=put").then((response) => {
          if (response.data.code == 200) {
            this.dialogTeacherVisible = false;
            //重新加载信息
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
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
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
            this.loadTeacher();
          }
        }).catch((error) => {
          console.log(error);
        });
      }
    },

    handleDelete(teacher_id) {
      this.$confirm('确定要删除该教师吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击了确定按钮，发送delete请求
        axios.delete('/edu/teachers/' + teacher_id)
            .then((response) => {
              if (response.data.code === 200) {
                this.$message({
                  message: response.data.msg,
                  type: 'success'
                });
                this.loadTeacher();
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
      axios.get('/edu/teachers/'+ this.findid)
          .then((response) => {
            if(response.data.data) {
              this.tableData=[];
              this.tableData.push(response.data.data)
              // this.$message({
              //   message: response.data.msg,
              //   type: 'success'
              // });
            }else{
              this.tableData=[];
            }
            this.loading = false;
          }).catch((error) => {
      });
      if(this.findid==""){
        this.loadTeacher();
      }
    },
  },
  created() {
    this.loadTeacher();
  }
}
</script>