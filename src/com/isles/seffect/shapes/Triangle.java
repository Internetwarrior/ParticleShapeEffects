package com.isles.seffect.shapes;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Comparator;

/**
* @author Liam Everton
*/

/*
 * Triangle Class implements the interface Shape in order to standardize it's structure.
 * This class handles generating the sides, vertices to create a triangle as well as filling the generated triangle.
 */
public class Triangle implements Shape {

    //Encapsulate the fields given by the constructor.
    private Location location; //location of player
    private Player player; //player relevant to event

    private Location[] vertices = new Location[3]; //vertices of the triangle.
    private double step; //frequency of sampling/fill.

    //comparators to sort the points into the regular order of the example triangle.
    private Comparator<Location> sortByY = (new Comparator<Location>() {
        public int compare(Location v1, Location v2) {
            return ((int) (v1.getY() - v2.getY()));
        }
    });

    private Comparator<Location> sortByZ = (new Comparator<Location>() {
        public int compare(Location v1, Location v2) {
            return ((int) (v1.getY() - v2.getY()));
        }
    });

    /**
     * Triangle constructor to encapsulate collected fields.
     * Previously collected vertices are also organized into a computable manner.
     *
     * @param player   - Reference of the relevant player.
     * @param location - Location property of the player object.
     * @param vertices - Array of locations selected by the user for the triangle's vertices.
     * @param fill     - The sampling rate/fill of the drawing.
     **/
    public Triangle(Player player, Location location, Location[] vertices, double fill) {
        this.player = player;
        this.location = location;
        this.vertices = vertices;
        this.step = fill;

        //sorting vertices into the recongizable order of the example triangle.
        this.vertices[0] = new Location(location.getWorld(), location.getX(), getLowestY(), getLowestZ()); //starting vertex.
        this.vertices[1] = new Location(location.getWorld(), location.getX(), getHighestY(), getLowestZ()); // second vertex directly above vertex.
        this.vertices[2] = new Location(location.getWorld(), location.getX(), getHighestY(), getHighestZ()); // hypotenuse starting point.
    }


    /**
     * Used to draw the line between two locations to form an edge,
     * as well as to cache the points sampled between these two locations
     * to be returned and used to fill the triangle.
     *
     * @param start_point - Starting location of the edge.
     * @param end_point   - Location of where the edge is to end.
     **/
    public Location[] drawEdge(Location start_point, Location end_point) {

        double distance_z = end_point.getZ() - start_point.getZ(); //calculated z distance between the two locations
        double distance_y = end_point.getY() - start_point.getY(); //calculated y distance between the two locations

        double total_distance = Math.sqrt(distance_y + distance_z); //the total distance
        Double total_steps = total_distance / step; //total num of steps to that point

        //Array initialization with enough bounds to store points.
        Location[] vertex_points = new Location[((int) (total_steps / step) + 1)];

        double current_z = start_point.getZ(); //z position to be incremented in loop
        double current_y = start_point.getY(); //y position to be incremented in loop

        int count = 0;
        for (double step_count = 0; step_count <= total_steps; step_count += step) { //total number of iterations = total num of steps * 0.2 (to create a fuller look)

            //increment each co-ordinate if neccessary.
            if (current_y <= end_point.getY()) current_y += step;
            if (current_z <= end_point.getZ()) current_z += step;

            //Spawn the particle in the relevant position and cache the calculated points to be returned after.
            location.getWorld().spawnParticle(Particle.REDSTONE, start_point.getX(), current_y, current_z, 1);
            vertex_points[count] = new Location(location.getWorld(), start_point.getX(), current_y, current_z);
            count++; //increment vertex_points index
        }
        return vertex_points;
    }

    /**
     * Draws all vertices within the vertices array object.
     */
    public void drawVertices() {
        for (int i = 0; i < 2; i++) {
            location.getWorld().spawnParticle(Particle.REDSTONE, vertices[i].getX(), vertices[i].getY(), vertices[i].getZ(), 1);
        }
    }

    /**
     * Fills triangle by using calculated locations and incrementing their co-ordinates.
     *
     * @param rows - points in the hypotenuse
     **/
    public void fill(Location[] rows) {
        double destination_z;

        if (vertices[0] != null) destination_z = vertices[0].getZ();
        else return;

        for (Location point : rows) {
            if (point != null) {
                for (double current_z = point.getZ(); current_z > destination_z + step; current_z -= step) {
                    location.getWorld().spawnParticle(Particle.REDSTONE, point.getX(), point.getY(), current_z, 1);
                }
            }
        }
    }


    /**
     * Draws the triangle and fills it if specified.
     *
     * @param should_fill - Whether the shape should be filled or not.
     **/
    public void draw(boolean should_fill) {
        drawVertices();
        drawEdge(vertices[0], vertices[1]);
        drawEdge(vertices[1], vertices[2]);
        if (should_fill) {
            fill(drawEdge(vertices[0], vertices[2])); //hypotoneuse
        } else {
            drawEdge(vertices[0], vertices[2]);
        }
    }


    //Used to grab sort and generate the lowest Z and Y values from the vertices array.
    private double getLowestZ() {
        Arrays.sort(vertices, sortByZ);
        return vertices[0].getZ();
    }

    private double getHighestZ() {
        Arrays.sort(vertices, sortByZ);
        return vertices[2].getZ();
    }

    private double getLowestY() {
        Arrays.sort(vertices, sortByY);
        return vertices[0].getY();
    }

    private double getHighestY() {
        Arrays.sort(vertices, sortByY);
        return vertices[2].getY();
    }
}
