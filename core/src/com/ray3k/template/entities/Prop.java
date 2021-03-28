package com.ray3k.template.entities;

import com.badlogic.gdx.audio.Sound;
import com.dongbat.jbump.Collisions;
import com.dongbat.jbump.Response.Result;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.SkeletonData;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;

public class Prop extends Entity {
    public boolean killOnOutside;
    public float deltaRotation;
    public boolean reloadLevel;
    public boolean endGame;
    
    public Prop(SkeletonData skeletonData, AnimationStateData animationData, boolean destroyOnComplete) {
        setSkeletonData(skeletonData, animationData);
        
        if (destroyOnComplete) {
            animationState.addListener(new AnimationStateAdapter() {
                @Override
                public void complete(TrackEntry entry) {
                    destroy = true;
                }
            });
        }
    
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void event(TrackEntry entry, Event event) {
                System.out.println("event");
                if (event.getData().getAudioPath() != null) {
                    Sound sound = assetManager.get(event.getData().getAudioPath());
                    sound.play(sfx);
                    System.out.println(event.getData().getAudioPath());
                }
            }
        });
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        skeleton.getRootBone().setRotation(skeleton.getRootBone().getRotation() + deltaRotation * delta);
        if (killOnOutside && isOutside(0, 0, 1024, 576, 50)) {
            destroy = true;
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
        if (reloadLevel) Core.core.transition(new GameScreen());
        if (endGame) GameScreen.gameScreen.endLevel = true;
    }
    
    @Override
    public void projectedCollision(Result result) {
    
    }
    
    @Override
    public void collision(Collisions collisions) {
    
    }
}
