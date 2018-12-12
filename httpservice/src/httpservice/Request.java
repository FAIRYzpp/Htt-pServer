package httpservice;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    public InputStream intput;
     String uri;

    public Request(InputStream input){
        this.intput = input;
    }

    public String getUri(){
        return uri;
    }

    public void parse(){
        StringBuffer request = new StringBuffer();
        byte[] buffer = new byte[2048];
        int i;
        try{
            i = intput.read(buffer);
        }catch (IOException e){
            e.printStackTrace();
            i = -1;
        }

        for (int k=0;k<i;k++){
            request.append((char)buffer[k]);
        }
        //System.out.println(request.toString());
        //System.out.println(uri);
        uri = parseUri(request.toString()).substring(4);
    }

    public String parseUri(String requestData){
        int index1,index2;
        index1 = requestData.indexOf(' ');
        if (index1 != -1){
            index2 = requestData.indexOf(' ',index1+1);
            if (index2 >index1){
                return requestData.substring(index1+1,index2);
            }
        }
        return null;
    }
}
