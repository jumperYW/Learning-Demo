package com.wayne.cglib;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CglibBean {

    /**
     * 实体Object
     */
    public Object object = null;

    /**
     * 属性map
     */
    public BeanMap beanMap = null;

    public CglibBean() {
        super();
    }

    public CglibBean(Map<String, Object> propertyMap) {
        this.object = generateBean(propertyMap);
        this.beanMap = BeanMap.create(this.object);
    }

    /**
     * 给bean属性赋值
     *
     * @param property 属性名
     * @param value    值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 通过属性名得到属性值
     *
     * @param property 属性名
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 得到该实体bean对象
     *
     * @return
     */
    public Object getObject() {
        return this.object;
    }

    @SuppressWarnings("rawtypes")
    private Object generateBean(Map<String, Object> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set<String> keySet = propertyMap.keySet();
        for (Iterator<String> i = keySet.iterator(); i.hasNext(); ) {
            String key = i.next();
            generator.addProperty(key, (Class) propertyMap.get(key));
        }
        return generator.create();
    }

}