package it.example.petrisnetsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonGiocaOnClick(View view){

        Intent intent = new Intent(this, PlayersActivity.class);
        startActivity(intent);

    }
}