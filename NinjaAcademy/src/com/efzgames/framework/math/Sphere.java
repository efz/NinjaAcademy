package com.efzgames.framework.math;

public class Sphere {
    public final Vector3 center = new Vector3();
    public float radius;
    
    public Sphere(float x, float y, float z, float radius) {
        this.center.set(x,y,z);
        this.radius = radius;
    }
    
    public Sphere(Vector3  center, float radius) {
        this.center.set(center);
        this.radius = radius;
    }
    
    public boolean contains(Vector2 point){
    	float dx = point.x - center.x;
    	float dy = point.y - center.y;
    	return dx*dx + dy*dy <= radius*radius;
    }
}