package com.redbull.game.controller;

import com.badlogic.gdx.physics.box2d.ContactListener;
import com.redbull.game.RedBullGame;
import com.redbull.game.model.GameModel;

public class GameController implements ContactListener{


    public static GameController instance;

    private GameController(){


    }

    public static GameController getInstance() {
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }

    public void update(float delta){
        GameModel.getInstance().update(delta);
    }

}
