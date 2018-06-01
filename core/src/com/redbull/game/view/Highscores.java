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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.redbull.game.RedBullGame;

import org.json.JSONArray;

public class Highscores extends ScreenAdapter {

    private static Highscores instance;

    private final RedBullGame game;
    private Stage stage;
    Skin mySkin,whiteSkin;
    int Help_Guides = 12;
    int row_height = Gdx.graphics.getHeight() / 12;
    int col_width = Gdx.graphics.getWidth() / 12;
    private Label outputLabel;
    GlyphLayout layout;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("The Outbox St.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;

    public Highscores (final RedBullGame game) {
        this.game=game;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        mySkin = new Skin(Gdx.files.internal("skin/clean-crispy-ui.json"));
        whiteSkin = new Skin(Gdx.files.internal("skin2/clean-crispy-ui.json"));



        Texture texture = new Texture(Gdx.files.internal("winners.png"));
        Image back = new Image(texture);
        float scaleFactor = (Gdx.graphics.getHeight()) / back.getHeight();
        back.setSize(scaleFactor * back.getWidth(), scaleFactor * back.getHeight());
        back.setPosition(Gdx.graphics.getWidth()/2 - back.getWidth()/2, 0);
        stage.addActor(back);



        JSONArray scores = game.getHighScores();
        Table table = new Table();
        table.setSkin(whiteSkin);
        table.setFillParent(true);
        table.setDebug(false);
        table.row();
        table.add("Username").expandX();
        table.add("Score").expandX();
        for (int i=0; i < scores.length(); i++) {
            table.row();
            try {
                table.add(scores.getJSONObject(i).getString("username"));
                table.add(Integer.toString(scores.getJSONObject(i).getInt("score")));
            }catch (Exception e){
                System.out.println(e.getCause());
            }

        }
        stage.addActor(table);



        Button button2 = new TextButton("Go back",mySkin);
        button2.setSize(col_width*6,row_height*1.5f);
        button2.setPosition(col_width*6-button2.getWidth()/2,row_height);
        button2.addListener(new InputHandler(this.game){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                try {
                    this.getGame().MainMenu();
                }catch (Exception e){}
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
        outputLabel.setPosition(0,0);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);


        parameter.size = 120;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.BLACK;

        font=generator.generateFont(parameter);
    }

    public static Highscores getInstance() {
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
        layout = new GlyphLayout(font, "Highscores");
        font.draw(game.getBatch(), "Highscores",Gdx.graphics.getWidth()/2-layout.width/2,(Gdx.graphics.getHeight()/12)*11);
        this.game.getBatch().end();
    }

}
