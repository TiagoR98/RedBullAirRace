package com.redbull.game.view.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.RedBullGame;
import com.redbull.game.view.Entities.EntityView;

public class PylonView extends EntityView {

    public PylonView(RedBullGame game){
        super(game);
    }

    @Override
    public Sprite createSprite(RedBullGame game) {
        Texture texture = game.getAssetManager().get("pylon.png");
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
