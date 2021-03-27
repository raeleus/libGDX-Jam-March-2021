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

    public static Sound sfx_assaultRifle;

    public static Sound sfx_bloodSquish;

    public static Sound sfx_click;

    public static Sound sfx_cry;

    public static Sound sfx_ekgBeep;

    public static Sound sfx_ekgFlat;

    public static Sound sfx_evilLaugh;

    public static Sound sfx_explosion;

    public static Sound sfx_happynes;

    public static Sound sfx_helicopter;

    public static Sound sfx_jumpLanding;

    public static Sound sfx_libgdxAhhh;

    public static Sound sfx_libgdxApplause;

    public static Sound sfx_libgdxGore;

    public static Sound sfx_libgdxHeeHee;

    public static Sound sfx_libgdxMagic;

    public static Sound sfx_libgdxMagnificentDee;

    public static Sound sfx_libgdxPoof;

    public static Sound sfx_libgdxSaw;

    public static Sound sfx_libgdxSomethingWrong;

    public static Sound sfx_manGrunt;

    public static Sound sfx_mumble;

    public static Sound sfx_ricochet;

    public static Sound sfx_rocketLauncher;

    public static Sound sfx_shotgun;

    public static Sound sfx_step;

    public static Sound sfx_surprised;

    public static Sound sfx_trap;

    public static Sound sfx_womanGrunt;

    public static Sound sfx_womanMumbling;

    public static Music bgm_audioSample;

    public static Music bgm_gameplay;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        skin_skin = assetManager.get("skin/skin.json");
        BloodSpine.skeletonData = assetManager.get("spine/blood.json");
        BloodSpine.animationData = assetManager.get("spine/blood.json-animation");
        BloodSpine.animationAnimation = BloodSpine.skeletonData.findAnimation("animation");
        BloodSpine.defaultSkin = BloodSpine.skeletonData.findSkin("default");
        BossSpine.skeletonData = assetManager.get("spine/boss.json");
        BossSpine.animationData = assetManager.get("spine/boss.json-animation");
        BossSpine.flyAnimation = BossSpine.skeletonData.findAnimation("fly");
        BossSpine.hurtAnimation = BossSpine.skeletonData.findAnimation("hurt");
        BossSpine.rollAnimation = BossSpine.skeletonData.findAnimation("roll");
        BossSpine.runAnimation = BossSpine.skeletonData.findAnimation("run");
        BossSpine.secondWindAnimation = BossSpine.skeletonData.findAnimation("second wind");
        BossSpine.throwAnimation = BossSpine.skeletonData.findAnimation("throw");
        BossSpine.throwUnderhandAnimation = BossSpine.skeletonData.findAnimation("throw underhand");
        BossSpine.defaultSkin = BossSpine.skeletonData.findSkin("default");
        CinematicGameSpine.skeletonData = assetManager.get("spine/cinematic-game.json");
        CinematicGameSpine.animationData = assetManager.get("spine/cinematic-game.json-animation");
        CinematicGameSpine.animationAnimation = CinematicGameSpine.skeletonData.findAnimation("animation");
        CinematicGameSpine.standingAnimation = CinematicGameSpine.skeletonData.findAnimation("standing");
        CinematicGameSpine.defaultSkin = CinematicGameSpine.skeletonData.findSkin("default");
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
        EnemySpine.skeletonData = assetManager.get("spine/enemy.json");
        EnemySpine.animationData = assetManager.get("spine/enemy.json-animation");
        EnemySpine.animationAnimation = EnemySpine.skeletonData.findAnimation("animation");
        EnemySpine.dieAnimation = EnemySpine.skeletonData.findAnimation("die");
        EnemySpine.flyAnimation = EnemySpine.skeletonData.findAnimation("fly");
        EnemySpine.hurtAnimation = EnemySpine.skeletonData.findAnimation("hurt");
        EnemySpine.jetpackAnimation = EnemySpine.skeletonData.findAnimation("jetpack");
        EnemySpine.rainbowAnimation = EnemySpine.skeletonData.findAnimation("rainbow");
        EnemySpine.defaultSkin = EnemySpine.skeletonData.findSkin("default");
        EnemySpine.clubSkin = EnemySpine.skeletonData.findSkin("club");
        EnemySpine.clubTinySkin = EnemySpine.skeletonData.findSkin("clubTiny");
        EnemySpine.diamondSkin = EnemySpine.skeletonData.findSkin("diamond");
        EnemySpine.hatSkin = EnemySpine.skeletonData.findSkin("hat");
        EnemySpine.heartSkin = EnemySpine.skeletonData.findSkin("heart");
        EnemySpine.plateSkin = EnemySpine.skeletonData.findSkin("plate");
        EnemySpine.spadeSkin = EnemySpine.skeletonData.findSkin("spade");
        EnemySpine.teacupSkin = EnemySpine.skeletonData.findSkin("teacup");
        EnemySpine.teacupTinySkin = EnemySpine.skeletonData.findSkin("teacupTiny");
        EnemySpine.teapotSkin = EnemySpine.skeletonData.findSkin("teapot");
        ExplosionSpine.skeletonData = assetManager.get("spine/explosion.json");
        ExplosionSpine.animationData = assetManager.get("spine/explosion.json-animation");
        ExplosionSpine.animationAnimation = ExplosionSpine.skeletonData.findAnimation("animation");
        ExplosionSpine.defaultSkin = ExplosionSpine.skeletonData.findSkin("default");
        KnifeSpine.skeletonData = assetManager.get("spine/knife.json");
        KnifeSpine.animationData = assetManager.get("spine/knife.json-animation");
        KnifeSpine.animationAnimation = KnifeSpine.skeletonData.findAnimation("animation");
        KnifeSpine.defaultSkin = KnifeSpine.skeletonData.findSkin("default");
        Level1Spine.skeletonData = assetManager.get("spine/level1.json");
        Level1Spine.animationData = assetManager.get("spine/level1.json-animation");
        Level1Spine.animationAnimation = Level1Spine.skeletonData.findAnimation("animation");
        Level1Spine.defaultSkin = Level1Spine.skeletonData.findSkin("default");
        Level2Spine.skeletonData = assetManager.get("spine/level2.json");
        Level2Spine.animationData = assetManager.get("spine/level2.json-animation");
        Level2Spine.animationAnimation = Level2Spine.skeletonData.findAnimation("animation");
        Level2Spine.defaultSkin = Level2Spine.skeletonData.findSkin("default");
        Level3Spine.skeletonData = assetManager.get("spine/level3.json");
        Level3Spine.animationData = assetManager.get("spine/level3.json-animation");
        Level3Spine.animationAnimation = Level3Spine.skeletonData.findAnimation("animation");
        Level3Spine.defaultSkin = Level3Spine.skeletonData.findSkin("default");
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
        PlayerSpine.skeletonData = assetManager.get("spine/player.json");
        PlayerSpine.animationData = assetManager.get("spine/player.json-animation");
        PlayerSpine.aimAnimation = PlayerSpine.skeletonData.findAnimation("aim");
        PlayerSpine.aimDownRightAnimation = PlayerSpine.skeletonData.findAnimation("aim-down-right");
        PlayerSpine.aimDuckAnimation = PlayerSpine.skeletonData.findAnimation("aim-duck");
        PlayerSpine.aimJumpAnimation = PlayerSpine.skeletonData.findAnimation("aim-jump");
        PlayerSpine.aimJumpDownAnimation = PlayerSpine.skeletonData.findAnimation("aim-jump-down");
        PlayerSpine.aimJumpDownRightAnimation = PlayerSpine.skeletonData.findAnimation("aim-jump-down-right");
        PlayerSpine.aimJumpUpAnimation = PlayerSpine.skeletonData.findAnimation("aim-jump-up");
        PlayerSpine.aimJumpUpRightAnimation = PlayerSpine.skeletonData.findAnimation("aim-jump-up-right");
        PlayerSpine.aimNoneAnimation = PlayerSpine.skeletonData.findAnimation("aim-none");
        PlayerSpine.aimNoneDuckAnimation = PlayerSpine.skeletonData.findAnimation("aim-none-duck");
        PlayerSpine.aimUpAnimation = PlayerSpine.skeletonData.findAnimation("aim-up");
        PlayerSpine.aimUpRightAnimation = PlayerSpine.skeletonData.findAnimation("aim-up-right");
        PlayerSpine.duckAnimation = PlayerSpine.skeletonData.findAnimation("duck");
        PlayerSpine.fallingAnimation = PlayerSpine.skeletonData.findAnimation("falling");
        PlayerSpine.helicopterAnimation = PlayerSpine.skeletonData.findAnimation("helicopter");
        PlayerSpine.jumpAnimation = PlayerSpine.skeletonData.findAnimation("jump");
        PlayerSpine.landAnimation = PlayerSpine.skeletonData.findAnimation("land");
        PlayerSpine.runAnimation = PlayerSpine.skeletonData.findAnimation("run");
        PlayerSpine.standAnimation = PlayerSpine.skeletonData.findAnimation("stand");
        PlayerSpine.defaultSkin = PlayerSpine.skeletonData.findSkin("default");
        PlayerWeaponSpine.skeletonData = assetManager.get("spine/playerWeapon.json");
        PlayerWeaponSpine.animationData = assetManager.get("spine/playerWeapon.json-animation");
        PlayerWeaponSpine.animationAnimation = PlayerWeaponSpine.skeletonData.findAnimation("animation");
        PlayerWeaponSpine.assaultRifleSkin = PlayerWeaponSpine.skeletonData.findSkin("assault-rifle");
        PlayerWeaponSpine.flameThrowerSkin = PlayerWeaponSpine.skeletonData.findSkin("flame-thrower");
        PlayerWeaponSpine.rocketLauncherSkin = PlayerWeaponSpine.skeletonData.findSkin("rocket-launcher");
        PlayerWeaponSpine.shotgunSkin = PlayerWeaponSpine.skeletonData.findSkin("shotgun");
        PreloadSpine.skeletonData = assetManager.get("spine/preload.json");
        PreloadSpine.animationData = assetManager.get("spine/preload.json-animation");
        PreloadSpine.animationAnimation = PreloadSpine.skeletonData.findAnimation("animation");
        PreloadSpine.defaultSkin = PreloadSpine.skeletonData.findSkin("default");
        ProjectileSpine.skeletonData = assetManager.get("spine/projectile.json");
        ProjectileSpine.animationData = assetManager.get("spine/projectile.json-animation");
        ProjectileSpine.animationAnimation = ProjectileSpine.skeletonData.findAnimation("animation");
        ProjectileSpine.bulletSkin = ProjectileSpine.skeletonData.findSkin("bullet");
        ProjectileSpine.enemySkin = ProjectileSpine.skeletonData.findSkin("enemy");
        ProjectileSpine.rocketSkin = ProjectileSpine.skeletonData.findSkin("rocket");
        textures_textures = assetManager.get("textures/textures.atlas");
        sfx_assaultRifle = assetManager.get("sfx/assault rifle.mp3");
        sfx_bloodSquish = assetManager.get("sfx/blood squish.mp3");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_cry = assetManager.get("sfx/cry.mp3");
        sfx_ekgBeep = assetManager.get("sfx/ekg-beep.mp3");
        sfx_ekgFlat = assetManager.get("sfx/ekg-flat.mp3");
        sfx_evilLaugh = assetManager.get("sfx/evil laugh.mp3");
        sfx_explosion = assetManager.get("sfx/explosion.mp3");
        sfx_happynes = assetManager.get("sfx/happynes.mp3");
        sfx_helicopter = assetManager.get("sfx/helicopter.mp3");
        sfx_jumpLanding = assetManager.get("sfx/jump landing.mp3");
        sfx_libgdxAhhh = assetManager.get("sfx/libgdx ahhh.mp3");
        sfx_libgdxApplause = assetManager.get("sfx/libgdx applause.mp3");
        sfx_libgdxGore = assetManager.get("sfx/libgdx gore.mp3");
        sfx_libgdxHeeHee = assetManager.get("sfx/libgdx hee hee.mp3");
        sfx_libgdxMagic = assetManager.get("sfx/libgdx magic.mp3");
        sfx_libgdxMagnificentDee = assetManager.get("sfx/libgdx magnificent dee.mp3");
        sfx_libgdxPoof = assetManager.get("sfx/libgdx poof.mp3");
        sfx_libgdxSaw = assetManager.get("sfx/libgdx saw.mp3");
        sfx_libgdxSomethingWrong = assetManager.get("sfx/libgdx something wrong.mp3");
        sfx_manGrunt = assetManager.get("sfx/man grunt.mp3");
        sfx_mumble = assetManager.get("sfx/mumble.mp3");
        sfx_ricochet = assetManager.get("sfx/ricochet.mp3");
        sfx_rocketLauncher = assetManager.get("sfx/rocket launcher.mp3");
        sfx_shotgun = assetManager.get("sfx/shotgun.mp3");
        sfx_step = assetManager.get("sfx/step.mp3");
        sfx_surprised = assetManager.get("sfx/surprised.mp3");
        sfx_trap = assetManager.get("sfx/trap.mp3");
        sfx_womanGrunt = assetManager.get("sfx/woman grunt.mp3");
        sfx_womanMumbling = assetManager.get("sfx/woman-mumbling.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_gameplay = assetManager.get("bgm/gameplay.mp3");
        bgm_menu = assetManager.get("bgm/menu.mp3");
    }

    public static class BloodSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class BossSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation flyAnimation;

        public static Animation hurtAnimation;

        public static Animation rollAnimation;

        public static Animation runAnimation;

        public static Animation secondWindAnimation;

        public static Animation throwAnimation;

        public static Animation throwUnderhandAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class CinematicGameSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation standingAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
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

    public static class EnemySpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation dieAnimation;

        public static Animation flyAnimation;

        public static Animation hurtAnimation;

        public static Animation jetpackAnimation;

        public static Animation rainbowAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;

        public static com.esotericsoftware.spine.Skin clubSkin;

        public static com.esotericsoftware.spine.Skin clubTinySkin;

        public static com.esotericsoftware.spine.Skin diamondSkin;

        public static com.esotericsoftware.spine.Skin hatSkin;

        public static com.esotericsoftware.spine.Skin heartSkin;

        public static com.esotericsoftware.spine.Skin plateSkin;

        public static com.esotericsoftware.spine.Skin spadeSkin;

        public static com.esotericsoftware.spine.Skin teacupSkin;

        public static com.esotericsoftware.spine.Skin teacupTinySkin;

        public static com.esotericsoftware.spine.Skin teapotSkin;
    }

    public static class ExplosionSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class KnifeSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class Level1Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class Level2Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class Level3Spine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

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

    public static class PlayerSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation aimAnimation;

        public static Animation aimDownRightAnimation;

        public static Animation aimDuckAnimation;

        public static Animation aimJumpAnimation;

        public static Animation aimJumpDownAnimation;

        public static Animation aimJumpDownRightAnimation;

        public static Animation aimJumpUpAnimation;

        public static Animation aimJumpUpRightAnimation;

        public static Animation aimNoneAnimation;

        public static Animation aimNoneDuckAnimation;

        public static Animation aimUpAnimation;

        public static Animation aimUpRightAnimation;

        public static Animation duckAnimation;

        public static Animation fallingAnimation;

        public static Animation helicopterAnimation;

        public static Animation jumpAnimation;

        public static Animation landAnimation;

        public static Animation runAnimation;

        public static Animation standAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class PlayerWeaponSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin assaultRifleSkin;

        public static com.esotericsoftware.spine.Skin flameThrowerSkin;

        public static com.esotericsoftware.spine.Skin rocketLauncherSkin;

        public static com.esotericsoftware.spine.Skin shotgunSkin;
    }

    public static class PreloadSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin defaultSkin;
    }

    public static class ProjectileSpine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static com.esotericsoftware.spine.Skin bulletSkin;

        public static com.esotericsoftware.spine.Skin enemySkin;

        public static com.esotericsoftware.spine.Skin rocketSkin;
    }
}
