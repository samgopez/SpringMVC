package com.spring.util;

import org.json.simple.JSONArray;

/**
 * Created by Sam on 28/11/2016.
 */
public class StudentUtil {

    public long incrementId() {
        JsonUtil jsonUtil = new JsonUtil();
        JSONArray jsonArray = jsonUtil.getFileJSONArray();
        long jsonArraySize = jsonArray.size();

        return jsonArraySize + 1;
    }
}
