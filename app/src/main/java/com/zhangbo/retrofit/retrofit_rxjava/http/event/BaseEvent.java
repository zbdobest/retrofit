package com.zhangbo.retrofit.retrofit_rxjava.http.event;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 13:56
 * desc   : 抽象消息类型的 Event
 * version: 1.0
 */
public class BaseEvent {
    private int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
