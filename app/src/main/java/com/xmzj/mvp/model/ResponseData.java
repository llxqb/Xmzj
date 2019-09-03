package com.xmzj.mvp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class ResponseData {

    private static final Gson sGson = new GsonBuilder().create();
    public Integer resultCode;
    public String errorMsg;
    public String result;
    public String verifyCode;
    public Object parsedData;
    public JSONObject mJsonObject;

    public ResponseData() {
        resultCode = 110;
        errorMsg = "信息获取失败";
    }

    public ResponseData(JSONObject jsonObject) {
        resultCode = jsonObject.optInt("code");
        errorMsg = jsonObject.optString("desc");
        result = jsonObject.optString("data");
        verifyCode = jsonObject.optString("verifyCode");
    }

    //list
    public ResponseData(JSONObject jsonObject, boolean isList){
        resultCode = jsonObject.optInt("code");
        errorMsg = jsonObject.optString("desc");
        mJsonObject = jsonObject;
    }

//    public ResponseData(JSONObject jsonObject, Integer flag) throws JSONException {
//        if(flag==1){//会员接口
//            resultCode = jsonObject.getInt("errcode");
//            errorDesc = jsonObject.getString("errmsg");
//            result = jsonObject.optString("data");
//        }
//
//    }

    public <T> T parseData(Class<T> objectClass) {
        T t = null;
        try {
            t = sGson.fromJson(result, objectClass);
            parsedData = t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

}
