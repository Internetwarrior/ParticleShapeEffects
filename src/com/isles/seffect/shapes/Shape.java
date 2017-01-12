package com.isles.seffect.shapes;

import org.bukkit.Location;

/**
 * @author Liam Everton
 */

/*
 * Shape interface is used to create a structure for future implemented shapes other than currently the Triangle.
 * Interface allows for individual logic to be created for each shape.
 */
public interface Shape {

    /**
     * Draw a vertex between the start location and the end location.
     * Returns all stored locations between the two locations.
     */
    Location[] drawEdge(Location start_point, Location end_point);

    /**
     * Draws all stored vertices.
     */
    void drawVertices();

    /**
     * Draws points to fill the shape.
     */
    void fill(Location[] rows);

    /**
     * Draws shape, boolean fill determines whether shape is filled or not.
     */
    void draw(boolean fill);

}
