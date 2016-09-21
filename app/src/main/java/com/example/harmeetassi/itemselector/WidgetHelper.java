package com.example.harmeetassi.itemselector;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WidgetHelper extends LinearLayout {
    private TextView image;

    public WidgetHelper(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.act_new, this);

        image = (TextView) findViewById(R.id.new_text);
//        box.setHint(hint);

    }

    public TextView getImage() {
        return image;
    }

}
