package com.selfcoders.bukkitlibrary;

import org.bukkit.Location;

public class LocationUtils {
    /**
     * Calculate the distance between two locations.
     *
     * @param location1 The first location
     * @param location2 The second location
     * @return The distance between the first and the second location
     */
    public static Integer getDistance(Location location1, Location location2) {
        if (location1.getWorld() != location2.getWorld()) {
            return null;
        }

        int distanceX = Math.abs((int) location1.getX() - (int) location2.getX());
        int distanceY = Math.abs((int) location1.getY() - (int) location2.getY());
        int distanceZ = Math.abs((int) location1.getZ() - (int) location2.getZ());

        return Math.max(distanceX, Math.max(distanceY, distanceZ));
    }
}