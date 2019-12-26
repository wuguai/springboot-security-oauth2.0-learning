package com.sweetmart.core.oauth.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sweetmart.core.oauth.response.BaseResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtils {
    public HttpUtils() {
    }

    public static void writer(BaseResponse bs, HttpServletResponse response) throws IOException {
        response.setContentType("application/json,charset=utf-8");
        response.setStatus(bs.getCode());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), bs);
    }
}

