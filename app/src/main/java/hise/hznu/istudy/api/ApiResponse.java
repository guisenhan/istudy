package hise.hznu.istudy.api;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacky Yu on 2016/7/8.
 *所有网络请求返回Json对象，必须符合以下模板（与服务端约定），否则出错
 {
    "id": "26",
    "result": {
        "msg": "success"
        "code": 1000,
        "data": {
                 "sign": "AX5234Bl243Bwde ",
                 "ip": "201.123.202.34",
                 "isFirst": "1",
                 "lastLoginTime": " 1280977330000 "
                 "info": {
                            "nickName": "啊啦",
                            "gender": "1",
                            "iconUrl": "https://xxx.xxx.com/xxx.jpg",
                            "city": "HZ",
                            "birthady": "1988-02-28",
                            "constelation": "双鱼",
                            …..
                 ｝,
        },
    }
 }
 或者
 {
     "id": "26",
     "result": {
         "msg": "success"
         "code": 1000,
         "data": {
                     "ip": "201.123.202.34",
                     "isFirst": "1",
                     "lastLoginTime": " 1280977330000 "
                     "arrayListName": [
                                         {
                                         "nickName": "啊啦1",
                                         "gender": "1",
                                         "iconUrl": "https://xxx.xxx.com/xxx1.jpg",
                                         ｝,
                                         {
                                         "nickName": "啊啦2",
                                         "gender": "2",
                                         "iconUrl": "https://xxx.xxx.com/xxx2.jpg",
                                         ｝,
                                         {
                                         "nickName": "啊啦3",
                                         "gender": "3",
                                         "iconUrl": "https://xxx.xxx.com/xxx3.jpg",
                                         ｝,
                                         ............
                                    ],
                },
        }
     }
 }
 或者
 {
     "id": "26",
     "result": {
     "msg": "success"
     "code": 1000,
     "data": {
             "arrayListName": [
                                 {
                                 "nickName": "啊啦1",
                                 "gender": "1",
                                 "iconUrl": "https://xxx.xxx.com/xxx1.jpg",
                                 ｝,
                                 {
                                 "nickName": "啊啦2",
                                 "gender": "2",
                                 "iconUrl": "https://xxx.xxx.com/xxx2.jpg",
                                 ｝,
                                 {
                                 "nickName": "啊啦3",
                                 "gender": "3",
                                 "iconUrl": "https://xxx.xxx.com/xxx3.jpg",
                                 ｝,
                                 ............
             ],
     },
 }

 或者分页返回结构体
 {
    "id": "26",
    "result": {
                "msg": "success"
                "code": 1000,
                "data": {
                         totalPage:3;
                         currentPage:1;
                         "messageList": [
                                         {
                                         "nickName": "啊啦1",
                                         "gender": "1",
                                         "iconUrl": "https://xxx.xxx.com/xxx1.jpg",
                                         ｝,
                                         {
                                         "nickName": "啊啦2",
                                         "gender": "2",
                                         "iconUrl": "https://xxx.xxx.com/xxx2.jpg",
                                         ｝,
                                         ............
                         ],
                },
    }
 }
 或者分页返回结构体
 {
     "id": "26",
     "result": {
     "msg": "success"
     "code": 1000,
     "data": {
            info:{
                 totalPage:3;
                 currentPage:1;
                 "messageList": [
                                {
                                    "nickName": "啊啦1",
                                    "gender": "1",
                                    "iconUrl": "https://xxx.xxx.com/xxx1.jpg",
                                ｝,
                                {
                                "nickName": "啊啦2",
                                "gender": "2",
                                "iconUrl": "https://xxx.xxx.com/xxx2.jpg",
                                ｝,
                                ............
                 ],
            }
            "ip": "201.123.202.34",
            "isFirst": "1",
            "lastLoginTime": " 1280977330000 "
     },
 }


 失败：
 {
    "id": "26",
    "result": {
        "msg": "false",
        "code": 1001
    }
 }
 *
 */

public class ApiResponse {

    private JSONObject jsonObject;//返回的所有JSONObject对象
//    private String id;            //某一个请求返回的id
//    private JSONObject result;    //某一个请求返回的JSONObject类型的result对象
//    private String msg;           //result对象中的msg消息字段
//    private long code;            //result对象中的code字段
//    private JSONObject data;      //result对象中的JSONObject对象，注意：不是JSONArray对象

    private String authtoken;
    private String hasnext;
    private JSONObject info;
    private String isfirst;
    private JSONArray items;
    private int pagecount;
    private int recordcount;
    private int retcode;

    public ApiResponse(JSONObject jsonObject) {

        this.jsonObject = jsonObject;
        this.authtoken = jsonObject.getString("authtoken");
        this.retcode =jsonObject.getIntValue("retcode");
        this.hasnext = jsonObject.getString("hasnext");

        this.info = jsonObject.getJSONObject("info");
        this.isfirst =jsonObject.getString("isfirst");
        this.items =jsonObject.getJSONArray("items");
        this.pagecount=jsonObject.getIntValue("pagecount");
        this.recordcount = jsonObject.getIntValue("recordcount");

    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getHasnext() {
        return hasnext;
    }

    public void setHasnext(String hasnext) {
        this.hasnext = hasnext;
    }

    public <T> T getInfo(Class <T> cls) {
        return JSON.parseObject(info.toJSONString(), cls);
    }

    public JSONObject getInfo() {
        return info;
    }

    public void setInfo(JSONObject info) {
        this.info = info;
    }

    public String getIsfirst() {
        return isfirst;
    }

    public void setIsfirst(String isfirst) {
        this.isfirst = isfirst;
    }

    public JSONArray getItems() {
        return items;
    }

    public void setItems(JSONArray items) {
        this.items = items;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getRecordcount() {
        return recordcount;
    }

    public void setRecordcount(int recordcount) {
        this.recordcount = recordcount;
    }

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }



    public <T>List<T> getListData(Class<T> cls){
        List<T> dataList = new ArrayList<T>();
        if(items.size()>0){
            for(int i=0 ; i <items.size();i++)
                dataList.add(JSON.parseObject(items.getJSONObject(i).toJSONString(),cls));
        }
        return dataList;
    }
    //    public JSONObject getJsonObject() {
//        return jsonObject;
//    }
//
//    public JSONObject getResult() {
//        return result;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public long getCode() {
//        return code;
//    }
//
//    public void setCode(long code) {
//        this.code = code;
//    }
//
//    public JSONObject getData() {
//        return data;
//    }
//
//    public void setData(JSONObject data) {
//        this.data = data;
//    }
//
//    public <T> T getData(Class<T> cls) {
//        return JSON.parseObject(data.toJSONString(), cls);
//    }

    /**
     * 获取data中的对象
     * @param entity 对象名
     * @return
     */
//    public <T> T getData(String entity, Class<T> cls) {
//        String[] ss = entity.split("\\.");
//        JSONObject json = items;
//        for (String s : ss) {
//            json = json.getJSONObject(s);
//        }
//        return JSON.parseObject(json.toJSONString(), cls);
////        return JSON.toJavaObject(json, cls);
//    }

    /**
     * 获取data中的分页对象
     * @param entity 对象名
     * @return
     */
//    public <T> ApiPageData<T> getPageData(String entity, Class<T> cls) {
//        String[] ss = entity.split("\\.");
//        JSONObject json = data;
//        for (String s : ss) {
//            json = json.getJSONObject(s);
//        }
//        return parsePageResponse(json, cls);
//
//    }

    /**
     * 获取data中的分页对象
     * @param cls 类
     * @return
     */
//    public <T> ApiPageData<T> getPageData(Class<T> cls) {
//        return parsePageResponse(data, cls);
//    }

    private <T> ApiPageData<T> parsePageResponse(JSONObject json, Class<T> cls) {
        ApiPageData<T> apiPageData = new ApiPageData<T>();
        apiPageData.setCurrentPage(json.getIntValue("currentPage"));
        apiPageData.setTotalPage(json.getIntValue("totalPage"));
        List<T> list = getApiPageDataArray(json, cls);
        apiPageData.setDataList(list);
        return apiPageData;
    }

    /**
     * 获取data中的对象
     * @param entity 对象名，以 . 分割开
     * @return
     */
//    public <T> T getEntity(String entity, Class<T> cls) {
//        String[] ss = entity.split("\\.");
//        JSONObject json = data;
//        for (String s : ss) {
//            json = json.getJSONObject(s);
//        }
//        return JSON.toJavaObject(json, cls);
//    }
    /**
     * 获取data中的list对象
     * @return
     */
//    public <T> List<T> getDataArray(Class<T> cls) {
//        return getDataArray("messageList",cls);
//    }

    /**
     * 获取data中的list对象
     * @param entity list对象名，以 . 分割开
     * @return
     */
//    public <T> List<T> getDataArray(String entity, Class<T> cls) {
//        List<T> list = new ArrayList<T>();
//        String[] ss = entity.split("\\.");
//        JSONObject json = data;
//        for (int i = 0; i < ss.length - 1; i++) {
//            json = json.getJSONObject(ss[i]);
//        }
//        JSONArray array = json.getJSONArray(ss[ss.length - 1]);
//        for (int i = 0; i < array.size(); i++) {
//            list.add(array.getObject(i, cls));
//        }
//        return list;
//    }

    /**
     * 获取ApiPageData中分页list对象
     * @param json 分页ApiPageData对象
     * @return
     */
    public <T> List<T> getApiPageDataArray(JSONObject json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        JSONArray array = json.getJSONArray("messageList");
        for (int i = 0; i < array.size(); i++) {
            list.add(array.getObject(i, cls));
        }
        return list;
    }


}

