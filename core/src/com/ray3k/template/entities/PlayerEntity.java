package com.ray3k.template.entities;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.attachments.PointAttachment;
import com.ray3k.template.Resources.*;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.PlayerSpine.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    private static final float JUMP_SPEED = 1800f;
    private static final float GRAVITY = -5000f;
    public static float landLevel = 50f;
    private static final float ASSAULT_BULLET_SPEED = 1000f;
    private static final float ASSAULT_BULLET_RATE = .05f;
    private static final float SHOTGUN_BULLET_SPEED = 800f;
    private static final float SHOTGUN_BULLET_RATE = .4f;
    private static final float SHOTGUN_BULLET_ANGLE = 120;
    private static final int SHOTGUN_BULLET_COUNT = 5;
    private static final float ROCKET_BULLET_SPEED = 600f;
    private static final float ROCKET_BULLET_RATE = .9f;
    private static final int ROCKET_BULLET_COUNT = 3;
    private static Vector2 temp = new Vector2();
    private static Vector2 temp2 = new Vector2();
    private PlayerWeaponEntity weapon;
    private Bone weaponHandBone;
    private Bone weaponBone;
    private float shotRate;
    private float shotTimer;
    private float shotSpeed;
    public boolean flying;
    
    public enum GunMode {
        ASSAULT, SHOTGUN, ROCKET
    }
    private GunMode gunMode = GunMode.ASSAULT;
    private boolean onGround;
    public static PlayerEntity player;
    public boolean controllable = true;
    public Animation running;
    public Animation jumping;
    public Animation ducking;
    
    @Override
    public void create() {
        running = runAnimation;
        jumping = jumpAnimation;
        ducking = duckAnimation;
        
        collisionBoxDebugColor = Color.BLUE;
        player = this;
        depth = PLAYER_DEPTH;
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(-10, 15, 50, 100, nullCollisionFilter);
        skeleton.setScale(.5f, .5f);
        animationState.setAnimation(0, running, true);
        animationState.setAnimation(1, aimAnimation, false);
        weapon = new PlayerWeaponEntity();
        weaponHandBone = skeleton.findBone("weapon");
        entityController.add(weapon);
        weaponBone = weapon.skeleton.getRootBone();
        
        setGunMode(GunMode.ASSAULT);
        gravityY = GRAVITY;
        animationState.setAnimation(0, jumping, true);
        onGround = false;
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void event(TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null) {
                    Sound sound = assetManager.get(event.getData().getAudioPath());
                    sound.play(sfx);
                }
            }
    
            @Override
            public void complete(TrackEntry entry) {
                if (entry.getAnimation() == helicopterAnimation) {
                    gameScreen.endLevel = true;
                }
            }
        });
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (controllable) {
            float weaponRotation = 0;
    
            if (y <= landLevel) {
                if (!onGround) {
                    onGround = true;
                    if (!bgm_gameplay.isPlaying()) {
                        bgm_gameplay.setVolume(bgm * .3f);
                        bgm_gameplay.setLooping(true);
                        bgm_gameplay.play();
                    }
                    sfx_jumpLanding.play(sfx);
                }
                Animation aim;
                if (gameScreen.areAllBindingsPressed(UP, RIGHT)) {
                    setCollisionBox(-10, 15, 50, flying ? 120 : 100, nullCollisionFilter);
                    aim = aimUpRightAnimation;
                    weaponRotation = 45;
                } else if (gameScreen.isBindingPressed(UP)) {
                    setCollisionBox(-10, 15, 50, flying ? 120 : 100, nullCollisionFilter);
                    aim = aimUpAnimation;
                    weaponRotation = 90;
                } else if (gameScreen.areAllBindingsPressed(DOWN, RIGHT)) {
                    setCollisionBox(-10, 15, 50, flying ? 120 : 100, nullCollisionFilter);
                    aim = aimDownRightAnimation;
                    weaponRotation = 315;
                } else if (gameScreen.isBindingPressed(DOWN)) {
                    if (flying) {
                        aim = aimJumpDownAnimation;
                        setCollisionBox(-10, 15, 50, 120, nullCollisionFilter);
                        weaponRotation = 270;
                    } else {
                        aim = aimDuckAnimation;
                        setCollisionBox(-10, 15, 50, 50, nullCollisionFilter);
                        weaponRotation = 0;
                    }
                } else {
                    setCollisionBox(-10, 15, 50, flying ? 120 : 100, nullCollisionFilter);
                    aim = aimAnimation;
                    weaponRotation = 0;
                }
                if (animationState.getCurrent(1).getAnimation() != aim) animationState.setAnimation(1, aim, false);
        
                if (gameScreen.isBindingJustPressed(JUMP)) {
                    onGround = false;
                    deltaY = JUMP_SPEED;
                    gravityY = GRAVITY;
                    animationState.setAnimation(0, jumping, true);
                } else {
                    Animation stance;
                    if (gameScreen.isBindingPressed(DOWN) && !gameScreen.isBindingPressed(RIGHT)) {
                        stance = ducking;
                    } else {
                        stance = running;
                    }
            
                    if (animationState.getCurrent(0).getAnimation() != stance)
                        animationState.setAnimation(0, stance, true);
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
    
            if (y < landLevel) {
                y = landLevel;
                deltaY = 0;
                gravityY = 0;
                animationState.setAnimation(0, landAnimation, false);
                animationState.addAnimation(0, running, true, 0);
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
                    sfx_shotgun.play(sfx);
                    for (int i = 0; i < SHOTGUN_BULLET_COUNT; i++) {
                        var projectile = new ProjectileEntity(this);
                        projectile.damage = 100f;
                        projectile.recoilSpeed = 225f;
                        entityController.add(projectile);
                        projectile.setPosition(temp.x, temp.y);
                        var rotation = weaponRotation - SHOTGUN_BULLET_ANGLE / 2 + SHOTGUN_BULLET_ANGLE / SHOTGUN_BULLET_COUNT * i;
                        projectile.setMotion(shotSpeed, rotation);
                        projectile.skeleton.getRootBone().setRotation(rotation);
                        projectile.skeleton.setSkin(ProjectileSpine.bulletSkin);
                        projectile.skeletonBounds.update(projectile.skeleton, true);
                        projectile.setCollisionBox(projectile.skeletonBounds, new ProjectileEntity.Filter());
                    }
                } else if (gunMode == GunMode.ROCKET) {
                    sfx_rocketLauncher.play(sfx);
                    for (int i = 0; i < ROCKET_BULLET_COUNT; i++) {
                        var projectile = new ProjectileEntity(this);
                        projectile.homing = true;
                        projectile.damage = 200f;
                        projectile.recoilSpeed = 600f;
                        entityController.add(projectile);
                        temp2.set(50f * i, 0f);
                        temp2.rotateDeg(weaponRotation + 180);
                        temp2.add(temp);
                        projectile.setPosition(temp2.x, temp2.y);
                        projectile.setMotion(shotSpeed, weaponRotation);
                        projectile.skeleton.getRootBone().setRotation(weaponRotation);
                        projectile.skeleton.setSkin(ProjectileSpine.rocketSkin);
                        projectile.skeletonBounds.update(projectile.skeleton, true);
                        projectile.setCollisionBox(projectile.skeletonBounds, new ProjectileEntity.Filter());
                    }
                } else {
                    sfx_assaultRifle.play(sfx);
                    var projectile = new ProjectileEntity(this);
                    projectile.damage = 25f;
                    projectile.recoilSpeed = 75f;
                    entityController.add(projectile);
                    projectile.setPosition(temp.x, temp.y);
                    projectile.setMotion(shotSpeed, weaponRotation);
                    projectile.skeleton.getRootBone().setRotation(weaponRotation);
                    projectile.skeleton.setSkin(ProjectileSpine.bulletSkin);
                    projectile.skeletonBounds.update(projectile.skeleton, true);
                    projectile.setCollisionBox(projectile.skeletonBounds, new ProjectileEntity.Filter());
                }
            }
        }
    }
    
    public void setGunMode(GunMode gunMode) {
        this.gunMode = gunMode;
        shotTimer = .2f;
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
    
    public void kill() {
        sfx_womanGrunt.play(sfx);
        destroy = true;
        weapon.destroy = true;
        var prop = new Prop(skeletonData, animationData, false);
        prop.animationState.setAnimation(0, standAnimation, false);
        prop.killOnOutside = true;
        prop.deltaRotation = 500f;
        prop.setPosition(x, y);
        prop.setMotion(1500, 90);
        prop.skeleton.getRootBone().setScale(.5f);
        prop.setGravity(3000, 270f);
        prop.reloadLevel = true;
        entityController.add(prop);
    }
    
    public void stopControlling() {
        controllable = false;
        animationState.setAnimation(0, running, false);
        y = landLevel;
        weapon.visible = false;
        animationState.clearTrack(1);
        animationState.setEmptyAnimation(0, 0);
        animationState.addAnimation(0, standAnimation, false, 2);
        animationState.addAnimation(0, helicopterAnimation, false, 0);
    }
}
