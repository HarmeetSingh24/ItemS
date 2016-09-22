package com.example.harmeetassi.itemselector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private static WidgetHelper widgetHelper;
    static LinearLayout accountLinear;
    private static ImageView new_button;
    private static Context context;
    private DBHelper dbHelper;
    private TextView textView2;
    private ImageView bounceBallImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        accountLinear = (LinearLayout) findViewById(R.id.my_account);
        new_button = (ImageView) findViewById(R.id.save_widget);
        context = getBaseContext();
        dbHelper = new DBHelper(context);
        textView2 = (TextView) findViewById(R.id.textView2);
        bounceBallImage = (ImageView) findViewById(R.id.bounceBallImage);
        TreeMap<Integer,String> arrayList = new TreeMap<>();
//        List<String> pol = new ArrayList<>(arrayList.values())
        arrayList = dbHelper.getAllDetails();
        if (arrayList.size() != 0) {
            bounceBallImage.setVisibility(View.GONE);
            textView2.setVisibility(View.GONE);
            bounceBallImage.clearAnimation();
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
        }else{
                textView2.setVisibility(View.VISIBLE);
                bounceBallImage.setVisibility(View.VISIBLE);
                bounceBallImage.clearAnimation();
                final TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0,
                        getDisplayHeight());
                transAnim.setStartOffset(500);
                transAnim.setDuration(3000);
                transAnim.setFillAfter(true);
                transAnim.setInterpolator(new BounceInterpolator());
                transAnim.setRepeatCount(Animation.INFINITE);
                transAnim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        bounceBallImage.clearAnimation();
                        final int left = bounceBallImage.getLeft();
                        final int top = bounceBallImage.getTop();
                        final int right = bounceBallImage.getRight();
                        final int bottom = bounceBallImage.getBottom();
                        bounceBallImage.layout(left, top, right, bottom);

                    }
                });
                bounceBallImage.startAnimation(transAnim);


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
                textView2.setVisibility(View.GONE);
                bounceBallImage.setVisibility(View.GONE);
                bounceBallImage.clearAnimation();
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
            else{
                textView2.setVisibility(View.VISIBLE);
                bounceBallImage.setVisibility(View.VISIBLE);
                bounceBallImage.clearAnimation();
                final TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0,
                        getDisplayHeight());
                transAnim.setStartOffset(500);
                transAnim.setDuration(3000);
                transAnim.setFillAfter(true);
                transAnim.setInterpolator(new BounceInterpolator());
                transAnim.setRepeatCount(Animation.INFINITE);
                transAnim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        bounceBallImage.clearAnimation();
                        final int left = bounceBallImage.getLeft();
                        final int top = bounceBallImage.getTop();
                        final int right = bounceBallImage.getRight();
                        final int bottom = bounceBallImage.getBottom();
                        bounceBallImage.layout(left, top, right, bottom);

                    }
                });
                bounceBallImage.startAnimation(transAnim);


            }
        }
    }

    private int getDisplayHeight(){
        return this.getResources().getDisplayMetrics().heightPixels;
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
