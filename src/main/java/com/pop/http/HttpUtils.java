package com.pop.http;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: Common-Utils
 * @description: http相关操作
 * @author: Pop
 * @create: 2019-09-26 10:39
 **/
public  class HttpUtils {

    @Data
    static class RemoteInfo {
        private String host;
        private String ip;
        private Integer port;

        public RemoteInfo() {
        }

        public RemoteInfo(String host, String ip, Integer port) {
            this.host = host;
            this.ip = ip;
            this.port = port;
        }
    }

    public static RemoteInfo getRemoteInfo(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        String host = request.getRemoteHost();
        int port = request.getRemotePort();
        return new RemoteInfo(host,ip,port);
    }

}
