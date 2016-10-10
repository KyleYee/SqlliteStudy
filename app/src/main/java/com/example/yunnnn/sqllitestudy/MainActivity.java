package com.example.yunnnn.sqllitestudy;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.input.InputManager;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String SQL = "create table user(" +
            "user_id INTEGER primary key autoincrement," +
            "username varchar(20) not null," +
            "password varchar(20) not null);";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText username = (EditText) findViewById(R.id.num);
        final EditText password = (EditText) findViewById(R.id.password);

        final SQLiteDatabase db = openOrCreateDatabase(getDatabasePath("user.db").getName(), MODE_PRIVATE, null);


        final Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    Snackbar.make(login, "输入的账号和密码不能为空", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if (!QueryData(db, username.getText().toString(), password.getText().toString())) {
                    Snackbar.make(login, "输入的账号或密码错误", Snackbar.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SAXParser.class);
                    startActivity(intent);
                }
            }
        });

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(login, "登录成功", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, YeePermissiom.class);
                startActivity(intent);
            }
        });

    }

    /**
     * 查询
     *
     * @param db
     * @param username
     * @param password
     * @return
     */
    public boolean QueryData(SQLiteDatabase db, String username, String password) {
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        try {
            String[] strings = {username, password};
            Cursor cursor = db.rawQuery("select * from user where username = ? and password = ?", strings);

            if (cursor.getCount() == 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            db.execSQL(SQL);
        }
        return false;
    }

}
