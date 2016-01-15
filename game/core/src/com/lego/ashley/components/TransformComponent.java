package com.lego.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by khachatur on 1/14/16.
 */
public class TransformComponent implements Component, Pool.Poolable{
    public Rectangle bounds = new Rectangle(0, 0, 0, 0);
    public int layerIndex;
    public int zIndex;

    public float scaleX = 1;
    public float scaleY = 1;

    public float originX;
    public float originY;
    public float rotation;

    @Override
    public void reset() {
        bounds.set(0, 0, 0, 0);
        zIndex = 0;
        scaleX = 1;
        scaleY = 1;
        layerIndex = 0;
    }

    public float getWidth() {
        return bounds.getWidth();
    }

    public float getHeight() {
        return bounds.getWidth();
    }

    public float getX() {
        return bounds.getX();
    }

    public float getY() {
        return bounds.getY();
    }

    //initialize setters

    public void setSize(float width, float height) {
        bounds.setSize(width, height);
    }

    public void setWidth(float width) {
        bounds.setWidth(width);
    }

    public void setHeight(float height) {
        bounds.setHeight(height);
    }

    public void setX(float x) {
        bounds.setX(x);
    }

    public void setY(float y) {
        bounds.setY(y);
    }
}
