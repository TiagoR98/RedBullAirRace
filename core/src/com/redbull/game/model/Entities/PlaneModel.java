package com.redbull.game.model.Entities;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class PlaneModel extends EntityModel {
    private boolean isKnife = false;
    private boolean animationControl = true;
    private int textIndice = -1;
    private int velocity;
    private String textNormal;
    private ArrayList<String> intTextures;
    private String textKnife;
    private float rotation=0;


   public PlaneModel(int velocity, String textNormal, ArrayList<String> intTextures, String textKnife){
       super(14, 50, 0);
       this.velocity = velocity;
       this.intTextures = intTextures;
       this.textNormal = textNormal;
       this.textKnife = textKnife;
   }

    public int getVelocity() {
        return velocity;
    }
    public boolean getIsKnife(){
       return isKnife;
    }

    @Override
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public float getRotation() {
        return this.rotation;
    }

    private void incrementAnimation(){
        if(isKnife) {
            if(textIndice == 0) {
                textIndice = -1;
                isKnife = !isKnife;
            }else  textIndice-=1;
        }
        else if(textIndice == intTextures.size()-1) {
            textIndice = -1;
            isKnife = !isKnife;
        }else  textIndice+=1;
    }

   public String getTexture(){
       if(textIndice == -1){
           if(isKnife) return textKnife;
           else return textNormal;
       }
       else {
           String ret = intTextures.get(textIndice);
           if(animationControl)
           incrementAnimation();
           animationControl=!animationControl;
           return ret;
       }
   }

   public void Swipe(){
       if(isKnife) textIndice = intTextures.size()-1;
       else textIndice = 0;
   }

};
