package me.zhujiajie.exam;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MsgFragment extends android.support.v4.app.Fragment {

    private static final String PATH = "http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=1";
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    private ListView listMessage;
    private MsgAdapter msgAdapter;

    public MsgFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_msg, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listMessage = (ListView) getActivity().findViewById(R.id.listMessage);
        new MyTask().execute(PATH);
        listMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),MsgActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... params) {
            return RequestData();
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                list = JSONAnalysis(result);
                msgAdapter = new MsgAdapter(getActivity(), list);
                listMessage.setAdapter(msgAdapter);
                msgAdapter.notifyDataSetChanged();
            } else if (result == null) {
                Toast.makeText(getActivity(), "请求数据失败...",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public String RequestData(){
        HttpURLConnection connection = null;
        StringBuilder response = null;
        try {
            URL url = new URL(PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(8000);
            connection.setConnectTimeout(8000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                connection.disconnect();
            }
        }
        return response.toString();
    }

    public List<Map<String, Object>> JSONAnalysis(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("comments");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject insideJsonObject = jsonArray.getJSONObject(i);
                String comment_author = insideJsonObject.getString("comment_author");
                String comment_date = insideJsonObject.getString("comment_date");
                String vote_positive = insideJsonObject.getString("vote_positive");
                String vote_negative = insideJsonObject.getString("vote_negative");
                String text_content = insideJsonObject.getString("text_content");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("comment_author", comment_author);
                map.put("comment_date", comment_date);
                map.put("vote_positive", vote_positive);
                map.put("vote_negative", vote_negative);
                map.put("text_content", text_content);
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
