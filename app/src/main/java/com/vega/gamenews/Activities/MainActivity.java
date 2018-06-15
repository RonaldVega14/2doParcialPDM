package com.vega.gamenews.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vega.gamenews.API.Desearializer.TokenD;
import com.vega.gamenews.API.GamesAPI;
import com.vega.gamenews.Models.Login;
import com.vega.gamenews.R;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.vega.gamenews.API.GamesAPI.END_POINT;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout layout1, layout2, progress;
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
                if(!credentials()){
                    login(username.getText().toString(), password.getText().toString());

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
        progress = findViewById(R.id.progressBar);

    }

    private boolean credentials(){
        if(username.getText().toString().equals("") || password.getText().toString().equals("")){ return true; }
        return false;
    }

    public void login(String user, String pass){

        progress.setVisibility(View.VISIBLE);
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new TokenD())
                .create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit = builder.build();
        GamesAPI gamesAPI = retrofit.create(GamesAPI.class);
        Call<String> call = gamesAPI.login(user, pass);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, R.string.logged, Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = MainActivity.this.getSharedPreferences("log", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token", response.body());
                    //System.out.println("-------------------------------------------------------"+response.body()+" TOKEN-------------------------------------------------");
                    editor.apply();
                    //System.out.println("-------------------------------------------------------"+preferences.contains("token") +" holaaaaaa -------------------------------------------------");
                    //Inicio de segunda actividad, Home Activity.
                    progress.setVisibility(View.GONE);
                    startMain(MainActivity.this);
                }else if(!response.isSuccessful() && response.code()!=200){
                    progress.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "ERROR: " , Toast.LENGTH_SHORT).show();
                }else{
                    progress.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, R.string.Fail2log2, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(t instanceof SocketTimeoutException) {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Timed Out", Toast.LENGTH_SHORT).show();
                }else if(t instanceof Exception) {
                    progress.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "We're sorry, apparently something went wrong, please try again later.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private static void startMain(Activity activity) {
        activity.startActivity(new Intent(activity, HomeActivity.class));
        activity.finish();
    }
}
