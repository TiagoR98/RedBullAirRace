package com.redbull.game.controller.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.redbull.game.model.Entities.PlaneModel;


public class PlaneBody extends EntityBody {

    public PlaneBody(World world, PlaneModel model) {
        super(world, model);

        float density = 1f, friction = 0f, restitution = 0.0f;
        int width = Gdx.graphics.getWidth() / 3, height = Gdx.graphics.getWidth() / 15;

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = rect;
        fixtureDef.density = density;      // how heavy is the fixture kg/m^2
        fixtureDef.friction = friction;     // how slippery is the fixture [0,1]
        fixtureDef.restitution = restitution;


        this.body.createFixture(fixtureDef);

        rect.dispose();
    }

}