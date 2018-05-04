package com.redbull.game.model;

import com.redbull.game.model.Entities.PylonModel;
import com.badlogic.gdx.utils.Pool;
import java.util.*;

public class GameModel {


    private static GameModel instance;

    public static GameModel getInstance(){
        if (instance == null){
            instance = new GameModel();
        }
        return instance;
    }

    Pool<PylonModel> pylonPool = new Pool<PylonModel>() {
        @Override
        protected PylonModel newObject() {
            return new PylonModel();
        }
    };

    ArrayList<PylonModel> Pylons;

    private GameModel(){
    PylonModel pyl = pylonPool.obtain();
    Pylons.add(pyl);
    }



    public Pool<PylonModel> getPylonPool() {
        return pylonPool;
    }

    public void update(float delta){

    //fazer update do vetor de pylons a das respetivas posicoes


    }



}

