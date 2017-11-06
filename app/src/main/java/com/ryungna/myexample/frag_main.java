package com.ryungna.myexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class frag_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_main);

        LinearLayout me = (LinearLayout)findViewById(R.id.layoutME);
        LinearLayout group = (LinearLayout)findViewById(R.id.layoutGROUP);
        LinearLayout friends = (LinearLayout)findViewById(R.id.layoutFRIENDS);

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new frag_M()).commit();

            }
        });

        group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new frag_G()).commit();
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.frame,new frag_F()).commit();

            }
        });
    }
}