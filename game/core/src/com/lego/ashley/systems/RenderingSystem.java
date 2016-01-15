package com.lego.ashley.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lego.ashley.components.TextureComponent;
import com.lego.ashley.components.TransformComponent;

import java.util.Comparator;

/**
 * Created by khachatur on 1/14/16.
 */
public class RenderingSystem extends SortedIteratingSystem {

    private Batch batch;
    private ComponentMapper<TextureComponent> textureComponentMapper = ComponentMapper.getFor(TextureComponent.class);
    private ComponentMapper<TransformComponent> transformComponentMapper = ComponentMapper.getFor(TransformComponent.class);


    public RenderingSystem(Batch batch) {
        super(Family.all(TextureComponent.class, TransformComponent.class).get(), new RendererComparator());
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        batch.begin();
        super.update(deltaTime);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TextureComponent textureComponent = textureComponentMapper.get(entity);
        TransformComponent transformComponent = transformComponentMapper.get(entity);

        TextureRegion textureRegion = textureComponent.textureRegion;
        if(textureRegion != null) {
            batch.draw(textureRegion, transformComponent.getX(), transformComponent.getY(), transformComponent.originX, transformComponent.originY,
                    transformComponent.getWidth(), transformComponent.getHeight(), transformComponent.scaleX, transformComponent.scaleY, transformComponent.rotation);
        }
    }

    private static class RendererComparator implements Comparator<Entity> {
        private ComponentMapper<TransformComponent> transformComponentMapper = ComponentMapper.getFor(TransformComponent.class);

        public RendererComparator() {
        }

        public int compare(Entity o1, Entity o2) {

            TransformComponent textureComponent1 = transformComponentMapper.get(o1);
            TransformComponent textureComponent2 = transformComponentMapper.get(o2);

            if (textureComponent1.layerIndex != textureComponent2.layerIndex) {
                return textureComponent1.layerIndex > textureComponent2.layerIndex ? 1 : -1;
            } else {
                return textureComponent1.zIndex > textureComponent2.zIndex ? 1 : -1;
            }
        }
    }
}
