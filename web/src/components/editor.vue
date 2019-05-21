<template>
    <div>
        <div ref="editor" style="text-align:left"></div>
    </div>
</template>

<script>
    import E from 'wangeditor'

    export default {
        name: 'editor',
        data() {
            return {
                messageTheme: "",
                editor: null,
                menu:[
                    'head',  // 标题
                    'bold',  // 粗体
                    'fontSize',  // 字号
                    'fontName',  // 字体
                    'italic',  // 斜体
                    // 'underline',  // 下划线
                    // 'strikeThrough',  // 删除线
                    'foreColor',  // 文字颜色
                    'backColor',  // 背景颜色
                    'link',  // 插入链接
                    // 'list',  // 列表
                    'justify',  // 对齐方式
                    'quote',  // 引用
                    'emoticon',  // 表情
                    'image',  // 插入图片
                    // 'table',  // 表格
                    // 'video',  // 插入视频
                    // 'code',  // 插入代码
                    // 'undo',  // 撤销
                    // 'redo'  // 重复
                ]
            }
        },
        methods: {
            // getContent: function () {
            //     alert(this.editorContent)
            // }
        },
        mounted() {
            var self = this
            self.editor = new E(this.$refs.editor)
            self.editor.customConfig.uploadImgShowBase64 = true
            self.editor.customConfig.menus=self.menu
            self.editor.customConfig.onchange = function (html) {

                self.$emit("change", html)
            }
            self.editor.create()
        }
    }
</script>

<style scoped>
</style>