package me.zhujiajie.exam;


import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class PicAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;
    public PicAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView  = LayoutInflater.from(context).inflate(R.layout.picitem, null);
            holder = new ViewHolder();

            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.comment_author = (TextView) convertView.findViewById(R.id.comment_author);
            holder.comment_date = (TextView) convertView.findViewById(R.id.comment_date);
            holder.vote_positive = (TextView) convertView.findViewById(R.id.vote_positive);
            holder.vote_negative = (TextView) convertView.findViewById(R.id.vote_negative);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setImageResource(R.mipmap.ic_launcher);
        holder.comment_author.setText(list.get(position).get("comment_author").toString());
        holder.comment_date.setText(list.get(position).get("comment_date").toString());
        holder.vote_positive.setText(list.get(position).get("vote_positive").toString());
        holder.vote_negative.setText(list.get(position).get("vote_negative").toString());
        DownImage downImage = new DownImage(list.get(position).get("image").toString());
        downImage.loadImage(new DownImage.ImageCallBack() {

            @Override
            public void getBitmap(Bitmap bitmap) {
                holder.image.setImageBitmap(bitmap);

            }
        });

        return convertView;
    }
    class ViewHolder{
        TextView comment_author,comment_date,vote_positive,vote_negative;
        ImageView image;
    }
}

