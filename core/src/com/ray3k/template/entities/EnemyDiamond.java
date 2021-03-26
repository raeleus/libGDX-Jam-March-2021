package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.EnemySpine.*;
import static com.ray3k.template.Resources.*;

public class EnemyDiamond extends EnemyEntity {
    public static final float TARGET_SPEED = 200f;
    public static final float ACCELERATION = 400f;
    
    @Override
    public void create() {
        super.create();
        health = 200f;
        skeleton.setSkin(diamondSkin);
        animationState.setAnimation(0, flyAnimation, true);
        
        setMotion(TARGET_SPEED, 180f);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeletonBounds, Core.nullCollisionFilter);
        collisionBoxDebugColor = Color.GREEN;
    }
    
    @Override
    public void act(float delta) {
        deltaX = Utils.approach(deltaX, -TARGET_SPEED, ACCELERATION * delta);
        moveTowards(Utils.approach(getSpeed(), TARGET_SPEED, ACCELERATION * delta), PlayerEntity.player.x, PlayerEntity.player.y);
        if (getCollisionBoxRight() < -50) destroy = true;
    }
    
    @Override
    public void hurt(float damage, float recoilSpeed) {
        addMotion(recoilSpeed, 0);
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
