package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redbull.game.RedBullGame;

public class ChoosePlane extends ScreenAdapter {

    private static ChoosePlane instance;

    private final RedBullGame game;
    private Stage stage;
    Skin mySkin;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;

    public ChoosePlane (final RedBullGame game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));


        Texture texture = new Texture(Gdx.files.internal("backgmenu.png"));
        Image back = new Image(texture);
        float scaleFactor = (Gdx.graphics.getHeight()) / back.getHeight();
        back.setSize(scaleFactor * back.getWidth(), scaleFactor * back.getHeight());
        back.setPosition(Gdx.graphics.getWidth()/3 - back.getWidth()/2, 0);
        stage.addActor(back);

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("masterbutton.png"))));
        final ImageButton masterButton = new ImageButton(drawable);
        masterButton.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                masterButton.setTransform(true);
                masterButton.setOrigin(Align.center);
                masterButton.setScale(1.5f);
                try {
                    this.getGame().startGame();
                }catch (Exception e){}
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                masterButton.setTransform(true);
                masterButton.setOrigin(Align.center);
                masterButton.setScale(0.96f);
                outputLabel.setText("Loading...");
                return true;
            }
        });
        masterButton.setSize(col_width*11,row_height*4);
        masterButton.setPosition(col_width*6-masterButton.getWidth()/2,row_height*7);
        stage.addActor(masterButton);

        Drawable drawable2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("challbutton.png"))));
        final ImageButton challengerButton = new ImageButton(drawable2);
        challengerButton.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                challengerButton.setTransform(true);
                challengerButton.setOrigin(Align.center);
                challengerButton.setScale(1.5f);
                try {
                    this.getGame().startGame();
                }catch (Exception e){}
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                challengerButton.setTransform(true);
                challengerButton.setOrigin(Align.center);
                challengerButton.setScale(0.96f);
                outputLabel.setText("Loading...");
                return true;
            }
        });
        challengerButton.setSize(col_width*11,row_height*4);
        challengerButton.setPosition(col_width*6-challengerButton.getWidth()/2,row_height*2);
        stage.addActor(challengerButton);


        outputLabel = new Label("",mySkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
    }

    public static ChoosePlane getInstance() {
        return instance;
    }

    public RedBullGame getGame() {
        return game;
    }


    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();


    }
}