<template>
  <div class="box">
    <el-button type="primary" style="margin-bottom: 10px;" @click="handleAdd">添加</el-button>
    <el-input v-model="findid" v-on:input="handleSelect" placeholder="输入老师号进行搜索" maxlength="10" clearable
              style="padding-bottom: 20px"/>

    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="课程号" align="center">
        <template #default="scope">
          {{ scope.row.subject_id }}
        </template>
      </el-table-column>

      <el-table-column label="课程名" align="center">
        <template #default="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>

      <el-table-column label="老师编号" align="center">
        <template #default="scope">
          {{ scope.row.teacher_id }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
          <el-button size="small" @click="handletoExam(scope.row)">
            考试安排
          </el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.subject_id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogSubjectVisible" title="课程录入">
    <el-form :model="subject">
      <el-form-item label="课程名">
        <el-input v-model="subject.name" autocomplete="off"/>
      </el-form-item>
    </el-form>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogSubjectVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确认</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogExamDataVisible" title="考试信息">
    {{ examData }}
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
        <el-button type="primary" @click="saveexam">提交</el-button>
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
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

export default {
  name: "List",
  data() {
    return {
      findid: null,
      teacherName: null,
      loading: true,
      dialogExamDataVisible: false,
      dialogSubjectVisible: false,
      tableData: [],
      time: [],
      subject: {
        subject_id: null, name: null, teacher_id: null
      },
      examData: {
        course_id: null, exam_name: null, teacher_id: null, room: null, start_time: null, end_time: null,
      }

    }
  },
  methods: {
    loadSubject() {
      axios.get('/edu/subjects')
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },

    handleAdd() {

      this.subject = {
        subject_id: null, name: null, teacher_id: this.teacherName
      };
          this.dialogSubjectVisible = true;
    },


    handleEdit(row) {
      this.subject = JSON.parse(JSON.stringify(row));
      this.dialogSubjectVisible = true;
    },

    handletoExam(row) {
      this.examData = {course_id: null, exam_name: null, teacher_id: null, room: null,  start_time: null, end_time: null}
      this.subject = JSON.parse(JSON.stringify(row));
      this.examData.course_id = this.subject.subject_id;
      this.examData.teacher_id = this.subject.teacher_id;
      this.examData.exam_name = this.subject.name;
      this.dialogExamDataVisible = true;
    },

    save() {

      if (this.subject.subject_id) {
        //编辑操作
        axios.post('/edu/subjects/' + this.subject.subject_id, "name=" + this.subject.name + "&teacher_id=" + this.subject.teacher_id + "&_method=put").then((response) => {
          if (response.data.code == 200) {
            this.dialogSubjectVisible = false;
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
            //重新加载信息
            this.loadSubject();
          }
        }).catch((error) => {
          console.log(error);
        });
      } else {
        let param = qs.stringify(this.subject);
        axios.post('/edu/subjects', param).then((response) => {
          if (response.data.code == 200) {
            this.dialogSubjectVisible = false;
            //重新加载信息
            this.$message({
              message: response.data.msg,
              type: 'success'
            });
            this.loadSubject();
          }
        }).catch((error) => {
          console.log(error);
        });
      }
    },

    saveexam() {
      this.examData.start_time = this.time[0];
      this.examData.end_time = this.time[1];
      let param = qs.stringify(this.examData);
      axios.post('/edu/schedules', param).then((response) => {
        if (response.data.code == 200) {
          this.dialogExamDataVisible = false;
          //重新加载信息
          this.$message({
            message: response.data.msg,
            type: 'success'
          });
          this.loadExamData();
        }
        if (response.data.code == 500) {
          this.dialogExamDataVisible = false;
          alert("考试已存在,无法添加")
          //重新信息
          this.loadExamData();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleDelete(id) {
      //发送delete

      axios.delete('/edu/subjects/' + id).then((response) => {
        if (response.data.code == 200) {
          //重新加载信息
          this.$message({
            message: response.data.msg,
            type: 'success'
          });
          this.loadSubject();
        }
        if (response.data.code == 500) {
          //重新加载信息
          alert("存在考试安排,无法删除")
          this.loadSubject();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleSelect() {
      axios.get('/edu/subjects/' + this.findid)
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },
  },

  created() {
    this.loadSubject();
    this.teacherName = sessionStorage.getItem("teacherName")
  }
}
</script>