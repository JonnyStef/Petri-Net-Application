package it.example.petrisnetsgame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MultiplayerGameActivity_player2 extends AppCompatActivity {

    Button t1, t2, t3, t4, t5, t6, t7, t8, t9;
    Button restart, home;
    TextView timeFinishPlayer1, timeFinishPlayer2, giocatore1Name, winner;
    ImageView net;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        net = findViewById(R.id.imageNet);

        t1 = findViewById(R.id.buttonT1);
        t2 = findViewById(R.id.buttonT2);
        t3 = findViewById(R.id.buttonT3);
        t4 = findViewById(R.id.buttonT4);
        t5 = findViewById(R.id.buttonT5);
        t6 = findViewById(R.id.buttonT6);
        t7 = findViewById(R.id.buttonT7);
        t8 = findViewById(R.id.buttonT8);
        t9 = findViewById(R.id.buttonT9);

        chronometer = findViewById(R.id.cronometer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        TextView playerTurn = findViewById(R.id.playerTurnText);
        String[] playerNames = getIntent().getStringArrayExtra("Nomi");

        if (playerNames != null) {
            playerTurn.setText(("E' il turno di " + playerNames[1]));

        }
    }

    public void createEndGamePopup(){

        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View finishGamePopupView = getLayoutInflater().inflate(R.layout.finish_player2_popup, null);

        timeFinishPlayer1 = finishGamePopupView.findViewById(R.id.timeFinishPlayer1Text);
        timeFinishPlayer2 = finishGamePopupView.findViewById(R.id.timeFinishPlayer2Text);
        giocatore1Name = finishGamePopupView.findViewById(R.id.player2Text);
        winner = finishGamePopupView.findViewById(R.id.winnerText);
        home = finishGamePopupView.findViewById(R.id.buttonHome);
        restart = finishGamePopupView.findViewById(R.id.buttonRestart);


        dialogbuilder.setView(finishGamePopupView);
        AlertDialog dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        timeFinishPlayer2.setText(chronometer.getText().toString());
        String minutesPlayer2 = (chronometer.getText().toString()).substring(0,1);
        String secondsPlayer2 = (chronometer.getText().toString()).substring(chronometer.getText().toString().indexOf(":") + 1);
        int minPlayer2 = Integer.parseInt(minutesPlayer2);
        int secPlayer2 = Integer.parseInt(secondsPlayer2);


        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        giocatore1Name.setText(playerNames[0]);

        CharSequence [] punteggioGiocatore1 = getIntent().getCharSequenceArrayExtra("punteggio_giocatore1");
        timeFinishPlayer1.setText(punteggioGiocatore1[0].toString());
        String minutesPlayer1 = (punteggioGiocatore1[0].toString()).substring(0,1);
        String secondsPlayer1 = (punteggioGiocatore1[0].toString()).substring(punteggioGiocatore1[0].toString().indexOf(":") + 1);
        int minPlayer1 = Integer.parseInt(minutesPlayer1);
        int secPlayer1 = Integer.parseInt(secondsPlayer1);

        String matchWinner = compareTimes(minPlayer1, secPlayer1, minPlayer2, secPlayer2);

        winner.setText(matchWinner);
        if(matchWinner.equals(playerNames[0]))
            winner.setTextColor(ContextCompat.getColor(this, R.color.blue));
        else if(matchWinner.equals(playerNames[1]))
            winner.setTextColor(ContextCompat.getColor(this, R.color.red));


        home.setOnClickListener(v -> {
            Intent i = new Intent(MultiplayerGameActivity_player2.this, MainActivity.class);
            startActivity(i);
        });

        restart.setOnClickListener(v -> {
            Intent i = new Intent(MultiplayerGameActivity_player2.this,
                    MultiplayerGameActivity_player1.class);
            i.putExtra("Nomi", playerNames);
            startActivity(i);
        });


    }

    public String compareTimes(int min1, int sec1, int min2, int sec2) {

        String[] playerNames = getIntent().getStringArrayExtra("Nomi");

        if (min1 < min2)
            return playerNames[0]  ;
        else if (min1 >min2)
            return playerNames[1];

        else{

            if(sec1 < sec2)
                return playerNames[0];
            else if (sec1 >sec2)
                return playerNames[1];
            else
                return getString(R.string.draw_message);
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(false);
    }

    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB) {
        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();
        return (stateA != null && stateA.equals(stateB));
    }


    public void buttonEscOnClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MultiplayerGameActivity_player2.this);
        builder.setTitle(R.string.warning);
        builder.setMessage(R.string.warning_message);
        builder.setPositiveButton("Sì", (dialog, which) ->
                startActivity(new Intent(MultiplayerGameActivity_player2.this, MainActivity.class)));

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT1(View view) {
        if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_init))) {
            net.setImageResource(R.drawable.rete1_t1);
            chronometer.stop();
            createEndGamePopup();

        } else {
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();

        }
    }

    public void startT2(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT3(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT4(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT5(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT6(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT7(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT8(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT9(View view) {
        Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }


}