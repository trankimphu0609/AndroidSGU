package com.trankimphu.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btnHello = (Button) findViewById(R.id.btnHello);
        btnHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtHello = (EditText) findViewById(R.id.edtHello);
                Intent ret = new Intent();
                ret.putExtra("txt", edtHello.getText().toString());
                ret.setType("text/plan");
                setResult(RESULT_OK, ret);
                finishActivity(MainActivity.CODE_HELLO);
                finish();
            }
        });
    }
}