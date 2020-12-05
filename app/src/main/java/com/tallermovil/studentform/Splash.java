package com.tallermovil.studentform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer = new Thread(){
          public void run(){
              try{
                  sleep(1000);
              }catch (Exception e){

              }finally {
                  Intent intent = new Intent(Splash.this, Form.class);
                  startActivity(intent);
                  finish();
              }
          }
        };
        timer.start();
    }
}