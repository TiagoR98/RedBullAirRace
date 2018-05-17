package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.redbull.game.RedBullGame;
import com.badlogic.gdx.graphics.Texture;
import com.redbull.game.controller.GameController;
import com.redbull.game.model.Entities.PylonModel;
import com.redbull.game.model.GameModel;
import com.redbull.game.view.Entities.EntityView;
import com.redbull.game.view.Entities.PylonView;

import java.util.ArrayList;

public class GameView extends ScreenAdapter {

    private final RedBullGame game;
    private int x2;

    public GameView(RedBullGame game){
        this.game = game;
        loadAssets();
    }

    private void loadAssets(){
        this.game.getAssetManager().load("p1v.png", Texture.class);
        this.game.getAssetManager().load("p2v.png", Texture.class);
        this.game.getAssetManager().load("p3v.png", Texture.class);
        this.game.getAssetManager().load("p4v.png", Texture.class);
        this.game.getAssetManager().load("p5v.png", Texture.class);
        this.game.getAssetManager().load("p6v.png", Texture.class);
        this.game.getAssetManager().load("p1a.png", Texture.class);
        this.game.getAssetManager().load("p2a.png", Texture.class);
        this.game.getAssetManager().load("p3a.png", Texture.class);
        this.game.getAssetManager().load("p4a.png", Texture.class);
        this.game.getAssetManager().load("p5a.png", Texture.class);
        this.game.getAssetManager().load("p6a.png", Texture.class);


    this.game.getAssetManager().load("backg.png", Texture.class);
    this.game.getAssetManager().finishLoading();

    Texture txt = game.getAssetManager().get("backg.png");

    x2 = -txt.getWidth();
    }

BitmapFont font = new BitmapFont();

    int y = 0;
    int x1=0;
    @Override
    public void render (float delta) {
        GameController.getInstance().update(delta);

        System.out.print("Fuck JAS");

        game.getBatch().begin();
        drawBackground(4);


        ArrayList<PylonModel> pylons = GameModel.getInstance().getPylons();
        for(PylonModel pylon : pylons){
            PylonView view = new PylonView(game,pylon.getPylonType());
            view.update(pylon);
            view.draw(game.getBatch());
        }

        game.getBatch().end();

    }

    private void drawBackground(int velocity){

        Texture texture = game.getAssetManager().get("backg.png");

        game.getBatch().draw(texture, x1-=velocity, 0, texture.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().draw(texture, x2-=velocity, 0, texture.getWidth(), Gdx.graphics.getHeight());

        if(x1<-texture.getWidth())
            x1=x2+texture.getWidth();

        if(x2<-texture.getWidth())
            x2=x1+texture.getWidth();

    }
}
