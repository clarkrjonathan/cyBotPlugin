package org.jonathanclark.cybot_plugin.spigot;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jonathanclark.cybot_plugin.client.Client;
import org.jonathanclark.cybot_plugin.commands.InitClient;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class CyBot extends JavaPlugin implements Listener{
	
	private static String ip;
	private static int port;
	
	private static Client client;
	
	/**
	 * Exits when server is started
	 */
	@Override
	public void onEnable() {
		System.out.println("CyBot Plugin loaded");
		
		this.getCommand("initClient").setExecutor(new InitClient());
		getServer().getPluginManager().registerEvents((Listener) this, (Plugin)this);
	}
	
	/**
	 * Executes when server is closed
	 */
	@Override
	public void onDisable() {
		try {
			client.closeClient();
		} catch (IOException e) {
			System.out.println("CyBot couldn't close client | Exiting...");
			e.printStackTrace();
		}
	}
	
	/**
	 * Attempts a connection of client to CyBot websocket
	 * @param ip - ip of cybot
	 * @param port - port to be used
	 * @return true if client was initialized else false if client is still null
	 * @throws IOException
	 */
	public static boolean connectClient(String ip, int port) throws IOException {
		try {
			System.out.println("Attempting connection with client...");
			client = new Client(ip, port);
			System.out.println("Successful connection to client");
		} catch (IOException e) {
			System.out.println("CyBot Plugin Error: ");
			System.out.println(e);
		}
		
		return client != null;
	}

}
