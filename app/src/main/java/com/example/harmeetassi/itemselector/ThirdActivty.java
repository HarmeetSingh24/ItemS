package com.example.harmeetassi.itemselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ThirdActivty extends AppCompatActivity {
    private TextView imageView;
    private EditText editText;
    private Button button,button1;
    private DBHelper dbHelper;
    int name;
    String detail;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);
//        int dpValue = 50; // margin in dips
//        float d = getBaseContext().getResources().getDisplayMetrics().density;
//        int margin = (int)(dpValue * d);
        Bundle b = getIntent().getExtras();
        name = b.getInt("name");
        detail = b.getString("detail");
        Log.d("id",name+"pol"+detail);
        imageView = (TextView) findViewById(R.id.textView1);
        editText = (EditText) findViewById(R.id.editText1);
        editText.setText(detail);
        button = (Button) findViewById(R.id.button1);
        button1 = (Button) findViewById(R.id.button2);
        dbHelper = new DBHelper(this.getApplicationContext());
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                dbHelper.delete(name);
                Intent it = new Intent();
                it.putExtra("data",edit);
                setResult(RESULT_OK,it);
                finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                dbHelper.update(edit,name);
                Intent it = new Intent();
                it.putExtra("data",edit);
                setResult(RESULT_OK,it);
                finish();
            }
        });

    }
}
