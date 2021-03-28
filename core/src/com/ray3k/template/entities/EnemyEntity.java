package com.ray3k.template.entities;

import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.EnemySpine.*;

public abstract class EnemyEntity extends Entity {
    public float health;
    public boolean flying;
    public boolean invincible;
    
    public abstract void hurt(float damage, float recoilSpeed);
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        depth = ENEMY_DEPTH;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
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
        if (collisions.size() > 0) PlayerEntity.player.kill();
    }
    
    public static final EnemyCollisionFilter enemyCollisionFilter = new EnemyCollisionFilter();
    public static class EnemyCollisionFilter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            if (other.userData instanceof PlayerEntity) return Response.cross;
            return null;
        }
    }
}
