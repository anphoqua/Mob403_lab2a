package com.codelabss.anphoqua.http_lab2a;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText name, age;
    Button estimate;
    TextView result;
    String str_name, str_age;

    public static final String SEVER_NAME = "http://192.168.64.2/mob403lab2/ageGET.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameID);
        age = findViewById(R.id.ageID);
        result = findViewById(R.id.resultID);
        estimate = findViewById(R.id.estimateID);

        estimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_name = name.getText().toString().trim();
                str_age = age.getText().toString().trim();

                BackgroundTaskGet backgroundTaskGet = new BackgroundTaskGet(MainActivity.this, result, str_name, str_age);
                backgroundTaskGet.execute();
            }
        });
    }
}
