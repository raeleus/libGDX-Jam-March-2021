package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.attachments.PointAttachment;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Resources.PlayerSpine.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    private static final float JUMP_SPEED = 1800f;
    private static final float GRAVITY = -5000f;
    private static final float LAND_LEVEL = 50f;
    private static final float ASSAULT_BULLET_SPEED = 1000f;
    private static final float ASSAULT_BULLET_RATE = .1f;
    private PlayerWeaponEntity weapon;
    private Bone weaponHandBone;
    private Bone weaponBone;
    private float shotRate;
    private float shotTimer;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setScale(.5f, .5f);
        animationState.setAnimation(0, runAnimation, true);
        animationState.setAnimation(1, aimAnimation, false);
        weapon = new PlayerWeaponEntity();
        weaponHandBone = skeleton.findBone("weapon");
        entityController.add(weapon);
        weaponBone = weapon.skeleton.getRootBone();
        
        shotRate = ASSAULT_BULLET_RATE;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        float weaponRotation = 0;
        
        if (y <= LAND_LEVEL) {
            Animation aim;
            if (gameScreen.areAllBindingsPressed(UP, RIGHT)) {
                aim = aimUpRightAnimation;
                weaponRotation = 45;
            } else if (gameScreen.isBindingPressed(UP)) {
                aim = aimUpAnimation;
                weaponRotation = 90;
            } else if (gameScreen.areAllBindingsPressed(DOWN, RIGHT)) {
                aim = aimDownRightAnimation;
                weaponRotation = 315;
            } else if (gameScreen.isBindingPressed(DOWN)) {
                aim = aimDuckAnimation;
                weaponRotation = 0;
            } else {
                aim = aimAnimation;
                weaponRotation = 0;
            }
            if (animationState.getCurrent(1).getAnimation() != aim) animationState.setAnimation(1, aim, false);
            
            if (gameScreen.isBindingJustPressed(JUMP)) {
                deltaY = JUMP_SPEED;
                gravityY = GRAVITY;
                animationState.setAnimation(0, jumpAnimation, true);
            } else {
                Animation stance;
                if (gameScreen.isBindingPressed(DOWN) && !gameScreen.isBindingPressed(RIGHT)) {
                    stance = duckAnimation;
                } else {
                    stance = runAnimation;
                }
    
                if (animationState.getCurrent(0).getAnimation() != stance) animationState.setAnimation(0, stance, true);
            }
        } else {
            Animation aim;
            if (gameScreen.areAllBindingsPressed(UP, RIGHT)) {
                aim = aimJumpUpRightAnimation;
                weaponRotation = 45;
            } else if (gameScreen.isBindingPressed(UP)) {
                aim = aimJumpUpAnimation;
                weaponRotation = 90;
            } else if (gameScreen.areAllBindingsPressed(DOWN, RIGHT)) {
                aim = aimJumpDownRightAnimation;
                weaponRotation = 315;
            } else if (gameScreen.isBindingPressed(DOWN)) {
                aim = aimJumpDownAnimation;
                weaponRotation = 270;
            } else {
                weaponRotation = 0;
                aim = aimJumpAnimation;
            }
            if (animationState.getCurrent(1).getAnimation() != aim) animationState.setAnimation(1, aim, false);
        }
        
        if (y < LAND_LEVEL) {
            y = LAND_LEVEL;
            deltaY = 0;
            gravityY = 0;
            animationState.setAnimation(0, landAnimation, false);
            animationState.addAnimation(0, runAnimation, true, 0);
        }
        
        weapon.setPosition(weaponHandBone.getWorldX(), weaponHandBone.getWorldY());
        weapon.skeleton.getRootBone().setRotation(weaponRotation);
        
        shotTimer -= delta;
        
        if (shotTimer <= 0 && gameScreen.isBindingPressed(SHOOT)) {
            shotTimer = shotRate;
            var point = (PointAttachment) weapon.skeleton.findSlot("muzzle").getAttachment();
            temp.setZero();
            point.computeWorldPosition(weaponBone, temp);
            var projectile = new ProjectileEntity();
            entityController.add(projectile);
            projectile.setPosition(temp.x, temp.y);
            projectile.setMotion(ASSAULT_BULLET_SPEED, weaponRotation);
        }
    }
    
    private static Vector2 temp = new Vector2();
    
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
    
    }
}
