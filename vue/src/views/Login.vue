<template>
    <div style="width: 100%; height: 100vh; background-color: #29468b;overflow: hidden">
        <div style="width: 400px;margin:150px auto">
            <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px">欢迎登录本管理系统</div>
            <el-form
                    label-width:100px
                    :model="form"
            >
                <el-form-item >
                    <el-input  v-model="form.username">
                        <template #prefix>
                            <el-icon><User /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <el-form-item >
                    <el-input type="password" v-model="form.password" show-password="true">
                        <template #prefix>
                            <el-icon><Lock /></el-icon>
                        </template>
                    </el-input>
                </el-form-item>
                <div style="text-align: center">
                    <el-button style="width: 48%" type="primary " @click="login">登录</el-button>
                    <el-button style="width: 48%" type="primary " @click="register">注册</el-button>
                </div>
            </el-form>
        </div>

    </div>
</template>

<script>
    import request from "../utils/request";
    export default {
        name: "Login",
        components:{
        },
        data(){
            return{
                url:"http://localhost:9090/login",
                form:{
                },

            }
        },
        methods:{
            login(){
                request.post("/login/doLogin", this.form
                        ).then((res) => {
                            if (res.code !== 200) {
                                this.$message({
                                    type: 'error',
                                    message: res.message
                                })
                            }else{
                                this.$message({
                                    type: 'success',
                                    message: '登陆成功 ！',
                                    //登录成功之后本地存储token

                                })
                                var token = res.obj;
                                localStorage.setItem("token",token);
                                //页面跳转  登录成功之后进入主页
                                this.$router.push("/")
                            }
                        })
                },
            register(){
                this.$router.push("/register")
            }
        }
    }

</script>

<style scoped>

</style>