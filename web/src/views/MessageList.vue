<template>
    <div>
        <el-table :data="messages" @row-dblclick="toInfo">
            <el-table-column label="主题" prop="theme"></el-table-column>
            <el-table-column label="留言人" prop="name"></el-table-column>
            <el-table-column label="留言日期" prop="createDateTime"></el-table-column>
            <el-table-column label="回复" prop="replyNum"></el-table-column>
            <el-table-column label="阅读" prop="readNum"></el-table-column>
            <el-table-column label="最后回复" prop="latestDateTime"></el-table-column>
        </el-table>
        <el-button @click="()=>{reply=!reply}">发表</el-button>
        <el-dialog :visible.sync="reply">
            <form-component v-if="reply" :item-id="0" :is-reply="false" @updateItem="updateItem(data)"></form-component>
        </el-dialog>
    </div>
</template>

<script>
    import {axiosMessageList} from '../utils/service'
    import FormComponent from "../components/FormComponent";

    export default {
        name: "MessageList",
        components: {FormComponent},
        data() {
            return {
                messages: [],
                reply: false,
                pageable: {}
            }
        },
        methods: {

            toInfo(row, event) {
                this.$router.push({path: `/info/${row.id}`})
            },
            updateItem(data) {
                this.reply = false
                window.console.log(data)
            }
        },
        created() {
            let self = this
            axiosMessageList({}).then(data => {
                self.$set(self, "messages", data.content)
                self.$set(self, "pageable", data.pageable)

            })
        }
    }
</script>

<style scoped>

</style>