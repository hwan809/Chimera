/*
 * Copyright (C) 2017 Karus Labs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.karuslabs.commons.animation;

import net.md_5.bungee.api.*;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import static org.apache.commons.lang.StringUtils.center;


/**
 * Represents a <code>Player</code>'s action bar. 
 * <p>
 * This class is Spigot exclusive. Attempts to run it with CraftBukkit will result in undefined behaviour.
 * <code>ActionBar</code> must be initialised via {@link #initialise(BukkitScheduler, Plugin)} before <code>animate()</code> may be called.
 * Instances are effectively immutable and contain a message to animate and display on a player's action bar.
 */
public class ActionBar {    
                
    private static BukkitScheduler scheduler;
    private static Plugin plugin;
    
    
    /**
     * Initialises <code>ActionBar</code> with the specified <code>BukkitScheduler</code> and
     * <code>Plugin</code>. This method must be called before {@link #animate(Player)} and {@link #animate(Player, int)}
     * may be called.
     * 
     * @param scheduler
     * @param plugin 
     */
    public static void initialise(BukkitScheduler scheduler, Plugin plugin) {
        ActionBar.scheduler = scheduler;
        ActionBar.plugin = plugin;
    }
    
    
    private String message;
    private ChatColor color;
    private int frames;
    private int maxLength;
    
    
    /**
     * Constructs a <code>ActionBar</code> with the specified <code>ConfigurationSection</code>.
     * Obtains the values mapped to the keys from the specified <code>ConfigurationSection</code> if present; else the default values.
     * <p>
     * Valid keys and their default values are as follows: <pre>message: "", color: WHITE, frames: 4</pre>. Note that the value for
     * <pre>color</pre> must be in all capital letters.
     * 
     * @param config the ConfigurationSection
     */
    public ActionBar(ConfigurationSection config) {
        message = ChatColor.translateAlternateColorCodes('&', config.getString("message", ""));
        color = ChatColor.valueOf(config.getString("color", "WHITE"));
        frames = config.getInt("frames", 4);
        maxLength = frames * 2 + message.length();
    }
    
    /**
     * Constructs a <code>ActionBar</code> with the specified message, color and frames.
     * 
     * @param message the message to be animated and displayed to a player
     * @param color the color of the arrows used in the animation
     * @param frames the number of frames
     */
    public ActionBar(String message, ChatColor color, int frames) {
        this.message = message;
        this.color = color;
        this.frames = frames;
        this.maxLength = frames * 2 + message.length();
    }
    
    
    /**
     * 
     * @param player 
     */
    public void animate(Player player) {
        animate(player, 0);
    }
    
    /**
     * 
     * @param player
     * @param delay 
     */
    public void animate(Player player, int delay) {
        for (int length = message.length(); length <= maxLength; length += 2, delay += 3) {
            int capturedLength = length;
            scheduler.scheduleSyncDelayedTask(plugin, 
                () -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(color + "«" + center(message, capturedLength) + color + "»")),
                delay
            );
        }
    }

    
    /**
     * @return 
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @return 
     */
    public ChatColor getColor() {
        return color;
    }

    /**
     * @return 
     */
    public int getFrames() {
        return frames;
    }
    
}
