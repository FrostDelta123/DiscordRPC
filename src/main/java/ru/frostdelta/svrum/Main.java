package ru.frostdelta.svrum;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLLoadCompleteEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;

@Mod(modid = "RPC", dependencies = "after:Minecraft Forge", canBeDeactivated = false)

public class Main {

    private String servername = "SandBox";
    private String ID;

    public static FMLEventChannel channel;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        String server = config.getString("Servername", config.CATEGORY_GENERAL, servername, "Servername");
		ID = config.getString("ID", config.CATEGORY_GENERAL, "ID", "IDDefault");

        config.save();

        String username = Minecraft.getMinecraft().getSession().getUsername();
        start(username, server);
    }



    @SideOnly(Side.CLIENT)
    private void start(String username, String server){
        Core core = new Core(ID);
        core.core(username, server);
    }

    @EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
    }


}
