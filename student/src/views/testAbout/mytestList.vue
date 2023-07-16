<template>
  <div class="container">

    <el-row style="margin-bottom: 10px;">
      <el-button type="primary" v-on:click="loadTestSchedule">刷新</el-button>
    </el-row>

    <el-table :data="tableData" border>
      <!---->
      <el-table-column label="课程名" >
        <template #default="scope">
          {{scope.row.course_name}}
        </template>
      </el-table-column>

      <el-table-column label="考试名" >
        <template #default="scope">
          {{scope.row.exam_name}}
        </template>
      </el-table-column>

      <el-table-column label="地点" >
        <template #default="scope">
          {{scope.row.room}}
        </template>
      </el-table-column>

      <el-table-column label="开始时间" >
        <template #default="scope">
          {{scope.row.start_time}}
        </template>
      </el-table-column>

      <el-table-column label="结束时间" >
        <template #default="scope">
          {{scope.row.end_time}}
        </template>
      </el-table-column>

      <el-table-column label="开始考试" >
        <template #default="scope">
          <el-button :disabled="scope.row.disabled" size="small" @click="loadTest(scope.row)">开始</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import axios from "axios"
import qs from "qs"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "mytestList",
  data(){
    return {
      tableData:[],
      dialogSubjectVisible:false,

      student:{
        student_id:sessionStorage.getItem("student_id")
      },
    }
  },
  methods:{
    loadTestSchedule(){
      //加载所有可进行的考试
      axios.get('/edu/examArrangements/'+this.student.student_id)
          .then((response)=>{
            let now = new Date()
            console.log(response.data.data);
            response.data.data.forEach(item=>{
              let endDate = new Date(item.end_time)
              if(now > endDate) item.disabled = true
              console.log(item.disabled, now > endDate)
            })
            this.tableData = response.data.data;

          }).catch((error)=>{
      });
    },

    loadTest(course){
      console.log(course)
      //跳转考试界面
      this.$router.push({
        path:`/testAbout/myTest/${course.course_id}`,
      })
    }
  },
  created() {
    this.loadTestSchedule();
  }
}
</script>

<style scoped>

</style>