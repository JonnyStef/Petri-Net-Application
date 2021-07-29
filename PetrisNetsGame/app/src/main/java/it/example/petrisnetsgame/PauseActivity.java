package it.example.petrisnetsgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class PauseActivity extends AppCompatActivity {

    Button resume, esc;
    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pause);

        resume= findViewById(R.id.buttonResume);
        esc= findViewById(R.id.buttonEsc);


    }

    public void buttonEscOnClick(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(PauseActivity.this);
        builder.setTitle(R.string.warning);
        builder.setMessage(R.string.warning_message);
        builder.setPositiveButton("Sì", (dialog, which) -> {

            startActivity(new Intent(PauseActivity.this, MainActivity.class));

        });

        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });
        builder.show();

}

    public void buttonResumeOnClick(View view) {

        startActivity(new Intent(PauseActivity.this, MultiplayerGameActivity_player1.class));
        chronometer.start();

    }
}