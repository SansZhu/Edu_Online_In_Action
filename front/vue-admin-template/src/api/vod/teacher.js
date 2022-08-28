import request from '@/utils/request'

const api_name = '/admin/vod/teacher'
export default {
    //current:当前页；limit：每页限制条数；searchObj：搜索条件
    pageList(current,limit,searchObj){
        return request({
            url: `${api_name}/findQueryPage/${current}/${limit}`,
            method: 'post',
            data: searchObj
        })
    }, 
    removeTeacherId(id){
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    }
}

