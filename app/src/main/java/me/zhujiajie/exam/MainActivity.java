package me.zhujiajie.exam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager pager;
    private PagerAdapter mAdapter;
    private ArrayList<Fragment> fragments;
    private ArrayList<TextView> tab = new ArrayList<TextView>();
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        initView();
        initViewPager();
        initTab();
    }

    private void initView(){
        pager = (ViewPager) findViewById(R.id.viewPager);
        fragments = new ArrayList<Fragment>();
        fragments.add(new PicFragment());
        fragments.add(new MsgFragment());
        fragments.add(new ChatFragment());
    }

    private void initViewPager(){
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),fragments);
        pager.setAdapter(mAdapter);
        pager.setOnPageChangeListener(this);
        pager.setCurrentItem(0);
    }

    private void initTab(){
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        tv_3 = (TextView) findViewById(R.id.tv_3);
        tab.add(tv_1);
        tab.add(tv_2);
        tab.add(tv_3);
        tab.get(0).setOnClickListener(new MyOnClickListener(0));
        tab.get(1).setOnClickListener(new MyOnClickListener(1));
        tab.get(2).setOnClickListener(new MyOnClickListener(2));
    }

    private class MyOnClickListener implements View.OnClickListener
    {
        private int index;
        public MyOnClickListener(int index)
        {
            this.index = index;
        }
        @Override
        public void onClick(View v) {
            pager.setCurrentItem(index);
        }
    }

    private void parseXMLWithPULL(String xmlData){
        Log.d("MainActivity",xmlData);
        try{
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            updataInfo info = null;
            String nodeName = xmlPullParser.getName();
            switch(eventType){
                case XmlPullParser.START_TAG:
                    if (nodeName.equals("updataInfo")){
                        info = new updataInfo();
                    }
                    if(nodeName.equals("versionCode")){
                        info.setVersionCode(Integer.parseInt(xmlPullParser.nextText()));
                    }
                    else if(nodeName.equals("versionName")){
                        info.setVersionName(Float.parseFloat(xmlPullParser.nextText()));
                    }
                    else if(nodeName.equals("updateContent")){
                        info.setUpdateContent(xmlPullParser.nextText());
                    }
                    else if(nodeName.equals("apkURL")){
                        info.setApkURL(xmlPullParser.nextText());
                    }
                    Log.d("MainActivity","" + info.getVersionCode());
                    Log.d("MainActivity","" + info.getVersionName());
                    Log.d("MainActivity","" + info.getUpdateContent());
                    Log.d("MainActivity","" + info.getApkURL());
                    break;
                default:
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case R.id.action_settings:
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_edit:
                Toast.makeText(this,"You clicked Edit",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_about:
                Toast.makeText(this,"You clicked About",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_update:
                Toast.makeText(this,"开始解析新版本",Toast.LENGTH_SHORT).show();
                HttpUtil.sendHttpRequest("http://hongyan.cqupt.edu.cn/app/cyxbsAppUpdate.xml", new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        parseXMLWithPULL(response);
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });
        }


        return super.onOptionsItemSelected(item);
    }

    public boolean onMenuOpened(int featureId, Menu menu){
        if(featureId == Window.FEATURE_ACTION_BAR && menu != null){
            if(menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisble",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
                }catch(Exception e){
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowShowingAlways(){
        try{
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class
                    .getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
