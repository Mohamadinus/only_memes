package com.example.onlymemes;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.onlymemes.databinding.ActivityMainBinding;
import com.iammert.library.readablebottombar.ReadableBottomBar;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main,new MemeFragment());
        transaction.commit();
        binding.readableBar.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int i) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (i) {
                    case 0:
                        transaction.replace(R.id.main,new MemeFragment());
                        break;
                    case 1:
                        transaction.replace(R.id.main,new DankmemeFragment());
                        break;
                }
                transaction.commit();
            }
        });

    }

}