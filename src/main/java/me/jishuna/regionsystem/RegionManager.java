package me.jishuna.regionsystem;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import java.util.*;

public class RegionManager {
    private static final List<Region> emptyList = Collections.emptyList();
    private final Long2ObjectMap<List<Region>> regionMap = new Long2ObjectOpenHashMap<>();

    public void addRegion(Region region) {
        int minX = region.getBounds().getMin().getBlockX() >> 4;
        int maxX = region.getBounds().getMax().getBlockX() >> 4;
        int minZ = region.getBounds().getMin().getBlockZ() >> 4;
        int maxZ = region.getBounds().getMax().getBlockZ() >> 4;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                long key = Utils.getChunkKey(x, z);
                regionMap.computeIfAbsent(key, k -> new ArrayList<>()).add(region);
            }
        }
    }

    public List<Region> getRegions(Chunk chunk) {
        return regionMap.getOrDefault(Utils.getChunkKey(chunk), emptyList);
    }

    public List<Region> getRegions(int x, int z) {
        return regionMap.getOrDefault(Utils.getChunkKey(x, z), emptyList);
    }

    public List<Region> getRegionsForLocation(Location location) {
        Vector position = location.toVector();
        List<Region> regions = new ArrayList<>();

        for (Region region : getRegions(location.getChunk())) {
            if (region.getBounds().contains(position)) {
                regions.add(region);
            }
        }
        return regions;
    }

    public List<Region> getRegionsForLocation(double x, double y, double z) {
        int chunkX = NumberConversions.floor(x) >> 4;
        int chunkZ = NumberConversions.floor(z) >> 4;

        List<Region> regions = new ArrayList<>();

        for (Region region : getRegions(chunkX, chunkZ)) {
            if (region.getBounds().contains(x, y, z)) {
                regions.add(region);
            }
        }
        return regions;
    }
}
