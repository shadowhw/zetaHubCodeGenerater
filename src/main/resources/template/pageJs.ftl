import request from '../../../../js/request';
import * as BaseConstant from '../../../../js/base-constant'

let servePath = "/" + BaseConstant.SERVER_NAME.admin;

// 分页查询
export function getPageList(data) {
    return request.post(servePath + '/meta/${entityName}/pageList', data);
}

// 新增
export function createData(data) {
    return request.post(servePath + '/meta/${entityName}/create', data);
}

// 修改
export function updateData(data) {
    return request.post(servePath + '/meta/${entityName}/update', data);
}

// 删除
export function deleteData(param) {
    return request.delete(servePath + '/meta/${entityName}/delete', {params: {...param}});
}

// 查询详情
export function detail(param) {
    return request.get(servePath + '/meta/${entityName}/queryDetail', {params: {...param}});
}

