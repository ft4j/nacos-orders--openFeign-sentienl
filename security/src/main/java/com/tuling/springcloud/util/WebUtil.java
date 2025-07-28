package com.tuling.springcloud.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {

    public static void renderString(HttpServletResponse response,String string){
        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
