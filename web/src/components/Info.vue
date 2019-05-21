<template>
    <el-row>
    <el-row style="height: 200px">
        <div v-if="message.theme">主题：{{message.theme}}</div>
        <el-col span="4" style="text-align: center;padding-top: 15px">
            <div style="width: 90px;height: 120px;margin: auto">
                <img style="width: 90px;height: 120px;margin: auto" :src="imgSrc" alt="">
            </div>
            <div><span style="margin: auto;padding: auto">{{message.username}}</span></div>
            <div><span style="margin: auto;padding: auto">{{message.ipAddress}}</span></div>
        </el-col>
        <el-col span="20">
            <div v-html="message.content"></div>
        </el-col>
    </el-row>
        <el-row style="text-align: right">
           日期： {{message.createDate}}
        </el-row>
    </el-row>
</template>

<script>
    import Info from '../components/Info'
    export default {
        name: "Info",
        props:{
            message:{
                type:Object,
                require:true
            }
        },
        components:{
            Info
        },
        computed:{
            imgSrc(){
                if(this.message&&this.message.avatar){
                    return `/img/${this.message.avatar}.jpg`
                }else{
                    return `/img/1.jpg`
                }
            }
        }
    }
</script>

<style scoped>

</style>