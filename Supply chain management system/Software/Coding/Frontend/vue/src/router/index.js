import { createRouter, createWebHashHistory } from "vue-router";
import Home from "@views/Home.vue";
// import PlanterHome from "@views/home/PlanterHome.vue";
import ProcessHome from "@views/pagehome/ProcessHome.vue";
import SaleHome from "@views/pagehome/SaleHome.vue";
import UserHome from "@views/home/UserHome.vue";
import ProduceHome from "@views/pagehome/ProduceHome.vue";
import TransportHome from "@views/pagehome/TransportHome.vue";
import StoreHome from "@views/pagehome/StoreHome.vue";
import SuperviseHome from "@views/home/SuperviseHome.vue";
import AdminHome from "@views/pagehome/AdminHome.vue";

const routes = [
    { path: '/', redirect: '/dashboard'+localStorage.getItem("userType") }, // 重定向
    {
        path: "/produceHome", name: "ProduceHome", component: ProduceHome,
        children: [
            {
                path: "/dashboard1", name: "dashboard1", meta: { title: '生产商首页' },
                component: () => import("@views/Dashboard.vue")
            },
            {
                path: "/produceAdd", name: "produceAdd", meta: { title: '生产商信息添加' },
                component: () => import("@views/pagedir/produceAdd.vue")
            },
            {
                path: "/produceQ", name: "produceQ", meta: { title: '生产商二维码标签' },
                component: () => import("@views/pagedir/produceQ.vue")
            },
            {
                path: "/breedEdit", name: "breedEdit", meta: { title: '种养殖信息编辑' },
                component: () => import("@views/pages/breedEdit.vue")
            },
            {
                path: "/breedList", name: "breedList", meta: { title: '种养殖信息' },
                component: () => import("@views/pages/breedList.vue")
            },
            {
                path: "/breedUserInfo", name: "breedUserInfo", meta: { title: '个人信息查看' },
                component: () => import("@views/pages/breedUserInfo.vue")
            },
            {
                path: "/passwordChange1", name: "passwordChange1", meta: { title: '密码修改页面' },
                component: () => import("@views/pagedir/passwordChange.vue")
            },

            {
                path: "/tabs", name: "tabs", meta: { title: 'tab标签' },
                component: () => import("@views/Tabs.vue")
            },
            {
                path: "/permission", name: "permission", meta: { title: '权限管理', permission: true },
                component: () => import("@views/Permission.vue")
            },
            {
                path: '/404', name: '404', meta: { title: '找不到页面' },
                component: () => import('@views/404.vue')
            },
            {
                path: '/403', name: '403', meta: { title: '没有权限' },
                component: () => import('@views/403.vue')
            },
            {
                path: "/test", name: "test", meta: { title: '测试页面' },
                component: () => import("@views/Test.vue")
            }
        ]
    }
    ,
    {
        path: "/processHome", name: "ProcessHome", component: ProcessHome,
        children: [
            {
                path: "/dashboard2", name: "dashboard2", meta: { title: '加工商首页' },
                component: () => import("@views/Dashboard.vue")
            },
            {
                path: "/processList", name: "processList", meta: { title: '加工商信息' },
                component: () => import("@views/pagedir/processList.vue")
            },
            {
                path: "/processQ", name: "processQ", meta: { title: '加工商二维码标签' },
                component: () => import("@views/pagedir/processQ.vue")
            },
            {
                path: "/passwordChange2", name: "passwordChange2", meta: { title: '密码修改页面' },
                component: () => import("@views/pagedir/passwordChange.vue")
            },
            {
                path: "/tabs", name: "tabs", meta: { title: 'tab标签' },
                component: () => import("@views/Tabs.vue")
            },
            {
                path: "/permission", name: "permission", meta: { title: '权限管理', permission: true },
                component: () => import("@views/Permission.vue")
            },
            {
                path: '/404', name: '404', meta: { title: '找不到页面' },
                component: () => import('@views/404.vue')
            },
            {
                path: '/403', name: '403', meta: { title: '没有权限' },
                component: () => import('@views/403.vue')
            },
            {
                path: "/test", name: "test", meta: { title: '测试页面' },
                component: () => import("@views/Test.vue")
            }
        ]
    },
    {
        path: "/transportHome", name: "TransportHome", component: TransportHome,
        children: [
            {
                path: "/dashboard3", name: "dashboard3", meta: { title: '运输商首页' },
                component: () => import("@views/Dashboard.vue")
            },
            {
                path: "/transportList", name: "transportList", meta: { title: '运输商' },
                component: () => import("@views/pagedir/transportList.vue")
            },
            {
                path: "/transportQ", name: "transportQ", meta: { title: '运输商二维码标签' },
                component: () => import("@views/pagedir/transportQ.vue")
            },
            {
                path: "/transportUserInfo", name: "transportUserInfo", meta: { title: '运输商信息' },
                component: () => import("@views/pages/transportUserInfo.vue")
            },
            {
                path: "/passwordChange3", name: "passwordChange3", meta: { title: '密码修改页面' },
                component: () => import("@views/pagedir/passwordChange.vue")
            },
            {
                path: "/tabs", name: "tabs", meta: { title: 'tab标签' },
                component: () => import("@views/Tabs.vue")
            },
            {
                path: "/permission", name: "permission", meta: { title: '权限管理', permission: true },
                component: () => import("@views/Permission.vue")
            },
            {
                path: '/404', name: '404', meta: { title: '找不到页面' },
                component: () => import('@views/404.vue')
            },
            {
                path: '/403', name: '403', meta: { title: '没有权限' },
                component: () => import('@views/403.vue')
            },
            {
                path: "/test", name: "test", meta: { title: '测试页面' },
                component: () => import("@views/Test.vue")
            }
        ]
    },
    {
        path: "/storeHome", name: "StoreHome", component: StoreHome,
        children: [
            {
                path: "/dashboard4", name: "dashboard4", meta: { title: '仓储首页' },
                component: () => import("@views/Dashboard.vue")
            },
            {
                path: "/storeList", name: "storeList", meta: { title: '仓储商' },
                component: () => import("@views/pagedir/storeList.vue")
            },
            {
                path: "/transportUserInfo", name: "transportUserInfo", meta: { title: '仓储商信息' },
                component: () => import("@views/pages/transportUserInfo.vue")
            },
            {
                path: "/storeQ", name: "storeQ", meta: { title: '仓储商二维码标签' },
                component: () => import("@views/pagedir/storeQ.vue")
            },
            {
                path: "/passwordChange4", name: "passwordChange4", meta: { title: '密码修改页面' },
                component: () => import("@views/pagedir/passwordChange.vue")
            },
            {
                path: "/tabs", name: "tabs", meta: { title: 'tab标签' },
                component: () => import("@views/Tabs.vue")
            },
            {
                path: "/permission", name: "permission", meta: { title: '权限管理', permission: true },
                component: () => import("@views/Permission.vue")
            },
            {
                path: '/404', name: '404', meta: { title: '找不到页面' },
                component: () => import('@views/404.vue')
            },
            {
                path: '/403', name: '403', meta: { title: '没有权限' },
                component: () => import('@views/403.vue')
            },
            {
                path: "/test", name: "test", meta: { title: '测试页面' },
                component: () => import("@views/Test.vue")
            }
        ]
    },

    {
        path: "/saleHome", name: "saleHome", component: SaleHome,
        children: [
            {
                path: "/dashboard5", name: "dashboard5", meta: { title: '销售商首页' },
                component: () => import("@views/Dashboard.vue")
            },
            {
                path: "/saleList", name: "saleList", meta: { title: '销售商页面' },
                component: () => import("@views/pagedir/saleList.vue")
            },
            {
                path: "/saleQ", name: "saleQ", meta: { title: '销售商二维码标签' },
                component: () => import("@views/pagedir/saleQ.vue")
            },
            {
                path: "/saleUserInfo", name: "saleUserInfo", meta: { title: '个人信息页面' },
                component: () => import("@views/pages/saleUserInfo.vue")
            },
            {
                path: "/passwordChange5", name: "passwordChange5", meta: { title: '密码修改页面' },
                component: () => import("@views/pagedir/passwordChange.vue")
            }
            ,
            {
                path: "/tabs", name: "tabs", meta: { title: 'tab标签' },
                component: () => import("@views/Tabs.vue")
            },
            {
                path: "/permission", name: "permission", meta: { title: '权限管理', permission: true },
                component: () => import("@views/Permission.vue")
            },
            {
                path: '/404', name: '404', meta: { title: '找不到页面' },
                component: () => import('@views/404.vue')
            },
            {
                path: '/403', name: '403', meta: { title: '没有权限' },
                component: () => import('@views/403.vue')
            },
            {
                path: "/test", name: "test", meta: { title: '测试页面' },
                component: () => import("@views/Test.vue")
            }
        ]
    },
    {
        path: "/adminHome", name: "adminHome", component: AdminHome,
        children: [
            {
                path: "/dashboard6", name: "dashboard6", meta: { title: '管理员首页' },
                component: () => import("@views/Dashboard.vue")
            },
            {
                path: "/planterUser", name: "planterUser", meta: { title: '种植商' },
                component: () => import("@views/tables/planterUser.vue")
            },
            {
                path: "/userManage", name: "userManage", meta: { title: '用户管理' },
                component: () => import("@views/pagedir/userManage.vue")
            },
            {
                path: "/adminQ", name: "adminQ", meta: { title: '产品溯源' },
                component: () => import("@views/pagedir/adminQ.vue")
            },
            {
                path: "/passwordChange5", name: "passwordChange5", meta: { title: '密码修改页面' },
                component: () => import("@views/tables/passwordChange.vue")
            }
            ,
            {
                path: "/tabs", name: "tabs", meta: { title: 'tab标签' },
                component: () => import("@views/Tabs.vue")
            },
            {
                path: "/permission", name: "permission", meta: { title: '权限管理', permission: true },
                component: () => import("@views/Permission.vue")
            },
            {
                path: '/404', name: '404', meta: { title: '找不到页面' },
                component: () => import('@views/404.vue')
            },
            {
                path: '/403', name: '403', meta: { title: '没有权限' },
                component: () => import('@views/403.vue')
            },
            {
                path: "/test", name: "test", meta: { title: '测试页面' },
                component: () => import("@views/Test.vue")
            },
            {
                path: "/tracePage", name: "tracePage", meta: { title: '产品溯源' },
                component: () => import("@views/pages/tracePage.vue")
            },
            {
                path: "/qrcode", name: "qrcode", meta: { title: '二维码生成' },
                component: () => import("@views/pages/qrcode.vue")
            }
        ]
    },
    {
        path: "/login", name: "Login", meta: { title: '登录' },
        component: () => import("@views/Login.vue")
    },
    {
        path: "/file", name: "file", meta: { title: '登录' },
        component: () => import("@views/tables/template_file.vue")
    },
    {
        path: "/register", name: "Register", meta: { title: '注册' },
        component: () => import("@views/Register.vue")
    }
    ,
    {
        path: "/trace", name: "trace", meta: { title: '产品溯源' },
        component: () => import("@views/trace.vue")
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

// router.beforeEach((to, from, next) => {
//     document.title = `${to.meta.title} | ${TITLE}`;
//     const role = localStorage.getItem('ms_username');
//     const token = localStorage.getItem('token');
    // if(token != undefined && token != null && token == "true"){
    //     next();
    // }
    // console.log(token)
    // if(token){
    //     next('/login')
    // }
    // next('/login');
    // next();
    // if (!role && to.path !== '/login') {
    //     next('/login');
    // } else if (to.meta.permission) {
    //     // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
    //     role === 'admin'
    //         ? next()
    //         : next('/403');
    // } else {
    //     next();
    // }
// });

export default router;
