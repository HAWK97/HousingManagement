package com.hawkbear.housingmanagement.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * qiniu 配置类
 *
 * @author xch
 * @since 2019/3/29 14:42
 **/
public class QiniuUtil {

    private static String accessKey = "TnIOszZVvneKT9xI9ySSiXpbpCsJeBGoFCUu6jTl";
    private static String secretKey = "1Mf5ksCyGwIzxTJoZ2zSUS65tS034t48G9nMQJV_";
    private static String bucket = "hawk97";
    private static String host = "cdn.stalary.com";

    //构造一个带指定Zone对象的配置类
    private static Configuration cfg = new Configuration(Zone.zone0());
    //...其他参数参考类注释
    private static UploadManager uploadManager = new UploadManager(cfg);

    /**
     * 获取上传凭证
     *
     * @return
     */
    private static String getUpToken() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }


    /**
     * 上传文件返回文件存储地址
     *
     * @param inputStream
     * @return
     */
    public static String upload(InputStream inputStream) throws IOException {
        byte[] uploadBytes = inputStreamToByte(inputStream);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        String upToken = getUpToken();
        try {
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return "http://" + host + "/" + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     *  * 输入流转字节流
     *  
     **/
    private static byte[] inputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int ch;
        while ((ch = is.read(buffer)) != -1) {
            bytestream.write(buffer, 0, ch);
        }
        byte data[] = bytestream.toByteArray();
        bytestream.close();
        return data;
    }



}
