package com.zhangbo.retrofit.retrofit_rxjava.http.event;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 13:59
 * desc   : 抽象消息类型的 Event
 * version: 1.0
 */
public class BaseActionEvent extends BaseEvent {
    public static final int SHOW_LOADING_DIALOG = 0;
    public static final int DISMISS_LOADING_DIALOG = 1;
    public static final int SHOW_TOAST = 2;
    public static final int FINISH = 3;
    public static final int FINISH_WITH_RESULT_OK = 4;
    private String message;

    public BaseActionEvent(int action) {
        super(action);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
