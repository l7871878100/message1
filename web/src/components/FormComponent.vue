<template>
    <div style="max-width: 800px">
        <el-form ref="form" :model="form" label-position="right">
            <el-form-item v-if="!isReply">
                <el-row>
                    <label for="content" style="float: left">主题:</label>
                </el-row>
                <el-row>
                    <el-input v-model="form.theme"></el-input>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-row>
                    <label for="content" style="float: left">姓名:</label>
                </el-row>
                <el-row>
                    <el-input v-model="form.username"></el-input>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-row>
                    <label for="content" style="float: left">性别:</label>
                </el-row>
                <el-row>
                    <el-col style="padding-left: 35px">
                        <el-radio style="float:left" v-model="form.gender" :label="true">男</el-radio>
                        <el-radio style="float: left" v-model="form.gender" :label="false">女</el-radio>
                        <div style="float: left;width: 60px;margin-right: 25px">
                            <el-select v-model="form.avatar">
                                <el-option value="1">1</el-option>
                                <el-option value="2">2</el-option>
                                <el-option value="3">3</el-option>
                                <el-option value="4">4</el-option>
                                <el-option value="5">5</el-option>
                                <el-option value="6">6</el-option>
                                <el-option value="7">7</el-option>
                                <el-option value="8">8</el-option>
                                <el-option value="9">9</el-option>
                                <el-option value="10">10</el-option>
                                <el-option value="11">12</el-option>
                                <el-option value="12">12</el-option>
                                <el-option value="13">13</el-option>
                                <el-option value="14">14</el-option>
                            </el-select>
                        </div>
                        <img style="width: 90px;height: 120px;float: left"
                             :src="imgSrc">
                    </el-col>

                </el-row>
            </el-form-item>
            <el-form-item>
                <el-row>
                    <label for="content" style="float: left">内容:</label>
                </el-row>
                <el-row>
                    <div style="float: left">

                        <editor id="content" style="float: left" @change="updateContent"></editor>
                    </div>
                </el-row>
            </el-form-item>
            <el-form-item>
                <el-button @click="clickSubmit">发表</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
    import Editor from './editor'
    import {axiosSubmitMessage} from '../utils/service'

    export default {
        name: "FormComponent",
        components: {
            Editor
        },
        props: {
            itemId: {
                type: Number,
                default: () => 0
            },
            isReply: {
                type: Boolean,
                default: () => false
            }
        },
        computed: {
            imgSrc() {
                if (this.form && this.form.avatar) {
                    return `/img/${this.form.avatar}.jpg`
                } else {
                    return `/img/1.jpg`
                }
            }
        },
        data() {
            return {
                messages: [],
                form: {
                    username: null,
                    avatar: 1,
                    theme: null,
                    gender: true,
                    content: null,
                    replyId: this.itemId
                }
            }
        },
        methods: {
            updateContent(data) {
                let self = this
                self.$set(self.form, 'content', data)
            },
            clickSubmit() {
                let self = this
                axiosSubmitMessage(this.form).then(data => {
                    if (data) {
                        self.$set(self, "form", {
                            username: self.form.username,
                            avatar: self.form.avatar,
                            gender: self.form.gender,
                            content: null,
                            theme: null,
                            replyId: this.form.replyId
                        })
                        this.$emit("updateItem",data)

                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>