package com.redbull.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.RedBullGame;
import com.badlogic.gdx.graphics.Texture;
import com.redbull.game.controller.GameController;
import com.redbull.game.model.Entities.PlaneModel;
import com.redbull.game.model.Entities.PylonModel;
import com.redbull.game.model.GameModel;
import com.redbull.game.view.Entities.EntityView;
import com.redbull.game.view.Entities.PlaneView;
import com.redbull.game.view.Entities.PylonView;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class GameView extends ScreenAdapter {

    private final RedBullGame game;
    private int x2;
    private static final double backgroundParallax = 0.50;

    private Box2DDebugRenderer debugRenderer;

    private Matrix4 debugMatrix;

    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("The Outbox St.ttf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

    private boolean touched = false;

    public final static float METER_TO_PIXEL_V = (Gdx.graphics.getHeight()*0.9f)/(float)GameModel.getInstance().ARENA_HEIGHT;
    public final static float METER_TO_PIXEL_H = (Gdx.graphics.getWidth()*0.9f)/(float)GameModel.getInstance().ARENA_WIDTH;


    BitmapFont font,fontSmall;
    GlyphLayout layout;

    private OrthographicCamera camera;
    ShapeRenderer shapeRenderer;


    TextureAtlas particleAtlas; //<-load some atlas with your particle assets in
    ParticleEffect effect;

    public GameView(RedBullGame game){
        shapeRenderer = new ShapeRenderer();
        this.game = game;

        Texture txt = game.getAssetManager().get("backg.png");
        x2 = -txt.getWidth();

        parameter.size = 120;
        parameter.borderWidth = 5;
        parameter.borderColor = Color.BLACK;

        generator.generateFont(parameter);

        font=generator.generateFont(parameter);

        parameter.size = 30;
        fontSmall=generator.generateFont(parameter);

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("smoke.p"), Gdx.files.internal(""));



        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void onUp() {
                if(GameModel.getInstance().getActivePlane().getIsKnife())
                    GameModel.getInstance().getActivePlane().Swipe();
            }

            @Override
            public void onRight() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLeft() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onDown() {
                if(!GameModel.getInstance().getActivePlane().getIsKnife())
                    GameModel.getInstance().getActivePlane().Swipe();

            }
        }));


    }



    int y = 0;
    int x1=0;
    @Override
    public void render (float delta) {
            if(touched)
                GameController.getInstance().update(delta);


        game.getBatch().begin();
        drawBackground((int)(GameModel.getInstance().getActivePlane().getVelocity()*backgroundParallax));




        PlaneModel plane = GameModel.getInstance().getActivePlane();
        Sprite pSprite = new PlaneView(game).createSprite(game);
        pSprite.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/3);
        pSprite.setOriginCenter();
        pSprite.setPosition(((plane.getX()*METER_TO_PIXEL_H)-pSprite.getWidth()/2),(plane.getY()*METER_TO_PIXEL_V)-pSprite.getHeight()/2);
        pSprite.setRotation(plane.getRotation());

        effect.start();
        effect.setPosition(pSprite.getX()+pSprite.getWidth()/2,pSprite.getY()+pSprite.getHeight()/2);
        effect.draw(game.getBatch(), delta);

        pSprite.draw(game.getBatch());

        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        ArrayList<PylonModel> pylons = GameModel.getInstance().getPylons();
        for(PylonModel pylon : pylons) {
            Sprite pylonSprite = new PylonView(game, pylon.getPylonType()).createSprite(game);
            pylonSprite.setPosition(pylon.getX(), pylon.getY());
            float scaleFactor = (float) ((Gdx.graphics.getHeight()*0.9) / pylonSprite.getHeight());
            pylonSprite.setSize(pylonSprite.getWidth() * scaleFactor, pylonSprite.getHeight() * scaleFactor);
            pylonSprite.draw(game.getBatch());
            //font.draw(game.getBatch(), "Fuck JAS", pylon.getX(),pylon.getPylonType().getHighestPoint()*METER_TO_PIXEL_V);
            //font.draw(game.getBatch(), "Fuck JAS", pylon.getX(),(pylon.getPylonType().getHighestPoint()-29.75f)*METER_TO_PIXEL_V);

            /*
            shapeRenderer.circle(pylon.getX()+pylonSprite.getWidth()/2,pylon.getPylonType().getHighestPoint()*METER_TO_PIXEL_V, 5);
            shapeRenderer.circle(pylon.getX()+pylonSprite.getWidth()/2,(pylon.getPylonType().getHighestPoint()-pylon.getPylonType().getPassingZone())*METER_TO_PIXEL_V, 5);
            */


            if (((pylonSprite.getX()+pylonSprite.getWidth()/2) <= plane.getX()*1.2*METER_TO_PIXEL_H) &&
                    ((pylonSprite.getX()+pylonSprite.getWidth()/2) >= plane.getX()*1.2*METER_TO_PIXEL_H - plane.getVelocity()))
                if (checkPylonPassage(pylon, plane) == -1) {
                    this.game.gameOver();
                }else{
                    game.scored();
                }
            }

        if(plane.getY()*METER_TO_PIXEL_V<=0)
            this.game.gameOver();

            layout = new GlyphLayout(font, Integer.toString(game.getScore()));

        //font.draw(game.getBatch(), "Fuck JAS", plane.getX()*METER_TO_PIXEL_H,plane.getY()*METER_TO_PIXEL_V);
        font.draw(game.getBatch(), Integer.toString(game.getScore()), ((Gdx.graphics.getWidth() / 2) - (layout.width / 2)), (float) (Gdx.graphics.getHeight()*0.9));


        game.getBatch().end();


        shapeRenderer.end();

        handleInputs(delta);

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

    private void handleInputs(float delta) {

        if (Gdx.input.isTouched()) {
            touched = true;
            GameController.getInstance().jump(delta);
        }

    }

    private int checkPylonPassage(PylonModel pylon,PlaneModel plane){
     if(plane.getY() < pylon.getPylonType().getHighestPoint() && plane.getY() > pylon.getPylonType().getHighestPoint()- PylonType.getPassingZone() && pylon.getPylonType().getneedsKnife() == plane.getIsKnife())
         return 0;
     else
         return -1;
    }
}
