const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  devServer: {
    port: 8080,
    proxy: {
      '/edu': {
        target: 'http://localhost/',  //需要跨域的url
        ws: true,                                  //代理webSocket
        changeOrigin: true,                        //允许跨域
        pathRewrite: {
          '^/edu': ''                         //重写路径
        }
      }
    }
  }
})