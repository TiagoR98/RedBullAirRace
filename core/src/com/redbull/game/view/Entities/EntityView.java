package com.redbull.game.view.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.RedBullGame;
import com.redbull.game.model.Entities.EntityModel;

public abstract class EntityView {
    Sprite sprite;
    PylonType type;

    public abstract Sprite createSprite(RedBullGame game);

    public EntityView(RedBullGame game){
        sprite = createSprite(game);
    }

    public EntityView(RedBullGame game,PylonType type){
        this.type = type;
        sprite = createSprite(game);
    }


    public void draw(SpriteBatch batch){
        sprite.draw(batch);
    }

    public void update(EntityModel model){
        sprite.setCenter(model.getX(), model.getY());
        sprite.setRotation((float) Math.toDegrees(model.getRotation()));
    }
}
