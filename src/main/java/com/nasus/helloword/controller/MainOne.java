package com.nasus.helloword.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;

public class MainOne {
    public static void main(String[] args) {
        String a= "2020%5C05%5C15%5C26987%5Cbuglist.xls";
        String b="a";
        String c="a";
        System.out.println(b==c);
        System.out.println(a.replaceAll("%5C", "//"));
        System.out.println(Matcher.quoteReplacement(File.separator));
        try {
            System.out.println(URLDecoder.decode(a, "UTF-8"));

            String keyWord = URLDecoder.decode("%C4%E3%BA%C3", "GBK");
            System.out.println(keyWord);  //输出你好
            // 将普通字符创转换成application/x-www-from-urlencoded字符串
            String urlString = URLEncoder.encode("你好", "GBK");  //输出%C4%E3%BA%C3

            System.out.println(urlString);
        }
        catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.getMessage();
            e.printStackTrace();
        }

    }
}
