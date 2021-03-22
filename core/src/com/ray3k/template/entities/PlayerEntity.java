package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.Animation;

import static com.ray3k.template.Core.Binding.*;
import static com.ray3k.template.Resources.PlayerSpine.*;
import static com.ray3k.template.screens.GameScreen.*;

public class PlayerEntity extends Entity {
    private static final float JUMP_SPEED = 1800f;
    private static final float GRAVITY = -5000f;
    private static final float LAND_LEVEL = 50f;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        skeleton.setScale(.5f, .5f);
        animationState.setAnimation(0, runAnimation, true);
        animationState.setAnimation(1, aimAnimation, false);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (y <= LAND_LEVEL) {
            Animation aim;
            if (gameScreen.areAllBindingsPressed(UP, RIGHT)) {
                aim = aimUpRightAnimation;
            } else if (gameScreen.isBindingPressed(UP)) {
                aim = aimUpAnimation;
            } else if (gameScreen.areAllBindingsPressed(DOWN, RIGHT)) {
                aim = aimDownRightAnimation;
            } else if (gameScreen.isBindingPressed(DOWN)) {
                aim = aimDuckAnimation;
            } else {
                aim = aimAnimation;
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
            } else if (gameScreen.isBindingPressed(UP)) {
                aim = aimJumpUpAnimation;
            } else if (gameScreen.areAllBindingsPressed(DOWN, RIGHT)) {
                aim = aimJumpDownRightAnimation;
            } else if (gameScreen.isBindingPressed(DOWN)) {
                aim = aimJumpDownAnimation;
            } else {
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
