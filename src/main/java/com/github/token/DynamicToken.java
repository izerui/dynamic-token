package com.github.token;

/**
 * Created by leon on 2018/8/2.
 */
public class DynamicToken {
    /**
     * 秘钥
     */
    private String secKey;
    /**
     * 时间片长度
     */
    private IntervalEnum interval;

    public DynamicToken(String secKey, IntervalEnum interval) {
        this.secKey = secKey;
        this.interval = interval;
    }

    public String getSecKey() {
        return secKey;
    }

    public void setSecKey(String secKey) {
        this.secKey = secKey;
    }

    public IntervalEnum getInterval() {
        return interval;
    }

    public void setInterval(IntervalEnum interval) {
        this.interval = interval;
    }
}
