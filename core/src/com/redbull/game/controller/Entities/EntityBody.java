package com.redbull.game.controller.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.redbull.game.model.Entities.EntityModel;

public abstract class EntityBody {
    final Body body;

    EntityBody(World world, EntityModel model) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(model.getX(),model.getY());
        bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);

    }

    public void applyImpulse(float forceX, float forceY, boolean awake) {
      body.applyLinearImpulse(0,200,body.getPosition().x,body.getPosition().y,awake);
    }

    public Object getUserData() {
        return body.getUserData();
    }

    public Body getBody() {
        return body;
    }
}
