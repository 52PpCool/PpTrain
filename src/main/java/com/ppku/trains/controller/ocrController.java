package com.ppku.trains.controller;

import com.alibaba.fastjson.JSONObject;

import org.apache.logging.log4j.util.Base64Util;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ocr")
public class ocrController {
    private String secretId = "205a69df725a4613b5cf7e3908004d74";
    /** 客户端密钥 */
    private String secretKey ="c70a1da695ea433e9fc5802ca68eddfa";


    @PostMapping("/baodan")
    public Map certificateCardOcr(@RequestBody MultipartFile file) throws Exception {
        //转BASE64

        JSONObject reqData = new JSONObject();//请求数据
        String imageBaseStr = Base64Utils.encodeToString(file.getBytes());
        Map<String, String> obj = new HashMap<>();
        reqData.put("ImageBase64",imageBaseStr);
        obj.put("sign",getAuthorizationHeader(secretId, secretKey, reqData.toJSONString()));
        obj.put("imageBaseStr",imageBaseStr);
        return obj;
    }

    public static String getAuthorizationHeader(String secretId,
        String secretKey, String bodyParam) throws Exception {
        String timestamp = String.valueOf(System.currentTimeMillis());
        // 1.0 组装待加签字符串
        String toDeal = bodyParam + "&" + secretId + "&" + secretKey + "&" + timestamp;
        // 2.0 使用sha256算法进行加签
        return sha256Hex(toDeal);
    }

    private static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(StandardCharsets.UTF_8));
        return bytesToHexFun(d);
    }

    private static String bytesToHexFun(byte[] bytes) {
        StringBuilder buf = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            buf.append(String.format("%02x", b & 0xff));
        }
        return buf.toString();
    }
}
