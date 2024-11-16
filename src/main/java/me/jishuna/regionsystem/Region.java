package me.jishuna.regionsystem;

import org.bukkit.util.BoundingBox;

import java.util.Objects;

public class Region {
    private final BoundingBox bounds;

    public Region(BoundingBox bounds) {
        this.bounds = bounds;
    }

    public BoundingBox getBounds() {
        return bounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region region)) return false;
        return Objects.equals(bounds, region.bounds);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bounds);
    }
}
