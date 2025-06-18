/*/*
 *
 * Statistics-Expansion
 * Copyright (C) 2020 Ryan McCarthy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
package net.trueog.miniplaceholders.statistics;

import org.bukkit.plugin.java.JavaPlugin;

public class StatisticExpansion extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        // Register the MiniPlaceholders.
        new StatisticsPlaceholders();

        getLogger().info(getName() + " v." + getPluginMeta().getVersion() + " has been enabled!");
    }
}
