package com.example.listycity;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView cityList;
    private ArrayAdapter<String> cityAdapter;
    private ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton","Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing","Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataList);
        cityList.setAdapter(cityAdapter);
        Button add = findViewById(R.id.add);
        Button confirm = findViewById(R.id.confirm);
        Button delete = findViewById(R.id.delete);
        EditText editCity = findViewById(R.id.type_city);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = cityList.getCheckedItemPosition();
                if(pos != ListView.INVALID_POSITION) {
                    dataList.remove(pos);
                }
                    cityList.clearChoices();
                    cityAdapter.notifyDataSetChanged();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                editCity.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
                editCity.requestFocus();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = editCity.getText().toString();
                if(!city.isEmpty()){
                    dataList.add(city);
                    cityAdapter.notifyDataSetChanged();
                    editCity.setText("");
                    confirm.setVisibility(View.GONE);
                    editCity.setVisibility(View.GONE);
                    editCity.clearFocus();
                }
            }
        });

}}