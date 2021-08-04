package it.example.petrisnetsgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonSinglePlayer, buttonTwoPlayers;
    boolean isPressed = false;


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

    public void onBackPressed() {
        if(isPressed){
            //quando si preme due volte indietro entro un secondo
            //chiudi tutte le activity
            finishAffinity();
            System.exit(0);

        }else{
            //quando il doppio click ritarda per 2 secondi
            //display toast
            Toast.makeText(getApplicationContext(),
                    R.string.click_to_exit, Toast.LENGTH_SHORT).show();

            isPressed = true;
        }

        //inizializzo il runnable
        Runnable runnable = () -> isPressed = false;

        //handler delay per 2 secondi
        new Handler().postDelayed(runnable, 2000);


    }
}
