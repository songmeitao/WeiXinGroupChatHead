package smt.weixingroupchat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ConversationMode> list;
    private ListView listview;
    ArrayList<Bitmap> bitmaps1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new MyAdapter());

    }


    private void initData() {

        initBitmap();

        list = new ArrayList<ConversationMode>();

        for(int i = 0; i < 20; i++) {
            ConversationMode mode1 = new ConversationMode();
            mode1.setTitle("研发讨论组"+i);
            mode1.setTime("12:11");
            mode1.setMessage("今天你去哪啊?");
            mode1.setBitmaps(bitmaps1);
            list.add(mode1);
        }


    }

    private void initBitmap() {

        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.aa));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.bb));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.cc));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.dd));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.ee));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.ff));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.gg));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.tt));
        bitmaps1.add(BitmapFactory.decodeResource(getResources(), R.drawable.zz));


    }


    class MyAdapter extends BaseAdapter {

        class ViewHolder {
            TextView title;
            TextView time;
            TextView message;
            GroupChatHead head;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getApplicationContext(), R.layout.item_layout, null);
                holder.head = (GroupChatHead) convertView.findViewById(R.id.head);
                holder.title = (TextView) convertView.findViewById(R.id.tv_title);
                holder.time = (TextView) convertView.findViewById(R.id.tv_time);
                holder.message = (TextView) convertView.findViewById(R.id.tv_message);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ConversationMode conversationMode = list.get(position);

            holder.head.setBitmaps(conversationMode.getBitmaps());
            holder.title.setText(conversationMode.getTitle());
            holder.time.setText(conversationMode.getTime());
            holder.message.setText(conversationMode.getMessage());

            return convertView;
        }


        @Override
        public Object getItem(int position) {
            return list.get(position);
        }


        @Override
        public int getCount() {
            return list.size();
        }
    }
}
