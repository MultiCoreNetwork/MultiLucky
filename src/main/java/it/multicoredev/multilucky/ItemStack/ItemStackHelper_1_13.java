package it.multicoredev.multilucky.ItemStack;

import it.multicoredev.mbcore.spigot.Chat;
import it.multicoredev.mclib.yaml.Configuration;
import it.multicoredev.multilucky.MultiLucky;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
public class ItemStackHelper_1_13 implements ItemStackHelper {
    @Override
    public ItemStack getItemStack(String material, int amount, boolean isVip) {
        Configuration config = MultiLucky.config;
        Material block = Material.getMaterial(material);

        if (block == null) {
            if (isVip) {
                Chat.severe(config.getString("messages.invalid-material-vip"));
            } else {
                Chat.severe(config.getString("messages.invalid-material"));
            }

            return null;
        }

        ItemStack itemStack = new ItemStack(block, amount);

        return itemStack;
    }
}
