package com.fongmi.android.tv.utils;

import com.github.catvod.net.OkHttp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Response;

public class WebdavUtil {

    public static class RemoteFile {
        public String path;
        public long modified;
    }

    private static Request.Builder builder(String url, String user, String pass) {
        Request.Builder b = new Request.Builder().url(url);
        if(user != null && !user.isEmpty()){
            String auth = android.util.Base64.encodeToString(
                    (user + ":" + pass).getBytes(),
                    android.util.Base64.NO_WRAP
            );
            b.addHeader("Authorization","Basic " + auth);
        }
        return b;
    }


    public static boolean testConnection(String url,String user,String pass){
        try{
            Request request = builder(url,user,pass)
                    .method("PROPFIND",
                    RequestBody.create(
                    MediaType.parse("application/xml"),
                    "<?xml version='1.0'?><d:propfind xmlns:d='DAV:'/>"))
                    .build();

            try(Response resp = OkHttp.newCall(request).execute()){
                return resp.code() == 207 || resp.isSuccessful();
            }
        }catch(Exception e){
            return false;
        }
    }


    public static boolean uploadFile(String url,String user,String pass,File file){
        try{
            Request request = builder(url+"/"+file.getName(),user,pass)
                    .put(RequestBody.create(
                    MediaType.parse("application/octet-stream"),
                    file))
                    .build();

            try(Response resp=OkHttp.newCall(request).execute()){
                return resp.isSuccessful();
            }

        }catch(Exception e){
            return false;
        }
    }
}
