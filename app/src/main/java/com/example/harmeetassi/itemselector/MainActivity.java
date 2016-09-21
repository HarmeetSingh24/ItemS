package com.example.harmeetassi.itemselector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private static WidgetHelper widgetHelper;
    static LinearLayout accountLinear;
    private static ImageView new_button;
    private static Context context;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        accountLinear = (LinearLayout) findViewById(R.id.my_account);
        new_button = (ImageView) findViewById(R.id.save_widget);
        context = getBaseContext();
        dbHelper = new DBHelper(context);
        TreeMap<Integer,String> arrayList = new TreeMap<>();
        arrayList = dbHelper.getAllDetails();
        if (arrayList.size() != 0) {
            for (Map.Entry<Integer,String> i:arrayList.entrySet()) {
                WidgetHelper widgetHelper = new WidgetHelper(context);
                final String temp = i.getValue();
                widgetHelper.getImage().setText(temp);
                accountLinear.addView(widgetHelper);
                final int finalI = i.getKey();
                widgetHelper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        find(finalI,temp);
                    }
                });
            }
        }
        new_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(it, 1);
                overridePendingTransition(R.animator.push_left_in, R.animator.push_left_out);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            accountLinear.removeAllViews();
            TreeMap<Integer,String> arrayList = new TreeMap<>();
            arrayList = dbHelper.getAllDetails();
            if (arrayList.size() != 0) {
                for (Map.Entry<Integer,String> i:arrayList.entrySet()) {
                    WidgetHelper widgetHelper = new WidgetHelper(context);
                    final String temp = i.getValue();
                    widgetHelper.getImage().setText(temp);
                    accountLinear.addView(widgetHelper);
                    final int finalI = i.getKey();
                    widgetHelper.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            find(finalI,temp);
                        }
                    });
                }
            }
        }
    }


    public void find(int finalI,String temp) {
        Intent it = new Intent(MainActivity.this, ThirdActivty.class);
        Log.d("hello",finalI+temp+"lop");
        Bundle b = new Bundle();
        b.putString("detail",temp);
        b.putInt("name",finalI);
        it.putExtras(b);
        startActivityForResult(it, 1);
        overridePendingTransition(R.animator.push_left_in, R.animator.push_left_out);
    }
}
