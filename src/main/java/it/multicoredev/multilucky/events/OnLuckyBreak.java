package it.multicoredev.multilucky.events;

import it.multicoredev.mbcore.spigot.Chat;
import it.multicoredev.mclib.misc.KeyVal;
import it.multicoredev.mclib.yaml.Configuration;
import it.multicoredev.multilucky.itemStack.ItemStackHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.LinkedList;
import java.util.Random;

import static it.multicoredev.multilucky.Utils.initItemStackHelper;

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
public class OnLuckyBreak implements Listener {
    private final Configuration config;
    private final Configuration blocks;
    private final LinkedList<String> cmds;
    private final LinkedList<String> cmdsName;
    private final LinkedList<String> cmdsVip;
    private final LinkedList<String> cmdsVipName;
    ItemStackHelper itemStackHelper;

    public OnLuckyBreak(Configuration config, Configuration blocks, LinkedList<String> cmds, LinkedList<String> cmdsName, LinkedList<String> cmdsVip, LinkedList<String> cmdsVipName) {
        this.config = config;
        this.blocks = blocks;
        this.cmds = cmds;
        this.cmdsName = cmdsName;
        this.cmdsVip = cmdsVip;
        this.cmdsVipName = cmdsVipName;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockBreak(BlockBreakEvent event) {
        itemStackHelper = initItemStackHelper();

        if (config.getBoolean("features.use-nbts")) {
            //TODO use-nbts
        } else {
            String blockMaterial = event.getBlock().getType().toString();
            byte blockData = event.getBlock().getState().getData().getData();

            KeyVal normalKeyVal = itemStackHelper.getMaterial(blocks.getString("luckyblock.material-block"));
            String normal = normalKeyVal.getKey().toString();
            byte normalData = (byte) normalKeyVal.getValue();

            KeyVal vipKeyVal = itemStackHelper.getMaterial(blocks.getString("luckyblock-vip.material-block"));
            String vip = vipKeyVal.getKey().toString();
            byte vipData = (byte) vipKeyVal.getValue();

            if (blockMaterial.equals(normal) && blockMaterial.equals(vip) && blockData == normalData && blockData == vipData) {
                Player player = event.getPlayer();

                if (player.hasPermission("multilucky.vip")) {
                    vipExecute(event);
                    return;
                } else if (player.hasPermission("multilucky.normal")) {
                    normalExecute(event);
                    return;
                } else return;
            }

            if (blockMaterial.equals(normal) && blockData == normalData) {
                normalExecute(event);
            } else if (blockMaterial.equals(vip) && blockData == vipData) {
                vipExecute(event);
            }
        }
    }

    private void normalExecute(BlockBreakEvent event) {
        if (event.getBlock().getState().getData().getData() != blocks.getByte("luckyblock.data")) return;
        if (!config.getBoolean("features.luckyblock")) return;

        Player player = event.getPlayer();
        String cmd;
        String name;

        if (!player.hasPermission("multilucky.normal")) {
            Chat.send(sendMessage(config.getString("luckyblock-no-perms")), player);
            event.setCancelled(true);
            return;
        }
        int random = new Random().nextInt(cmds.size());

        cmd = cmds.get(random);
        name = cmdsName.get(random);

        commandExecute(cmd, player, false, name);

        event.setCancelled(true);
        event.getBlock().setType(Material.AIR);
        event.getBlock().getDrops().clear();
    }

    private void vipExecute(BlockBreakEvent event) {
        if (event.getBlock().getState().getData().getData() != blocks.getByte("messages.luckyblock-vip.data")) return;
        if (!config.getBoolean("features.luckyblock-vip")) return;

        Player player = event.getPlayer();
        String cmd;
        String name;

        if (!player.hasPermission("multilucky.vip")) {
            Chat.send(sendMessage(config.getString("messages.luckyblock-vip-no-perms")), player);
            event.setCancelled(true);
            return;
        }
        int random = new Random().nextInt(cmdsVip.size());

        cmd = cmdsVip.get(random);
        name = cmdsVipName.get(random);

        commandExecute(cmd, player, true, name);

        event.setCancelled(true);
        event.getBlock().setType(Material.AIR);
        event.getBlock().getDrops().clear();
    }

    private void commandExecute(String cmd, Player player, boolean isVip, String name) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd.replace("%player%", player.getName()));

        if (isVip) {
            Chat.send(sendMessage(config.getString("messages.luckyblock-vip").replace("%item%", name)), player);
        } else {
            Chat.send(sendMessage(config.getString("messages.luckyblock").replace("%item%", name)), player);
        }
    }

    private String sendMessage(String message) {
        return config.getString("messages.prefix") + message;
    }
}
