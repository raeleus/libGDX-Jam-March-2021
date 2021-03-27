package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.dongbat.jbump.CollisionFilter;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Item;
import com.dongbat.jbump.Response;
import com.dongbat.jbump.Response.Result;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Resources.ProjectileSpine.*;

public class ProjectileEntity extends Entity {
    public float damage;
    public float recoilSpeed;
    public Entity parent;
    public boolean homing;
    
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
        if (homing && GameScreen.gameScreen.closestFlying != null) {
            setDirection(Utils.pointDirection(x, y, GameScreen.gameScreen.closestFlying.x, GameScreen.gameScreen.closestFlying.y));
            skeleton.getRootBone().setRotation(getDirection());
        }
        
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
                if (enemy.x < 1024) {
                    if (enemy.invincible) {
                        destroy = true;
                        var prop = new Prop(skeletonData, animationData, false);
                        prop.killOnOutside = true;
                        prop.skeleton.setSkin(skeleton.getSkin());
                        prop.setMotion(getSpeed(), MathUtils.random(360));
                        prop.setPosition(x, y);
                        prop.skeleton.getRootBone().setRotation(prop.getDirection());
                        Core.entityController.add(prop);
                        Resources.sfx_ricochet.play(Core.sfx);
                    } else if (skeleton.getSkin() != rocketSkin || enemy.flying || GameScreen.gameScreen.closestFlying == null) {
                        enemy.hurt(!homing && enemy.flying && !(enemy instanceof EnemyPlate)  && !(enemy instanceof EnemyPot) ? damage / 4 : damage, recoilSpeed);
                        destroy = true;
    
                        if (skeleton.getSkin() == rocketSkin) {
                            var prop = new Prop(ExplosionSpine.skeletonData, ExplosionSpine.animationData, true);
                            prop.setPosition(x, y);
                            prop.animationState.setAnimation(0, ExplosionSpine.animationAnimation, false);
                            Core.entityController.add(prop);
                        }
                    }
                }
            } else if (collision.other.userData instanceof ProjectileEntity) {
                var projectile = (ProjectileEntity) collision.other.userData;
                if (!projectile.destroy && (projectile.parent instanceof EnemyEntity && parent instanceof PlayerEntity || projectile.parent instanceof PlayerEntity && parent instanceof EnemyEntity)) {
                    if (projectile.skeleton.getSkin() != rocketSkin && skeleton.getSkin() != rocketSkin) {
                        Resources.sfx_ricochet.play(Core.sfx);
                        
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
                        prop.setMotion(getSpeed(), getDirection() + 180);
                        prop.setPosition(x, y);
                        prop.skeleton.getRootBone().setRotation(prop.getDirection());
                        Core.entityController.add(prop);
                    }
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
