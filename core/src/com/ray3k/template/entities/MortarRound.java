package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Resources.ProjectileSpine.*;

public class MortarRound extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setSkin(enemySkin);
        setCollisionBox(skeletonBounds, mortarRoundCollisionFilter);
        animationState.setAnimation(0, animationAnimation,true);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        skeleton.getRootBone().setRotation(getDirection());
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
                PlayerEntity.player.kill();
            }
        }
    }
    
    public static MortarRoundCollisionFilter mortarRoundCollisionFilter = new MortarRoundCollisionFilter();
    public static class MortarRoundCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof PlayerEntity) return Response.cross;
            return null;
        }
    }
}
