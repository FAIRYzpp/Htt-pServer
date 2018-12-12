package com.redis;

import redis.clients.jedis.Jedis;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CanShuHua {
    public static void main(String args[]){
        String filepath = "D:\\Program Files\\JetBrains\\Maven\\untitled\\sql\\src\\com\\mysql";
        filepath += "\\id.txt";

        try {
            File file = new File(filepath);
            if (!file.exists()){
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Jedis jedis = new Jedis("10.13.0.103",6379);
            for (int i = 1;i<=jedis.hlen("user_id");i++){
                String str = "id="+i;
                bw.write(str);
                bw.newLine();
            }
            bw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
