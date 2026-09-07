package com.fongmi.android.tv.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.github.catvod.net.OkHttp;

public class WebdavUtil {

    public static class RemoteFile {
        public String path;
        public long modified;
    }

    public static boolean testConnection(String url, String user, String pass) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .method("PROPFIND",
                            RequestBody.create(MediaType.parse("application/xml"),
                                    "<?xml version='1.0'?><d:propfind xmlns:d='DAV:'/>"))
                    .build();
            try (Response resp = execute(request)) {
                return resp.code() == 207 || resp.isSuccessful();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean uploadFile(String url, String user, String pass, File file) {
        try {
            Request request = new Request.Builder()
                    .url(url.endsWith("/") ? url + file.getName() : url + "/" + file.getName())
                    .put(RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            try (Response resp = execute(request)) {
                return resp.isSuccessful();
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean downloadFile(String url, String user, String pass, String remotePath, File local) {
        return false;
    }

    public static List<RemoteFile> listFiles(String url, String user, String pass) {
        return new ArrayList<>();
    }

    private static Response execute(Request request) throws Exception {
        Call call = OkHttp.get().newCall(request);
        return call.execute();
    }
}
