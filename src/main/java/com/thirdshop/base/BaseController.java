package com.thirdshop.base;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BaseController {
    protected Logger logger= LoggerFactory.getLogger(this.getClass());
    protected final static String DATE_FORMAT="yyyy-MM-dd";

    public String responseResult(Object obj) {
        String jsonObj=null;
        if (obj != null) {
            logger.info("后端返回对象：{}",obj);
            jsonObj= JSONObject.toJSONString(obj);
            logger.info("后端返回数据："+jsonObj);
        }
        logger.info("输出结果：{}",jsonObj);
        return jsonObj;
    }

    // 下面是判断null的操作

    public boolean isEmpty(String str) {
        return (null == str) || (str.trim().length() <= 0);
    }

    public boolean isEmpty(Character cha) {
        return (null == cha) || cha.equals(' ');
    }

    public boolean isEmpty(Object obj) {
        return (null == obj);
    }

    public boolean isEmpty(Object[] objs) {
        return (null == objs) || (objs.length <= 0);
    }

    public boolean isEmpty(Collection<?> obj) {
        return (null == obj) || obj.isEmpty();
    }

    public boolean isEmpty(Set<?> set) {
        return (null == set) || set.isEmpty();
    }

    public boolean isEmpty(Serializable obj) {
        return null == obj;
    }

    public boolean isEmpty(Map<?, ?> map) {
        return (null == map) || map.isEmpty();
    }

    /**
     *
     * 获得map
     * @return
     */
    public Map<String,Object> getMap(){
        return new HashMap<String,Object>();
    }
}
