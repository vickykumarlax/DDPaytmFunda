package com.example.ddpaytmfunda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainRecyleview extends AppCompatActivity {
RecyclerView rewv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_recyleview);


        rewv=(RecyclerView)findViewById(R.id.recv);
        rewv.setLayoutManager(new LinearLayoutManager(this));

      String  data[]={"C","C++","JAVA","PHP","KOTLIN","PYTHON",".NET","JQUERY","MYSQL","HTML","SWIFT"};
        rewv.setAdapter(new myAdapter(data));

    }
}