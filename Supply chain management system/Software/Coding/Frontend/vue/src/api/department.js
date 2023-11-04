import request from '../utils/request';

/**
 * 获取基本信息
 */
function viewData(id) {
    return request({
        url: `/restful/commonUse/view`,
        method: 'post',
        data: data
    });
};



export default {
    read_datas,
    create_data,
    update_data,
    delete_data
}
