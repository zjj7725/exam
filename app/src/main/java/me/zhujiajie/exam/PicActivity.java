package me.zhujiajie.exam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;


public class PicActivity extends Activity {

    private ImageView defaultPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        defaultPicture = (ImageView) findViewById(R.id.default_picture);
        defaultPicture.setImageResource(R.mipmap.ic_launcher);
    }
}
