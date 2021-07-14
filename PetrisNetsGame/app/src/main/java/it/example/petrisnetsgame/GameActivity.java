package it.example.petrisnetsgame;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView playerTurn = findViewById(R.id.playerTurnText);
        String [] playerNames = getIntent().getStringArrayExtra("Nomi");

        if(playerNames != null){
            playerTurn.setText(("E' il turno di " + playerNames[0]));

        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }
}