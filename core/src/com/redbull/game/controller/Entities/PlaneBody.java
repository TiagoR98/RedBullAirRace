package com.redbull.game.controller.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.redbull.game.model.Entities.PlaneModel;


public class PlaneBody extends EntityBody {

    public PlaneBody(World world, PlaneModel model) {
        super(world, model);

        float density = 0f, friction = 0f, restitution = 0.0f;
        int width = Gdx.graphics.getWidth() / 3, height = Gdx.graphics.getWidth() / 15;

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;


        this.body.createFixture(fixtureDef);
        this.body.setAwake(false);
        rect.dispose();
    }

}