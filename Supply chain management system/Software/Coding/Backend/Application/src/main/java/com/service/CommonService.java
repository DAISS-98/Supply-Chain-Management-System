package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.Fabric;
import com.common.StringUtil;
import com.dao.*;
import com.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.entity.Process;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import static com.alibaba.fastjson.JSON.parseObject;


@Service
public class CommonService {
    @Resource
    Fabric fabric;
    @Resource
    private ProduceMapper aaaMapper;
    @Resource
    private ProcessMapper bbbMapper;
    @Resource
    private TransportMapper cccMapper;
    @Resource
    private StoreMapper dddMapper;
    @Resource
    private SaleMapper eeeMapper;
    @Resource
    private ProduceUserMapper userAMapper;
    @Resource
    private ProcessUserMapper userBMapper;
    @Resource
    private TransportUserMapper userCMapper;
    @Resource
    private StoreUserMapper userDMapper;
    @Resource
    private SaleUserMapper userEMapper;


    /**
     * 根据id检索一览
     */
    public List searchList(Long id){
        String type = StringUtil.getType(id);
        Object object = getCurrentObject(id);
        Produce produce = null;
        Process process = null;
        Transport transport = null;
        Store store = null;
        Sale sale = null;
        List list  = new ArrayList();
        switch (type){
            case "1" :  {
                produce = (Produce)object;
                List<Produce> list1 = aaaMapper.selectByAll(produce);
                list = list1;
            };break;
            case "2" :  {
                process = (Process)object;
                List<Process> list2 = bbbMapper.selectByAll(process);
                setDataValue(list2,"2");
                list = list2;
            };break;
            case "3" :  {
                transport = (Transport)object;
                List<Transport> list3= cccMapper.selectByAll(transport);
                setDataValue(list3,"3");
                list = list3;
            };break;
            case "4" :  {
                store = (Store)object;
                List<Store> list4= dddMapper.selectByAll(store);
                setDataValue(list4,"4");
                list = list4;
            };break;
            case "5" :  {
                sale = (Sale)object;
                List<Sale> list5= eeeMapper.selectByAll(sale);
                setDataValue(list5,"5");
                list = list5;
            };break;
            case "6" :  {
                sale = (Sale)object;
                List<Sale> list5= eeeMapper.selectByAll(new Sale());
                setDataValue(list5,"6");
                list = list5;
            };break;
            default: break;
        }
        return list;
    }

    /**
     * 溯源
     */
    public List searchByKey(String type,String key){
        Object object = getCurrentObject(type,key);
        Produce produce = null;
        Process process = null;
        Transport transport = null;
        Store store = null;
        Sale sale = null;
        List list  = new ArrayList();
        switch (type){
            case "1" :  {
                produce = (Produce)object;
                List<Produce> list1 = aaaMapper.selectByAll(produce);
                list = list1;
            };break;
            case "2" :  {
                process = (Process)object;
                List<Process> list2 = bbbMapper.selectByAll(process);
                setDataValue(list2,"2");
                list = list2;
            };break;
            case "3" :  {
                transport = (Transport)object;
                List<Transport> list3= cccMapper.selectByAll(transport);
                setDataValue(list3,"3");
                list = list3;
            };break;
            case "4" :  {
                store = (Store)object;
                List<Store> list4= dddMapper.selectByAll(store);
                setDataValue(list4,"4");
                list = list4;
            };break;
            case "5" :  {
                sale = (Sale)object;
                List<Sale> list5= eeeMapper.selectByAll(sale);
                setDataValue(list5,"5");
                list = list5;
            };break;

            default: break;
        }
        return list;
    }

    /**
     * 根据key检索一条数据
     */
    public Object searchByKey(String key){
        //依次按照key检索
        Produce produce = aaaMapper.selectByPrimaryKey(key);
        Process process = bbbMapper.selectByPrimaryKey(key);
        Transport transport = cccMapper.selectByPrimaryKey(key);
        Store store = dddMapper.selectByPrimaryKey(key);
        Sale sale = eeeMapper.selectByPrimaryKey(key);
        ArrayList list = new ArrayList();
        list.add(produce);
        list.add(process);
        list.add(transport);
        list.add(store);
        list.add(sale);
        //依次添加
        produce.setList(list);
    return produce;
    }


