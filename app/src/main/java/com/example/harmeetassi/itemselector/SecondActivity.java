package com.example.harmeetassi.itemselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {
    private TextView imageView;
    private EditText editText;
    private Button button;
    private DBHelper dbHelper;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
//        int dpValue = 50; // margin in dips
//        float d = getBaseContext().getResources().getDisplayMetrics().density;
//        int margin = (int)(dpValue * d);
        imageView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        dbHelper = new DBHelper(this.getApplicationContext());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                dbHelper.insert(edit);
                dbHelper.close();
                Intent it = new Intent();
                it.putExtra("data",edit);
                setResult(RESULT_OK,it);
                finish();
            }
        });

    }
}
