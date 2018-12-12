package httpservice;

import com.redis.ConnectMySQL;
import com.redis.RedisUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Response {
    public OutputStream output;
    private Request request;

    public Response(OutputStream output) {
        this.output = output;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void dealRequest() throws IOException {
        String uri = request.getUri();
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(uri);
        if ( !matcher.matches() ){
            String message1 = "HTTP/1.1 400 File Not Found\r\n" + "Content-Type:text/html\r\n" + "Content-Length:20\r\n" + "\r\n" + "<h1>" + "Please enter the ID of the INT" + "</h1>";
            output.write(message1.getBytes());
        }

        if (matcher.matches()) {
            int uri2 = Integer.parseInt(uri.trim());
            RedisUtils redisUtils = new RedisUtils();
            Jedis jedis = redisUtils.getJedis();
            if (jedis.hexists("user_id", String.valueOf(uri2))) {
                try {
                    String value = jedis.hget("user_id", String.valueOf(uri2));
                    System.out.println(value);
                    String message2 = "HTTP/1.1 200 OK\r\n" + "Content-Type:text/html\r\n" + "Content-Length:50\r\n" + "\r\n" + "<h1>" +"My id is:"+uri2+"</h1>"+"\r\n"+"<h2>"+"My user_id is"+"\r\n"+value + "</h2>";
                    output.write(message2.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                    redisUtils.returnResource(jedis);
                }finally {
                    redisUtils.returnBrokenResource(jedis);
                }
            }else {
                try {
                    String message3 = "HTTP/1.1 400 File Not Found\r\n" + "Content-Type:text/html\r\n" + "Content-Length:20\r\n" + "\r\n" + "<h1>" + "Id Not Found" + "</h1>";
                    output.write(message3.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
