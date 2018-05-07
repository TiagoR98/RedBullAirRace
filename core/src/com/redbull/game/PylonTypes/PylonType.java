package com.redbull.game.PylonTypes;

import com.badlogic.gdx.graphics.Texture;
import com.redbull.game.RedBullGame;

public abstract class PylonType {
    private int highestPoint;
    private boolean needsKnife;
    private int passingZone;
    private String pylonTexture;

    public PylonType(String texture){
        pylonTexture=texture;
    }

    public int getHighestPoint() {
        return highestPoint;
    }

    public int getPassingZone() {
        return passingZone;
    }

    public String getPylonTexture() {
        return pylonTexture;
    }

    public boolean getneedsKnife() {
        return needsKnife;
    }

    public void setHighestPoint(int highestPoint) {
        this.highestPoint = highestPoint;
    }

    public void setNeedsKnife(boolean needsKnife) {
        this.needsKnife = needsKnife;
    }

    public void setPassingZone(int passingZone) {
        this.passingZone = passingZone;
    }

    public void setPylonTexture(String pylonTexture) {
        this.pylonTexture = pylonTexture;
    }
}
