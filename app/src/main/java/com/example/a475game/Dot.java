package com.example.a475game;

public class Dot {
    public float x, y, radius;
    public Dot(float x, float y, float radius)
    {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    private static float distance(float x1, float y1, float x2, float y2)
    {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    boolean contains(float x1, float y1)
    {
        float distanceToCenter = Dot.distance(this.x, this.y, x1, y1);
        return distanceToCenter < radius;
    }

}
