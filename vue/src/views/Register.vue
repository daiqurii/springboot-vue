<template>
    <div style="width: 100%; height: 100vh; background-color: #29468b;overflow: hidden">
        <div style="width: 400px;margin:150px auto">
            <div style="color: #cccccc;font-size: 30px;text-align: center;padding: 30px">管理系统用户注册</div>
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
                <el-button style="width: 100%" type="primary " @click="register">注册新用户</el-button>
            </el-form>
        </div>

    </div>
</template>

<script>
    import request from "../utils/request";
    export default {
        name: "Register",
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
            register(){
                request.post("/login/register", this.form
                        ).then((res) => {
                            if (res.code !== 200) {
                                this.$message({
                                    type: 'error',
                                    message: res.message
                                })
                            }else{
                                this.$message({
                                    type: 'success',
                                    message: '注册成功 ！'
                                })
                                //页面跳转  登录成功之后进入主页
                                this.$router.push("/login")
                            }
                        })
                },
        }
    }

</script>

<style scoped>

</style>