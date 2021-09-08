package it.example.petrisnetsgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonPlay;
    boolean isPressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = findViewById(R.id.buttonPlay);

    }

    public void buttonGiocaOnCLick(View view) {
        Intent i = new Intent(this, PlayersActivity.class);
        startActivity(i);
    }

    public void onBackPressed() {
        if(isPressed){

            finishAffinity();
            System.exit(0);

        }else{
            Toast.makeText(getApplicationContext(),
                    R.string.click_to_exit,
                    Toast.LENGTH_SHORT).show();

            isPressed = true;
        }

        Runnable runnable = () -> isPressed = false;
        new Handler().postDelayed(runnable, 2000);
    }
}
