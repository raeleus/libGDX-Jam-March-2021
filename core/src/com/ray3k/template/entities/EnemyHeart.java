package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.spine.attachments.PointAttachment;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.EnemySpine.*;
import static com.ray3k.template.Resources.*;

public class EnemyHeart extends EnemyEntity {
    public static final float TARGET_SPEED = 100f;
    public static final float ACCELERATION = 400f;
    private static final float BULLET_SPEED = 500f;
    private static final float BULLET_RATE = 1.4f;
    private float shotTimer = BULLET_RATE;
    private static Vector2 temp = new Vector2();
    
    @Override
    public void create() {
        super.create();
        health = 400f;
        skeleton.setSkin(heartSkin);
        animationState.setAnimation(0, animationAnimation, true);
        
        setMotion(TARGET_SPEED, 180f);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeletonBounds, enemyCollisionFilter);
    }
    
    @Override
    public void act(float delta) {
        deltaX = Utils.approach(deltaX, -TARGET_SPEED, ACCELERATION * delta);
        if (getCollisionBoxRight() < -50) destroy = true;
        
        shotTimer -= delta;
        if (shotTimer < 0) {
            shotTimer = BULLET_RATE;
            var point = (PointAttachment) skeleton.findSlot("projectile").getAttachment();
            temp.setZero();
            var bone = skeleton.findBone("enemy");
            point.computeWorldPosition(bone, temp);
            var weaponRotation = point.computeWorldRotation(bone);
            
            var projectile = new ProjectileEntity(this);
            projectile.damage = 25f;
            projectile.recoilSpeed = 75f;
            entityController.add(projectile);
            projectile.skeleton.setSkin(ProjectileSpine.enemySkin);
            projectile.setPosition(temp.x, temp.y);
            projectile.setMotion(BULLET_SPEED, weaponRotation);
            projectile.skeleton.getRootBone().setRotation(weaponRotation);
            projectile.skeletonBounds.update(projectile.skeleton, true);
            projectile.setCollisionBox(projectile.skeletonBounds, new ProjectileEntity.Filter());
        }
    }
    
    @Override
    public void hurt(float damage, float recoilSpeed) {
        addMotion(recoilSpeed / 2, 0);
        health -= damage;
        if (!destroy && health < 0) {
            destroy = true;
            var prop = new Prop(skeletonData, animationData, false);
            prop.killOnOutside = true;
            prop.skeleton.setSkin(skeleton.getSkin());
            prop.animationState.setAnimation(0, dieAnimation, false);
            prop.setPosition(x, y);
            prop.setMotion(getSpeed(), getDirection());
            prop.addMotion(MathUtils.random(300, 600), 90);
            prop.deltaRotation = -MathUtils.random(200, 360);
            prop.gravityY = -2000f;
            Core.entityController.add(prop);
            sfx_manGrunt.play(sfx);
            sfx_bloodSquish.play(sfx);
    
            for (int i = 0; i < 10; i++) {
                prop = new Prop(BloodSpine.skeletonData, BloodSpine.animationData, false);
                prop.killOnOutside = true;
                prop.deltaRotation = MathUtils.random(200f);
                prop.skeleton.getRootBone().setRotation(MathUtils.random(360));
                prop.setMotion(MathUtils.random(1000), MathUtils.random(360));
                prop.addMotion(getSpeed(), getDirection());
                prop.setPosition(getCollisionBoxCenterX(), getCollisionBoxCenterY());
                prop.gravityY = -1000f;
                Core.entityController.add(prop);
            }
        } else {
            for (int i = 0, particles = (int) (damage / 25f * 2); i < particles; i++) {
                var prop = new Prop(BloodSpine.skeletonData, BloodSpine.animationData, false);
                prop.killOnOutside = true;
                prop.deltaRotation = MathUtils.random(200f);
                prop.skeleton.getRootBone().setRotation(MathUtils.random(360));
                prop.setMotion(MathUtils.random(1000), MathUtils.random(360));
                prop.addMotion(getSpeed(), getDirection());
                prop.setPosition(getCollisionBoxCenterX(), getCollisionBoxCenterY());
                prop.gravityY = -1000f;
                Core.entityController.add(prop);
            }
        }
    }
}
