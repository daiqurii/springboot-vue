<template >
    <div style="padding: 10px">
        <!-- 功能区域 -->
        <div style="margin: 10px 0">
            <el-button type="primary" @click="add">新增</el-button>
            <el-button type="primary">导入</el-button>
            <el-button type="primary">导出</el-button>
        </div>
        <!-- 搜索区域 -->
        <div style="margin: 10px 0">
            <el-input
                    v-model="search"
                    placeholder="搜索"
                    style="width: 20%; margin-right: 5px"
                    clearable
            ></el-input>
            <el-button type="primary" @click="load">查询</el-button>
        </div>
        <!-- 表格区域 -->
        <el-table :data="tableData" border stripe style="width: 100%">
            <el-table-column prop="id" label="ID"/>
            <el-table-column prop="username" label="用户名"/>
            <el-table-column prop="nickname" label="昵称"/>
            <el-table-column prop="age" label="年龄"/>
            <el-table-column prop="sex" label="性别"/>
            <el-table-column prop="address" label="地址"/>
            <el-table-column fixed="right" label="Operations">
                <template #default="scope">
                    <el-button type="primary" size="mini" @click="handleEdit(scope.row)"
                    >编辑
                    </el-button
                    >
                    <el-popconfirm
                            width="200px"
                            confirm-button-text="是的"
                            cancel-button-text="不，谢谢"
                            :icon="InfoFilled"
                            icon-color="#626AEF"
                            title="你确定要删除吗?"
                            @confirm="handleClick(scope.row)"
                            @cancel="cancelEvent"
                    >
                        <template #reference>
                            <el-button type="primary" size="mini"  style="background-color:red">删除
                            </el-button>
                        </template>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <div class="example-pagination-block" style="margin-top: 5px;">
            <el-pagination
                    v-model:current-page="currentPage"
                    v-model:page-size="pageSize"
                    :page-sizes="[5, 10, 20]"
                    :small="small"
                    :disabled="disabled"
                    :background="background"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total"
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
            />
        </div>
        <!-- 添加用户信息-->
        <el-dialog
                v-model="dialogVisible"
                title="提示"
                width="30%"
        >
            <!--        添加用户信息表单-->
            <el-form :inline="true" :model="form" label-width="120px">
                <el-form-item label="用户名">
                    <el-input v-model="form.username" placeholder="用户名"/>
                </el-form-item>
                <el-form-item label="昵称">
                    <el-input v-model="form.nickname" placeholder="昵称"/>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-input v-model="form.age" placeholder="年龄"/>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.sex" >
                        <el-radio label="男" size="large">男</el-radio>
                        <el-radio label="女" size="large">女</el-radio>
                        <el-radio label="未知" size="large">未知</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="地址">
                    <el-input type="textarea" v-model="form.address" placeholder="地址"/>
                </el-form-item>
            </el-form>
            <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">返回</el-button>
                <el-button type="primary" @click="save">
                  提交
                </el-button>
              </span>
            </template>
        </el-dialog>

    </div>
</template>

<script>
    import request from "../utils/request";
    export default {
        name: "Home",
        components: {},
        data() {
            return {
                url:"http://localhost:9090",
                currentPage:1,
                pageSize:10,
                total:0,
                form: {},
                dialogVisible: false,
                search: "",
                tableData: [],
            };
        },
        created() {
            this.load()
        },
        methods: {
            load(){
                var paramsObj = {
                    pageNum:this.currentPage,
                    pageSize:this.pageSize,
                    search:this.search,
                }
              request.get("/user/getAll",{params:paramsObj}).then(res =>{
                  this.tableData = res.obj.records
                  this.total = res.obj.total
              })
            },
            add() {
                // 清空表单 才能添加新对象
                this.form = {};
                //    打开弹窗 Dialog并添加信息
                this.dialogVisible = true;
            },
            save() {
                if(this.form.id){
                    request.put("/user/put",this.form).then(res => {
                        console.log(res)
                        this.load()
                        if(res.code === 200){
                            this.$message({
                                type:"success",
                                message:"更新成功"
                            })
                        }else{
                            this.$message({
                                type:"error",
                                message:"更新失败"
                            })
                        }

                    })
                }else {
                    request.post("/user/add",this.form).then(res => {
                        console.log(res)
                        this.load()
                        if(res.code === 200){
                            this.$message({
                                type:"success",
                                message:"新增成功"
                            })
                        }else{
                            this.$message({
                                type:"error",
                                message:"新增失败"
                            })
                        }
                    })
                }

                this.dialogVisible = false;
            },
            handleEdit(row){
                this.form = JSON.parse(JSON.stringify(row)) //深拷贝
                this.dialogVisible = true
            },
            handleClick(row) {
                var id = row.id
                request.delete("/user/delete/"+id).then(res =>{
                    this.load()
                    if(res.code === 200){
                        this.$message({
                            type:"success",
                            message:"删除成功"
                        })
                    }else{
                        this.$message({
                            type:"error",
                            message:"删除失败"
                        })
                    }
                })
            },
            handleSizeChange(pageSize) {
                this.pageSize = pageSize
                this.load()
            },
            handleCurrentChange(pageNum) {
                this.currentPage = pageNum
                this.load()
            },
        },
    };
</script>

<style scoped>
</style>