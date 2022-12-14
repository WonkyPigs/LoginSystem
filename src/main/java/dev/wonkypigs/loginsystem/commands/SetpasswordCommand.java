package dev.wonkypigs.loginsystem.commands;

import dev.wonkypigs.loginsystem.LoginSystem;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SetpasswordCommand implements CommandExecutor {

    private final LoginSystem plugin = LoginSystem.getPlugin(LoginSystem.class);

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        PersistentDataContainer pdata = player.getPersistentDataContainer();

        if(pdata.has(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING)) {
            player.sendMessage(plugin.prefix + ChatColor.GREEN + "You already have a password set.");
        }
        else{
            if(args.length == 1) {
                pdata.set(new NamespacedKey(plugin, "login_password"), PersistentDataType.STRING, args[0]);
                player.sendMessage(plugin.prefix + ChatColor.GREEN + "Your password has been set to " + args[0]);
                pdata.set(new NamespacedKey(plugin, "loggedin"), PersistentDataType.STRING, "yes");
            }
            else{
                player.sendMessage(plugin.prefix + ChatColor.GREEN + "Usage: '/setpassword <password>'");
            }
        }
        return true;
    }
}
