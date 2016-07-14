package com.saat.auto.cafe.api;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class WebHelper {

    public static final String API_PATH = "/api";
    public static final String API_V1_PATH = API_PATH + "/v1";

    /**
     * Helper method to get the URI of the server. Result will be formatted as [scheme]://[server]:[port],
     * unless the port is 80, in which case it will not have the port specified in the result.
     */
    public StringBuilder getServerUriBuilder() {
        // Attribution to get HttpServletRequest: http://stackoverflow.com/a/1795931
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        StringBuilder builder = new StringBuilder();
        builder.append(request.getScheme()).append("://").append(request.getServerName());
        int port = request.getServerPort();
        if (port != 80) {
            builder.append(":").append(port);
        }
        return builder;
    }
}
