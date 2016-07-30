package com.fozoto.duobao.util;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by qingyan on 16-7-28.
 */
public class JsonUtil {
    private Logger log = Logger.getLogger(JsonUtil.class);

    @Test
    public void testJsonString() {
        String url = "http://t.apiplus.cn/newly.do?code=cqssc&format=json";
        try {
            String result = OkHttpClientManager.getAsString(url);
            log.debug(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
