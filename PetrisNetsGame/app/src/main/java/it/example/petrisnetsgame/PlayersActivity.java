package it.example.petrisnetsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
    }

    public void buttonAvviaOnClick(View view){

        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }

    public void buttonBackOnClick(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}