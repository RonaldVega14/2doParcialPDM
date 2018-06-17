package com.vega.gamenews.Database.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vega.gamenews.API.Desearializer.PlayerD;
import com.vega.gamenews.API.GamesAPI;
import com.vega.gamenews.Database.DBInstance;
import com.vega.gamenews.Database.Daos.PlayerDao;
import com.vega.gamenews.Database.Entities.PlayerEntity;
import com.vega.gamenews.Models.Player;
import com.vega.gamenews.R;

import java.net.SocketTimeoutException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.vega.gamenews.API.GamesAPI.END_POINT;

public class PlayerRepository {

    private PlayerDao playerDao;
    private String token;
    private Context context;

    public PlayerRepository(Application app){

        DBInstance db = DBInstance.getInstance(app);
        playerDao = db.playerDao();

        SharedPreferences preferences = app.getSharedPreferences("log", Context.MODE_PRIVATE);
        token = preferences.getString("token", "");
        context = app.getApplicationContext();

    }

    public void getPlayer(){

        Gson gson = new GsonBuilder().registerTypeAdapter(Player.class, new PlayerD()).create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();

        GamesAPI gamesAPI = retrofit.create(GamesAPI.class);

        Call<List<Player>> jugadores = gamesAPI.getPlayersByGame("Bearer " + token);
        jugadores.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if(response.isSuccessful()){
                    for(Player jugador : response.body()){
                        insert(new PlayerEntity(jugador.getId(), jugador.getName(),
                                jugador.getBiografia(), jugador.getAvatar(),
                                jugador.getGame()));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                if(t instanceof SocketTimeoutException)
                    Toast.makeText(context, "Timed Out", Toast.LENGTH_SHORT).show();
                else if(t instanceof Exception)
                    Toast.makeText(context, R.string.Fail2log2, Toast.LENGTH_LONG).show();

            }
        });

    }

    public LiveData<List<PlayerEntity>> getPlayerByGame(String game){
        return playerDao.getPlayerByGame(game);
    }

    public LiveData<List<PlayerEntity>> getPlayers(){
        return playerDao.getPlayers();
    }

    public void insert(PlayerEntity playerEntity){
        new InsertAsyncTask(playerDao).execute(playerEntity);
    }

    private static class InsertAsyncTask extends AsyncTask<PlayerEntity, Void, Void>{

        private PlayerDao playerDao;

        InsertAsyncTask(PlayerDao playerDao){
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(PlayerEntity... playerEntities) {
            playerDao.insertPlayer(playerEntities[0]);
            return null;
        }
    }
}
