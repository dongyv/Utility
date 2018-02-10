package com.dql.cla.util;

import okhttp3.*;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OkHttpUtil {

    private static final MediaType mediaType = MediaType
            .parse("application/x-www-form-urlencoded; charset=utf-8");

    private static final MediaType jsonMediaType = MediaType
            .parse("application/json; charset=utf-8");

    public static final MediaType streamMediaType = MediaType
            .parse("text/x-markdown; charset=utf-8");

    private static OkHttpClient client_post = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(50, 5L, TimeUnit.MINUTES))
            .build();


    private static OkHttpClient client_jsonPost = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(50, 5L, TimeUnit.MINUTES))
            .build();


    private static OkHttpClient client_jsonTwoMinutePost = new OkHttpClient().newBuilder()
            .connectTimeout(80, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(50, 5L, TimeUnit.MINUTES))
            .build();


    private static OkHttpClient client_get = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(50, 5L, TimeUnit.MINUTES))
            .build();

    private static OkHttpClient client_get3 = new OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(50, 5L, TimeUnit.MINUTES))
            .build();

    /**
     * okhttp post请求
     *
     * @param url
     *            请求地址
     * @param data
     *            请求数据
     * @return 请求返回结果
     */
    public static String post(String url, String data) {
        Request request;
        if (StringUtils.isNotBlank(data)) {
            RequestBody requestBody = RequestBody.create(mediaType, data);
            request = new Request.Builder().url(url).post(requestBody).build();
        } else {
            request = new Request.Builder().url(url).build();
        }

        Response response = null;
        try {
            response = client_post.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }

        return null;
    }


    /**
     * okhttp jsonPost请求
     *
     * @param url
     *            请求地址
     * @param data
     *            请求数据
     * @return 请求返回结果
     */
    public static String jsonPost(String url, String data)
    {
        Request request;
        if (StringUtils.isNotBlank(data)) {
            RequestBody requestBody = RequestBody.create(jsonMediaType, data);
            request = new Request.Builder().url(url).post(requestBody).build();
        } else {
            request = new Request.Builder().url(url).build();
        }

        Response response = null;
        try {
            response = client_jsonPost.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return null;
    }

    /**
     * okhttp jsonPost请求
     *
     * @param url
     *            请求地址
     * @param data
     *            请求数据
     * @return 请求返回结果
     */
    public static String jsonTwoMinutePost(String url, String data)
    {
        Request request;
        if (StringUtils.isNotBlank(data)) {
            RequestBody requestBody = RequestBody.create(jsonMediaType, data);
            request = new Request.Builder().url(url).post(requestBody).build();
        } else {
            request = new Request.Builder().url(url).build();
        }

        Response response = null;
        try {
            response = client_jsonTwoMinutePost.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return null;
    }



    /**
     * 120秒超时时间
     * @param url
     * @param paramsMap
     * @return
     */
    public static String postTwoMinutes(String url, Map<String,String> paramsMap){
        Request request;
        if (!paramsMap.isEmpty()){
            FormBody.Builder builder = new FormBody.Builder();
            for (String key:paramsMap.keySet()){
                builder.add(key, paramsMap.get(key));
            }
            RequestBody requestBody = builder.build();
            request = new Request.Builder().url(url).post(requestBody).build();
        }else {
            request = new Request.Builder().url(url).build();
        }

        Response response = null;
        try {
            response = client_jsonTwoMinutePost.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();;
        } finally {
            if (response != null) {
                response.body().close();
            }
        }

        return null;
    }


    /**
     * 120秒超时时间
     * 文件传输
     * @param url
     * @param paramsMap
     * @return
     */
    public static String postTwoMinFile(String url,Map<String, Object> paramsMap) {
        Request request;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        //设置类型
        builder.setType(MultipartBody.FORM);
        //追加参数
        for (String key : paramsMap.keySet()) {
            Object object = paramsMap.get(key);
            if (object instanceof File) {
                File file = (File) object;
                builder.addFormDataPart(key, file.getName(), RequestBody.create(null, file));
            }else {
                builder.addFormDataPart(key, object.toString());
            }
        }
        RequestBody requestBody = builder.build();
        request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            response = client_jsonTwoMinutePost.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();;
        } finally {
            if (response != null) {
                response.body().close();
            }
        }

        return null;
    }





    /**
     * okhttp get请求
     *
     * @param url
     *            请求地址
     * @return 请求返回结果
     */
    public static String get(String url) {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client_get.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return null;
    }

    /**
     * okhttp get请求
     *
     * @param url
     *            请求地址
     * @return 请求返回结果
     */
    public static String get(String url, int connecTimeout, int readTimeout)
    {
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client_get3.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.body().close();
            }
        }
        return null;
    }

}
