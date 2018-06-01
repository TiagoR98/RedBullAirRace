package com.redbull.game.model;

import com.badlogic.gdx.Gdx;
import com.redbull.game.PylonTypes.*;
import com.redbull.game.PylonTypes.PylonType;
import com.redbull.game.model.Entities.PlaneModel;
import com.redbull.game.model.Entities.PylonModel;
import com.badlogic.gdx.utils.Pool;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameModel {


    private static GameModel instance;
    private ArrayList<PylonType> pylonTypes;

    private PlaneModel challengerPlane, masterPlane;
    public final int ARENA_HEIGHT=100;
    public final int ARENA_WIDTH=50;


    private void createPylonTypes(){

        PylonType p1v = new P1v();
        PylonType p2v = new P2v();
        PylonType p3v = new P3v();
        PylonType p4v = new P4v();
        PylonType p5v = new P5v();
        PylonType p6v = new P6v();

        PylonType p1a = new P1a();
        PylonType p2a = new P2a();
        PylonType p3a = new P3a();
        PylonType p4a = new P4a();
        PylonType p5a = new P5a();
        PylonType p6a = new P6a();
        //introduzir propriedades do tipo normal

        pylonTypes.add(p1v);
        pylonTypes.add(p2v);
        pylonTypes.add(p3v);
        pylonTypes.add(p4v);
        pylonTypes.add(p5v);
        pylonTypes.add(p6v);

        pylonTypes.add(p1a);
        pylonTypes.add(p2a);
        pylonTypes.add(p3a);
        pylonTypes.add(p4a);
        pylonTypes.add(p5a);
        pylonTypes.add(p6a);


    }

    public static GameModel getInstance(){
        if (instance == null){
            instance = new GameModel();
        }
        return instance;
    }

    public static GameModel newInstance(){
        instance = new GameModel();
        return instance;
    }

    Pool<PylonModel> pylonPool;

    private final int distancePylons = 600;
    private final int screenMargin = Gdx.graphics.getWidth()/2;
    private final int realHeight = 100;
    private final int pylonSize = 90;
    PlaneModel active;

    ArrayList<PylonModel> pylons;

    private GameModel(){
        pylonTypes = new ArrayList<PylonType>();
        createPylonTypes();

        pylons = new ArrayList<PylonModel>();
        pylonPool = new Pool<PylonModel>() {
            @Override
            protected PylonModel newObject() {
                int randomNum = ThreadLocalRandom.current().nextInt(0, pylonTypes.size());
                return new PylonModel(screenMargin,pylonTypes.get(randomNum));
            }
        };

        PylonModel pyl = pylonPool.obtain();
        pylons.add(pyl);


        ArrayList<String> listChallenger = new ArrayList<String>();
        listChallenger.add("chall2.png");
        listChallenger.add("chall3.png");
        listChallenger.add("chall4.png");
        listChallenger.add("chall5.png");
        listChallenger.add("chall6.png");

        challengerPlane = new PlaneModel(5, "chall1.png",listChallenger,"chall7.png",1);

        ArrayList<String> listMaster = new ArrayList<String>();
            listMaster.add("master2.png");
            listMaster.add("master3.png");
            listMaster.add("master4.png");
            listMaster.add("master5.png");
            listMaster.add("master6.png");

        masterPlane = new PlaneModel(8, "master1.png",listMaster,"master7.png",1.5f);
        setChallengerPlane();

    }

    public int getPylonTextureHeight(){
        return meterHegithToPix(pylonSize);
    }

    public int getScreenMargin() {
        return screenMargin;
    }

    public Pool<PylonModel> getPylonPool() {
        return pylonPool;
    }

    public ArrayList<PylonModel> getPylons() {
        return pylons;
    }

    public void update(float delta){
        float velocity = this.getActivePlane().getVelocity();
    //distancia entre pylons
    if(pylons.get(pylons.size() - 1).getX()<= Gdx.graphics.getWidth()-distancePylons){
        pylons.add(pylonPool.obtain());
    }

    //elimina pylons sai ecra
    if(pylons.get(0).getX()< 0 - screenMargin){
        pylons.remove(0);
    }

    for(PylonModel temp : pylons)
        temp.update(velocity);
    }

    public int meterHegithToPix(int meters){
        return meters*Gdx.graphics.getHeight()/realHeight;
    }

    public void setChallengerPlane() {
        this.active = challengerPlane;
    }

    public void setMasterPlane() {
        this.active = masterPlane;
    }

    public PlaneModel getMasterPlane() {
        return masterPlane;
    }

    public PlaneModel getActivePlane(){
        return active;
    }

    public boolean isActiveMaster(){
        if(masterPlane == active)
           return true;
        else
          return false;
    }

}

