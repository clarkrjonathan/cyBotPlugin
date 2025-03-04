package org.jonathanclark.cybot_plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;
import java.util.Scanner;
import org.jonathanclark.cybot_plugin.spigot.CyBot;

public class InitClient implements CommandExecutor{
	
	/**
	 * @param sender - sender of the command
	 * @param command - command object with information about 
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		System.out.println("Init Client called");
		if(args.length == 2 && label.equalsIgnoreCase("initclient")) {
			Scanner scanner = new Scanner(args[1]);
			
			int port = scanner.nextInt();
			scanner.close();
			
			String ip = args[0];
			
			
			try {
				CyBot.connectClient(ip, port);
			} catch (IOException e) {
				System.out.println("CyBot Error: Error while executing InitClient: ");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		return false;
	}

}
