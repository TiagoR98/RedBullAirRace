package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redbull.game.RedBullGame;

public class GameOver extends ScreenAdapter {
    private static GameOver instance;

    private final RedBullGame game;
    private Stage stage;
    Skin mySkin;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;
    GlyphLayout layout;
    TextField txtUsername;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("The Outbox St.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;

    public GameOver(final RedBullGame game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));


        Texture texture = new Texture(Gdx.files.internal("backg.png"));
        Image back = new Image(texture);
        float scaleFactor = (Gdx.graphics.getHeight()) / back.getHeight();
        back.setSize(scaleFactor * back.getWidth(), scaleFactor * back.getHeight());
        back.setPosition(Gdx.graphics.getWidth()/3 - back.getWidth()/2, 0);
        stage.addActor(back);


        Button button1 = new TextButton("Race Again",mySkin);
        button1.setSize(col_width*6,row_height*1.5f);
        button1.setPosition(col_width*6-button1.getWidth()/2,row_height*5);
        button1.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().startGame();
                }catch (Exception e){}
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button1);

        Button button2 = new TextButton("Return Main Menu",mySkin);
        button2.setSize(col_width*6,row_height*1.5f);
        button2.setPosition(col_width*6-button2.getWidth()/2,row_height*3);
        button2.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().MainMenu();
                }catch (Exception e){}
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button2);

        Button button3 = new TextButton("Submit Score",mySkin);
        button3.setSize(col_width*6,row_height*1f);
        button3.setPosition(col_width*6-button3.getWidth()/2,row_height*1.5f);
        button3.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().submitScore();
                }catch (Exception e){}
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button3);


        outputLabel = new Label("",mySkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,0);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);

        parameter.size = 120;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.BLACK;

        font=generator.generateFont(parameter);
    }

    public static GameOver getInstance() {
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
        this.game.getBatch().begin();
        layout = new GlyphLayout(font, "Game Over");
        font.draw(game.getBatch(), "Game Over",Gdx.graphics.getWidth()/2-layout.width/2,(Gdx.graphics.getHeight()/12)*10);

        layout = new GlyphLayout(font, Integer.toString(game.getScore()));
        font.draw(game.getBatch(), Integer.toString(game.getScore()),Gdx.graphics.getWidth()/2-layout.width/2,(Gdx.graphics.getHeight()/12)*8);
        this.game.getBatch().end();


    }
}