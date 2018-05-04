package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.redbull.game.RedBullGame;
import com.badlogic.gdx.graphics.Texture;
import com.redbull.game.controller.GameController;

public class GameView extends ScreenAdapter {

    private final RedBullGame game;
    private int x2;

    public GameView(RedBullGame game){
        this.game = game;
        loadAssets();
    }

    private void loadAssets(){
    this.game.getAssetManager().load("pylon.png", Texture.class);
    this.game.getAssetManager().load("pylonknife.png", Texture.class);
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

        game.getBatch().begin();
        drawBackground(4);
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
