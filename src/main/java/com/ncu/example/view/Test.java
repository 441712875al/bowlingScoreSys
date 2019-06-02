package com.ncu.example.view;

import com.alibaba.fastjson.JSONObject;

public class Test {
    public static void main(String[] args){
        JSONObject obj  = new JSONObject();
        obj.getString("name");
        obj.put("name",100);
    }
}
