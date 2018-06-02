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

public class SubmitScore extends ScreenAdapter {

    private static SubmitScore instance;

    private final RedBullGame game;
    private Stage stage;
    Skin mySkin,labelSkin;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;
    GlyphLayout layout;
    TextField txtUsername;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("The Outbox St.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;

    public SubmitScore(final RedBullGame game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin = this.game.getAssetManager().get("skin/clean-crispy-ui.json");
        labelSkin = new Skin(Gdx.files.internal("skin3/clean-crispy-ui.json"));


        Texture texture = this.game.getAssetManager().get("backg.png");
        Image back = new Image(texture);
        float scaleFactor = (Gdx.graphics.getHeight()) / back.getHeight();
        back.setSize(scaleFactor * back.getWidth(), scaleFactor * back.getHeight());
        back.setPosition(Gdx.graphics.getWidth()/3 - back.getWidth()/2, 0);
        stage.addActor(back);

        txtUsername = new TextField("", mySkin);
        txtUsername.setMessageText("username");
        txtUsername.setMaxLength(12);
        txtUsername.setSize(col_width*6,row_height);
        txtUsername.setPosition(col_width*6-txtUsername.getWidth()/2,row_height*6);
        stage.addActor(txtUsername);


        Button button1 = new TextButton("Submit",mySkin);
        button1.setSize(col_width*6,row_height*1.5f);
        button1.setPosition(col_width*6-button1.getWidth()/2,row_height*4);
        button1.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().submitHighScore(txtUsername.getText(),this.getGame().getScore());
                    this.getGame().MainMenu();
                }catch (Exception e){
                    outputLabel.setText("Connection Error");
                }
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(button1);

        Button button2 = new TextButton("Return Main Menu",mySkin);
        button2.setSize(col_width*6,row_height*1.5f);
        button2.setPosition(col_width*6-button2.getWidth()/2,row_height*2);
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



        outputLabel = new Label("",labelSkin,"default");
        outputLabel.setSize(Gdx.graphics.getWidth(),row_height);
        outputLabel.setPosition(0,row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);

        parameter.size = 120;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.BLACK;

        font=generator.generateFont(parameter);
    }

    public static SubmitScore getInstance() {
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
        layout = new GlyphLayout(font, "Submit Score");
        font.draw(game.getBatch(), "Submit Score",Gdx.graphics.getWidth()/2-layout.width/2,(Gdx.graphics.getHeight()/12)*10);
        this.game.getBatch().end();


    }

}
