package it.multicoredev.multilucky.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class MultiLuckyCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("multilucky.admin")) return null;
        if (args.length == 0) return null;
        else if (args.length == 1) return Arrays.asList("help", "give", "reload");
        else if (args.length == 2 && args[0].equalsIgnoreCase("give")) return getPlayerList();
        else if (args.length == 3 && args[0].equalsIgnoreCase("give")) return Arrays.asList("normal", "vip");

        return null;
    }

    private List<String> getPlayerList() {
        List<String> playerList = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerList.add(player.getName());
        }

        return playerList;
    }
}
