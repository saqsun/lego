package com.lego.ashley.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.lego.ashley.components.MovementComponent;
import com.lego.ashley.components.TextureComponent;
import com.lego.ashley.components.TransformComponent;

/**
 * Created by khachatur on 1/15/16.
 */
public class MovementSystem extends IteratingSystem {
    private ComponentMapper<MovementComponent> movementComponentMapper = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<TransformComponent> transformComponentMapper = ComponentMapper.getFor(TransformComponent.class);

    public MovementSystem() {
        super(Family.all(MovementComponent.class, TransformComponent.class).get());
    }
    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        MovementComponent movementComponent = movementComponentMapper.get(entity);
        TransformComponent transformComponent = transformComponentMapper.get(entity);

        transformComponent.setX(transformComponent.getX() + movementComponent.velocity.x  * deltaTime);
        transformComponent.setY(transformComponent.getY() + movementComponent.velocity.y  * deltaTime);

        movementComponent.velocity.x += movementComponent.acceleration.x  * deltaTime;
        movementComponent.velocity.y += movementComponent.acceleration.y  * deltaTime;
    }
}
