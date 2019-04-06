package com.gentle.jjwt;



import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.gentle.exception.CheckException;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class TokenManager {

	public static SecretKey generalKey() {
		byte[] encodedKey = Base64.decode(JwtConstants.JWT_SECERT);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}

    /**
     *
     * @param subject
     * @return
     */
    public static String createJWT(String subject) {
        return createJWT(JwtConstants.ID,subject,JwtConstants.JWT_TTL);
    }

 
	/**
	 * 签发JWT
	 * 
	 * @param id 签发的id，可以如果是长时间token 随便写
	 * @param subject 放用户id
	 * @param ttlMillis 过期时间，分秒计算，30*60*1000 表示 30 分钟
	 * @return
	 * @throws Exception
	 */
	public static String createJWT(String id, String subject, long ttlMillis) {
		//jjwt已经发封装好了所有的请求头，这里是指定签名算法
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		//生成jwt的时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		//获取服务器端的密钥，这个不能贡献出去，贡献出去后就可以随意伪造了
		SecretKey secretKey = generalKey();
		JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(now).signWith(signatureAlgorithm,
				secretKey);
		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date expDate = new Date(expMillis);
			builder.setExpiration(expDate);
		}
		return builder.compact();
	}
 
	/**
	 * 解析JWT字符串
	 * 
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt) throws CheckException {
		SecretKey secretKey = generalKey();
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
	}


	public static void main(String[] args) throws Exception {
        String a = createJWT("1", "100", 5000);
        System.out.println(a);
        Claims claims = parseJWT(a);
        System.out.println(claims.getSubject());
    }
}
