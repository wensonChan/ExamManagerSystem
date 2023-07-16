<template>
  <div class="container">

    <el-row style="margin-bottom: 10px;">
      <el-button type="primary" v-on:click="handleDrop">退选</el-button>
    </el-row>

    <el-table :data="tableData" border>
      <el-table-column label="课程编号" width="180">
        <template #default="scope">
          {{scope.row.subject_id}}
        </template>
      </el-table-column>

      <el-table-column label="课程名称" width="360">
        <template #default="scope">
          {{scope.row.course_name}}
        </template>
      </el-table-column>

      <el-table-column label="老师" width="360">
        <template #default="scope">
          {{scope.row.teacher_name  }}
        </template>
      </el-table-column>

      <el-table-column label="成绩" width="360">
        <template #default="scope">
          {{scope.row.score}}
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogElectivesVisible" title="退选课程">
    <el-form :model="subject">
      <el-form-item label="课程编号">
        <el-input v-model="subject.subject_id" autocomplete="off" />
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogElectivesVisible = false">取消</el-button>
        <el-button type="primary" @click="affirm">确认退选</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios"
import qs from "qs"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "List",
  data(){
    return {
      tableData:[

      ],
      dialogElectivesVisible:false,

      student:{
        student_id:sessionStorage.getItem("student_id")
      },

      subject:{
        subject_id: null
      }
    }
  },
  methods:{
    loadElectives(){
      //显示所有已选的学科
      if(this.student.student_id){
        axios.get('/edu/electives/'+this.student.student_id)
            .then((response)=>{
              this.tableData = response.data.data;
            }).catch((error)=>{
        });
      }
    },

    handleDrop(){
      this.subject={
        subject_id:null,
      },
          this.dialogElectivesVisible=true;
    },

    affirm(){
      //确认退选
      let params = {student_id:this.student.student_id, subject_id:this.subject.subject_id, _method:"delete"}
      qs.stringify(params)
      axios.post('/edu/electives/',params)
          .then((response)=>{
            if(response.data.code == 200){
              this.dialogElectivesVisible=false;

              //重新加载课程信息
              this.loadElectives();
            }
          }).catch((error)=>{
            console.log(error);
      });
    },
  },
  created() {
    this.loadElectives();
  }
}
</script>

<style scoped>

</style>