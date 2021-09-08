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
    TextView timeFinishPlayer1, timeFinishPlayer2, giocatore1Name, winner, deadlock;
    ImageView net;
    Chronometer chronometer;

    @SuppressLint("UseCompatLoadingForDrawables")
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
        TextView target = findViewById(R.id.gameTargetText);

        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        int chosenNet = getIntent().getIntExtra("Rete", 0);

        if (playerNames != null) {
            playerTurn.setText(("E' il turno di " + playerNames[1]));
            playerTurn.setTextColor(getColor(R.color.red));

            settingNet(chosenNet, target);

        }
    }


    public void settingNet(int numberDrawn, TextView target) {
        if (numberDrawn == 1 || numberDrawn == 2) {
            net.setImageResource(R.drawable.rete1_init);
            target.setText((getString(R.string.target_game1)));
        } else if (numberDrawn == 3 || numberDrawn == 4) {
            net.setImageResource(R.drawable.rete2_init);
            target.setText((getString(R.string.target_game2)));
        } else if (numberDrawn == 5 || numberDrawn == 6) {
            net.setImageResource(R.drawable.rete3_init);
            target.setText((getString(R.string.target_game3)));
        } else if (numberDrawn == 7 || numberDrawn == 8) {
            net.setImageResource(R.drawable.rete4_init);
            target.setText((getString(R.string.target_game4)));
        } else {
            net.setImageResource(R.drawable.rete5_init);
            target.setText((getString(R.string.target_game5)));
        }
    }


    public void createEndGamePopup(){

        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View finishGamePopupView = getLayoutInflater().inflate(R.layout.finish_player2_popup,
                null);

        timeFinishPlayer1 = finishGamePopupView.findViewById(R.id.timeFinishPlayer1Text);
        timeFinishPlayer2 = finishGamePopupView.findViewById(R.id.timeFinishPlayer2Text);
        giocatore1Name = finishGamePopupView.findViewById(R.id.player2Text);
        winner = finishGamePopupView.findViewById(R.id.winnerText);
        home = finishGamePopupView.findViewById(R.id.buttonHome);
        restart = finishGamePopupView.findViewById(R.id.buttonRestart);
        String matchWinner;

        dialogbuilder.setView(finishGamePopupView);
        AlertDialog dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        timeFinishPlayer2.setText(chronometer.getText().toString());
        String minutesPlayer2 = (chronometer.getText().toString()).substring(0,1);
        String secondsPlayer2 = (chronometer.getText().toString()).
                substring(chronometer.getText().toString().indexOf(":") + 1);
        int minPlayer2 = Integer.parseInt(minutesPlayer2);
        int secPlayer2 = Integer.parseInt(secondsPlayer2);


        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        giocatore1Name.setText(playerNames[0]);

        CharSequence [] punteggioGiocatore1 = getIntent().
                getCharSequenceArrayExtra("punteggio_giocatore1");
        if(!punteggioGiocatore1[0].toString().equals("DEADLOCK")) {
            timeFinishPlayer1.setText(punteggioGiocatore1[0].toString());
            String minutesPlayer1 = (punteggioGiocatore1[0].toString()).substring(0, 1);
            String secondsPlayer1 = (punteggioGiocatore1[0].toString()).
                    substring(punteggioGiocatore1[0].toString().indexOf(":") + 1);
            int minPlayer1 = Integer.parseInt(minutesPlayer1);
            int secPlayer1 = Integer.parseInt(secondsPlayer1);

            matchWinner = compareTimes(minPlayer1, secPlayer1, minPlayer2, secPlayer2);

            winner.setText(matchWinner);

        } else {
            timeFinishPlayer1.setText(punteggioGiocatore1[0].toString());
            matchWinner = winnerWithDeadlock(timeFinishPlayer1.getText().toString(),
                    timeFinishPlayer2.getText().toString());
            winner.setText(matchWinner);
        }

        if (matchWinner.equals(playerNames[0]))
            winner.setTextColor(ContextCompat.getColor(this, R.color.blue));
        else if (matchWinner.equals(playerNames[1]))
            winner.setTextColor(ContextCompat.getColor(this, R.color.red));


        home.setOnClickListener(v -> {
            Intent i = new Intent(MultiplayerGameActivity_player2.this,
                    MainActivity.class);
            startActivity(i);
        });


        restart.setOnClickListener(v -> {
            Intent i = new Intent(MultiplayerGameActivity_player2.this,
                    MultiplayerGameActivity_player1.class);
            i.putExtra("Nomi", playerNames);
            startActivity(i);
        });
    }


    public void create_deadlock_player2_popup(){

        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View deadlockPlayer2PopupView = getLayoutInflater().
                inflate(R.layout.deadlock_player2_popup, null);

        timeFinishPlayer1 = deadlockPlayer2PopupView.findViewById(R.id.timeFinishPlayer1Text);

        giocatore1Name = deadlockPlayer2PopupView.findViewById(R.id.player2Text);
        deadlock = deadlockPlayer2PopupView.findViewById(R.id.deadlockText);
        winner = deadlockPlayer2PopupView.findViewById(R.id.winnerText);
        home = deadlockPlayer2PopupView.findViewById(R.id.buttonHome);
        restart = deadlockPlayer2PopupView.findViewById(R.id.buttonRestart);

        dialogbuilder.setView(deadlockPlayer2PopupView);
        AlertDialog dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        giocatore1Name.setText(playerNames[0]);

        CharSequence [] punteggioGiocatore1 = getIntent().
                getCharSequenceArrayExtra("punteggio_giocatore1");
        if(punteggioGiocatore1 == null)
            timeFinishPlayer1.setText(R.string.deadlock);
        else
            timeFinishPlayer1.setText(punteggioGiocatore1[0].toString());

        String matchWinner = winnerWithDeadlock(timeFinishPlayer1.getText().toString(),
                deadlock.getText().toString());

        winner.setText(matchWinner);

        if(matchWinner.equals(playerNames[0]))
            winner.setTextColor(ContextCompat.getColor(this, R.color.blue));
        else if(matchWinner.equals(playerNames[1]))
            winner.setTextColor(ContextCompat.getColor(this, R.color.red));


        home.setOnClickListener(v -> {
            Intent i = new Intent(MultiplayerGameActivity_player2.this,
                    MainActivity.class);
            startActivity(i);
        });

        restart.setOnClickListener(v -> {
            Intent i = new Intent(MultiplayerGameActivity_player2.this,
                    MultiplayerGameActivity_player1.class);
            i.putExtra("Nomi", playerNames);
            startActivity(i);
        });
    }


    public String winnerWithDeadlock (String player1Final, String player2Final){

        String[] playerNames = getIntent().getStringArrayExtra("Nomi");

        if(player1Final.equals("DEADLOCK") && player2Final.equals("DEADLOCK"))
            return getString(R.string.draw_message);
        else if(player1Final.equals("DEADLOCK") && !player2Final.equals("DEADLOCK"))
            return playerNames[1];
        else
            return playerNames[0];
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

        AlertDialog.Builder builder = new AlertDialog.Builder(
                MultiplayerGameActivity_player2.this);
        builder.setTitle(R.string.warning);
        builder.setMessage(R.string.warning_message);
        builder.setPositiveButton("Sì", (dialog, which) ->
                startActivity(new Intent(MultiplayerGameActivity_player2.this,
                        MainActivity.class)));
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    // Inizio dei metodi delle transizioni

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT1(View view) {
        if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_init)))
            net.setImageResource(R.drawable.rete1_state1);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_init)))
            net.setImageResource(R.drawable.rete2_state1);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_init))) {
            net.setImageResource(R.drawable.rete3_state1_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_init)))
            net.setImageResource(R.drawable.rete4_state1);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state3)))
            net.setImageResource(R.drawable.rete4_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state6)))
            net.setImageResource(R.drawable.rete4_state9);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state3)))
            net.setImageResource(R.drawable.rete5_state8);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state13)))
            net.setImageResource(R.drawable.rete5_state15);
        else {
            Toast.makeText(this, R.string.disabilitated_transition,
                    Toast.LENGTH_SHORT).show();
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT2(View view) {
        if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state5)))
            net.setImageResource(R.drawable.rete1_state8);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state4))) {
            net.setImageResource(R.drawable.rete1_state7_deadlock);
            create_deadlock_player2_popup();
        } else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state9)))
            net.setImageResource(R.drawable.rete1_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state4))) {
            net.setImageResource(R.drawable.rete2_state7_deadlock);
            create_deadlock_player2_popup();
        } else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state5)))
            net.setImageResource(R.drawable.rete2_state8);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state9))) {
            net.setImageResource(R.drawable.rete2_state12_deadlock);
            create_deadlock_player2_popup();
        } else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state10)))
            net.setImageResource(R.drawable.rete2_state13);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_init)))
            net.setImageResource(R.drawable.rete3_state2);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state1)))
            net.setImageResource(R.drawable.rete4_state4);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state5)))
            net.setImageResource(R.drawable.rete4_state8);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state9)))
            net.setImageResource(R.drawable.rete4_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_init)))
            net.setImageResource(R.drawable.rete5_state1);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state1))){
            net.setImageResource(R.drawable.rete5_state4_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state2)))
            net.setImageResource(R.drawable.rete5_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state7)))
            net.setImageResource(R.drawable.rete5_state10);
        else
            Toast.makeText(this, R.string.disabilitated_transition,
                    Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT3(View view) {
        if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state1)))
            net.setImageResource(R.drawable.rete1_state2);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state1)))
            net.setImageResource(R.drawable.rete2_state2);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_init)))
            net.setImageResource(R.drawable.rete3_state3);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state5)))
            net.setImageResource(R.drawable.rete4_state9);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state8)))
            net.setImageResource(R.drawable.rete4_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state10))) {
            net.setImageResource(R.drawable.rete4_state13_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_init)))
            net.setImageResource(R.drawable.rete5_state2);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state1)))
            net.setImageResource(R.drawable.rete5_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state2)))
            net.setImageResource(R.drawable.rete5_state6);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state7)))
            net.setImageResource(R.drawable.rete5_state11);
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT4(View view) {
        if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state2)))
            net.setImageResource(R.drawable.rete1_state3);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state2)))
            net.setImageResource(R.drawable.rete2_state3);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_init)))
            net.setImageResource(R.drawable.rete3_state4);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state3)))
            net.setImageResource(R.drawable.rete4_state6);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state4)))
            net.setImageResource(R.drawable.rete4_state7);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state8)))
            net.setImageResource(R.drawable.rete4_state10);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state11))) {
            net.setImageResource(R.drawable.rete4_state13_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state12)))
            net.setImageResource(R.drawable.rete4_state14);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state15))) {
            net.setImageResource(R.drawable.rete4_state17_final);
            createEndGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state3))) {
            net.setImageResource(R.drawable.rete5_state9_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state13))) {
            net.setImageResource(R.drawable.rete5_state16_deadlock);
            create_deadlock_player2_popup();
        }
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT5(View view) {
        if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state2)))
            net.setImageResource(R.drawable.rete1_state4);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state5)))
            net.setImageResource(R.drawable.rete2_state9);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state3)))
            net.setImageResource(R.drawable.rete2_state5);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state6)))
            net.setImageResource(R.drawable.rete2_state10);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state8))) {
            net.setImageResource(R.drawable.rete2_state12_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state2)))
            net.setImageResource(R.drawable.rete3_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state12)))
            net.setImageResource(R.drawable.rete4_state15);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state14))) {
            net.setImageResource(R.drawable.rete4_state17_final);
            createEndGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state10)))
            net.setImageResource(R.drawable.rete5_state13);
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT6(View view) {
        if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state5))) {
            net.setImageResource(R.drawable.rete3_state8_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state6)))
            net.setImageResource(R.drawable.rete3_state9);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state11))) {
            net.setImageResource(R.drawable.rete3_state12_final);
            createEndGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state8)))
            net.setImageResource(R.drawable.rete4_state12);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state10)))
            net.setImageResource(R.drawable.rete4_state14);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_init)))
            net.setImageResource(R.drawable.rete5_state3);
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT7(View view) {
        if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state3)))
            net.setImageResource(R.drawable.rete1_state5);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state6)))
            net.setImageResource(R.drawable.rete1_state9);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state2)))
            net.setImageResource(R.drawable.rete2_state4);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state3)))
            net.setImageResource(R.drawable.rete3_state6);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_init))) {
            net.setImageResource(R.drawable.rete4_state2_dealock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state1)))
            net.setImageResource(R.drawable.rete4_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state8))) {
            net.setImageResource(R.drawable.rete5_state12_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state15))) {
            net.setImageResource(R.drawable.rete5_state17_final);
            createEndGamePopup();
        }
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT8(View view) {
        if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state3)))
            net.setImageResource(R.drawable.rete1_state6);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state5)))
            net.setImageResource(R.drawable.rete1_state9);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state6))) {
            net.setImageResource(R.drawable.rete1_state10_deadlock);
            create_deadlock_player2_popup();
        }
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state8)))
            net.setImageResource(R.drawable.rete1_state11);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state3)))
            net.setImageResource(R.drawable.rete2_state6);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state6))) {
            net.setImageResource(R.drawable.rete2_state11_deadlock);
            create_deadlock_player2_popup();
        }
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state5)))
            net.setImageResource(R.drawable.rete2_state10);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state8)))
            net.setImageResource(R.drawable.rete2_state13);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state6))) {
            net.setImageResource(R.drawable.rete3_state10_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_init)))
            net.setImageResource(R.drawable.rete4_state3);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state1)))
            net.setImageResource(R.drawable.rete4_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state4)))
            net.setImageResource(R.drawable.rete4_state8);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state7)))
            net.setImageResource(R.drawable.rete4_state10);
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void startT9(View view) {
        if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state11))) {
            net.setImageResource(R.drawable.rete1_state12);
            createEndGamePopup();
        }
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state13))) {
            net.setImageResource(R.drawable.rete2_state14_final);
            createEndGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state4))) {
            net.setImageResource(R.drawable.rete3_state7_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state6)))
            net.setImageResource(R.drawable.rete3_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state9))) {
            net.setImageResource(R.drawable.rete3_state12_final);
            createEndGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state12))) {
            net.setImageResource(R.drawable.rete4_state16_deadlock);
            create_deadlock_player2_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state2)))
            net.setImageResource(R.drawable.rete5_state7);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state5)))
            net.setImageResource(R.drawable.rete5_state10);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state6)))
            net.setImageResource(R.drawable.rete5_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state11))) {
            net.setImageResource(R.drawable.rete5_state14_deadlock);
            create_deadlock_player2_popup();
        }
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }
}