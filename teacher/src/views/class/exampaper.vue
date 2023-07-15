<template>
  <div class="box">

    <el-button type="primary" style="margin-bottom: 10px;" @click="handleAdd">添加</el-button>

    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="试卷编号" align="center">
        <template #default="scope">
          {{ scope.row.course_id }}
        </template>
      </el-table-column>

      <el-table-column label="题目编号" align="center">
        <template #default="scope">
          {{ scope.row.title_id }}
        </template>
      </el-table-column>

      <el-table-column label="试题" align="center">
        <template #default="scope">
          {{ scope.row.title }}
        </template>
      </el-table-column>

      <el-table-column label="分值" align="center">
        <template #default="scope">
          {{ scope.row.score }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="small" type="danger" @click="handleDelete(scope.row.course_id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogExampaperVisible" title="试卷制作">
    <el-form :model="exampaper">

      <el-form-item label="试卷编号">
        <el-select v-model="exampaper.course_id" placeholder="请选择试卷">
          <el-option
              v-for="item in subjectList"
              :key="item.subject_id"
              :label="item.subject_id+':'+item.name"
              :value="item.subject_id"
              v-on:click="loadquestion"
          />
        </el-select>
      </el-form-item>

      <el-form-item label="题目编号">
        <el-select v-model="exampaper.title_id" placeholder="请选择题目">
          <el-option
              v-for="item in questionList"
              :key="item.question_id"
              :label="item.question_id+':'+item.title"
              :value="item.question_id"
          />
        </el-select>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogExampaperVisible = false">取消</el-button>
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
      loading: true,
      dialogExampaperVisible: false,
      subjectList: [],
      questionList: [],
      tableData: [],
      exampaper: {
        course_id: null,
        title_id: null,
      }
    }
  },
  methods: {
    loadSubject() {
      axios.get('/edu/subjects')
          .then((response) => {
            this.subjectList = response.data.data;
          }).catch((error) => {
      });
    },

    loadquestion() {
      axios.get('/edu/questions/' + this.exampaper.course_id)
          .then((response) => {
            this.questionList = response.data.data
            //将options属性的值转成json对象
            this.questionList.map((item) => {
              item.options = JSON.parse(item.options);
            });
          }).catch((error) => {
      });
    },

    loadExampaper() {
      axios.get('/edu/exampapers')
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },

    handleAdd() {
      this.exampaper = {course_id: null, title_id: null,};
      this.dialogExampaperVisible = true;
    },

    handleEdit(row) {
      this.exampaper = JSON.parse(JSON.stringify(row));
      this.dialogExampaperVisible = true;
    },

    save() {
      let param = qs.stringify(this.exampaper);
      axios.post('/edu/exampapers/', param).then((response) => {
        if (response.data.code == 200) {
          this.dialogExampaperVisible = false;
          // 重新加载信息
          this.$message({
            message: response.data.msg,
            type: 'success'
          });
          this.loadExampaper();
        }
        if(response.data.code == 401){
          this.$message({
          message: response.data.msg,
          type: 'warning'
        });}

      })
          .catch((error) => {
            console.log(error);
          });

    },

    handleDelete(id) {
      //发送delete
      axios.delete('/edu/exampapers/' + id).then((response) => {
        if (response.data.code == 200) {
          //重新加载信息
          this.$message({
            message: response.data.msg,
            type: 'success'
          });
          this.loadExampaper();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleSelect() {
      axios.get('/edu/exampaper/' + this.findid)
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },
  },

  created() {
    this.loadExampaper();
    this.loadSubject();
    // this.loadquestion();
  }
}
</script>