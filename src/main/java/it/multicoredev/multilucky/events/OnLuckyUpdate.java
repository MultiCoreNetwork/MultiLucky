package it.multicoredev.multilucky.events;

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
/*
//TODO Fix
public class OnLuckyUpdate implements Listener {
    private final Configuration blocks;
    private ItemStackHelper itemStackHelper;

    public OnLuckyUpdate(Configuration blocks) {
        this.blocks = blocks;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    private void onBlockUpdate(BlockPhysicsEvent event) {
        itemStackHelper = initItemStackHelper();

        String blockMaterial = event.getBlock().getType().toString();
        byte blockData = event.getBlock().getState().getData().getData();

        String normal = blocks.getString("luckyblock.material-block");
        String vip = blocks.getString("luckyblock-vip.material-block");
        byte normalData = blocks.getByte("luckyblock.data");
        byte vipData = blocks.getByte("luckyblock-vip.data");

        if (blockMaterial.equals(normal) && blockData == normalData) {
            event.getBlock().setType(Material.valueOf(itemStackHelper.getMaterial(normal).getKey().toString()));
            return;
        }

        if (blockMaterial.equals(vip) && blockData == vipData) {
            event.getBlock().setType(Material.valueOf(itemStackHelper.getMaterial(vip).getKey().toString()));
        }
    }
}
 */

