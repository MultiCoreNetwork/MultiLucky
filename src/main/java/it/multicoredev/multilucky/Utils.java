package it.multicoredev.multilucky;

import it.multicoredev.multilucky.ItemStack.ItemStackHelper;
import it.multicoredev.multilucky.ItemStack.ItemStackHelper_1_12;
import it.multicoredev.multilucky.ItemStack.ItemStackHelper_1_13;
import org.bukkit.Bukkit;

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
public class Utils {
    private static final String version = Bukkit.getBukkitVersion();

    public static ItemStackHelper initItemStackHelper() {
        if (isLegacy()) {
            return new ItemStackHelper_1_12();
        } else {
            return new ItemStackHelper_1_13();
        }
    }

    private static boolean isLegacy() {
        if (version.startsWith("1.13")) return false;
        else if (version.startsWith("1.14")) return false;
        else if (version.startsWith("1.15")) return false;
        else if (version.startsWith("1.16")) return false;
        else return true;
    }
}
