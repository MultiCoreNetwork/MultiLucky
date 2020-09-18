package it.multicoredev.multilucky;

import it.multicoredev.mbcore.spigot.Chat;
import it.multicoredev.mclib.yaml.Configuration;
import it.multicoredev.multilucky.commands.MultiLuckyCompleter;
import it.multicoredev.multilucky.commands.MultiLuckyExecutor;
import it.multicoredev.multilucky.events.OnLuckyBreak;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

/**
 * Copyright © 2020 Daniele Patella. All rights reserved.
 * This file is part of MultiLucky.
 * Unauthorized copying, modifying, distributing of this file, via any medium is strictly prohibited.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
public class MultiLucky extends JavaPlugin {
    private Configuration config;
    private Configuration blocks;
    private LinkedList<String> cmds;
    private LinkedList<String> cmdsName;
    private LinkedList<String> cmdsVip;
    private LinkedList<String> cmdsVipName;

    @Override
    public void onEnable() {
        try {
            initConfig();
            initBlocks();
            initDrops();
        } catch (IOException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new OnLuckyBreak(config, blocks, cmds, cmdsName, cmdsVip, cmdsVipName), this);
        getCommand("multilucky").setExecutor(new MultiLuckyExecutor(this, config, blocks));
        getCommand("multilucky").setTabCompleter(new MultiLuckyCompleter());

        Chat.info("&eMultiLucky loaded and enabled");
    }

    private void initConfig() throws IOException {
        config = new Configuration(new File(getDataFolder(), "config.yml"), getResource("config.yml"));

        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) throw new IOException("Cannot create plugin directory");
        }

        config.autoload();
    }

    private void initBlocks() throws IOException {
        blocks = new Configuration(new File(getDataFolder(), "blocks.yml"), getResource("blocks.yml"));

        if (!getDataFolder().exists()) {
            if (!getDataFolder().mkdir()) throw new IOException("Cannot create plugin directory");
        }

        blocks.autoload();
    }

    private void initDrops() {
        for (String drop : blocks.getSection("luckyblock.drops").getKeys()) {
            cmds = new LinkedList<>();
            cmdsName = new LinkedList<>();

            int chance = blocks.getInt("luckyblock.drops." + drop + ".chance");
            String cmd = blocks.getString("luckyblock.drops." + drop + ".cmd");
            String name = blocks.getString("luckyblock.drops." + drop + ".reward-name");

            for (int i = 0; i < chance; i++) {
                cmds.add(cmd);
                cmdsName.add(name);
            }
        }

        for (String drop : blocks.getSection("luckyblock-vip.drops").getKeys()) {
            cmdsVip = new LinkedList<>();
            cmdsVipName = new LinkedList<>();

            int chance = blocks.getInt("luckyblock-vip.drops." + drop + ".chance");
            String cmdVip = blocks.getString("luckyblock-vip.drops." + drop + ".cmd");
            String name = blocks.getString("luckyblock-vip.drops." + drop + ".reward-name");

            for (int i = 0; i < chance; i++) {
                cmdsVip.add(cmdVip);
                cmdsVipName.add(name);
            }
        }
    }

    @Override
    public void onDisable() {
        Chat.info("&eMultiLucky disabled");
    }
}
