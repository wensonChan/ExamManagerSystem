<template>
  <div class="container">

    <el-column style="margin-bottom: 10px;">
      <el-button type="primary" v-on:click="handleCurricula">选择</el-button>
    </el-column>

    <el-column style="margin-bottom: 10px;">
      <el-button type="primary" v-on:click="handleSelect">查询</el-button>
    </el-column>

    <el-column style="margin-bottom: 10px;">
      <el-button type="primary" v-on:click="loadSubject">还原</el-button>
    </el-column>

    <el-table :data="tableData" border>
      <!---->
      <el-table-column label="课程编号" width="180">
        <template #default="scope">
          {{scope.row.subject_id}}
        </template>
      </el-table-column>

      <el-table-column label="课程名称" width="360">
        <template #default="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>

      <el-table-column label="老师" width="360">
        <template #default="scope">
          {{scope.row.teacher_name}}
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogSubjectVisible" title="选修课程">
    <el-form :model="subject">
      <el-form-item label="课程编号">
        <el-input v-model="subject.subject_id" autocomplete="off" />
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogSubjectVisible = false">取消</el-button>
        <el-button type="primary" @click="affirm">确认选课</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogSelectVisible" title="选修课程">
    <el-form :model="teacher.teacher_name">
      <el-form-item label="教师姓名">
        <el-input v-model="teacher.teacher_name" autocomplete="off" />
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogSelectVisible = false">取消</el-button>
        <el-button type="primary" @click="SelectByTeacher">查询</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios"
import qs from "qs"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "subjectList",
  data(){
    return {
      tableData:[],
      dialogSubjectVisible:false,//选课的弹窗显示属性
      dialogSelectVisible:false,//查询老师的弹窗显示属性

      student:{
        student_id:sessionStorage.getItem("student_id")
      },

      teacher:{
        teacher_id:null,
        teacher_name:null
      },

      subject:{
        subject_id: null
      }

    }
  },
  methods:{
    loadSubject(){
      //显示所有课程信息
      axios.get('/edu/subjects')
          .then((response)=>{
            this.tableData = response.data.data;
            console.log(response.data.data)
          }).catch((error)=>{
      });
    },

    handleSelect(){
      this.teacher={
        teacher_name:null
      },
          this.dialogSelectVisible=true;
    },

    SelectByTeacher(){
      //依据老师的名字查询并显示所有可选的学科
      axios.get('/edu/subjects/'+this.teacher.teacher_name)
          .then((response)=>{
            if(response.data.code == 200){
              this.dialogSelectVisible=false;

              //加载课程信息
              this.tableData = response.data.data;
            }
          }).catch((error)=>{
      });
    },

    handleCurricula(){
      this.subject={
        subject_id:null
      },
          this.dialogSubjectVisible=true;
    },

    affirm(){
      //确认选课
      let params = {student_id:this.student.student_id, subject_id:this.subject.subject_id, _method:"post"}
      axios.post('/edu/electives/',params)
          .then((response)=>{
            if(response.data.code == 200){
              this.dialogSubjectVisible=false;
              //重新加载课程信息
              this.loadSubject();
            }
          }).catch((error)=>{
            console.log(error);
          });
    },
  },
  created() {
    this.loadSubject();
  }
}
</script>

<style scoped>

</style>