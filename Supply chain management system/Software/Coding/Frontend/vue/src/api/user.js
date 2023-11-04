import request from '../utils/request';




function userLogin(data) {
    return request({
        url: `/restful/user/login`,
        method: 'post',
        data: data
    });
};
function userRegister(data) {
    return request({
        url: `/restful/user/register`,
        method: 'post',
        data: data
    });
};





export default {
    userRegister,
    userLogin
}
