package com.redbull.game.model.Entities;

public abstract class EntityModel {

    private float x;
    private float y;
    private float rotation;

    public EntityModel(float x, float y, float rotation){
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }


}
