package com.gentle.jjwt;

/**
 * @Auther: Gentle
 * @Date: 2019/3/31 20:52
 * @Description:
 */

public interface JwtConstants {
    /** jwtid  **/
    String JWT_ID = "5236A";
    /**
     *
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
