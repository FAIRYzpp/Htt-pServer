package httpservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    private static final int port = 80;
    public static void start(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port,1, InetAddress.getByName("10.222.137.170"));
            System.out.println("等待来浏览器的连接.....");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true) {
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                InputStream finalInput = input;
                OutputStream finalOutput = output;
                Socket finalSocket = socket;
                cachedThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        Request request = new Request(finalInput);
                        request.parse();
                        Response response = new Response(finalOutput);
                        response.setRequest(request);
                        try {
                            response.dealRequest();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            finalSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        }

    public static void main(String args[]){
        HttpServer server = new HttpServer();
        server.start();
    }
}
