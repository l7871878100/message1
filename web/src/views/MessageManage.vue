<template>
    <div>
        <el-table :data="messages" @row-dblclick="dblClick">
            <el-table-column label="主题" prop="theme"></el-table-column>
            <el-table-column label="留言人" prop="username"></el-table-column>
            <el-table-column label="显示">
                <template slot-scope="scope">
                    <span>{{scope.row.display?"是":"否"}}</span>
                </template>
            </el-table-column>
            <el-table-column label="删除">
                <template slot-scope="scope">
                    <span>{{scope.row.deleted?"是":"否"}}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="deleteItem(scope.row.id)">删除</el-button>
                    <el-button @click="displayItem(scope.row.id)">显示</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :visible.sync="show">
            <info :message="message"></info>
            <el-button @click="deleteItem(message.id)">删除</el-button>
            <el-button @click="displayItem(message.id)">显示</el-button>
        </el-dialog>
    </div>
</template>

<script>
    import {axiosManageMessageList, axiosManageDeleteMessage, axiosManageDisplayMessage} from '../utils/service'
    import Info from "../components/Info";

    export default {
        name: "MessageManage",
        components: {Info},
        data() {
            return {
                "pageable":{},
                messages: [{
                    "id": 1,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 1,
                    "theme": "hello",
                    "username": "admin",
                    "content": "12345",
                    "display": true,
                    "ipAddress": "127.0.0.1",
                    "replyId": null,
                    "deleted": false,
                    "readNum": 1,
                    "replyNum": 3,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": "张三"
                }, {
                    "id": 3,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 3,
                    "theme": "",
                    "username": "111",
                    "content": "erwr",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 2,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 4,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 4,
                    "theme": "",
                    "username": "111",
                    "content": "werwer",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 3,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-19",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 5,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 5,
                    "theme": "",
                    "username": "111",
                    "content": "werwer",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 6,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 6,
                    "theme": "",
                    "username": "1212",
                    "content": "wertww",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-19",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 7,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 7,
                    "theme": "",
                    "username": "12121",
                    "content": "<p>121333</p>",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 10,
                    "messageQuote": null,
                    "gender": false,
                    "avatar": 10,
                    "theme": "",
                    "username": "",
                    "content": "<p>小心有鬼</p>",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": null,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 13,
                    "messageQuote": null,
                    "gender": true,
                    "avatar": 13,
                    "theme": "",
                    "username": "admin",
                    "content": "<p>154878</p>",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 14,
                    "messageQuote": null,
                    "gender": true,
                    "avatar": 14,
                    "theme": "",
                    "username": "admin",
                    "content": "<p>154878222223</p>",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }, {
                    "id": 15,
                    "messageQuote": null,
                    "gender": true,
                    "avatar": 1,
                    "theme": "",
                    "username": "admin",
                    "content": "<p>1548782222232323</p>",
                    "display": true,
                    "ipAddress": "0:0:0:0:0:0:0:1",
                    "replyId": 1,
                    "deleted": false,
                    "readNum": 0,
                    "replyNum": 0,
                    "latestDate": "2019-05-20",
                    "createDate": "2019-05-20",
                    "latestName": ""
                }],
                show: false,
                message: {}

            }
        },
        computed: {},
        methods: {
            deleteItem(id) {
                let self = this
                axiosManageDeleteMessage(id).then(data => {
                    if (data) {
                        self.$set(self, 'show', false)

                        self.$set(self, "messages", self.messages.map(item => {
                            if (item.id == id) {
                                item.deleted = true
                            }
                            return item
                        }))

                    }
                })
            },
            displayItem(id) {
                let self = this
                axiosManageDisplayMessage(id).then(data => {
                    if (data) {
                        self.$set(self, 'show', false)
                        self.$set(self, "messages", self.messages.map(item => {
                            if (item.id == id) {
                                item.display = true
                            }
                            return item
                        }))

                    }
                })
            },
            dblClick(row, event) {
                let self = this
                self.$set(self, "message", row)
                self.$set(self, 'show', true)
            }
        },
        mounted() {
            let self = this
            axiosManageMessageList().then(data => {
                self.$set(self, "messages", data.content)
                self.$set(self, "pageable", data.pageable)
            })
        }
    }
</script>

<style scoped>

</style>