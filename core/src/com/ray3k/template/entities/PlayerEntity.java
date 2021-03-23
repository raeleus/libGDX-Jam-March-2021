package com.ray3k.template.entities;

import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.attachments.PointAttachment;
import com.ray3k.template.Resources.*;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.PlayerSpine.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    private static final float JUMP_SPEED = 1800f;
    private static final float GRAVITY = -5000f;
    private static final float LAND_LEVEL = 50f;
    private static final float ASSAULT_BULLET_SPEED = 1000f;
    private static final float ASSAULT_BULLET_RATE = .05f;
    private static final float SHOTGUN_BULLET_SPEED = 800f;
    private static final float SHOTGUN_BULLET_RATE = .4f;
    private static final float SHOTGUN_BULLET_ANGLE = 120;
    private static final float SHOTGUN_BULLET_COUNT = 5;
    private static final float ROCKET_BULLET_SPEED = 600f;
    private static final float ROCKET_BULLET_RATE = .6f;
    private PlayerWeaponEntity weapon;
    private Bone weaponHandBone;
    private Bone weaponBone;
    private float shotRate;
    private float shotTimer;
    private float shotSpeed;
    public enum GunMode {
        ASSAULT, SHOTGUN, ROCKET
    }
    private GunMode gunMode = GunMode.ASSAULT;
    private static Vector2 temp = new Vector2();
    
    @Override
    public void create() {
        depth = PLAYER_DEPTH;
        setSkeletonData(skeletonData, animationData);
        skeleton.setScale(.5f, .5f);
        animationState.setAnimation(0, runAnimation, true);
        animationState.setAnimation(1, aimAnimation, false);
        weapon = new PlayerWeaponEntity();
        weaponHandBone = skeleton.findBone("weapon");
        entityController.add(weapon);
        weaponBone = weapon.skeleton.getRootBone();
        
        setGunMode(GunMode.ASSAULT);
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
    
        if (gameScreen.isBindingJustPressed(CHANGE_WEAPON)) {
            setGunMode(GunMode.values()[(gunMode.ordinal() + 1) % GunMode.values().length]);
        }
        
        shotTimer -= delta;
        if (shotTimer <= 0 && gameScreen.isBindingPressed(SHOOT)) {
            shotTimer = shotRate;
            var point = (PointAttachment) weapon.skeleton.findSlot("muzzle").getAttachment();
            temp.setZero();
            point.computeWorldPosition(weaponBone, temp);
            
            if (gunMode == GunMode.SHOTGUN) {
                for (int i = 0; i < SHOTGUN_BULLET_COUNT; i++) {
                    var projectile = new ProjectileEntity();
                    entityController.add(projectile);
                    projectile.setPosition(temp.x, temp.y);
                    projectile.setMotion(shotSpeed, weaponRotation - SHOTGUN_BULLET_ANGLE / 2 + SHOTGUN_BULLET_ANGLE / SHOTGUN_BULLET_COUNT * i);
                }
            } else {
                var projectile = new ProjectileEntity();
                entityController.add(projectile);
                projectile.setPosition(temp.x, temp.y);
                projectile.setMotion(shotSpeed, weaponRotation);
            }
        }
    }
    
    public void setGunMode(GunMode gunMode) {
        this.gunMode = gunMode;
        if (this.gunMode == GunMode.ASSAULT) {
            shotRate = ASSAULT_BULLET_RATE;
            shotSpeed = ASSAULT_BULLET_SPEED;
            weapon.skeleton.setSkin(PlayerWeaponSpine.assaultRifleSkin);
        } else if (this.gunMode == GunMode.SHOTGUN) {
            shotRate = SHOTGUN_BULLET_RATE;
            shotSpeed = SHOTGUN_BULLET_SPEED;
            weapon.skeleton.setSkin(PlayerWeaponSpine.shotgunSkin);
        } else if (this.gunMode == GunMode.ROCKET) {
            shotRate = ROCKET_BULLET_RATE;
            shotSpeed = ROCKET_BULLET_SPEED;
            weapon.skeleton.setSkin(PlayerWeaponSpine.rocketLauncherSkin);
        }
        shotTimer = shotRate;
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
    
    }
}
