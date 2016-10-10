package com.example.yunnnn.sqllitestudy;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by yunnnn on 2016/10/8.
 */

public class RegisterActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registe_layout);

        final SQLiteDatabase db = openOrCreateDatabase(getDatabasePath("user.db").getName(), MODE_PRIVATE, null);

        final EditText username = (EditText) findViewById(R.id.register_num);
        final EditText password = (EditText) findViewById(R.id.register_password);
        final EditText password_sure = (EditText) findViewById(R.id.register_password_sure);

        final Button complete = (Button) findViewById(R.id.register_complete);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRegister(username, password, password_sure, db, complete);
            }
        });
    }

    private void checkRegister(EditText username, EditText password, EditText password_sure, SQLiteDatabase db, Button complete) {
        if (username.getText().toString() != "" && (password.getText().toString().equals(password_sure.getText().toString()))) {
            try {
                db.execSQL("insert into user(username,password) values(?,?);", new String[]{username.getText().toString(), password.getText().toString()});
            } catch (Exception e) {
                try {
                    db.execSQL(MainActivity.SQL);
                } catch (Exception e1) {
                    Snackbar.make(complete, "表重复了", Snackbar.LENGTH_SHORT).show();
                }
            }

            finish();

        } else {
            Snackbar.make(complete, "输入的密码错误", Snackbar.LENGTH_SHORT).show();
        }
    }
}
