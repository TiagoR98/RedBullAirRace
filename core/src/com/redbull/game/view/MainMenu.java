package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redbull.game.RedBullGame;
import com.redbull.game.model.GameModel;

import static com.redbull.game.controller.GameController.instance;

public class MainMenu extends ScreenAdapter {

    private static MainMenu instance;

    private final RedBullGame game;
    private Stage stage;
    Skin mySkin;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getWidth() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;

    public MainMenu (final RedBullGame game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));

        Button button2 = new TextButton("Start Game",mySkin);
        button2.setSize(col_width*8,row_height*2);
        button2.setPosition(col_width*6-button2.getWidth()/2,Gdx.graphics.getHeight()-row_height*12);
        button2.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                this.getGame().startGame();
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                outputLabel.setText("Loading...");
                return true;
            }
        });
        stage.addActor(button2);
        outputLabel = new Label("",mySkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
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
