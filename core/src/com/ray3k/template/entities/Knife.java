package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.*;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.KnifeSpine.*;

public class Knife extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeletonBounds, knifeCollisionFilter);
        animationState.setAnimation(0, animationAnimation,true);
        collisionBoxDebugColor = Color.RED;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (getCollisionBoxRight() < -50) {
            destroy = true;
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
        for (int i = 0; i < collisions.size(); i++) {
            var collision = collisions.get(i);
            if (collision.other.userData instanceof PlayerEntity) {
                destroy = true;
            }
        }
    }
    
    public static KnifeCollisionFilter knifeCollisionFilter = new KnifeCollisionFilter();
    public static class KnifeCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof PlayerEntity) return Response.cross;
            return null;
        }
    }
}
