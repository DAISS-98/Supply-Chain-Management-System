import request from '../utils/request';


//登录注册===========================================
export function login(data) {
    return request({
        url: `/restful/common/login`,
        method: 'post',
        data: data
    });
};


//=========================生产商

export function editData(data) {
    return request({
        url: `/restful/common/insertOrUpdate`,
        method: 'post',
        data: data
    });
};


export function viewData(data) {
    return request({
        url: `/restful/common/searchList`,
        method: 'post',
        data: data
    });
};


export function userRegister(data) {
    return request({
        url: `/restful/common/userRegister`,
        method: 'post',
        data: data
    });
};
export function searchByKey(data) {
    return request({
        url: `/restful/common/searchByKey`,
        method: 'post',
        data: data
    });
};
export function updatePassword(data) {
    return request({
        url: `/restful/common/updatePassword`,
        method: 'post',
        data: data
    });
};


export function insertOrUpdate(data) {
    return request({
        url: `/restful/common/insertOrUpdate`,
        method: 'post',
        data: data
    });
};
export function deleteData(data) {
    return request({
        url: `/restful/common/deleteOne`,
        method: 'post',
        data: data
    });
};


export function getIp(data) {
    return request({
        url: `/restful/common/getIp`,
        method: 'post',
        data: data
    });
};

export function searchAllUser(data) {
    return request({
        url: `/restful/common/searchAllUser`,
        method: 'post',
        data: data
    });
};
export function deleteUser(data) {
    return request({
        url: `/restful/common/deleteUser`,
        method: 'post',
        data: data
    });
};



//=========================管理员END


//
// export default {
//     filedown,
//     queryAll,
//     addUser,
//     deleteUser,
//     viewUser,
//     viewData,
//     addData,
//     editData,
//     deleteData,
//     updatePassword
// }
