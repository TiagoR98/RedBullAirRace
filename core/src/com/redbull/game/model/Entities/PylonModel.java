package com.redbull.game.model.Entities;

import com.badlogic.gdx.Gdx;
import com.redbull.game.PylonTypes.PylonType;


public class PylonModel extends EntityModel{

    private PylonType pylonType;

    public PylonModel(int screenMargin,PylonType type){
        super(Gdx.graphics.getWidth() + screenMargin, Gdx.graphics.getHeight()/3, 0);
        pylonType = type;
        }

    public PylonType getPylonType() {
        return pylonType;
    }

    public void update(float velocity){
        this.setX(this.getX()-velocity);
    }

}
