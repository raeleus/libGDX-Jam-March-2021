package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.Bone;
import com.esotericsoftware.spine.Event;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.utils.SkeletonDrawable;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.LogoLibgdxSpine.*;

public class LibgdxScreen extends JamScreen {
    private Stage stage;
    private Array<SpineDrawable> spineDrawables;
    private final static Color BG_COLOR = new Color(Color.WHITE);
    private ObjectSet<Sound> sounds;
    private ParticleEffect particleEffect;
    private Bone bone;
    
    @Override
    public void show() {
        super.show();
    
        particleEffect = new ParticleEffect();
        particleEffect.load(Gdx.files.internal("particles/wood.p"), Resources.textures_textures, "logo/");

        spineDrawables = new Array<>();
        sounds = new ObjectSet<>();
        
        Skeleton skeleton = new Skeleton(skeletonData);
        AnimationState animationState = new AnimationState(animationData);
        var spineDrawable = new SpineDrawable(skeletonRenderer, skeleton, animationState);
        spineDrawable.getAnimationState().setAnimation(0, standingAnimation, false);
        spineDrawable.getAnimationState().apply(spineDrawable.getSkeleton());
        spineDrawables.add(spineDrawable);
        
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);
        
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);
    
        Image image = new Image(spineDrawable);
        image.setScaling(Scaling.fit);
        root.add(image).grow();
        spineDrawable.getAnimationState().setAnimation(0, animationAnimation, false);
    
        spineDrawable.getAnimationState().addListener(new AnimationState.AnimationStateAdapter() {
            @Override
            public void complete(AnimationState.TrackEntry entry) {
                if (entry.getAnimation() == animationAnimation) {
                    core.transition(new LogoScreen());
                }
            }
    
            @Override
            public void event(AnimationState.TrackEntry entry, Event event) {
                if (event.getData().getAudioPath() != null && !event.getData().getAudioPath().equals("")) {
                    Sound sound = assetManager.get(event.getData().getAudioPath());
                    sound.play();
                    sounds.add(sound);
                } else if (event.getData().getName().equals("particles")) {
                    if (event.getInt() == 1) {
                        bone = skeleton.findBone("particles");
                        particleEffect.reset();
                        particleEffect.start();
                    } else {
                        particleEffect.allowCompletion();
                    }
                }
            }
        });
        
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                core.transition(new LogoScreen());
                return true;
            }
    
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                core.transition(new LogoScreen());
                return true;
            }
        });
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
        
        for (SkeletonDrawable skeletonDrawable : spineDrawables) {
            skeletonDrawable.update(delta);
        }
        
        if (bone != null) {
            particleEffect.setPosition(bone.getX(), bone.getY());
            particleEffect.update(delta);
        }
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
        
        if (bone != null) {
            stage.getBatch().begin();
            particleEffect.draw(stage.getBatch());
            stage.getBatch().end();
        }
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void hide() {
        super.hide();
        for (Sound sound : sounds) {
            sound.stop();
        }
    }
}
