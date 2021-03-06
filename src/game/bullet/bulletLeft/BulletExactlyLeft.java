package game.bullet.bulletLeft;

import base.GameObject;
import base.Vector2D;
import game.bullet.ExplosionSpawner;
import game.gift.Gift;
import game.gift.GiftExactly;
import game.gift.GiftTriple;
import game.physic.BoxCollider;
import game.physic.PhysicBody;
import game.physic.RunHitObject;
import game.player.playerLeft.PlayerLeft;
import game.player.playerRight.PlayerRight;
import platform.Platform;
import renderer.AnimationRenderer;
import utils.Utils;

import javax.sound.sampled.Clip;

public class BulletExactlyLeft extends GameObject implements PhysicBody {

    public Vector2D velocity;
    public BoxCollider boxCollider;
    private RunHitObject runHitObject;
    private Clip clip;

    public BulletExactlyLeft() {
        this.velocity = new Vector2D();
        this.renderer = new AnimationRenderer(2, 50, 50,
                "resources/images/bullet1.png",
                "resources/images/bullet2.png",
                "resources/images/bullet3.png",
                "resources/images/bullet4.png",
                "resources/images/bullet5.png",
                "resources/images/bullet6.png",
                "resources/images/bullet7.png",
                "resources/images/bullet8.png"
        );
        this.boxCollider = new BoxCollider(36, 36);
        this.runHitObject = new RunHitObject(
                Gift.class,
                GiftTriple.class,
                GiftExactly.class,
                PlayerLeft.class,
                PlayerRight.class,
                Platform.class
        );
    }

    private void dieBullet() {
        if (this.position.x < 0 || this.position.x > 1024 || this.position.y > 600)
            this.isAlive = false;
    }

    @Override
    public void run() {
        super.run();
        this.boxCollider.position.set(this.position.x - 18, this.position.y - 18);
        this.position.addUp(this.velocity);
        this.dieBullet();
        this.runHitObject.run(this);
    }

    @Override
    public void getHit(GameObject gameObject) {
        this.isAlive = false;
        this.clip = Utils.loadAudio("resources/audio/bullet.wav");
        this.clip.start();
        ExplosionSpawner.instance.explosion(this);

    }

    @Override
    public BoxCollider getBoxCollider() {
        return this.boxCollider;
    }
}
