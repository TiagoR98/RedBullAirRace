package com.redbull.game.view.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.RedBullGame;
import com.redbull.game.model.GameModel;

public class PlaneView extends EntityView {

    public PlaneView(RedBullGame game){
        super(game);
    }

    @Override
    public Sprite createSprite(RedBullGame game) {
        Texture texture = game.getAssetManager().get(GameModel.getInstance().getActivePlane().getTexture());
        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }
}
