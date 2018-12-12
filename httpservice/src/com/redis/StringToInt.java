package com.redis;

import redis.clients.jedis.Jedis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringToInt {
    public void readRedis(){
        Jedis jedis = new Jedis("10.13.0.103",6379);
        if (jedis.hexists("user_id","1")){
            System.out.println("a");
        }else {
            System.out.println("b");
        }
    }

    public void StrToInt(){
        String str = "123";
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if ( isNum.matches()){
            int i = Integer.parseInt(str.trim());
            System.out.println(i);
        }
    }

    public void delFirstStr(){
        String string = "abcdef";
        String str2 = string.substring(3);
        System.out.println(str2);
    }
    public static void main(String args[]){
        StringToInt stringToInt = new StringToInt();
        stringToInt.readRedis();
    }

}
