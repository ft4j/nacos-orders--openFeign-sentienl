package com.tuling.springcloud.util;

import com.tuling.springcloud.cahce.UserInfo;
import com.tuling.springcloud.cahce.UserinfoCache;
import com.tuling.springcloud.domain.MyUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    // 密钥（实际项目中建议从配置文件读取，且确保足够复杂）
    private static final String SECRET_KEY = "your-256-bit-secret-key-should-be-very-long-and-secure-enough-for-hs256";

    // 默认过期时间：2小时（单位：毫秒）
    private static final long DEFAULT_EXPIRATION = 2 * 60 * 60 * 1000;

    /**
     * 生成签名密钥（使用HS256算法需要256位密钥）
     */
    private static SecretKey getSecretKey() {
        // 注意：密钥长度需满足算法要求（HS256需要至少256位）
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    /**
     * 生成Token
     * @param subject 主题（通常为用户名或用户ID）
     * @return JWT令牌
     */
    public static String generateToken(String subject) {
        return generateToken(subject, new HashMap<>(), DEFAULT_EXPIRATION);
    }

    /**
     * 生成带自定义载荷和过期时间的Token
     * @param subject 主题
     * @param claims 自定义载荷信息
     * @param expiration 过期时间（毫秒）
     * @return JWT令牌
     */
    public static String generateToken(String subject, Map<String, Object> claims, long expiration) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims) // 设置自定义载荷
                .setSubject(subject) // 设置主题
                .setIssuedAt(now) // 设置签发时间
                .setExpiration(expirationDate) // 设置过期时间
                .signWith(getSecretKey(), SignatureAlgorithm.HS256) // 设置签名算法和密钥
                .compact();
    }

    /**
     * 从Token中获取主题信息（用户名/用户ID）
     * @param token JWT令牌
     * @return 主题信息
     */
    public static String getSubject(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从Token中获取指定的载荷信息
     * @param token JWT令牌
     * @param claimResolver 解析函数
     * @param <T> 载荷类型
     * @return 载荷值
     */
    public static <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    /**
     * 解析Token获取所有载荷信息
     * @param token JWT令牌
     * @return 所有载荷
     */
    private static Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey()) // 设置签名密钥
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 检查Token是否过期
     * @param token JWT令牌
     * @return 是否过期
     */
    public static boolean isTokenExpired(String token) {
        Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    /**
     * 校验Token是否有效
     * @param token JWT令牌
     * @param subject 预期的主题（用于比对）
     * @return 是否有效
     */
    public static boolean validateToken(String token, String subject) {
        String tokenSubject = getSubject(token);
        // 校验主题是否一致且Token未过期
        return (tokenSubject.equals(subject) && !isTokenExpired(token));
    }

    /**
     * 从token中获取客户信息
     * @return
     */
    public static UserInfo getUserInfo(){
        UserInfo principal = (UserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}
