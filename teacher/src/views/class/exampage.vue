<template>
  <div class="box">

    <el-button type="primary" style="margin-bottom: 10px;" @click="handleAdd">添加</el-button>
    <el-input v-model="findid" v-on:input="handleSelect" placeholder="输入课程号进行搜索" maxlength="10" clearable
              style="padding-bottom: 20px"/>
    {{ findid }}
    <el-table :data="tableData" v-loading="loading" border style="width: 100%">

      <el-table-column label="题目编号" align="center">
        <template #default="scope">
          {{ scope.row.question_id }}
        </template>
      </el-table-column>

      <el-table-column label="课程号" align="center">
        <template #default="scope">
          {{ scope.row.subject_id }}
        </template>
      </el-table-column>

      <el-table-column label="题目类型" align="center">
        <template #default="scope">
          <template v-if="scope.row.type==0">选择题</template>
          <template v-else-if="scope.row.type==1">判断题</template>
          <template v-else-if="scope.row.type==2">填空题</template>
          <template v-else>其他题</template>
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

      <el-table-column label="选项" align="center">
        <template #default="scope">
          <template v-for="option1 in scope.row.option">
            <el-radio v-model="scope.row.answer" disabled :label="option1.value">{{ option1.value }}</el-radio>
          </template>
        </template>
      </el-table-column>

      <el-table-column label="参考答案" align="center">
        <template #default="scope">
          {{ scope.row.answer }}
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
          <el-button size="small" type="danger" @click="handleDelete(scope.row.question_id)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>
  </div>
  <el-dialog v-model="dialogFormVisible" title="保存题库">
    <el-form :model="questionData">
      {{ questionData }}
      <el-form-item label="课程名">

        <el-select v-model="questionData.subject_id" placeholder="请选择学科">
          <el-option
              v-for="item in subjectList"
              :key="item.subject_id"
              :label="item.subject_id+':'+item.name"
              :value="item.subject_id"
          />
        </el-select>
      </el-form-item>


      <el-form-item label="题目类型">
        <el-select v-model="questionData.type" placeholder="Select">
          <el-option
              v-for="item in typeList"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <div v-if="questionData.type==0">
        <el-form-item label="　　">
          <el-input v-model="optionItem" style="width: 60px" size="small" placeholder="选项"/>
          <el-button size="small" style="margin: 0px 20px;" v-on:click="handleSaveOption">保存</el-button>
        </el-form-item>

        <el-form-item label="选项">
          <el-radio-group v-model="questionData.answer">
            <el-radio v-for="item in questionData.option" :label="item.value">{{ item.value }}</el-radio>
          </el-radio-group>
        </el-form-item>
      </div>
      <el-form-item label="试题">
        <el-input v-model="questionData.title" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="分数">
        <el-input v-model="questionData.score" autocomplete="off"/>
      </el-form-item>

      <el-form-item label="参考答案">
        <el-input type="textarea" v-model="questionData.answer" autocomplete="off"/>
      </el-form-item>


    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关闭</el-button>
        <el-button type="primary" @click="save">保存</el-button>
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
      dialogFormVisible: false,
      typeList: [{
        label: "选择题",
        value: 0
      }, {
        label: "判断题",
        value: 1
      }, {
        label: "填空题",
        value: 2
      }],
      optionItem: null,
      subjectList: [],
      tableData: [],
      questionData: {question_id: null, title: null, score: null, type: null, answer: null, subject_id: null, option: []}
    }
  },
  methods: {

    //加载学科
    loadSubject() {
      axios.get('/edu/subjects')
          .then((response) => {
            this.subjectList = response.data.data;
          }).catch((error) => {
      });
    },

    loadquestion() {
      axios.get('/edu/questions')
          .then((response) => {
            this.tableData = response.data.data
            this.loading = false;
            //将option属性的值转成json对象
            this.tableData.map((item) => {
              item.option = JSON.parse(item.option);
            });
          }).catch((error) => {
      });
    },

    handleAdd() {
      this.questionData = {question_id: null, title: null, score: null, type: null, answer: null, subject_id: null, option: []};
      this.dialogFormVisible = true;
    },

    handleEdit(row) {
      this.questionData = JSON.parse(JSON.stringify(row));
      this.dialogFormVisible = true;
    },

    //监听点击保存选项
    handleSaveOption() {
      if (this.optionItem) {
        //判断是否存在
        let result = this.questionData.option.find((item) => {
          return item.value == this.optionItem;
        });
        if (result) {
          alert("选项已经存在");
          return;
        }
        this.questionData.option.push({value: this.optionItem});
        this.optionItem = null;
      }
    },

    save() {
      if (this.questionData.question_id) {

        this.questionData.option =JSON.stringify(this.questionData.option);
        console.log(this.questionData.option)
        axios.post('/edu/questions/' + this.questionData.question_id, "title=" + this.questionData.title +
            "&score=" + this.questionData.score + "&type=" + this.questionData.type + "&answer=" + this.questionData.answer +
            "&subject_id=" + this.questionData.subject_id + "&option=" + this.questionData.option + "&question_id=" + this.questionData.question_id+ "&_method=put").then((response) => {
          if (response.data.code == 200) {
            this.dialogFormVisible = false;
            this.loadquestion();
          }
        }).catch((error) => {
          console.log(error);
        });
      } else {
        //深度复制
        this.questionData.question_id=1;
        let param = JSON.parse(JSON.stringify(this.questionData));
        if (param.option && param.option.length > 0) {
          param.option = JSON.stringify(param.option);
        }
        param = qs.stringify(param);
        axios.post('/edu/questions/', param).then((response) => {
          if (response.data.code == 200) {
            this.dialogFormVisible = false;
            this.loadquestion();
          }
        }).catch((error) => {
          console.log(error);
        });
      }
    },
    handleDelete(id) {
      //发送delete
      axios.delete('/edu/questions/' + id).then((response) => {
        if (response.data.code == 200) {
          //重新加载学科信息
          this.loadquestion();
        }
      }).catch((error) => {
        console.log(error);
      });
    },

    handleSelect() {
      axios.get('/edu/questions/'+this.findid)
          .then((response) => {
            this.tableData = response.data.data;
            this.loading = false;
            //将option属性的值转成json对象
            this.tableData.map((item) => {
              item.option = JSON.parse(item.option);
            });
          }).catch((error) => {
      });
    },

  },
  created() {
    this.loadquestion();
    this.loadSubject();
  }
}
</script>