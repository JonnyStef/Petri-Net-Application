package it.example.petrisnetsgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlayersActivity extends AppCompatActivity {

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        player1 = findViewById(R.id.player1editText);
        player2 = findViewById(R.id.player2editText);
    }

    public void buttonAvviaOnClick(View view){

        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();


        Intent intent = new Intent(this, GameActivity.class);
        //salva i nomi inseriti e li porta nella GameActivity
        intent.putExtra("Nomi", new String[] {player1Name, player2Name});
        startActivity(intent);

    }

    public void buttonBackOnClick(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}