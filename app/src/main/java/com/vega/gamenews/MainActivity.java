package com.vega.gamenews;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout layout1, layout2;
    private EditText username, password;
    private Button login;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FillComponents();
        //Aqui se hace la espera para luego hacer visibkle los layout de log in
        //(Runnable con los layout a mostrar, tiempo de espera en milisegundos)
        handler.postDelayed(runnable, 1500);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(credentials()){

                }else{
                    Toast.makeText(MainActivity.this, "Por favor no dejar campos vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public void FillComponents(){
        layout1 = findViewById(R.id.layout1);
        layout2 = findViewById(R.id.layout2);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.Login);

    }

    private boolean credentials(){
        if(username.getText().toString().equals("") || password.getText().toString().equals("")){ return true; }
        return false;
    }
}
