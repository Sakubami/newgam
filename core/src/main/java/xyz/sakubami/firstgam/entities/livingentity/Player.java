package xyz.sakubami.firstgam.entities.livingentity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import xyz.sakubami.firstgam.saving.SerializedEntity;
import xyz.sakubami.firstgam.textures.entities.EntityTexture;

public class Player extends LivingEntity{

    public Player() {
        super(EntityTexture.PLAYER, "PLAYER");
    }
}
