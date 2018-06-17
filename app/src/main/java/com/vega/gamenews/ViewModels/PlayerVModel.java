package com.vega.gamenews.ViewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.vega.gamenews.Database.Entities.PlayerEntity;
import com.vega.gamenews.Database.Repositories.PlayerRepository;

import java.util.List;

public class PlayerVModel extends AndroidViewModel{

    private PlayerRepository playerRepository;

    public PlayerVModel(@NonNull Application application) {
        super(application);
        playerRepository = new PlayerRepository(application);


    }

    public LiveData<List<PlayerEntity>> getPlayerByGame(String game){
        return playerRepository.getPlayerByGame(game);
    }

    public LiveData<List<PlayerEntity>> getPlayers(){
        return playerRepository.getPlayers();
    }

    public void insert(PlayerEntity player){
        playerRepository.insert(player);
    }
}
