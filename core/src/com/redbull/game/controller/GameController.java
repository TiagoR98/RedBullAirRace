package com.redbull.game.controller;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import com.redbull.game.controller.Entities.PlaneBody;
import com.redbull.game.model.Entities.EntityModel;
import com.redbull.game.model.GameModel;

public class GameController implements ContactListener{

    private World world;

    private PlaneBody planeBody;

    private float accumulator;

    public static GameController instance;


    private GameController(){
      initializeControllers();
    }

    public void initializeControllers(){
        world = new World(new Vector2(0, -35f*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate()), true);

        planeBody = new PlaneBody(world, GameModel.getInstance().getActivePlane());

        world.setContactListener(this);
    }


    public static GameController getInstance() {
        if (instance == null){
            instance = new GameController();
        }
        return instance;
    }


    public void update(float delta){
        GameModel.getInstance().update(delta);

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body body : bodies) {
            ((EntityModel) body.getUserData()).setPosition(body.getPosition().x, body.getPosition().y);
                ((EntityModel)body.getUserData()).setRotation(body.getLinearVelocity().y);
        }

        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= 1/60f) {
            world.step(1/60f, 60, 20);
            accumulator -= 1/60f;
        }

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

    public void jump(float delta){
        if(GameModel.getInstance().getActivePlane().getIsKnife()){
            if (planeBody.getBody().getLinearVelocity().y < 1*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate())
                planeBody.getBody().setLinearVelocity(0, planeBody.getBody().getLinearVelocity().y + 1*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate());
            else if (planeBody.getBody().getLinearVelocity().y == 1*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate())
                planeBody.getBody().setLinearVelocity(0, 1*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate());
        }else {
            if (planeBody.getBody().getLinearVelocity().y < 30*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate())
                planeBody.getBody().setLinearVelocity(0, planeBody.getBody().getLinearVelocity().y + 3*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate());
            else
                planeBody.getBody().setLinearVelocity(0, 30*GameModel.getInstance().getActivePlane().getPlaneLinearCompensate());
        }
    }

}

