<template>
    <div>
        <!-- 头部 -->
        <Header></Header>
        <!-- 主体 -->
        <div style="display:flex">
            <!-- 侧边栏 -->
            <Aside></Aside>
            <!-- 内容 -->
            <router-view style="flex:1"></router-view>
        </div>
    </div>
</template>

<script>
    import Header from '../components/Header.vue';
    import Aside from '../components/Aside.vue';

    export default {
        name: "Layout",
        components:{
            Header,
            Aside
        }
    }
</script>

<style scoped>

</style>