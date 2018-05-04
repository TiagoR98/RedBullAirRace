package com.redbull.game.view.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.RedBullGame;
import com.badlogic.gdx.graphics.Texture;

public class BackgroundView extends EntityView{

    public BackgroundView(RedBullGame game){
        super(game);
    }

    @Override
    public Sprite createSprite(RedBullGame game) {
    Texture texture = game.getAssetManager().get("backg.png");
    texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
