package ru.frostdelta.svrum;


import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Core {

	private String id;

	public Core(String ID){
		this.id = ID;
	}

    @SideOnly(Side.CLIENT)
    public void core(String username, String servername){

        DiscordRPC lib = DiscordRPC.INSTANCE;
        String applicationId = id;
        String steamId = "";
        DiscordEventHandlers handlers = new DiscordEventHandlers();

        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.details = username;
        presence.state = "Playing on " + servername;
        presence.largeImageKey = "";
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
