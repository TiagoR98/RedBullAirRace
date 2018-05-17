package com.redbull.game.view.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.RedBullGame;
import com.redbull.game.model.GameModel;
import com.redbull.game.view.Entities.EntityView;
import com.redbull.game.view.GameView;

public class PylonView extends EntityView {


    public PylonView(RedBullGame game,PylonType type){
        super(game,type);
    }



    @Override
    public Sprite createSprite(RedBullGame game) {
        Texture texture = game.getAssetManager().get(type.getPylonTexture());
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
