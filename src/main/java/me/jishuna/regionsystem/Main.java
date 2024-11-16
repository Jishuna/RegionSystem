package me.jishuna.regionsystem;

import org.bukkit.util.BoundingBox;

public class Main {
    private static final RegionManager manager = new RegionManager();

    public static void main(String[] args) {
        BoundingBox box = new BoundingBox(-1, 0, 0, 16, 16, 16);
        Region region = new Region(box);

        manager.addRegion(region);

        System.out.println(manager.getRegionsForLocation(-1, 0, 0).size()); // Should match 1 region
        System.out.println(manager.getRegionsForLocation(-2, 0, 0).size()); // Should match 0 regions

        manager.removeRegion(region);

        System.out.println(manager.getRegionsForLocation(-1, 0, 0).size()); // Should match 0 regions
    }
}
