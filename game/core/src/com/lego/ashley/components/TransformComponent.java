package com.lego.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by khachatur on 1/14/16.
 */
public class TransformComponent implements Component, Pool.Poolable {
    public Rectangle bounds;
    public int layerIndex;
    public int zIndex;

    public float scaleX;
    public float scaleY;

    public float originX;
    public float originY;
    public float rotation;

    public TransformComponent() {
        bounds = new Rectangle();
        reset();
    }

    @Override
    public void reset() {
        bounds.set(0, 0, 0, 0);
        layerIndex = 0;
        zIndex = 0;

        scaleX = 1;
        scaleY = 1;

        originX = 0;
        originY = 0;
        rotation = 0;
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

    public void setPosition(Vector2 vector2) {
        bounds.setPosition(vector2);
    }

    public void setPosition(float x, float y) {
        bounds.setPosition(x, y);
    }

    public void setX(float x) {
        bounds.setX(x);
    }

    public void setY(float y) {
        bounds.setY(y);
    }
}
