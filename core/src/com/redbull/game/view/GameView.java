package com.redbull.game.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
    private static final double backgroundParallax = 0.70;

    private Box2DDebugRenderer debugRenderer;

    /**
     * The transformation matrix used to transform meters into
     * pixels in order to show fixtures in their correct places.
     */
    private Matrix4 debugMatrix;

    public final static float PIXEL_TO_METER = 0.04f;

    /**
     * The width of the viewport in meters. The height is
     * automatically calculated using the screen ratio.
     */
    private static final float VIEWPORT_WIDTH = 30;

    private OrthographicCamera camera;


    public GameView(RedBullGame game){
        this.game = game;
        loadAssets();

        debugRenderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
                getHeight());

    }

    private void loadAssets(){
        this.game.getAssetManager().load("p1v.png", Texture.class);
        this.game.getAssetManager().load("p2v.png", Texture.class);
        this.game.getAssetManager().load("p3v.png", Texture.class);
        this.game.getAssetManager().load("p4v.png", Texture.class);
        this.game.getAssetManager().load("p5v.png", Texture.class);
        this.game.getAssetManager().load("p6v.png", Texture.class);
        this.game.getAssetManager().load("p1a.png", Texture.class);
        this.game.getAssetManager().load("p2a.png", Texture.class);
        this.game.getAssetManager().load("p3a.png", Texture.class);
        this.game.getAssetManager().load("p4a.png", Texture.class);
        this.game.getAssetManager().load("p5a.png", Texture.class);
        this.game.getAssetManager().load("p6a.png", Texture.class);

        this.game.getAssetManager().load("master1.png", Texture.class);
        this.game.getAssetManager().load("master2.png", Texture.class);
        this.game.getAssetManager().load("master3.png", Texture.class);
        this.game.getAssetManager().load("master4.png", Texture.class);
        this.game.getAssetManager().load("master5.png", Texture.class);
        this.game.getAssetManager().load("master6.png", Texture.class);
        this.game.getAssetManager().load("master7.png", Texture.class);



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

        System.out.print("Fuck JAS");

        debugMatrix = game.getBatch().getProjectionMatrix().cpy().scale(1,
                1, 0);

        game.getBatch().begin();
        drawBackground((int)(GameModel.getInstance().getActivePlane().getVelocity()*backgroundParallax));

        PlaneModel plane = GameModel.getInstance().getActivePlane();
        Sprite pSprite = new PlaneView(game).createSprite(game);
        pSprite.setOriginCenter();
        pSprite.setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth()/3);
        pSprite.setPosition((plane.getX()-pSprite.getWidth()/2),plane.getY()-pSprite.getHeight()/2);
        pSprite.setRotation(plane.getRotation());
        pSprite.draw(game.getBatch());

        ArrayList<PylonModel> pylons = GameModel.getInstance().getPylons();
        for(PylonModel pylon : pylons){
            PylonView view = new PylonView(game,pylon.getPylonType());
            view.update(pylon);
            view.draw(game.getBatch());
        }

        game.getBatch().end();
        debugRenderer.render(GameController.getInstance().getWorld(), debugMatrix);

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
            GameController.getInstance().jump(delta);
        }
    }
}
