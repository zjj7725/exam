package me.zhujiajie.exam;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.URL;

public class DownImage {
    public String image_path;

    public DownImage(String image_path) {
        this.image_path = image_path;
    }

    public void loadImage(final ImageCallBack callBack) {

        final Handler handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap bitmap = (Bitmap) msg.obj;
                callBack.getBitmap(bitmap);
            }

        };

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(image_path);
                    InputStream is = url.openStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public interface ImageCallBack {
        public void getBitmap(Bitmap bitmap);
    }
}
