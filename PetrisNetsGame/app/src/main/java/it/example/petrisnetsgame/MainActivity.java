package it.example.petrisnetsgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonSinglePlayer, buttonTwoPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSinglePlayer = findViewById(R.id.buttonSingle);
        buttonTwoPlayers = findViewById(R.id.buttonTwo);


    }

    public void buttonTwoPlayersOnCLick(View view) {
        Intent i = new Intent(this, PlayersActivity.class);
        startActivity(i);

    }
}
