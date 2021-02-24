package com.github.akshay.datastore.service;

public class DataStoreHandler {

    private ISetStoreService iSetStoreService;
    private IListStoreService iListStoreService;
    private IPrimitiveStoreService iPrimitiveStoreService;

    public DataStoreHandler(ISetStoreService iSetStoreService, IListStoreService iListStoreService, IPrimitiveStoreService iPrimitiveStoreService) {
        this.iSetStoreService = iSetStoreService;
        this.iListStoreService = iListStoreService;
        this.iPrimitiveStoreService = iPrimitiveStoreService;
    }

    public Object get(String key) {
        return iPrimitiveStoreService.get(key);
    }

    public void set(String key, Object value) {
        iPrimitiveStoreService.set(key,value);
    }

    public void delete(String key) {
        iPrimitiveStoreService.delete(key);
    }

    public void lAdd(String key, Object value, Object... values) {
        iListStoreService.lAdd(key,value,values);
    }

    public void lGet(String key, int index, int... indexes) {
        iListStoreService.lGet(key,index,indexes);
    }

    public void lGetAll(String key) {
        iListStoreService.lGetAll(key);
    }

    public void lDelete(String key, int index, int... indexes) {
        iListStoreService.lDelete(key,index,indexes);
    }

    public void sAdd(String key, Object value, Object... values) {
        iSetStoreService.sAdd(key,value,values);
    }

    public void sGetAll(String key) {
        iSetStoreService.sGetAll(key);
    }

    public void sDel(String key, Object value, Object... values) {
        iSetStoreService.sDel(key,value,values);
    }

    public void sContains(String key, Object value, Object... values) {
        iSetStoreService.sContains(key,value,values);
    }




}
