package com.spring.util;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sam on 28/11/2016.
 */
public class StudentUtil {

    private static final Logger logger = LoggerFactory.getLogger(StudentUtil.class);

    public long incrementId() {
        logger.debug("Start incrementId()");
        JsonUtil jsonUtil = new JsonUtil();
        JSONArray jsonArray = jsonUtil.getFileJSONArray();
        long jsonArraySize = jsonArray.size();

        return jsonArraySize + 1;
    }
}
