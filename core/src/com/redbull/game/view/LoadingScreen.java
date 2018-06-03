package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redbull.game.RedBullGame;


public class LoadingScreen extends ScreenAdapter {

    private static LoadingScreen instance;

    private final RedBullGame game;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    ProgressBar pbar;
    private float percent=0;
    Pixmap pixmap;
    TextureRegionDrawable drawable;
    ProgressBar.ProgressBarStyle progressBarStyle;

    public LoadingScreen(final RedBullGame game) {
        this.game=game;

        createProgressBar();

    }

    private void createProgressBar() {
        progressBarStyle = new ProgressBar.ProgressBarStyle();
        pixmap = new Pixmap(0, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobAfter = drawable;

        Pixmap pixmap = new Pixmap(100, 20, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();

        progressBarStyle.knobBefore = drawable;

        pbar = new ProgressBar(0,1,0.05f,false,progressBarStyle);
        pbar.setSize(Gdx.graphics.getWidth()*0.9f,row_height*3.5f);
        pbar.setPosition(col_width*6-pbar.getWidth()/2,row_height*4);
        pbar.setValue(0);
    }


    public static LoadingScreen getInstance() {
        return instance;
    }

    public RedBullGame getGame() {
        return game;
    }


    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        percent = Interpolation.linear.apply(percent, this.game.getAssetManager().getProgress(), 0.05f);
        pbar.setValue(percent);

        this.game.getBatch().begin();
            pbar.draw(this.game.getBatch(),1);
        this.game.getBatch().end();

        if(percent>0.999){
            this.game.MainMenu();
        }

    }
}
