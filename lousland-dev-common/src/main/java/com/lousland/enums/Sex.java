package com.lousland.enums;

/**
 * 性别枚举
 *
 * @author ：Hanzhi
 * @date ：2021/6/16 18:08
 */
public enum Sex {
    /**
     * 男性
     */
    man(1, "男"),
    /**
     * 女性
     */
    woman(0, "女"),
    /**
     * 保密
     */
    secret(2, "保密");

    public final Integer type;
    public final String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
