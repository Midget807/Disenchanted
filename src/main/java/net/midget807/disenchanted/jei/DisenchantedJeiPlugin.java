package net.midget807.disenchanted.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.midget807.disenchanted.DisenchantedMain;
import net.minecraft.resources.Identifier;

@JeiPlugin
public class DisenchantedJeiPlugin implements IModPlugin {
    @Override
    public Identifier getPluginUid() {
        return DisenchantedMain.id("jei_plugin");
    }
}
