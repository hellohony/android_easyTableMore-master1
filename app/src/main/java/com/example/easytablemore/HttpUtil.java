package com.example.easytablemore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    // 回调函数 Callback NDK
    public static void sendRequest(final String address,
                                   final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String TAG="RightFragment";
                String web = address;
                HttpURLConnection conn = null;
                BufferedReader reader = null;
                final StringBuilder builder = new StringBuilder();
                //builder.append("开始执行\n");
                try {

                    // 打开连接
                    URL url = new URL(web);
                    conn = (HttpURLConnection) url.openConnection();
                    // 设置属性

                    conn.setRequestMethod("GET"); // post
                    conn.setConnectTimeout(8000); // 连接超时
                    conn.setReadTimeout(8000); // 读取超时

                    // 读取数据
                    InputStream in = conn.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line = reader.readLine();
                    //builder.append("第一行:").append(line);
                    while (line != null) {
                        builder.append(line);
                        line = reader.readLine();
                    }
                    // 正常结束
                    if (listener != null) {
                        listener.onFinish(builder.toString().trim());
                    }

                    // return builder.toString().trim();

                } catch (Exception ex) {
                    ex.printStackTrace();
                    // 异常
                    if (listener != null) {
                        listener.onError(ex);
                    }
                    // return "";
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }

                }
            }
        }).start();
    }
}
