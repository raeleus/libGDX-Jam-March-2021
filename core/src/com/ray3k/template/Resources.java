package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.SkeletonData;

public class Resources {
    public static Skin skin_skin;

    public static TextureAtlas textures_textures;

    public static Sound sfx_click;

    public static Sound sfx_cry;

    public static Sound sfx_ekgBeep;

    public static Sound sfx_ekgFlat;

    public static Sound sfx_happynes;

    public static Sound sfx_libgdxAhhh;

    public static Sound sfx_libgdxApplause;

    public static Sound sfx_libgdxGore;

    public static Sound sfx_libgdxHeeHee;

    public static Sound sfx_libgdxMagic;

    public static Sound sfx_libgdxMagnificentDee;

    public static Sound sfx_libgdxPoof;

    public static Sound sfx_libgdxSaw;

    public static Sound sfx_libgdxSomethingWrong;

    public static Sound sfx_mumble;

    public static Music bgm_audioSample;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        Cinematic1Spine.skeletonData = assetManager.get("spine/cinematic1.json");
        Cinematic1Spine.animationData = assetManager.get("spine/cinematic1.json-animation");
        Cinematic1Spine.animationAnimation = Cinematic1Spine.skeletonData.findAnimation("animation");
        Cinematic1Spine.standingAnimation = Cinematic1Spine.skeletonData.findAnimation("standing");
        Cinematic1Spine.defaultSkin = Cinematic1Spine.skeletonData.findSkin("default");
        Cinematic2Spine.skeletonData = assetManager.get("spine/cinematic2.json");
        Cinematic2Spine.animationData = assetManager.get("spine/cinematic2.json-animation");
        Cinematic2Spine.animationAnimation = Cinematic2Spine.skeletonData.findAnimation("animation");
        Cinematic2Spine.standingAnimation = Cinematic2Spine.skeletonData.findAnimation("standing");
        Cinematic2Spine.defaultSkin = Cinematic2Spine.skeletonData.findSkin("default");
        Cinematic3Spine.skeletonData = assetManager.get("spine/cinematic3.json");
        Cinematic3Spine.animationData = assetManager.get("spine/cinematic3.json-animation");
        Cinematic3Spine.animationAnimation = Cinematic3Spine.skeletonData.findAnimation("animation");
        Cinematic3Spine.standingAnimation = Cinematic3Spine.skeletonData.findAnimation("standing");
        Cinematic3Spine.defaultSkin = Cinematic3Spine.skeletonData.findSkin("default");
        Cinematic4Spine.skeletonData = assetManager.get("spine/cinematic4.json");
        Cinematic4Spine.animationData = assetManager.get("spine/cinematic4.json-animation");
        Cinematic4Spine.animationAnimation = Cinematic4Spine.skeletonData.findAnimation("animation");
        Cinematic4Spine.standingAnimation = Cinematic4Spine.skeletonData.findAnimation("standing");
        Cinematic4Spine.defaultSkin = Cinematic4Spine.skeletonData.findSkin("default");
        LogoLibgdxSpine.skeletonData = assetManager.get("spine/logo-libgdx.json");
        LogoLibgdxSpine.animationData = assetManager.get("spine/logo-libgdx.json-animation");
        LogoLibgdxSpine.animationAnimation = LogoLibgdxSpine.skeletonData.findAnimation("animation");
        LogoLibgdxSpine.standingAnimation = LogoLibgdxSpine.skeletonData.findAnimation("standing");
        LogoLibgdxSpine.defaultSkin = LogoLibgdxSpine.skeletonData.findSkin("default");
        LogoRay3kSpine.skeletonData = assetManager.get("spine/logo-ray3k.json");
        LogoRay3kSpine.animationData = assetManager.get("spine/logo-ray3k.json-animation");
        LogoRay3kSpine.animationAnimation = LogoRay3kSpine.skeletonData.findAnimation("animation");
        LogoRay3kSpine.standingAnimation = LogoRay3kSpine.skeletonData.findAnimation("standing");
        LogoRay3kSpine.defaultSkin = LogoRay3kSpine.skeletonData.findSkin("default");
        PreloadSpine.skeletonData = assetManager.get("spine/preload.json");
        PreloadSpine.animationData = assetManager.get("spine/preload.json-animation");
        PreloadSpine.animationAnimation = PreloadSpine.skeletonData.findAnimation("animation");
        PreloadSpine.defaultSkin = PreloadSpine.skeletonData.findSkin("default");
        textures_textures = assetManager.get("textures/textures.atlas");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_cry = assetManager.get("sfx/cry.mp3");
        sfx_ekgBeep = assetManager.get("sfx/ekg-beep.mp3");
        sfx_ekgFlat = assetManager.get("sfx/ekg-flat.mp3");
        sfx_happynes = assetManager.get("sfx/happynes.mp3");
        sfx_libgdxAhhh = assetManager.get("sfx/libgdx ahhh.mp3");
        sfx_libgdxApplause = assetManager.get("sfx/libgdx applause.mp3");
        sfx_libgdxGore = assetManager.get("sfx/libgdx gore.mp3");
        sfx_libgdxHeeHee = assetManager.get("sfx/libgdx hee hee.mp3");
        sfx_libgdxMagic = assetManager.get("sfx/libgdx magic.mp3");
        sfx_libgdxMagnificentDee = assetManager.get("sfx/libgdx magnificent dee.mp3");
        sfx_libgdxPoof = assetManager.get("sfx/libgdx poof.mp3");
        sfx_libgdxSaw = assetManager.get("sfx/libgdx saw.mp3");
        sfx_libgdxSomethingWrong = assetManager.get("sfx/libgdx something wrong.mp3");
        sfx_mumble = assetManager.get("sfx/mumble.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class Cinematic1Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class Cinematic2Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class Cinematic3Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class Cinematic4Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class LogoLibgdxSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class LogoRay3kSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class PreloadSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }
}
