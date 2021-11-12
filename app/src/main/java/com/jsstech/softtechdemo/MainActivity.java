
package com.jsstech.softtechdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);


        getSupportFragmentManager().beginTransaction().
              replace(R.id.frame,new FirstFragment()).commit();
              }
}