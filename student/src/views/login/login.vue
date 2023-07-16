<template>
  <div class="box">
    <div class="left"></div>
    <div class="right">
      <h4>登录</h4>
      <form action="">
        <input class="acc" v-model="users.student_id" name="user" type="text" placeholder="学号" autocomplete="off"
               required>
        <input class="acc" v-model="users.password" name="pw" type="password" placeholder="密码" autocomplete="off"
               required>
        <input class="submit" type="submit" value="Login" v-on:click.prevent="loging">
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import qs from "qs"
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
export default {
  name: "login",
  data() {
    return {
      users: {
        student_id: null,
        password: null
      },
    }
  },
  methods: {
    loging() {
      axios.post('/edu/student/idLogin', "student_id="+this.users.student_id+"&password="+this.users.password)
          .then((response)=>{
            console.log(response.data.data);
            if(response.data.code == 200){
              sessionStorage.setItem("student_id", this.users.student_id);
              this.$router.push({
                path:"/electives/List",
              });
            }
          })
          .catch((error)=>{
            console.log(error);
            alert("账号或密码错误")
          });
    }
  }
}
</script>

<style scoped>

/* 定义选中文本的样式 */
::selection {
  color: #fff;
  background-color: #83a4d4;
}

/* 定义.box类的样式 */
.box {
  margin: 150px auto;
  display: flex;
  overflow: hidden;
  width: 1000px;
  height: 600px;
  background-color: rgba(255, 255, 255, 60%);
  border-radius: 1.5rem;
  box-shadow: 0 0 1rem 0.2rem rgb(0 0 0 / 10%);
}

/* 定义.box类内.left元素的样式 */
.box .left {
  position: relative;
  width: 35%;
  height: 100%;
  background-color: skyblue;
}

/* 在.left元素之前添加背景图层 */
.box .left::before {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  background-size: cover;
  opacity: 80%;
}

/* 定义.box类内.right元素的样式 */
.box .right {
  display: flex;
  width: 65%;
  flex-direction: column;
  align-items: center;
}

/* 定义.box类内.right元素下的h4元素的样式 */
.box .right h4 {
  color: #83a4d4;
  font-size: 32px;
  margin-top: 5rem;
}

/* 定义.box类内.right元素下的form元素的样式 */
.box .right form {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

/* 定义.box类内.right元素下的form元素内的.acc类的样式 */
.box .right form .acc {
  outline: none;
  width: 80%;
  height: 5rem;
  font-size: 24px;
  margin-top: 3rem;
  padding: 1rem 0 0 1.6rem;
  border: none;
  border-bottom: 1px solid #83a4d4;
  color: #83a4d4;
  background-color: rgba(0, 0, 0, 0);
}

/* 定义.box类内.right元素下的form元素内的.acc类获得焦点时的样式 */
.right form .acc:focus {
  outline: none;
  color: #83a4d4;
  padding: 1rem 0 0 1.6rem;
}

/* 定义.right元素下的.submit、.register、.deleteUn、.edit类的样式 */
.right .submit, .register, .deleteUn, .edit {
  width: 50%;
  height: 4rem;
  color: #f6f6f6;
  background-image: linear-gradient(to bottom, #83a4d4, #b6fbff);
  font-size: 1.4rem;
  border: none;
  border-radius: 0.5rem;
  margin: 2rem 0 0 50%;
  transform: translateX(-50%);
  cursor: pointer;
}

/* 定义.right元素下的.submit类在鼠标悬停时的样式 */
.right .submit:hover {
  box-shadow: 0 0 2rem -0.5rem rgb(0 0 0 / 15%);
}

/* 定义.right元素下的.register类在鼠标悬停时的样式 */
.right .register:hover {
  box-shadow: 0 0 2rem -0.5rem rgb(0 0 0 / 15%);
}

/* 定义.right元素下的.fn类的样式 */
.right .fn {
  display: flex;
  justify-content: space-between;
  margin-top: -5rem;
  width: 70%;
}

/* 定义.right元素下的.fn元素内的a标签的样式 */
.right .fn a {
  font-size: 18px;
  margin-top: 5rem;
  padding: 1rem 2rem;
  color: #666;
  text-decoration: none;
}

/* 定义.right元素下的.fn元素内的a标签在鼠标悬停时的样式 */
.right .fn a:hover {
  color: #83a4d4;
}
</style>