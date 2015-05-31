package me.zhujiajie.exam;


public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
