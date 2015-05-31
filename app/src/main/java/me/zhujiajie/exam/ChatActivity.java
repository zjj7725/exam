package me.zhujiajie.exam;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends Activity implements View.OnClickListener{

    private ListView msgListView;
    private EditText inputText;
    private Button send;
    private ChatAdapter adapter;
    private List<Chat> chatList = new ArrayList<Chat>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initChat();
        adapter = new ChatAdapter(ChatActivity.this,R.layout.chat_item,chatList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(this);
    }

    private void initChat() {
        Chat msg1 = new Chat("I have tried my best.", Chat.TYPE_RECEIVED);
        chatList.add(msg1);
        Chat msg2 = new Chat("I will be more hardworking.", Chat.TYPE_RECEIVED);
        chatList.add(msg2);
        Chat msg3 = new Chat("I love redrock.", Chat.TYPE_RECEIVED);
        chatList.add(msg3);
        Chat msg4 = new Chat("Thank you for your help,Tang Da.", Chat.TYPE_RECEIVED);
        chatList.add(msg4);
    }

    @Override
    public void onClick(View v) {
        String content = inputText.getText().toString();
        if (!"".equals(content)){
            Chat msg = new Chat(content,Chat.TYPE_SENT);
            chatList.add(msg);
            adapter.notifyDataSetChanged();
            msgListView.setSelection(chatList.size());
            inputText.setText("");
        }
    }
}