    //插入或者更新数据
    public void insertOrUpdate(Long userId,String key,String personName,Object obj){
        String type = StringUtil.getType(userId);
        Produce produce = null;
        Process process = null;
        Transport transport = null;
        Store store = null;
        Sale sale = null;
        if(type .equals("1")){
            produce = mapToObject(obj,Produce.class);
            //查找
            Produce a1 = null;
            if(key != null){
                a1 =  aaaMapper.selectByPrimaryKey(key);
            }
            //找到就更新，找不到添加

            produce.setProduceUserId(userId);
            produce.setProduceSupplierName(personName);
            if(a1 == null){
                produce.setProduceKey(getKey());
                aaaMapper.insert(produce);
            }else {
                produce.setProduceKey(key);
                aaaMapper.updateByPrimaryKey(produce);
            }

        }else if(type .equals("2")){
            process = mapToObject(obj,Process.class);
            Process b1 = bbbMapper.selectByPrimaryKey(key);
            process.setProcessUserId(userId);
            process.setProcessName(personName);
            if(b1 == null){
                process.setProcessKey(key);
                bbbMapper.insert(process);
            }else {
                process.setProcessKey(key);
                bbbMapper.updateByPrimaryKey(process);
            }

        }else if(type .equals("3")){
            transport = mapToObject(obj,Transport.class);
            Transport c1 = cccMapper.selectByPrimaryKey(key);
            transport.setTransportUserId(userId);
            transport.setTransportName(personName);
            if(c1 == null){
                transport.setTransportKey(key);
                cccMapper.insert(transport);
            }else {
                transport.setTransportKey(key);
                cccMapper.updateByPrimaryKey(transport);
            }
        }else if(type .equals("4")){
            store = mapToObject(obj,Store.class);
            Store d1 = dddMapper.selectByPrimaryKey(key);
            store.setStoreUserId(userId);
            store.setStoreName(personName);
            if(d1 == null){
                store.setStoreKey(key);
                dddMapper.insert(store);
            }else {
                store.setStoreKey(key);
                dddMapper.updateByPrimaryKey(store);
            }
        }else if(type .equals("5")){
            sale = mapToObject(obj,Sale.class);
            Sale c1 = eeeMapper.selectByPrimaryKey(key);
            sale.setUserId(userId);
            sale.setSaleName(personName);
            if(c1 == null){
                sale.setSaleKey(key);
                eeeMapper.insert(sale);
            }else {
                sale.setSaleKey(key);
                eeeMapper.updateByPrimaryKey(sale);
            }
        }
        try {
            setFabricData(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //删除
    public void deleteOne(String key){
        aaaMapper.deleteByPrimaryKey(key);
        bbbMapper.deleteByPrimaryKey(key);
        cccMapper.deleteByPrimaryKey(key);
        dddMapper.deleteByPrimaryKey(key);
        eeeMapper.deleteByPrimaryKey(key);
    }


    //根据账户查找用户信息
    public Object findUserInfoByAccount(String account,String userType){
        //依次查找用户的信息  查找直接返回
        if(userType .equals("1")){
            List<ProduceUser> produceUsers = userAMapper.selectByAccount(account);
            if(produceUsers != null && produceUsers.size() >0 ){
                ProduceUser produceUser = produceUsers.get(0);
                return produceUser;
            }
        }else if(userType .equals("2")){
            List<ProcessUser> processUsers = userBMapper.selectByAccount(account);
            if(processUsers != null && processUsers.size()>0){
                ProcessUser processUser = processUsers.get(0);
                return processUser;
            }
        }else if(userType .equals("3")){
            List<TransportUser> transportUsers = userCMapper.selectByAccount(account);
            if(transportUsers != null && transportUsers.size()>0){
                TransportUser transportUser = transportUsers.get(0);
                return transportUser;
            }
        }else if(userType .equals("4")){
            List<StoreUser> storeUsers = userDMapper.selectByAccount(account);
            if(storeUsers != null && storeUsers.size()>0){
                StoreUser storeUser = storeUsers.get(0);
                return storeUser;
            }
        }else if(userType .equals("5")){
            List<SaleUser> saleUsers = userEMapper.selectByAccount(account);
            if(saleUsers != null && saleUsers.size()>0){
                SaleUser saleUser = saleUsers.get(0);
                return saleUser;
            }
        }
        return null;
    }



    //根据userId查找用户的信息
    public Object findUserInfoByUserId(Long id){
        String userType = StringUtil.getType(id);
        if(userType .equals("1")){
            ProduceUser produceUser = userAMapper.selectByPrimaryKey(id);
            return produceUser;
        }else if(userType .equals("2")){
            ProcessUser processUser = userBMapper.selectByPrimaryKey(id);
            return processUser;
        }else if(userType .equals("3")){
            TransportUser transportUser = userCMapper.selectByPrimaryKey(id);
            return transportUser;
        }else if(userType .equals("4")){

        }
        return null;
    }


    //用户信息一览
    public List findByUserType(String userType){
        List list = new ArrayList();
        if(userType .equals("1")){
            ProduceUser produceUser = new ProduceUser();
            list = userAMapper.selectByAll(produceUser);
        }else if(userType .equals("2")){
            ProcessUser processUser = new ProcessUser();
            list = userBMapper.selectByAll(processUser);
        }else if(userType .equals("3")){
            TransportUser transportUser = new TransportUser();
            list = userCMapper.selectByAll(transportUser);
        }else if(userType .equals("4")){
            StoreUser storeUser = new StoreUser();
            list = userDMapper.selectByAll(storeUser);
        }else if(userType .equals("5")){
            SaleUser saleUser = new SaleUser();
            list = userEMapper.selectByAll(saleUser);
        }
        return list;
    }


    //根据用户类型修改用户信息
    public void updateUserInfoByUserId(Object object , Long userId){
        String userType = StringUtil.getType(userId);
        if(userType .equals("1") ){
            ProduceUser produceUser = (ProduceUser) object;
            produceUser.setUserId(userId);
            userAMapper.updateByPrimaryKey(produceUser);
        }else if(userType .equals("2")){
            ProcessUser processUser = (ProcessUser) object;
            userBMapper.updateByPrimaryKey(processUser);
        }else if(userType .equals("3")){
            TransportUser transportUser = (TransportUser) object;
            userCMapper.updateByPrimaryKey(transportUser);
        }else if(userType .equals("4")){
            StoreUser storeUser = (StoreUser) object;
            userDMapper.updateByPrimaryKey(storeUser);
        }else if(userType .equals("5")){
            SaleUser saleUser = (SaleUser) object;
            userEMapper.updateByPrimaryKey(saleUser);
        }

    }

    //新增注册
    public Boolean addUser(Object object,String userType){
        Long id = StringUtil.getId(userType);
        if(userType .equals("1")){
            ProduceUser produceUser = mapToObject(object, ProduceUser.class);
            produceUser.setUserId(id);
            Object userInfoByAccount = findUserInfoByAccount(produceUser.getAccount(), userType);
            if(userInfoByAccount != null){
                return  false;
            }else {
                userAMapper.insert(produceUser);
            }
        }else if(userType .equals("2")){
            ProcessUser processUser = mapToObject(object, ProcessUser.class);
            processUser.setUserId(id);
            Object userInfoByAccount = findUserInfoByAccount(processUser.getAccount(), userType);
            if(userInfoByAccount != null){
                return  false;
            }else {
                userBMapper.insert(processUser);
            }
        }else if(userType .equals("3")){
            TransportUser transportUser = mapToObject(object, TransportUser.class);
            transportUser.setUserId(id);
            Object userInfoByAccount = findUserInfoByAccount(transportUser.getAccount(), userType);
            if(userInfoByAccount != null){
                return  false;
            }else {
                userCMapper.insert(transportUser);
            }
        }else if(userType .equals("4")){
            StoreUser storeUser = mapToObject(object, StoreUser.class);
            storeUser.setUserId(id);
            Object userInfoByAccount = findUserInfoByAccount(storeUser.getAccount(), userType);
            if(userInfoByAccount != null){
                return  false;
            }else {
                userDMapper.insert(storeUser);
            }
        }else if(userType .equals("5")){
            SaleUser saleUser = mapToObject(object, SaleUser.class);
            saleUser.setUserId(id);
            Object userInfoByAccount = findUserInfoByAccount(saleUser.getAccount(), userType);
            if(userInfoByAccount != null){
                return  false;
            }else {
                userEMapper.insert(saleUser);
            }
        }
        return true;
    }

    //删除用户
    public void deleteUserByUserId(Long userId){
        String userType = StringUtil.getType(userId);
        if(userType .equals("1")){
            userAMapper.deleteByPrimaryKey(userId);
        }else if(userType .equals("2")){
            userBMapper.deleteByPrimaryKey(userId);
        }else if(userType .equals("3")){
            userCMapper.deleteByPrimaryKey(userId);
        }else if(userType .equals("4")){
            userDMapper.deleteByPrimaryKey(userId);
        }else if(userType .equals("5")){
            userEMapper.deleteByPrimaryKey(userId);
        }

    }


    public List<User> findAllUser(){
        List<ProduceUser> a = userAMapper.selectByAll(new ProduceUser());
        List<ProcessUser> b = userBMapper.selectByAll(new ProcessUser());
        List<TransportUser> c = userCMapper.selectByAll(new TransportUser());
        List<StoreUser> d = userDMapper.selectByAll(new StoreUser());
        List<SaleUser> e = userEMapper.selectByAll(new SaleUser());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(a);
        arrayList.addAll(b);
        arrayList.addAll(c);
        arrayList.addAll(d);
        arrayList.addAll(e);
        return arrayList;
    }

    //修改用户密码
    public void updatePasswordByUserId(Long userId,String password){
        String userType = StringUtil.getType(userId);
        if(userType .equals("1")){
            userAMapper.updatePasswordByUserId(password,userId);
        }else if(userType .equals("2")){
            userBMapper.updatePasswordByUserId(password,userId);
        }else if(userType .equals("3")){
            userCMapper.updatePasswordByUserId(password,userId);
        }else if(userType .equals("4")){
            userDMapper.updatePasswordByUserId(password,userId);
        }else if(userType .equals("5")){
            userEMapper.updatePasswordByUserId(password,userId);
        }
    }

    //设置id,并返回对应类的对象
    public Object getCurrentObject(Long userId){
        String userType = StringUtil.getType(userId);
        if(userType .equals("1")){
            Produce produce = new Produce();
            produce.setProduceUserId(userId);
            return produce;
        }else if(userType .equals("2")){
            Process process = new Process();
            process.setProcessUserId(userId);
            return process;
        }else if(userType .equals("3")){
            Transport transport = new Transport();
            transport.setTransportUserId(userId);
            return transport;
        }else if(userType .equals("4")){
            Store store = new Store();
            store.setStoreUserId(userId);
            return store;
        }else if(userType .equals("5")){
            Sale sale = new Sale();
            sale.setUserId(userId);
            return sale;
        }
        return null;
    }
    public Object getCurrentObject(String  userType,String key){
        if(userType .equals("1")){
            Produce produce = new Produce();
            produce.setProduceKey(key);
            return produce;
        }else if(userType .equals("2")){
            Process process = new Process();
            process.setProcessKey(key);
            return process;
        }else if(userType .equals("3")){
            Transport transport = new Transport();
            transport.setTransportKey(key);
            return transport;
        }else if(userType .equals("4")){
            Store store = new Store();
            store.setStoreKey(key);
            return store;
        }else if(userType .equals("5")){
            Sale sale = new Sale();
            sale.setSaleKey(key);
            return sale;
        }
        return null;
    }


    public String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    public void setFabricData(String key) throws Exception {
        Produce produce = aaaMapper.selectByPrimaryKey(key);
        Process process = bbbMapper.selectByPrimaryKey(key);
        Transport transport = cccMapper.selectByPrimaryKey(key);
        Store store = dddMapper.selectByPrimaryKey(key);
        Sale sale = eeeMapper.selectByPrimaryKey(key);
        FabricData fabricData = new FabricData();
        fabricData.setOnlyKey(key);
        fabricData.setProduce(produce);
        fabricData.setProcess(process);
        fabricData.setTransport(transport);
        fabricData.setStore(store);
        fabricData.setSale(sale);
        fabric.sdk(fabricData);
    }




    public  <T> T mapToObject(Object object, Class<T> clazz) {
        return parseObject(JSONObject.toJSONString(object), clazz);
    }

    public String  getKey(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public void setDataValue(List list ,String userType){
        if(userType.equals("2")){
            for(int i = 0;i<list.size();i++){
                Process process = (Process)list.get(i);
                Produce produce = aaaMapper.selectByPrimaryKey(process.getSuperKey());
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(produce);
                process.setList(arrayList);
            }
        }else if(userType.equals("3")){
            for(int i = 0;i<list.size();i++){
                Transport transport = (Transport)list.get(i);
                Produce produce = aaaMapper.selectByPrimaryKey(transport.getSuperKey());
                Process process = bbbMapper.selectByPrimaryKey(transport.getSuperKey());
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(produce);
                arrayList.add(process);
                transport.setList(arrayList);
            }
        }else if(userType.equals("4")){
            for(int i = 0;i<list.size();i++){
                Store store = (Store)list.get(i);
                Produce produce = aaaMapper.selectByPrimaryKey(store.getSuperKey());
                Process process = bbbMapper.selectByPrimaryKey(store.getSuperKey());
                Transport transport = cccMapper.selectByPrimaryKey(store.getSuperKey());
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(produce);
                arrayList.add(process);
                arrayList.add(transport);
                store.setList(arrayList);
            }
        }else if(userType.equals("5") || userType.equals("6")){
            for(int i = 0;i<list.size();i++){
                Sale sale = (Sale)list.get(i);
                Produce produce = aaaMapper.selectByPrimaryKey(sale.getSuperKey());
                Process process = bbbMapper.selectByPrimaryKey(sale.getSuperKey());
                Transport transport = cccMapper.selectByPrimaryKey(sale.getSuperKey());
                Store store = dddMapper.selectByPrimaryKey(sale.getSuperKey());
                ArrayList arrayList = new ArrayList<>();
                arrayList.add(produce);
                arrayList.add(process);
                arrayList.add(transport);
                arrayList.add(store);
                sale.setList(arrayList);
            }
        }


    }



}
