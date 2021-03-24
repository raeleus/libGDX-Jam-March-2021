package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.Color;
import com.dongbat.jbump.CollisionFilter;
import com.ray3k.template.*;

import static com.ray3k.template.Resources.EnemySpine.*;

public class EnemyClubEntity extends EnemyEntity {
    @Override
    public void create() {
        super.create();
        skeleton.setSkin(clubSkin);
        animationState.setAnimation(0, animationAnimation, true);
        
        setMotion(200f, 180f);
        skeletonBounds.update(skeleton, true);
        setCollisionBox(skeletonBounds, Core.nullCollisionFilter);
        collisionBoxDebugColor = Color.GREEN;
    }
}
