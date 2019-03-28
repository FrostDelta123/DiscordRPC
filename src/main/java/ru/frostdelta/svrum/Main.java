package ru.frostdelta.svrum;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "rpc", canBeDeactivated = true)

public class Main {

    private String servername = "SandBox";
    

    public static FMLEventChannel channel;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        String server = config.getString("Servername", config.CATEGORY_GENERAL, servername, "Servername");
        config.save();
        String username = Minecraft.getMinecraft().getSession().getUsername();
        start(username, server);

    }



    @SideOnly(Side.CLIENT)
    private void start(String username, String server){
        Core core = new Core();
        core.core(username, server);
    }

    @Mod.EventHandler
    public void loadComplete(FMLLoadCompleteEvent event) {
    }


}
