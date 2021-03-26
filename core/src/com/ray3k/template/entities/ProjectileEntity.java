package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.ProjectileSpine.*;

public class ProjectileEntity extends Entity {
    public float damage;
    public float recoilSpeed;
    public Entity parent;
    
    public ProjectileEntity(Entity parent) {
        this.parent = parent;
    }
    
    @Override
    public void create() {
        depth = Core.PROJECTILE_DEPTH;
        setSkeletonData(skeletonData, animationData);
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
            if (collision.other.userData instanceof  EnemyEntity) {
                var enemy = (EnemyEntity) collision.other.userData;
                if (!enemy.isOutside(0, 0, 1024, 576, 0)) {
                    enemy.hurt(damage, recoilSpeed);
                    destroy = true;
                }
            } else if (collision.other.userData instanceof ProjectileEntity) {
                var projectile = (ProjectileEntity) collision.other.userData;
                if (projectile.parent instanceof EnemyEntity && parent instanceof PlayerEntity || projectile.parent instanceof PlayerEntity && parent instanceof EnemyEntity) {
                    projectile.destroy = true;
                    var prop = new Prop(skeletonData, animationData, false);
                    prop.killOnOutside = true;
                    prop.skeleton.setSkin(projectile.skeleton.getSkin());
                    prop.setMotion(projectile.getSpeed(), projectile.getDirection() + 180);
                    prop.setPosition(projectile.x, projectile.y);
                    prop.skeleton.getRootBone().setRotation(prop.getDirection());
                    Core.entityController.add(prop);
    
                    destroy = true;
                    prop = new Prop(skeletonData, animationData, false);
                    prop.killOnOutside = true;
                    prop.skeleton.setSkin(skeleton.getSkin());
                    prop.setMotion(getSpeed(), projectile.getDirection() + 180);
                    prop.setPosition(x, y);
                    prop.skeleton.getRootBone().setRotation(prop.getDirection());
                    Core.entityController.add(prop);
                }
            }
        }
    }
    
    public static class Filter implements CollisionFilter {
        @Override
        public Response filter(Item item, Item other) {
            var projectile = (ProjectileEntity) item.userData;
            if (other.userData instanceof EnemyEntity && projectile.parent instanceof PlayerEntity) {
                return Response.cross;
            } else if (other.userData instanceof PlayerEntity && projectile.parent instanceof EnemyEntity) {
                return Response.cross;
            } else if (other.userData instanceof ProjectileEntity) {
                var otherProjectile = (ProjectileEntity) other.userData;
                if (projectile.parent != otherProjectile.parent) return Response.cross;
            }
            return null;
        }
    }
}
