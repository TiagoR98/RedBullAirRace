package com.redbull.game.controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
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

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void beginContact(Contact contact) {

    }
}

