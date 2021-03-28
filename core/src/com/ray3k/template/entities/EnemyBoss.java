package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.ray3k.template.*;
import com.ray3k.template.Resources.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.BossSpine.*;
import static com.ray3k.template.Resources.*;

public class EnemyBoss extends EnemyEntity {
    public static final float TARGET_SPEED = 200f;
    public static final float ACCELERATION = 400f;
    public static final float STANDING_X = 950f;
    public static final float DELAY_START = 3.0f;
    public static final float DELAY_MINIMUM = .7f;
    public static final float HEALTH_START = 20000f;
    private float delay = 3.0f;
    private float timer = delay;
    
    @Override
    public void create() {
        super.create();
        sfx_evilLaugh.play(sfx);
        health = HEALTH_START;
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, runAnimation, true);
        
        setMotion(TARGET_SPEED, 180f);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeletonBounds, Core.nullCollisionFilter);
    }
    
    @Override
    public void act(float delta) {
        deltaX = Utils.approach(deltaX, -TARGET_SPEED, ACCELERATION * delta);
        if (x < STANDING_X) {
            x = STANDING_X;
            deltaX = 0;
        }
        if (getCollisionBoxRight() < -50) destroy = true;
        
        timer -= delta;
        if (timer < 0) {
            delay = DELAY_START * health / HEALTH_START;
            if (delay < DELAY_MINIMUM) delay = DELAY_MINIMUM;
            timer = delay;
            
            var choice = MathUtils.random(3);
            switch (choice) {
                case 0:
                    animationState.setAnimation(1, throwAnimation, false);
                    animationState.addEmptyAnimation(1, .15f, 0);
    
                    var knife = new Knife();
                    knife.setMotion(900, 180);
                    knife.setPosition(x, y + 180);
                    entityController.add(knife);
                    knife.depth = ENEMY_DEPTH + 1;
                    break;
                case 1:
                    animationState.setAnimation(1, throwUnderhandAnimation, false);
                    animationState.addEmptyAnimation(1, .15f, 0);
    
                    knife = new Knife();
                    knife.setMotion(900, 180);
                    knife.setPosition(x, y + 100);
                    entityController.add(knife);
                    knife.depth = ENEMY_DEPTH - 1;
                    break;
                case 2:
                    animationState.setAnimation(1, throwAnimation, false);
                    animationState.addEmptyAnimation(1, .15f, 0);
    
                    var mortar = new MortarRound();
                    mortar.gravityY = -1000f;
                    mortar.setMotion(875, 135);
                    mortar.setPosition(x, y + 180);
                    entityController.add(mortar);
                    mortar.depth = ENEMY_DEPTH + 1;
                    break;
                case 3:
                    animationState.setAnimation(1, throwUnderhandAnimation, false);
                    animationState.addEmptyAnimation(1, .15f, 0);
        
                    mortar = new MortarRound();
                    mortar.gravityY = 1000f;
                    mortar.setMotion(900, 225);
                    mortar.setPosition(x, y + 100);
                    entityController.add(mortar);
                    mortar.depth = ENEMY_DEPTH - 1;
                    break;
            }
            
        }
    }
    
    @Override
    public void hurt(float damage, float recoilSpeed) {
        if (animationState.getCurrent(2) == null || animationState.getCurrent(2).isComplete()) {
            animationState.setAnimation(2, hurtAnimation, false);
        }
        health -= damage;
        sfx_manGrunt.play(sfx);
        if (!destroy && health < 0) {
            sfx_evilLaugh.play(sfx);
            
            for (var entity : entityController.entities) {
                if (entity instanceof MortarRound || entity instanceof Knife) {
                    entity.destroy = true;
                }
            }
            
            PlayerEntity.player.stopControlling();
            destroy = true;
            var prop = new Prop(skeletonData, animationData, true);
            prop.skeleton.setSkin(skeleton.getSkin());
            prop.animationState.setAnimation(0, secondWindAnimation, false);
            prop.setPosition(x, y);
            Core.entityController.add(prop);
    
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
