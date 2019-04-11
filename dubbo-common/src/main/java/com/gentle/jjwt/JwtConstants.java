package com.gentle.jjwt;

/**
 * @author : Gentle
 * @date : 2019/04/11 : 16:23
 */
public interface JwtConstants {
    /** jwtid  **/
    String JWT_ID = "5236A";
    /**
     * id
     */
    String ID="jwt";
    /**
     * 密匙，这里是自己随便写，当时一定要32位
     */
    String JWT_SECERT = "0000df7f12334e888889999123c0005d";
    /**
     * //token有效时间
     */
    long JWT_TTL = 30*60*1000;

}
