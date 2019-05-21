
// const host = "http://localhost:8888"
const host = ""


export function axiosMessageList(params={}) {

    let page, size
    if (params) {
        page = params.page
        size = params.size
    }
    return window.axios.get(`${host}/messages?page=${page?page:0}&size=${size?size:15}`).then(response => {
        let data = response.data
        return data
    })
}

export function axiosManageMessageList(params={}) {

    let page, size
    if (params) {
        page = params.page
        size = params.size
    }
    return window.axios.get(`${host}/admin/messages?page=${page?page:0}&size=${size?size:15}`).then(response => {
        let data = response.data
        return data
    })
}

export function axiosSubmitMessage(messageForm) {
    return window.axios.post(host+"/message/publish", messageForm).then(res=>{
        return res.data
    })
}

export function axiosManageDeleteMessage(id) {
    return window.axios.post(host+"/admin/delete/"+id).then(res=>{
           return  res.data
        }
    )
}
export function axiosManageDisplayMessage(id) {
    return window.axios.post(host+"/admin/display/"+id).then(res=>{
           return  res.data
        }
    )
}



export function axiosMessageReplyList(id){
    return window.axios.get(host+"/messages/"+id+"/replyList").then(res=>res.data)
}