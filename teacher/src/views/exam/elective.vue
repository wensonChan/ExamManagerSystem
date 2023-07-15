<template>
  <div class="box">

    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="学号" align="center">
        <template #default="scope">
          {{ scope.row.student_id }}
        </template>
      </el-table-column>

      <el-table-column label="分数" align="center">
        <template #default="scope">
          {{ scope.row.score }}
        </template>
      </el-table-column>

      <el-table-column label="科目" align="center">
        <template #default="scope">
          {{ scope.row.subject_id }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <el-dialog v-model="dialogElectiveVisible" title="成绩录入">
    <el-form :model="elective">
      <el-form-item label="学号">
        <el-text v-model="elective.student_id" v-text="elective.student_id" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="分数">
        <el-input v-model="elective.score" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="科目">
        <el-text v-model="elective.subject_id" v-text="elective.subject_id" autocomplete="off"/>
      </el-form-item>


    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogElectiveVisible = false">取消</el-button>
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
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'

export default {
  name: "List",
  data() {
    return {
      findid: null,
      loading: true,
      dialogElectiveVisible: false,
      tableData: [],
      elective: {
        student_id: null,
        score: null,
        subject_id: null
      }
    }
  },
  methods: {
    loadElective() {
      axios.get('/edu/scores')
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },

    handleEdit(row) {
      this.elective = JSON.parse(JSON.stringify(row));
      this.dialogElectiveVisible = true;
    },

    save() {
      //编辑操作
      axios.post('/edu/scores/' + this.elective.student_id, "student_id=" + this.elective.student_id + "&score=" + this.elective.score +"&subject_id=" + this.elective.subject_id +"&_method=put").then((response) => {
        if (response.data.code == 200) {
          this.dialogElectiveVisible = false;
          //重新加载信息
          this.$message({
            message: response.data.msg,
            type: 'success'
          });
          this.loadElective();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleSelect() {
      axios.get('/edu/scores/' + this.findid)
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
          }).catch((error) => {
      });
    },
  },

  created() {
    this.loadElective();
  }
}
</script>