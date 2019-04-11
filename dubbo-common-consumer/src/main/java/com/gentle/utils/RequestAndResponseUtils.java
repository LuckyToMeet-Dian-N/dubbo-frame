package com.gentle.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;

/**
 * 请求的工具类
 * @author Gentle
 * @date 2019/04/11 : 16:21
 */
public class RequestAndResponseUtils {
	private static String D = ",";
	private static int IP_LENGTH = 15;
	private static  String UNKNOWN = "unknown";
	private static String LOCALHOST = "127.0.0.1";

	/**
	 * 获取request对象
	 * @return 返回request对象
	 */
	public static HttpServletRequest getRequest() {
		// HttpServlerRequest
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

	}
	/**
	 * 获取responnse对象
	 * @return 返回 responnse 对象
	 */
	public  static HttpServletResponse getResponse() {
		// HttpServlerRequest
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

	}

	/**
	 * 获取 ip
	 * @return ip 地址
	 */
	public static String getRequestIpAddr(){
		HttpServletRequest httpServletRequest = getRequest();
		String ip = httpServletRequest.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
			ip = httpServletRequest.getRemoteAddr();
			if(ip.equals(LOCALHOST)){
				//根据网卡取本机配置的IP
				InetAddress inet=null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if(ip != null && ip.length() > IP_LENGTH){
			if(ip.indexOf(D)>0){
				ip = ip.substring(0,ip.indexOf(D));
			}
		}
		return ip;
	}
}