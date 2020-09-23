package it.multicoredev.multilucky.events;

import it.multicoredev.mclib.yaml.Configuration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

/**
 * Copyright © 2020 Daniele Patella. All rights reserved.
 * This file is part of ShyPlugins.
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
/*
//TODO Fix
public class OnLuckyUpdate implements Listener {
    private final Configuration blocks;

    public OnLuckyUpdate(Configuration blocks) {
        this.blocks = blocks;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockUpdate(BlockPhysicsEvent event) {
        String blockMaterial = event.getBlock().getType().toString();
        byte blockData = event.getBlock().getState().getData().getData();

        String normal = blocks.getString("luckyblock.material-block");
        String vip = blocks.getString("luckyblock-vip.material-block");
        byte normalData = blocks.getByte("luckyblock.data");
        byte vipData = blocks.getByte("luckyblock-vip.data");

        if (blockMaterial.equals(normal) && blockData == normalData) {
            event.setCancelled(true);
            return;
        }

        if (blockMaterial.equals(vip) && blockData == vipData) {
            event.setCancelled(true);
        }
    }
}
 */

