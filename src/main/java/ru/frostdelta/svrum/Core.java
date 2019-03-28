package ru.frostdelta.svrum;


import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Core {

    @SideOnly(Side.CLIENT)
    public void core(String username, String servername){
        System.out.println("1123213123123");
        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = "460797089811660800";
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();

        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = username;
        presence.state = "Playing on " + servername;
        presence.largeImageKey = "test";
        lib.Discord_UpdatePresence(presence);
        lib.Discord_RunCallbacks();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC").start();

    }

}
