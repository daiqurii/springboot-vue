package com.dzx.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dzx.entity.User;

import java.util.Calendar;
import java.util.Map;

/**
 * TODO
 *
 * @author Daiquiri
 * @version 1.0
 * @date 2022/12/9 0:20
 */
public class JWTUtils {

    private static final String SING = "dzx123456";

    /**
     * 生成token
     */
    public static String getToken(User user){
        Calendar instance = Calendar.getInstance();
        //默认7天过期
        instance.add(Calendar.DATE,7);
        //创建jwt builder
        JWTCreator.Builder builder = JWT.create();

        builder.withClaim("userId", user.getId())
                .withClaim("username", user.getUsername());

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
        return token;
    }

    /**
     * 验证token合法性
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }
}

