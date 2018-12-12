package com.redis;

import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TxtToRedis {
    public void saveTextFileToRedis(String host,String path,String key) throws Exception{
        //把文件的每一行写入map
        Map map = fileToMap(path);
        //数据存储到redis
        RedisUtils.getJedis().hmset(key,map);
    }

    //写入
    public Map fileToMap(String path){
        Map<String,String> map = new HashMap();
        String data = null;
        int i = 0;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)),"GBK"))){
            while ((data=br.readLine()) != null){
                //System.out.println(data);
                i++;
                map.put(i+"",data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


    public static void main(String args[]) throws Exception {
        TxtToRedis txtToRedis = new TxtToRedis();
        txtToRedis.saveTextFileToRedis("10.13.0.103","C:\\Users\\Administrator\\Desktop\\user.txt","user_id");
    }


}
