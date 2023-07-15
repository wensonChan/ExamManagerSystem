<template>
  <div class="box">
    <el-input v-model="findid" v-on:input="handleSelect" placeholder="输入考试名称进行搜索" clearable
              style="padding-bottom: 20px"/>
    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="课程号" align="center">
        <!--        label考试编号-->
        <template #default="scope">
          {{ scope.row.course_id }}
        </template>
      </el-table-column>

      <el-table-column label="考试名称" align="center">
        <template #default="scope">
          {{ scope.row.exam_name }}
        </template>
      </el-table-column>

      <el-table-column label="老师编号" align="center">
        <template #default="scope">
          {{ scope.row.teacher_id }}
        </template>
      </el-table-column>

      <el-table-column label="课室" align="center">
        <template #default="scope">
          {{ scope.row.room }}
        </template>
      </el-table-column>

      <el-table-column label="开始时间" align="center">
        <template #default="scope">
          {{ scope.row.start_time }}
        </template>
      </el-table-column>

      <el-table-column label="结束时间" align="center">
        <template #default="scope">
          {{ scope.row.end_time }}
        </template>
      </el-table-column>


      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.course_id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogExamDataVisible" title="考试信息">
    <el-form :model="examData">

      <el-form-item label="考试名称">
        <el-input v-model="examData.exam_name" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="课室">
        <el-input v-model="examData.room" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="考试时间设定">
        <el-date-picker
            v-model="this.time"
            type="datetimerange"
            range-separator="To"
            start-placeholder="Start date"
            end-placeholder="End date"
            format="YYYY/MM/DD hh:mm:ss"
            value-format="YYYY-MM-DD HH:mm:ss"
        />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogExamDataVisible = false">取消</el-button>
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

export default {
  name: "List",
  data() {
    return {
      findid: null,
      loading: true,
      dialogExamDataVisible: false,
      time: [],
      tableData: [],
      examData: {
        course_id: null, exam_name: null, teacher_id: null, room: null,start_time:null,end_time:null
      }

    }
  },
  methods: {
    loadExamData() {

      axios.get('/edu/schedules')
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },

    handleEdit(row) {
      this.time = null;
      this.examData = JSON.parse(JSON.stringify(row));
      this.dialogExamDataVisible = true;
    },

    save() {
      //编辑操作
      if(this.time!=null){
        this.examData.start_time=this.time[0];
        this.examData.end_time=this.time[1];
      }

      axios.post('/edu/schedules/' +
          this.examData.course_id, "exam_name=" + this.examData.exam_name + "&teacher_id=" + this.examData.teacher_id+ "&room=" + this.examData.room + "&start_time=" + this.examData.start_time + "&end_time=" + this.examData.end_time + "&_method=put").then((response) => {
        if (response.data.code == 200) {
          this.dialogExamDataVisible = false;
          //重新加载信息
          this.loadExamData();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleDelete(course_id) {
      //发送delete
      axios.delete('/edu/schedules/' + course_id).then((response) => {
        if (response.data.code == 200) {
          //重新加载信息
          this.loadExamData();
        }
        if (response.data.code == 500) {
          //重新加载信息
          alert(response.data.msg);
          this.loadExamData();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleSelect() {
      axios.get('/edu/schedules' +"?exam_name="+this.findid)
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },
  },


  created() {
    this.loadExamData();
  }
}
</script>