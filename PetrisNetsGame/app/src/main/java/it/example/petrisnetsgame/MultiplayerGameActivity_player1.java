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

public class MultiplayerGameActivity_player1 extends AppCompatActivity {

    Button t1, t2, t3, t4, t5, t6, t7, t8, t9;
    Button inizia;
    TextView timeFinish, giocatore2TextTurn;
    ImageView net;
    Chronometer chronometer;
    int chosenNet;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView target = findViewById(R.id.gameTargetText);
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
            playerTurn.setText(("E' il turno di " + playerNames[0]));
            playerTurn.setTextColor(getColor(R.color.blue));
            chosenNet = netSelection();
            setTarget(target);
        }
    }


    public void createFirstGamePopup() {
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View finishPopupView = getLayoutInflater().inflate(R.layout.finish_player1_popup,
                null);

        timeFinish = finishPopupView.findViewById(R.id.timeFinishText);
        giocatore2TextTurn = finishPopupView.findViewById(R.id.giocatore2Text);
        inizia = finishPopupView.findViewById(R.id.buttonInizia);

        dialogbuilder.setView(finishPopupView);
        AlertDialog dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        timeFinish.setText(chronometer.getText());
        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        giocatore2TextTurn.setText(playerNames[1]);

        inizia.setOnClickListener(v -> {

            Intent i = new Intent(MultiplayerGameActivity_player1.this,
                    MultiplayerGameActivity_player2.class);
            i.putExtra("punteggio_giocatore1", new CharSequence[]{chronometer.getText()});
            i.putExtra("Nomi", playerNames);
            i.putExtra("Rete",chosenNet);
            startActivity(i);
        });
    }


    public void create_deadlock_player1_popup() {

        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        final View deadlockPopupView = getLayoutInflater().inflate(R.layout.deadlock_player1_popup,
                null);

        giocatore2TextTurn = deadlockPopupView.findViewById(R.id.giocatore2Text);
        inizia = deadlockPopupView.findViewById(R.id.buttonInizia);

        dialogbuilder.setView(deadlockPopupView);
        AlertDialog dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        giocatore2TextTurn.setText(playerNames[1]);

        inizia.setOnClickListener(v -> {

            Intent i = new Intent(MultiplayerGameActivity_player1.this,
                    MultiplayerGameActivity_player2.class);
            i.putExtra("Nomi", playerNames);
            i.putExtra("punteggio_giocatore1", new CharSequence[]{"DEADLOCK"});
            i.putExtra("Rete",chosenNet);
            startActivity(i);
        });
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

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.warning);
        builder.setMessage(R.string.warning_message);
        builder.setPositiveButton("Sì", (dialog, which) ->
                startActivity(new Intent(MultiplayerGameActivity_player1.this,
                        MainActivity.class)));

        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.show();
    }


    public int netSelection() {
        int risultato = (int) (Math.random() * 10);

        if (risultato == 1 || risultato == 2) {
            net.setImageResource(R.drawable.rete1_init);
            return risultato;
        } else if (risultato == 3 || risultato == 4) {
            net.setImageResource(R.drawable.rete2_init);
            return risultato;
        } else if (risultato == 5 || risultato == 6) {
            net.setImageResource(R.drawable.rete3_init);
            return risultato;
        } else if (risultato == 7 || risultato == 8) {
            net.setImageResource(R.drawable.rete4_init);
            return risultato;
        } else {
            net.setImageResource(R.drawable.rete5_init);
            return risultato;
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void setTarget(TextView target){
        if ((areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_init))))
            target.setText((getString(R.string.target_game1)));
        else if ((areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_init))))
            target.setText((getString(R.string.target_game2)));
        else if ((areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_init))))
            target.setText((getString(R.string.target_game3)));
        else if ((areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_init))))
            target.setText((getString(R.string.target_game4)));
        else if ((areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_init))))
            target.setText((getString(R.string.target_game5)));

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
            create_deadlock_player1_popup();
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
            create_deadlock_player1_popup();
        } else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state9)))
            net.setImageResource(R.drawable.rete1_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state4))) {
            net.setImageResource(R.drawable.rete2_state7_deadlock);
            create_deadlock_player1_popup();
        } else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state5)))
            net.setImageResource(R.drawable.rete2_state8);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state9))) {
            net.setImageResource(R.drawable.rete2_state12_deadlock);
            create_deadlock_player1_popup();
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
            create_deadlock_player1_popup();
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
            create_deadlock_player1_popup();
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
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state12)))
            net.setImageResource(R.drawable.rete4_state14);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state15))) {
            net.setImageResource(R.drawable.rete4_state17_final);
            createFirstGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state3))) {
            net.setImageResource(R.drawable.rete5_state9_deadlock);
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state13))) {
            net.setImageResource(R.drawable.rete5_state16_deadlock);
            create_deadlock_player1_popup();
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
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state2)))
            net.setImageResource(R.drawable.rete3_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state12)))
            net.setImageResource(R.drawable.rete4_state15);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state14))) {
            net.setImageResource(R.drawable.rete4_state17_final);
            createFirstGamePopup();
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
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state6)))
            net.setImageResource(R.drawable.rete3_state9);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state11))) {
            net.setImageResource(R.drawable.rete3_state12_final);
            createFirstGamePopup();
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
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state1)))
            net.setImageResource(R.drawable.rete4_state5);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state8))) {
            net.setImageResource(R.drawable.rete5_state12_deadlock);
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state15))) {
            net.setImageResource(R.drawable.rete5_state17_final);
            createFirstGamePopup();
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
            create_deadlock_player1_popup();
        }
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_state8)))
            net.setImageResource(R.drawable.rete1_state11);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state3)))
            net.setImageResource(R.drawable.rete2_state6);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state5)))
            net.setImageResource(R.drawable.rete2_state10);
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state6))) {
            net.setImageResource(R.drawable.rete2_state11_deadlock);
            create_deadlock_player1_popup();
        }
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state8)))
            net.setImageResource(R.drawable.rete2_state13);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state6))) {
            net.setImageResource(R.drawable.rete3_state10_deadlock);
            create_deadlock_player1_popup();
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
            createFirstGamePopup();
        }
        else if(areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete2_state13))) {
            net.setImageResource(R.drawable.rete2_state14_final);
            createFirstGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state4))) {
            net.setImageResource(R.drawable.rete3_state7_deadlock);
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state6)))
            net.setImageResource(R.drawable.rete3_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete3_state9))) {
            net.setImageResource(R.drawable.rete3_state12_final);
            createFirstGamePopup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete4_state12))) {
            net.setImageResource(R.drawable.rete4_state16_deadlock);
            create_deadlock_player1_popup();
        }
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state2)))
            net.setImageResource(R.drawable.rete5_state7);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state5)))
            net.setImageResource(R.drawable.rete5_state10);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state6)))
            net.setImageResource(R.drawable.rete5_state11);
        else if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete5_state11))) {
            net.setImageResource(R.drawable.rete5_state14_deadlock);
            create_deadlock_player1_popup();
        }
        else
            Toast.makeText(this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }
}