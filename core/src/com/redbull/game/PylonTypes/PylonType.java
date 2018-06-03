package com.redbull.game.PylonTypes;


public abstract class PylonType {
    private float highestPoint;
    private boolean needsKnife;
    private String pylonTexture;
    private static final float passingZone = 29.75f;

    public PylonType(String texture){
        pylonTexture=texture;
    }

    public float getHighestPoint() {
        return highestPoint;
    }

    public static float getPassingZone() {
        return passingZone;
    }

    public String getPylonTexture() {
        return pylonTexture;
    }

    public boolean getneedsKnife() {
        return needsKnife;
    }

    public void setHighestPoint(float highestPoint) {
        this.highestPoint = highestPoint;
    }

    public void setNeedsKnife(boolean needsKnife) {
        this.needsKnife = needsKnife;
    }

}
