package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redbull.game.RedBullGame;
import com.redbull.game.controller.GameController;
import com.redbull.game.model.GameModel;
import com.redbull.game.view.Input.InputHandler;

public class MainMenu extends ScreenAdapter {

    private static MainMenu instance;

    private final RedBullGame game;
    private Stage stage;
    Skin mySkin,labelSkin;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;

    public MainMenu (final RedBullGame game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin = this.game.getAssetManager().get("skin/clean-crispy-ui.json");
        labelSkin = this.game.getAssetManager().get("skin3/clean-crispy-ui.json");


        createBackground();
        float scaleFactor;

        createLogoRbar();

        createButtonStart();

        createButtonHighscores();

        createLabelOut();

        GameModel.getInstance();
        GameController.getInstance();
    }

    private void createLabelOut() {
        outputLabel = new Label("",labelSkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height*1.5f);
        outputLabel.setPosition(0,row_height/2);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
    }

    private void createButtonHighscores() {
        Button button3 = new TextButton("Highscores",mySkin);
        button3.setSize(col_width*6,row_height*1.5f);
        button3.setPosition(col_width*6-button3.getWidth()/2,row_height*3);
        button3.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().highscores();
                }catch (Exception e){
                   // outputLabel.setText("Connection Error");
                }
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Loading...");
                return true;
            }
        });
        stage.addActor(button3);
    }

    private void createButtonStart() {
        Button button2 = new TextButton("Start Game",mySkin);
        button2.setSize(col_width*6,row_height*1.5f);
        button2.setPosition(col_width*6-button2.getWidth()/2,row_height*6);
        button2.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().seeRules();
                }catch (Exception e){}
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Loading...");
                return true;
            }
        });
        stage.addActor(button2);
    }

    private void createLogoRbar() {
        float scaleFactor;Texture texture2 = this.game.getAssetManager().get("rbarlogowht.png");
        Image rbarlogo = new Image(texture2);
        scaleFactor = (float) ((Gdx.graphics.getWidth() * 0.8) / rbarlogo.getWidth());
        rbarlogo.setSize(scaleFactor*rbarlogo.getWidth(),scaleFactor*rbarlogo.getHeight());
        rbarlogo.setPosition(col_width*6-rbarlogo.getWidth()/2, row_height*9);
        stage.addActor(rbarlogo);
    }

    private void createBackground() {
        Texture texture = this.game.getAssetManager().get("backgmenu.png");
        Image back = new Image(texture);
        float scaleFactor = (Gdx.graphics.getHeight()) / back.getHeight();
        back.setSize(scaleFactor * back.getWidth(), scaleFactor * back.getHeight());
        back.setPosition(Gdx.graphics.getWidth()/3 - back.getWidth()/2, 0);
        stage.addActor(back);
    }

    public static MainMenu getInstance() {
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
