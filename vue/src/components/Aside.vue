<template>
    <div>
        <el-menu
                style="width: 200px;min-height: calc(100vh - 50px)"
                default-active="user"
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                :default-openeds="['1']"
        >
            <el-sub-menu index="system">
                <template #title>
                    系统管理
                </template>
                <el-menu-item index="user" :route="{path:'/'}">用户管理</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="data" :route="{path:'/'}">数据管理</el-menu-item>

        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "Aside",
        components: {},
    };
</script>

<style scoped></style>