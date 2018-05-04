package com.redbull.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.redbull.game.model.Entities.BackgroundModel;
import com.redbull.game.view.GameView;
import com.redbull.game.RedBullGame;

public class GameModel {
    private BackgroundModel background;
    private BackgroundModel background2;


    private static GameModel instance;

    public static GameModel getInstance(){
        if (instance == null){
            instance = new GameModel();
        }
        return instance;
    }

    private GameModel(){
        background = new BackgroundModel();
        background2 = new BackgroundModel();
    }

    public BackgroundModel getBackground() {
        return background;
    }
    public BackgroundModel getBackground2() {
        return background2;
    }

    public void update(float delta){


    }



}

