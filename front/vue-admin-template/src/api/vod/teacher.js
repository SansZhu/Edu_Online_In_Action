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
    //删除讲师
    removeTeacherId(id){
        return request({
            url: `${api_name}/remove/${id}`,
            method: 'delete'
        })
    },
    // 添加讲师
    saveTeacher(teacherObj){
        return request({
            url: `${api_name}/saveTeacher`,
            method: 'post',
            data: teacherObj
        })
    },
    //获取讲师
    getTeacherById(id){
        return request({
            url: `${api_name}/findById/${id}`,
            method: 'get',
        })
    },
    //修改讲师
    updateTeacher(teacherObj){
        return request({
            url: `${api_name}/update`,
            method: 'put',
            data: teacherObj
        })
    },
    batchDelete(idList){
        return request({
            url: `${api_name}/removeBatch`,
            method: 'delete',
            data: idList
        })
    },
     //所有讲师
     list() {
        return request({
          url: `${api_name}/findAll`,
          method: `get`
        })
    }

}

