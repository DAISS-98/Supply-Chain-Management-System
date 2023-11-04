import axios from 'axios';

import qs from 'qs'
// let qs = require('qs')
const request = axios.create({
    baseURL: BASE_URL,
    timeout: TIMEOUT,
    // header:{"Access-Control-Allow-Origin": "*"}
});


// service.interceptors.request.use(
//     config => {
//         return config;
//     },
//     error => {
//         console.log(error);
//         return Promise.reject();
//     }
// );

// service.interceptors.response.use(
    // response => {
    //     if (response.status === 200) {
    //         return response.data;
    //     } else {
    //         Promise.reject();
    //     }
    // },
    // error => {
    //     console.log(error);
    //     return Promise.reject();
    // }
// );
//region 遮罩层
/**
 *  拦截异步请求响应 Loading加载中。。。
 */
//定义loading变量



const commonConfig = {
    headers: {
        'Accept': 'application/json;charset=utf-8',
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
        'X-Requested-With': 'XMLHttpRequest'    //用于sso判断请求类型
    },
    withCredentials: true
};


/**
 * Axios get 请求
 * @param url
 * @param data
 * @param config 自定义的 config，请求时会将其与 commonConfig 差异合并。config 优先级更高
 * @returns {Promise}
 */
// let cancel;
export function get(url, data = {}, config, showLoading = true) {
    // url = baseUrl + url;
    let requestConfig = Object.assign({}, commonConfig, config, {params: data});
    //CancelToken 用于取消请求
    requestConfig.cancelToken = new axios.CancelToken(function executor(c) {
        requestConfig.cancel = c;
    });
    return new Promise((resolve, reject) => {
        // if (showLoading) {
        //     showFullScreenLoading();
        // }
        axios.get(url, requestConfig).then(response => {
            // if (showLoading) {
            //     tryHideFullScreenLoading();
            // }
            if (response) {
                resolve(response);
            }
        }).catch(error => {
            if (showLoading) {
                tryHideFullScreenLoading();
            }
            reject(error);
        });
    })
}






/**
 * Axios post 请求
 * @param url
 * @param data
 * @param config 自定义的 config，请求时会将其与 commonConfig 差异合并。config 优先级更高
 * @param upload true:文件上传，默认 false
 * @returns {Promise}
 */
export function post(url, data = {}, config, upload = false, showLoading = true) {
    //
    // url = ba seUrl + url;
    let requestConfig = Object.assign({}, commonConfig, config);
    //CancelToken 用于取消请求
    requestConfig.cancelToken = new axios.CancelToken(function executor(c) {
        requestConfig.cancel = c;
    });
    return new Promise((resolve, reject) => {
        // if(showLoading){
        //     showFullScreenLoading();
        // }
        axios.post(url, upload ? data : qs.stringify(data), requestConfig).then(response => {
            console.log(response)
            // if(showLoading){
            //     tryHideFullScreenLoading();
            // }
            if (response) {
                resolve(response);
            }
        }).catch(error => {
            console.log(error)
            // if(showLoading){
            //     tryHideFullScreenLoading();
            // }
            // reject(error);
        })
    })
}



/**
 * axios响应拦截器，根据返回值处理单点登录相关业务
 */
// axios.interceptors.response.use(function (response) {
//     if (response.status == 200) {
//         const responseData = response.data;
//         if (responseData.type == 'application/octet-stream' ||responseData.type == 'application/mspdf') {
//
//             //文件流
//             return response;
//         }
//         let headers = response.headers;
//         //后端输出文件流时会重置此属性
//         if (headers['content-type'] === 'application/octet-stream') {
//             return response;
//         }
// return response;
// if (responseData.status == 200) {
//     let res = responseData.data || {};
//     if(res.result){
//         return res;
//     }else if("validator" == res.code){
//         return res;
//     }else{
//         if(res.msg){
//             alertMsg(res.msg);
//         }
//         return Promise.reject(res);
//     }
// } else if (responseData.status == 302) {
//如果服务端响应302则表示用户可能未登录，需要请求sso登录页询问是否登录。
// authenticate302Request(responseData.location, response.config.url, Promise.resolve, Promise.reject);
//     vueMain.$router.push({path: '/login'});
// } else if (responseData.status == 301) {
//301:用户未登录，跳转到登录页面。301 状态码只会在 302 之后发起的请求时才会返回
// alertMsg('需要重新登录', 'info')
// if (top && top.location) {
//     //子页面过期，需要让父页面跳转到登录页面
//     top.location.href = responseData.location;
// } else {
//     location.href = responseData.location;
// }
//     vueMain.$router.push({path: '/login'});
// } else if (responseData.status == 403) {
//     //无权限
//     alertMsg(vueMain.$t('txt.no_access_right'));
//     return Promise.reject(vueMain.$t('txt.no_access_right'));
// } else if (responseData.status == 500) {
//     //500
//     let msg = responseData.msg ?  responseData.msg : vueMain.$t('txt.server_error');
//     alertMsg(msg);
//     return Promise.reject(msg);
// }else if(responseData.status == 401){
//     //验证未通过，需要重新登录
//     vueMain.$router.push({path: '/login'});
// }
//     }
// }, function (error) {
//     console.log("axios.interceptors.response 中出错 ：" + JSON.stringify(error));
//     return Promise.reject(error);
// });






export default request;
