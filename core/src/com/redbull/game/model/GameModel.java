package com.redbull.game.model;

import com.badlogic.gdx.Gdx;
import com.redbull.game.PylonTypes.KnifePylon;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.RedBullGame;
import com.redbull.game.model.Entities.PylonModel;
import com.badlogic.gdx.utils.Pool;
import com.redbull.game.PylonTypes.NormalPylon;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {


    private static GameModel instance;
    private ArrayList<PylonType> pylonTypes;

    private void createPylonTypes(){


        PylonType normal = new NormalPylon();
        PylonType knife = new KnifePylon();
        //introduzir propriedades do tipo normal

        pylonTypes.add(normal);
        pylonTypes.add(knife);

    }

    public static GameModel getInstance(){
        if (instance == null){
            instance = new GameModel();
        }
        return instance;
    }

    Pool<PylonModel> pylonPool;

    private final int distancePylons = 500;
    private final int screenMargin = 200;

    ArrayList<PylonModel>  Pylons;

    private GameModel(){
        pylonTypes = new ArrayList<PylonType>();
        createPylonTypes();

        Pylons = new ArrayList<PylonModel>();
        pylonPool = new Pool<PylonModel>() {
            @Override
            protected PylonModel newObject() {
                int randomNum = ThreadLocalRandom.current().nextInt(0, pylonTypes.size());
                return new PylonModel(screenMargin,pylonTypes.get(randomNum));
            }
        };

        PylonModel pyl = pylonPool.obtain();
        Pylons.add(pyl);
    }


    public int getScreenMargin() {
        return screenMargin;
    }

    public Pool<PylonModel> getPylonPool() {
        return pylonPool;
    }

    public ArrayList<PylonModel> getPylons() {
        return Pylons;
    }

    public void update(float delta){
        float velocity = 6;
    //distancia entre pylons
    if(Pylons.get(Pylons.size() - 1).getX()<= Gdx.graphics.getWidth()-distancePylons){
        Pylons.add(pylonPool.obtain());
    }

    //elimina pylons sai ecra
    if(Pylons.get(0).getX()< 0 - screenMargin){
        Pylons.remove(0);
    }

    for(PylonModel temp : Pylons)
        temp.update(velocity);
    }



}

