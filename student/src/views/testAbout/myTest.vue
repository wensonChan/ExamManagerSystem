<template>
  <div class="question">
    <div>
      <span> {{ currentQuestionIndex + 1 }}.</span>
      <span> {{ currentQuestion.title }}</span>
      <span> ({{ currentQuestion.score }}分)</span>
    </div>
    <div>
      <!--单选-->
      <div class="answer" v-if="currentQuestion.type==0">
        <el-radio-group v-model="currentQuestion.answer">
          <el-radio size="large" style="font-size: 20px" v-for="(item,index) in currentQuestion.content"
                    :key="item.value" :label="item.value">
            {{ answersLetter[index] + '. ' + item.value }}
          </el-radio>
        </el-radio-group>
      </div>
      <!--填空-->
      <div class="answer" v-else-if="currentQuestion.type==1">
        <el-input
            v-model="currentQuestion.answer"
            style="width: 95%"
            :rows="4"
            type="textarea"
            placeholder="请在此输入你的答案"
        />
      </div>
      <!--简答-->
      <div class="answer" v-else-if="currentQuestion.type==2">
        <el-input
            v-model="currentQuestion.answer"
            style="width: 95%"
            :rows="4"
            type="textarea"
            placeholder="请在此输入你的答案"
        />
      </div>
    </div>

    <div class="operation-footer">
      <el-button size="small" :disabled="preDisabled" @click="preQuestion">上一题</el-button>
      <el-button size="small" type="primary" :disabled="nextDisable" @click="nextQuestion">下一题</el-button>
      <el-button size="small" type="primary" @click="sumitExam">保存提交</el-button>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import {ElMessage, ElMessageBox} from 'element-plus'
import qs from "qs"

axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "myTest",
  data() {
    return {
      tableData: [],
      couse_id: '',
      student: {
        student_id: sessionStorage.getItem("student_id")
      },

      currentQuestionIndex: 1,
      answersLetter: ["A", "B", "C", "D"],
      questions: [
        {
          type: null,//0选择题，1填空题，2简答题
          title: null,
          content: [{value: '1'}, {value: '2'}, {value: '3'}, {value: '4'}],
          score: null,
          course_id: null,
          answer: '',
        }, {
          type: 1,
          title: '题目填空描述',
          content: [],
          score: 11,
          course_id: null,
          answer: '',
        }
      ]
    }
  },
  computed: {
    currentQuestion() {
      return this.questions[this.currentQuestionIndex]
    },
    preDisabled() {
      return this.currentQuestionIndex == 0
    },
    nextDisable() {
      return this.currentQuestionIndex + 1 == this.questions.length
    },
  },
  mounted() {
    this.currentQuestionIndex = 0
    console.log(this.currentQuestionIndex, this.preDisabled, this.nextDisable)
  },
  methods: {
    //  上一题
    preQuestion() {
      this.save(this.currentQuestion).then(
          (res) => {
            ElMessage.success('保存成功')
            this.currentQuestionIndex--
          }
      ).catch(
          err => {
            console.log(err)
            ElMessage.error(err)
          }
      )

    },
    //  下一题
    nextQuestion() {
      this.save(this.currentQuestion).then((res) => {
        ElMessage.success('保存成功')
        this.currentQuestionIndex++
      }).catch(err => {
        console.log(err)
        ElMessage.error(err)
      })

    },
    //  提交
    sumitExam() {
      console.log('提交')
      ElMessageBox.confirm(
          '是否确认提交试卷。',
          'tips',
          {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning',
          })
          .then(() => {
            this.save(this.currentQuestion).then((res) => {
              ElMessage({
                type: 'success',
                message: '已提交',
              })
              this.$router.push('/testAbout/mytestList')
            }).catch(err => {
              console.log(err)
              ElMessage.error(err)
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: '取消',
            })
          })
    },
    //保存
    async save(question) {
      console.log(question)
      if (!question.answer || question.answer.length == 0) throw ("请选择答案")
      let params = {
        "student_id": this.student.student_id,
        "course_id": this.couse_id,
        "title_id": question.title_id,
        "stu_answer": question.answer
      }
      axios.put('/edu/examAnswers?' + qs.stringify(params))
          .then((response) => {
            if (response.data.code == 200) {
            }
          }).catch((error) => {
      });
    }
  },
  created() {
    if (this.$route.params.id) this.couse_id = this.$route.params.id
    console.log(this.couse_id)
    axios.get('/edu/questions/' + this.student.student_id)
        .then((response) => {
          if (response.data.code == 200) {
            //获取考试信息
            this.questions = response.data.data;
            this.questions.forEach(item => {
              item.content = JSON.parse(item.content)
              console.log(item.content)
            })
            console.log(this.questions)
          }
        }).catch((error) => {
      alert("用户" + this.student.student_id + "现在没有进行中的考试")
    });
  }
}

</script>

<style scoped>
.question {
  font-size: 25px;
  padding: 10px 0px 10px 10px;
}

.answer {
  margin-top: 20px;
}

.operation-footer {
  margin-top: 10px;
  display: flex;
  justify-content: center;
}
</style>