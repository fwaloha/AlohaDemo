package com.wf.aloha.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wangfei on 2017/5/23.
 */

public class Material implements Serializable {
    /** 原始图片url */
    private String picture;
    /** 水印图片url */
    private String waterPicture;
    /** 图片名 */
    private String name;

    /** 图片处理字段，原图片缩略图：PicturePair.picture+"?"+PicturePair.picHandleParams */
    private String picHandleParams;

    public String getPicture() {
        return picture;
    }

    public String getWaterPicture() {
        return waterPicture;
    }

    public String getName() {
        return name;
    }

    public String getPicHandleParams() {
        return picHandleParams;
    }

    public static Material parseJSON(JSONObject jsonObj) {
        Material inst = new Material();
        try {
            inst.picture = jsonObj.getString("picture");
            inst.waterPicture = jsonObj.optString("waterPicture");
            inst.name = jsonObj.getString("name");
            inst.picHandleParams = jsonObj.optString("picHandleParams");
        } catch (JSONException e) {
            inst = null;
        }
        return inst;
    }

    public static ArrayList<Material> fromJson(JSONArray obj) {
        ArrayList<Material> list = new ArrayList<Material>();
        if (obj == null) {
            return list;
        }
        try {
            for (int i = 0; i < obj.length(); i++) {
                JSONObject item = obj.getJSONObject(i);
                list.add(Material.parseJSON(item));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            list = null;
        }
        return list;
    }
}
