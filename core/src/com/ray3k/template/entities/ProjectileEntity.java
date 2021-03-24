package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.ProjectileSpine.*;

public class ProjectileEntity extends Entity {
    @Override
    public void create() {
        depth = Core.PROJECTILE_DEPTH;
        setSkeletonData(skeletonData, animationData);
        skeleton.setScale(.25f, .25f);
        collisionBoxDebugColor = Color.RED;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (isOutside(0, 0, 1024, 576, 50)) {
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
            var enemy = (EnemyEntity) collision.other.userData;
            enemy.destroy = true;
        }
    }
    
    public static class Filter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof EnemyEntity) return Response.cross;
            return null;
        }
    }
}
