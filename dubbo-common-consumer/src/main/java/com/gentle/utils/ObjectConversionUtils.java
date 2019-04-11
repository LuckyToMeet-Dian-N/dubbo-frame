package com.gentle.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gentle
 * @date 2019/04/11 : 16:21
 * 对象转换工具类
 */
public class ObjectConversionUtils {
    /**
     *  将一个 List 对象转换成新List 对象
     *  例如 List<UserDTO> 转换成 List<UserVO>
     * @param list
     * @param clazz
     * @return
     */
    public static List listConversionToNewList(List list ,Class clazz){
        List newList = new ArrayList();
        try {
            for (Object o:list){
                Object o1 = clazz.newInstance();
                BeanUtils.copyProperties(o,o1);
                newList.add(o1);
            }
        } catch (InstantiationException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return newList;
    }






}




