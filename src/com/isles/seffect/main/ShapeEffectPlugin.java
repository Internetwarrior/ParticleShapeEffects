package com.isles.seffect.main;

import com.isles.seffect.listener.ShapeEffectListener;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * @author Liam Everton
 */


/**
 * The Shape Effect plugin creates and draws a two-dimensional triangle
 * within the 3d environment of Minecraft
 * <p>
 * ShapeEffectPlugin Class is used to initialize the spigot plugin and register the listener
 * for handling in-game events.
 */

public class ShapeEffectPlugin extends JavaPlugin {

    //Initializing listener for in-game events.
    private ShapeEffectListener listener = new ShapeEffectListener(this);

    /**
     * Plugin has been enabled on the server.
     **/
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(listener, this);
        getLogger().info("Particle Shape Effects has been enabled!");
    }

    /**
     * Plugin has been disabled on the server.
     **/
    @Override
    public void onDisable() {
        getLogger().info("Particle Shape Effects has been disabled!");
    }

}
