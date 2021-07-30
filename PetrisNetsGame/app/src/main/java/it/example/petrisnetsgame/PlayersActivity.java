package it.example.petrisnetsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayersActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogbuilder;
    private AlertDialog dialog;
    Button avvia, rules;

    private EditText player1;
    private EditText player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        player1 = findViewById(R.id.player1editText);
        player2 = findViewById(R.id.player2editText);

        avvia = findViewById(R.id.buttonAvvia);
        rules = findViewById(R.id.buttonRules);
    }

    public void multiplayerRulesPopup(){

        dialogbuilder = new AlertDialog.Builder(this);
        final View rulesPopupView = getLayoutInflater().inflate(R.layout.multiplayer_rules_popup, null);


        dialogbuilder.setView(rulesPopupView);
        dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);

    }

    public void buttonAvviaOnClick(View view) {

        String player1Name = player1.getText().toString();
        String player2Name = player2.getText().toString();

        if (player1Name.isEmpty()) {
            player1.setError(getString(R.string.empty_warning));
            player1.requestFocus();
            return;

        }

        if (player2Name.isEmpty()) {
            player2.setError(getString(R.string.empty_warning));
            player2.requestFocus();
            return;

        }

        Intent intent = new Intent(this, MultiplayerGameActivity_player1.class);
        intent.putExtra("Nomi", new String[]{player1Name, player2Name});
        startActivity(intent);
    }


   // }

    public void buttonBackOnClick(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    public void buttonRulesOnClick(View view) {
        multiplayerRulesPopup();

    }
}