package com.jumper.encode;

import java.net.URLEncoder;

public class UrlEncodeDemo {

    public static void main(String[] args) throws Exception {
        String url = "http://www.daojia.com?a=123&b=asdf";
        url = URLEncoder.encode(url,"utf-8");
        System.out.println(url);
        url = URLEncoder.encode(url,"utf-8");
        System.out.println(url);
    }

}



