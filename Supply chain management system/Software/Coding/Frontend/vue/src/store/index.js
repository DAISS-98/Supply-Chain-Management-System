import { createStore } from 'vuex'

export default createStore({
  state: {
    userFlag:null,//用户标志
    userType:null,//用户标志
    userId:null,
    account:null,
    tagsList: [], // 标签列表
    collapse: false, // 侧边栏是否折叠
    departmentData: [],
    majorData: [],
    teacherData: [],
    studentData: [],
    courseData: [],
    selectCourseData: [],
    user:{}
  },
  mutations: {
    setUser(state,data){
      state.user=data
    },
    setUserFlag(state,data){
      state.userFlag = data
    },
    setAccount(state,data){
      state.account = data
    },
    setUserType(state,data){
      state.userType = data
    },
    setUserId(state,data){
      state.userId = data
    },
    delTagsItem(state, data) {
      state
        .tagsList
        .splice(data.index, 1);
    },
    setTagsItem(state, data) {
      state
        .tagsList
        .push(data)
    },
    clearTags(state) {
      state.tagsList = []
    },
    closeTagsOther(state, data) {
      state.tagsList = data;
    },
    closeCurrentTag(state, data) {
      for (let i = 0, len = state.tagsList.length; i < len; i++) {
        const item = state.tagsList[i];
        if (item.path === data.$route.fullPath) {
          if (i < len - 1) {
            data
              .$router
              .push(state.tagsList[i + 1].path);
          } else if (i > 0) {
            data
              .$router
              .push(state.tagsList[i - 1].path);
          } else {
            data
              .$router
              .push("/");
          }
          state
            .tagsList
            .splice(i, 1);
          break;
        }
      }
    },
    // 侧边栏折叠
    handleCollapse(state, data) {
      state.collapse = data;
    },
    // 存储数据
    handleData(state, data) {


      switch (data[0]) {
        case 'department':
          state.departmentData = data[1];
          break;
        case 'major':
          state.majorData = data[1];
          break;
        case 'teacher':
          state.teacherData = data[1];
          break;
        case 'student':
          state.studentData = data[1];
          break;
        case 'course':
          state.courseData = data[1];
          break;
        case 'selectCourse':
          state.selectCourseData = data[1];
          break;
      }
    },
  },
  actions: {


  },
  getters:{

  },
  modules: {}
})
