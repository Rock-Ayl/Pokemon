package com.rock.pokemon.gdx.util;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * Json 额外逻辑工具包
 *
 * @Author ayl
 * @Date 2023-11-23
 */
public class JsonExtraUtils {

    /**
     * 深克隆单个对象,也可以将一个对象转化为另一个对象(当然,结构得基本一致或继承关系)
     *
     * @param object       源对象,比如父对象,不能是数组等结构
     * @param toJavaObject 目标对象
     * @return
     */
    public static <T> T deepClone(Object object, Class<T> toJavaObject) {
        //判空
        if (object == null) {
            //过
            return null;
        }
        //先转为string再转为对应实体,如果转为json对象再转实体某些特殊情况会报错
        return JSON.parseObject(JSON.toJSONString(object), toJavaObject);
    }

    /**
     * 深克隆为数组对象,也可以将一个对象数组转化为另一个对象数组(当然,结构得基本一致或继承关系)
     *
     * @param listOrArrObject 源对象,比如父对象,必须是数组、集合等结构
     * @param toJavaObject    目标对象
     * @return
     */
    public static <T> List<T> deepCloneList(Object listOrArrObject, Class<T> toJavaObject) {
        //判空
        if (listOrArrObject == null) {
            //过
            return new ArrayList<>();
        }
        //先转为string再转为对应实体,如果转为json对象再转实体某些特殊情况会报错
        return JSON.parseArray(JSON.toJSONString(listOrArrObject), toJavaObject);
    }

}
