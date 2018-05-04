package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.redbull.game.RedBullGame;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.redbull.game.controller.GameController;
import com.redbull.game.model.Entities.BackgroundModel;
import com.redbull.game.model.GameModel;
import com.redbull.game.view.Entities.BackgroundView;
import com.redbull.game.view.Entities.EntityView;

import javax.swing.text.ViewFactory;

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

        /*
        Gdx.gl.glClearColor(0,0,1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        font.getData().setScale(10);
        font.draw(game.getBatch(), "Hello JAS!", 10, y+=10);
        if(y-100 >= Gdx.graphics.getHeight())
            y=0;
    */


        BackgroundModel background = GameModel.getInstance().getBackground();
        //BackgroundModel background2 = GameModel.getInstance().getBackground2();
        //EntityView view = new BackgroundView(game);
        //view.update(background);
        //view.draw(game.getBatch());
        Texture texture = game.getAssetManager().get("backg.png");


        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        game.getBatch().draw(texture, x1-=4, 0, texture.getWidth(), Gdx.graphics.getHeight());
        game.getBatch().draw(texture, x2-=4, 0, texture.getWidth(), Gdx.graphics.getHeight());

        if(x1<-texture.getWidth())
            x1=x2+texture.getWidth();

        if(x2<-texture.getWidth())
            x2=x1+texture.getWidth();


        game.getBatch().end();

    }
}
