package com.servicedeskmanager.servicedesk;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

public class ItemAssetList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_asset_list);

        TextView inveId =(TextView)this.findViewById(R.id.item_inventory_id);
        inveId.setText("You are welcome",TextView.BufferType.SPANNABLE);
        Spannable spn = (Spannable) inveId.getText();
        spn.setSpan(new BackgroundColorSpan(Color.RED), 0, 7,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spn.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


    }
}
