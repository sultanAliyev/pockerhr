package kz.iitu.service.configuration.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.HashMap;

public class TokenUtils {

    private final static String TOKEN_SECRET_KEY = "hrbotservice1234";

    public static String generateUserToken(String email, String password) {
        return JWT.create()
                .withHeader(new HashMap<>(){{
                    put("email", email);
                    put("password", password);
                    put("time", System.currentTimeMillis());
                }}).sign(Algorithm.HMAC256(TOKEN_SECRET_KEY));
    }

}