package com.redbull.game.model;

import com.redbull.game.model.Entities.BackgroundModel;

public class GameModel {
    private BackgroundModel background;

    private static GameModel instance;

    public static GameModel getInstance(){
        if (instance == null){
            instance = new GameModel();
        }
        return instance;
    }

    private GameModel(){
        background = new BackgroundModel(50,50,0);
    }

    public BackgroundModel getBackground() {
        return background;
    }

    public void update(float delta){
        background.setPosition(background.getX()+10, background.getY());
    }


}

