package it.multicoredev.multilucky.commands;

import it.multicoredev.mbcore.spigot.Chat;
import it.multicoredev.mclib.yaml.Configuration;
import it.multicoredev.multilucky.MultiLucky;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Copyright © 2020 by Daniele Patella
 * This file is part of MultiLucky.
 * MCLib is under "The 3-Clause BSD License", you can find a copy <a href="https://opensource.org/licenses/BSD-3-Clause">here</a>.
 * <p>
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
public class MultiLuckyExecutor implements CommandExecutor {
    private final MultiLucky plugin;
    private final Configuration config;
    private final Configuration blocks;
    private final String version = Bukkit.getBukkitVersion();

    public MultiLuckyExecutor(MultiLucky plugin, Configuration config, Configuration blocks) {
        this.plugin = plugin;
        this.config = config;
        this.blocks = blocks;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("multilucky.admin")) {
            Chat.send(config.getString("messages.insufficient-perms"), sender);
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            if (sender instanceof Player) {

                sendHelp((Player) sender);
            } else {
                Chat.send(sendMessage(config.getString("messages.console")), sender);
                return true;
            }
            return true;
        }

        if (args[0].equalsIgnoreCase("give")) {
            if (args.length < 3) {
                Chat.send(sendMessage(config.getString("messages.invalid-usage")), sender);
                return true;
            }

            Player player = Bukkit.getPlayer(args[1]);

            if (player == null) {
                Chat.send(sendMessage(config.getString("messages.invalid-player")), sender);
                return true;
            }

            String type = args[2];

            if (!type.equalsIgnoreCase("normal") && !type.equalsIgnoreCase("vip")) {
                Chat.send(sendMessage(config.getString("messages.invalid-type")), sender);
                return true;
            }

            int amount;

            if (args.length < 4) {
                amount = 1;
            } else {
                try {
                    amount = Integer.parseInt(args[3]);
                } catch (NumberFormatException ignored) {
                    Chat.send(sendMessage(config.getString("messages.invalid-amount")), sender);
                    return true;
                }
            }

            Material material;

            String vip = blocks.getString("luckyblock-vip.material-block");
            String normal = blocks.getString("luckyblock.material-block");

            ItemStack item;
            boolean isVip;

            if (!isNewer()) {
                if (type.equalsIgnoreCase("vip")) {
                    isVip = true;
                    material = Bukkit.getUnsafe().getMaterial(vip, Integer.parseInt(version));
                } else {
                    isVip = false;
                    material = Bukkit.getUnsafe().getMaterial(normal, Integer.parseInt(version));
                }

                if (material == null) return true;
                byte data = getData(material);

                item = new ItemStack(material, amount, (short) 0, data);
            } else {
                if (type.equalsIgnoreCase("vip")) {
                    isVip = true;
                    material = Material.getMaterial(vip);
                } else {
                    isVip = false;
                    material = Material.getMaterial(normal);
                }

                if (material == null) return true;

                item = new ItemStack(material, amount);
            }

            ItemMeta itemMeta = item.getItemMeta();

            if (!(itemMeta == null)) {
                if (isVip) {
                    itemMeta.setDisplayName(Chat.getTranslated(blocks.getString("luckyblock-vip.display-name")));
                } else {
                    itemMeta.setDisplayName(Chat.getTranslated(blocks.getString("luckyblock.display-name")));
                }
            }

            item.setItemMeta(itemMeta);
            PlayerInventory inventory = player.getInventory();

            if (config.getBoolean("features.use-nbts")) {
                //TODO use-nbts
            } else {
                inventory.addItem(item);
                return true;
            }

            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            plugin.onDisable();
            plugin.onEnable();

            Chat.send(sendMessage(config.getString("messages.reload")), sender);
            return true;
        }

        return true;
    }

    private boolean isNewer() {
        if (version.startsWith("1.13")) return true;
        else if (version.startsWith("1.14")) return true;
        else if (version.startsWith("1.15")) return true;
        else return version.startsWith("1.16");
    }

    private byte getData(Material material) {
        if (material.toString().equalsIgnoreCase("WET_SPONGE")) return 1;
        else return 0;
    }

    private void sendHelp(Player player) {
        Chat.send("&f&m--&e MultiLucky &f&m--", player);
        Chat.send(config.getString("help-messages.params"), player);
        Chat.send("&6/mlucky [help] &3- &r" + config.getString("help-messages.help"), player);
        Chat.send("&6/mlucky give <player> <type> [amount] &3- &r" + config.getString("help-messages.give"), player);
        Chat.send("&6/mlucky reload &3- &r" + config.getString("help-messages.reload"), player);
        Chat.send("&f&m--&e MultiLucky &f&m--", player);
    }

    private String sendMessage(String message) {
        return config.getString("messages.prefix") + message;
    }
}