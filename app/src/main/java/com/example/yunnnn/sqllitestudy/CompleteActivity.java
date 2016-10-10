package com.example.yunnnn.sqllitestudy;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunnnn on 2016/10/8.
 */

public class CompleteActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_login_layout);
        ListView listView = (ListView) findViewById(R.id.list_view);

        final SQLiteDatabase db = openOrCreateDatabase(getDatabasePath("user.db").getName(), MODE_PRIVATE, null);

        List<UserBean> userbean = query(db);

        ListAdapter listAdapter = new ListAdapter(userbean, this);
        listView.setAdapter(listAdapter);

    }

    private List<UserBean> query(SQLiteDatabase db) {
        try {
            List<UserBean> userBeanList = new ArrayList<>();
            Cursor cursor = db.rawQuery("select * from user", null);
            String username = null;
            String password = null;
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                username = cursor.getString(cursor.getColumnIndex("username"));
                password = cursor.getString(cursor.getColumnIndex("password"));
                userBeanList.add(new UserBean(username, password));
            }

            return userBeanList;
        } catch (Exception e) {

        }
        return null;
    }


    class ListAdapter extends BaseAdapter {
        private List<UserBean> userBeen;
        private Context context;
        private LayoutInflater inflater;

        public ListAdapter(List<UserBean> userbean, Context completeActivity) {
            this.userBeen = userbean;
            this.context = completeActivity;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return userBeen.size();
        }

        @Override
        public Object getItem(int position) {
            return userBeen.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_list_view, parent,false);

            TextView username = (TextView) view.findViewById(R.id.username);
            TextView password = (TextView) view.findViewById(R.id.password);

            username.setText("账号:" + userBeen.get(position).username);
            password.setText("密码:" + userBeen.get(position).password);

            return view;
        }
    }

    class UserBean {
        String username;
        String password;

        public UserBean(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
