package com.redbull.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.RedBullGame;
import com.badlogic.gdx.graphics.Texture;
import com.redbull.game.controller.GameController;
import com.redbull.game.model.Entities.PlaneModel;
import com.redbull.game.model.Entities.PylonModel;
import com.redbull.game.model.GameModel;
import com.redbull.game.view.Entities.PlaneView;
import com.redbull.game.view.Entities.PylonView;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.redbull.game.view.Input.SimpleDirectionGestureDetector;

import java.util.ArrayList;

public class GameView extends ScreenAdapter {

    private final RedBullGame game;
    private int x2;
    private static final double backgroundParallax = 0.50;


    private boolean touched = false;

    public final static float METER_TO_PIXEL_V = (Gdx.graphics.getHeight()*0.9f)/(float)GameModel.getInstance().ARENA_HEIGHT;
    public final static float METER_TO_PIXEL_H = (Gdx.graphics.getWidth()*0.9f)/(float)GameModel.getInstance().ARENA_WIDTH;


    BitmapFont font;
    GlyphLayout layout;

    ParticleEffect effect;
    Music smokeOn,passing;

    public GameView(RedBullGame game){
        this.game = game;

        Texture txt = game.getAssetManager().get("backg.png");
        x2 = -txt.getWidth();

        font=this.game.getFont();
        effect = this.game.getEffect();



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

        if(GameModel.getInstance().isActiveMaster())
            smokeOn = game.getAssetManager().get("smokeonsonka.mp3");
        else
            smokeOn = game.getAssetManager().get("smokeon.mp3");

        smokeOn.setVolume(1);
        smokeOn.setLooping(false);
        smokeOn.play();

        passing = game.getAssetManager().get("passing.mp3");
        passing.setVolume(0.3f);

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
        Sprite pSprite = createPlaneSprite(plane);

        drawSmoke(delta, pSprite);

        pSprite.draw(game.getBatch());

        ArrayList<PylonModel> pylons = GameModel.getInstance().getPylons();

        drawPylons(plane, pylons);

        chackPlaneUnderwater(plane);

        layout = new GlyphLayout(font, Integer.toString(game.getScore()));
        font.draw(game.getBatch(), Integer.toString(game.getScore()), ((Gdx.graphics.getWidth() / 2) - (layout.width / 2)), (float) (Gdx.graphics.getHeight()*0.9));

        game.getBatch().end();

        handleInputs(delta);

    }

    private void chackPlaneUnderwater(PlaneModel plane) {
        if(plane.getY()*METER_TO_PIXEL_V<=0) {
            gameOver();
        }
    }

    private void drawSmoke(float delta, Sprite pSprite) {
        effect.start();
        effect.setPosition(pSprite.getX()+pSprite.getWidth()/2,pSprite.getY()+pSprite.getHeight()/2);
        effect.draw(game.getBatch(), delta);
    }

    private void drawPylons(PlaneModel plane, ArrayList<PylonModel> pylons) {
        for(PylonModel pylon : pylons) {
            Sprite pylonSprite = createPylonSprite(pylon);

            checkPylonPassage(plane, pylon, pylonSprite);
        }
    }

    private void checkPylonPassage(PlaneModel plane, PylonModel pylon, Sprite pylonSprite) {
        if (((pylonSprite.getX()+pylonSprite.getWidth()/2) <= plane.getX()*1.2*METER_TO_PIXEL_H) &&
                ((pylonSprite.getX()+pylonSprite.getWidth()/2) >= plane.getX()*1.2*METER_TO_PIXEL_H - plane.getVelocity()))
            if (checkPylonPassage(pylon, plane) == -1) {
                gameOver();
            }else{
                game.scored();
                passing.stop();
                passing.play();
            }
    }

    private void gameOver() {
        passing.stop();
        this.game.gameOver();
    }

    private Sprite createPylonSprite(PylonModel pylon) {
        Sprite pylonSprite = new PylonView(game, pylon.getPylonType()).createSprite(game);
        pylonSprite.setPosition(pylon.getX(), pylon.getY());
        float scaleFactor = (float) ((Gdx.graphics.getHeight()*0.9) / pylonSprite.getHeight());
        pylonSprite.setSize(pylonSprite.getWidth() * scaleFactor, pylonSprite.getHeight() * scaleFactor);
        pylonSprite.draw(game.getBatch());
        return pylonSprite;
    }

    private Sprite createPlaneSprite(PlaneModel plane) {
        Sprite pSprite = new PlaneView(game).createSprite(game);
        pSprite.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/3);
        pSprite.setOriginCenter();
        pSprite.setPosition(((plane.getX()*METER_TO_PIXEL_H)-pSprite.getWidth()/2),(plane.getY()*METER_TO_PIXEL_V)-pSprite.getHeight()/2);
        pSprite.setRotation(plane.getRotation());
        return pSprite;
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
