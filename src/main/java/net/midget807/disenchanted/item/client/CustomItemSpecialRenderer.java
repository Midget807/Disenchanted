package net.midget807.disenchanted.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class CustomItemSpecialRenderer implements NoDataSpecialModelRenderer {
    public static final Transformation DEFAULT_TRANSFORMATION = new Transformation(null, null, new Vector3f(1.0F, -1.0F, -1.0F), null);
    private final TridentModel model;

    public CustomItemSpecialRenderer(TridentModel model) {
        this.model = model;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {

    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {

    }
}
