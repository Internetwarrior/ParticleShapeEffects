package com.isles.seffect.listener;

import com.isles.seffect.main.ShapeEffectPlugin;
import com.isles.seffect.shapes.Triangle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Liam Everton
 */

public class ShapeEffectListener implements Listener {

    //Reference of plugin - Used to access it's methods within the same instance. (disabling/enabling plugin)
    private ShapeEffectPlugin plugin;

    //Buffer of locations selected by the user to create the triangle. SINGULAR INSTANCED.
    private Location[] vertices = new Location[3]; //length of 3 due to triangle and no other shapes capable yet


    /**
     * Construct event listener for ShapeEffectPlugin.
     *
     * @param plugin - the parent plugin.
     **/
    public ShapeEffectListener(ShapeEffectPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Called upon a user interacting within the server.
     * Handled in order to check whether a user has selected a vertice, or chosen the FILL option.
     * <p>
     * NOTE: not instanced, there is no way to differ which values belong to what user,
     * therefore we would need to use a key:value system with multiple users when implementing
     * this plugin in a real-world environment.
     *
     * @param event - the object storing event details such as the relevant player and location.
     **/
    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        if (event.getClickedBlock() == null) { //check if block is air/not existent.
            for (int i = 0; i < 3; i++) { //iterate through all vertices.
                if (vertices[i] == null) { //null check current vertex.
                    vertices[i] = location; //store current selected location in vertex location.
                    player.sendMessage("You have selected Vertex Number: " + i);
                    if (vertices[2] != null) { //null check last vertex to see if last point has been selected or not.
                        player.sendMessage("Left-click with redstone in hand to fill the shape.");
                    }
                    return;
                }
            }
            if (vertices[2] != null) { // null check last vertex.
                Triangle triangle = new Triangle(player, location, vertices, 0.2D); //create triangle object with event values.
                boolean fill = false; //Fill or no fill.
                if (player.getEquipment().getItemInMainHand().getType().equals(Material.REDSTONE)) // check if redstone in hand
                    fill = true;
                triangle.draw(fill); //draw triangle using particles.
                player.sendMessage("A triangle has been created " + "FILLED: " + fill);
                vertices = new Location[3]; //reset cached vertices.
            }
        }

    }
}



