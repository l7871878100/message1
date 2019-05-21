<template>
    <div>
        <template v-for="(message,index) in messages">
            <info :key="index" :message="message"></info>
        </template>
        <el-row>
            <el-button @click="reply=!reply">回复</el-button>
        </el-row>
        <el-dialog :visible.sync="reply">

            <form-component :itemId="itemId" :is-reply="true"></form-component>
        </el-dialog>
    </div>
</template>

<script>
    import Info from '../components/Info'
    import FormComponent from "../components/FormComponent";
    import {axiosMessageReplyList} from "../utils/service";

    export default {
        name: "ManageInfo",
        components: {
            FormComponent,
            Info
        },
        data() {
            return {
                "reply": false,
                "pageable": {},
                itemId: 1,
                messages: [{
                    "id": 1,
                    "messageQuote": null,
                    "theme": "hello",
                    "username": "admin",
                    "content": "12345",
                    "display": true,
                    "ipAddress": "127.0.0.1",
                    "replyId": null,
                    "deleted": false,
                    "readNum": 1,
                    "replyNum": 0,
                    "latestDate": "08:00:00",
                    "latestName": "张三"
                }, {
                    "id": 2,
                    "messageQuote": null,
                    "theme": null,
                    "username": "张三",
                    "content": "654321",
                    "display": true,
                    "ipAddress": "127.0.0.1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "08:00:00",
                    "latestName": null
                }, {
                    "id": 5,
                    "messageQuote": null,
                    "theme": "",
                    "username": "111",
                    "content": "werwer",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "08:00:00",
                    "latestName": ""
                }, {
                    "id": 6,
                    "messageQuote": null,
                    "theme": "",
                    "username": "1212",
                    "content": "wertww",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "08:00:00",
                    "latestName": ""
                }, {
                    "id": 7,
                    "messageQuote": null,
                    "theme": "",
                    "username": "12121",
                    "content": "<p>121333</p>",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "08:00:00",
                    "latestName": ""
                }]
            }
        },
        created() {
            let self = this
            self.$set(self, 'itemId', self.$route.params.id)
            axiosMessageReplyList(self.itemId).then(data => {
                self.$set(self, "messages", data.content)
                self.$set(self, "pageable", data.pageable)
            })
        }
    }
</script>

<style scoped>

</style>