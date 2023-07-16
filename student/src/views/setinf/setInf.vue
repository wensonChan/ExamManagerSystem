<template>
  <el-dialog v-model="dialogSetVisible" title="信息修改">
    <el-form :model="tableData">
      <el-form-item label="学号">
        <el-input v-model="tableData.student_id" disabled="true" />
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="tableData.stu_name" disabled="true" />
      </el-form-item>
      <el-form-item label="输入原密码">
        <el-input v-model="tableData.confirmPassword" autocomplete="off" />
      </el-form-item>
      <el-form-item label="设置新密码">
        <el-input v-model="tableData.newPassword" autocomplete="off" />
      </el-form-item>
      <el-form-item label="设置新号码">
        <el-input v-model="tableData.phone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="设置新邮箱">
        <el-input v-model="tableData.mail" autocomplete="off" />
      </el-form-item>
    </el-form>

    <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogSetVisible = false">取消</el-button>
            <el-button type="primary" @click="setNewInf">确认修改</el-button>
          </span>
    </template>
  </el-dialog>

</template>

<script>
import axios from "axios"
import qs from "qs"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "setInf",
  data(){
    return {
      tableData:[],

      dialogSetVisible:false,

      student:{
        student_id:null
      },
    }
  },

  methods:{
    selectNewInf(){
      //查询个人信息
      axios.get('/edu/students/'+this.student.student_id)
          .then((response)=>{
            this.tableData = response.data.data;
            console.log(response.data.data)
          }).catch((error)=>{
      });
      console.log(this.NewInf);
    },

    setNewInf(){
      console.log(this.tableData )
      //修改信息
      if(this.tableData.confirmPassword == this.tableData.password && this.tableData.confirmPassword){
        let params = {"student_id":this.tableData.student_id,
          "password":this.tableData.newPassword,
          "phone":this.tableData.phone,
          "mail":this.tableData.mail};
        axios.put('/edu/students?'+qs.stringify(params))
            .then((response)=>{
              if(response.data.code == 200){
                alert("修改个人信息成功");
                this.dialogSetVisible = false;
                this.selectNewInf();
              }else{
                alert("数据库操作失败");
                this.selectNewInf();
              }
              console.log(response.data.data)
            }).catch((error)=>{

        });
      }else{
        alert("原密码错误或者请检查新密码不能为空")
      }
    }
  },
  created() {
    this.student.student_id = sessionStorage.getItem("student_id");
    this.selectNewInf();
    this.dialogSetVisible = true;
  }
}
</script>

<style scoped>

</style>