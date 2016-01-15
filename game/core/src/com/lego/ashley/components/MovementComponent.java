package com.lego.ashley.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by khachatur on 1/14/16.
 */
public class MovementComponent implements Component, Pool.Poolable {
    public Vector2 velocity = new Vector2();
    public Vector2 acceleration = new Vector2();
    @Override
    public void reset() {
        velocity.set(0, 0);
    }
}
