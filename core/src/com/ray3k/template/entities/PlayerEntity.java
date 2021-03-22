package com.ray3k.template.entities;

import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;

import static com.ray3k.template.Resources.PlayerSpine.*;

public class PlayerEntity extends Entity {
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
        
        if (y < 0) y = 0;
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
