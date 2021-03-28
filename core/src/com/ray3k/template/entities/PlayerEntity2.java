package com.ray3k.template.entities;

import static com.ray3k.template.Resources.PlayerSpine.*;

public class PlayerEntity2 extends PlayerEntity {
    @Override
    public void create() {
        super.create();
        jumping = jumpMissileAnimation;
        running = missileAnimation;
        ducking = missileAnimation;
        flying = true;
    }
}
