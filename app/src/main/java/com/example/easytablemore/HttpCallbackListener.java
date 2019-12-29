package com.example.easytablemore;

public interface HttpCallbackListener {
    void onFinish(String response);// 正常结束
    void onError(Exception ex);// 异常
}

