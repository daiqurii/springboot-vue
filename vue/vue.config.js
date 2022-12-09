const {defineConfig} = require('@vue/cli-service')

module.exports = defineConfig({
    transpileDependencies: true,
    lintOnSave: false,//关闭语法检查
    devServer: {
        proxy: {
            '/api': {//请求前缀，有这个前缀(紧跟端口号)的才会走代理
                target: 'http://localhost:9090',//后端的接口
                pathRewrite:{'/api':''},//pathRewrite的key写一个正则的匹配条件,匹配以api开头的路径，把api替换为空字符串
                ws: true,//用于支持websocket
                changeOrigin: true//是否对告诉真实服务器真实的源，false不改变源(真实的源) true(改变源)-----用于控制请求头中的host值
            },
        }
    }

})

