package it.example.petrisnetsgame;

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

    //MyCanvas myCanvas;
    Button t1, t2, t3, t4, t5, t6, t7, t8, t9;
    Button inizia;
    TextView timeFinish,giocatore2TextTurn;
    ImageView net;
    Chronometer chronometer;
    private AlertDialog.Builder dialogbuilder;
    private AlertDialog dialog;



    //boolean pause_flag = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //myCanvas = new MyCanvas(this);
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
            playerTurn.setText(("E' il turno di " + playerNames[0]));

        }

    }

    public void createFirstGamePopup(){

        dialogbuilder = new AlertDialog.Builder(this);
        final View finishPopupView = getLayoutInflater().inflate(R.layout.finish_popup, null);

        timeFinish = finishPopupView.findViewById(R.id.timeFinishText);
        giocatore2TextTurn = finishPopupView.findViewById(R.id.giocatore2Text);
        inizia = finishPopupView.findViewById(R.id.buttonInizia);

        dialogbuilder.setView(finishPopupView);
        dialog = dialogbuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

        timeFinish.setText(chronometer.getText());

        String[] playerNames = getIntent().getStringArrayExtra("Nomi");
        giocatore2TextTurn.setText(playerNames[1]);

        inizia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MultiplayerGameActivity_player1.this, MultiplayerGameActivity_player1.class);
                startActivity(i);
            }
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

            AlertDialog.Builder builder = new AlertDialog.Builder(MultiplayerGameActivity_player1.this);
            builder.setTitle(R.string.warning);
            builder.setMessage(R.string.warning_message);
            builder.setPositiveButton("Sì", (dialog, which) -> {

                startActivity(new Intent(MultiplayerGameActivity_player1.this, MainActivity.class));

            });

            builder.setNegativeButton("No", (dialog, which) -> {

                dialog.dismiss();

            });
            builder.show();
        }

            public void startT1(View view) {
                if (areDrawablesIdentical(net.getDrawable(), getDrawable(R.drawable.rete1_init))) {
                    net.setImageResource(R.drawable.rete1_t1);
                    createFirstGamePopup();
                    chronometer.stop();

                } else {
                    Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();

                }
            }

    public void startT2(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT3(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT4(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT5(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT6(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT7(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT8(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }

    public void startT9(View view) {
        Toast.makeText(MultiplayerGameActivity_player1.this, R.string.disabilitated_transition, Toast.LENGTH_SHORT).show();
    }
}