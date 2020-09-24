package it.multicoredev.multilucky.itemStack;

import it.multicoredev.mbcore.spigot.Chat;
import it.multicoredev.mclib.misc.KeyVal;
import it.multicoredev.mclib.yaml.Configuration;
import it.multicoredev.multilucky.MultiLucky;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
public class ItemStackHelper_1_12 implements ItemStackHelper {
    @Override
    @SuppressWarnings("deprecation")
    public ItemStack getItemStack(String material, int amount, boolean isVip) {
        Configuration config = MultiLucky.config;
        Material block;
        byte data;

        if (material.equalsIgnoreCase("WET_SPONGE")) {
            block = Material.getMaterial("SPONGE");
            data = 1;
        } else {
            block = Material.getMaterial(material);
            data = 0;
        }

        if (block == null) {
            if (isVip) {
                Chat.severe(config.getString("messages.invalid-material-vip"));
            } else {
                Chat.severe(config.getString("messages.invalid-material"));
            }

            return null;
        }

        return new ItemStack(block, amount, (short) 0, data);
    }

    @Override
    public KeyVal<Material, Byte> getMaterial(String material) {
        Material block;
        byte data;

        if (material.equalsIgnoreCase("WET_SPONGE")) {
            block = Material.getMaterial("SPONGE");
            data = 1;
        } else {
            block = Material.getMaterial(material);
            data = 0;
        }

        return new KeyVal<>(block, data);
    }
}
