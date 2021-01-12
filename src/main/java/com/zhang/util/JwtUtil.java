package com.zhang.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JWT token 工具类,提供JWT生成,校验,工作
 *
 * @author 11140
 * @Date 2020-12-30
 * @Description: TODO
 */
@Component
public class JwtUtil {

    /**
     * 过期时间改为从配置文件获取
     */
    private  String accessTokenExpireTime;

    /**
     * JWT认证解密私钥(Base64加密)
     */
    private  String encryptJwtKey;

    /**
     * JWT认证加密公钥(Base64加密)
     */
    private  String publicJwtKey;
    @Value("${accessTime}")
    public void setAccessTokenExpireTime(String accessTokenExpireTime) {
        this.accessTokenExpireTime = accessTokenExpireTime;
    }

    @Value("${privateKey}")
    public void setencryptJwtKey(String encryptJwtKey) {
        this.encryptJwtKey = encryptJwtKey;
    }
    @Value("${publicKey}")
    public void setPublicJwtKey(String publicJwtKey) {
        this.publicJwtKey = publicJwtKey;
    }

    /**
     * 校验token是否正确
     * @param token Token
     * @return boolean 是否正确
     * @author Wang
     */
    public boolean verify(String token) throws Exception {
        try {
            // 帐号加JWT私钥解密
            String secret = getClaim(token, "account") + Base64ConvertUtil.decode(encryptJwtKey);
            if(secret == null){
                throw new UnsupportedOperationException("解码出错");
            }
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (UnsupportedEncodingException e) {
            throw new Exception("JWTToken认证解密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }

    /**
     * 获得Token中的信息无需secret解密也能获得
     * @param token
     * @param claim
     * @return java.lang.String
     * @author Wang
     */
    public String getClaim(String token, String claim) throws Exception {
        try {
            DecodedJWT jwt = JWT.decode(token);
            String info = jwt.getClaim(claim).asString();
            // 只能输出String类型，如果是其他类型返回null
            return info;
        } catch (JWTDecodeException e) {
            throw new Exception("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }
    /**
     * 获得Token中的过期时间
     * @param token
     * @return java.lang.String
     * @author Wang
     */
    public Boolean checkExprieTime(String token) throws Exception {
        try {
            DecodedJWT jwt = JWT.decode(token);

           return  jwt.getExpiresAt().getTime() >= System.currentTimeMillis();
        } catch (JWTDecodeException e) {
            throw new Exception("解密Token中的公共信息出现JWTDecodeException异常:" + e.getMessage());
        }
    }
    /**
     * 生成签名
     * @param id 帐号
     * @return java.lang.String 返回加密的Token
     * @author Wang
     */
    public String sign(String id, Long currentTimeMillis) throws Exception {
        try {
            // 帐号加JWT私钥加密
            String secret = id + Base64ConvertUtil.decode(encryptJwtKey);
            // 此处过期时间是以毫秒为单位，所以乘以1000
            Date date = new Date(currentTimeMillis + Integer.parseInt(accessTokenExpireTime) *60*60*1000);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 附带account帐号信息
            String jwt = JWT.create()
                    .withClaim("account", id)
                    .withClaim("currentTimeMillis", currentTimeMillis)
                    .withExpiresAt(date)
                    .sign(algorithm);
            return jwt;

        } catch (UnsupportedEncodingException e) {
            throw new Exception("JWTToken加密出现UnsupportedEncodingException异常:" + e.getMessage());
        }
    }
}

