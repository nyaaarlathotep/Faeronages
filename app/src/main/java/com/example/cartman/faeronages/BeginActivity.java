package com.example.cartman.faeronages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cartman.faeronages.game.ActivityCollector;

public class BeginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);


        Button buttonStart =(Button) findViewById(R.id.buttonStart);
        Button buttonOptions=(Button)findViewById(R.id.buttonOptions);
        Button buttonQuit=(Button)findViewById(R.id.buttonQuit);
        Button buttonAchieve=(Button)findViewById(R.id.buttonAchieve);

        buttonStart.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v){
            Intent intent=new Intent(BeginActivity.this,Roll.class);
            startActivity(intent);
        }
    });
        buttonOptions.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent=new Intent(BeginActivity.this,options.class);
                startActivity(intent);
            }
        });
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                ActivityCollector.finishAll();
            }
        });
    }

}
