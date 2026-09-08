package com.fongmi.android.tv.utils;

import com.github.catvod.net.OkHttp;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WebdavUtil {

    public static class RemoteFile {
        public String path;
        public long modified;
    }

    private static boolean request(String url) {
        try {
            return OkHttp.get().get(url).isSuccessful();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean testConnection(String url, String user, String pass) {
        return request(url);
    }

    public static boolean uploadFile(String url, String user, String pass, File file) {
        return request(url);
    }

    public static boolean downloadFile(String url, String user, String pass, String remotePath, File local) {
        return request(url);
    }

    public static List<RemoteFile> listFiles(String url, String user, String pass) {
        return new ArrayList<>();
    }
}
