package com.efzgames.framework.math;

public class Rectangle2 {
	public final Vector2 topLeft;
	public final Vector2 bottomRight;
    
    public Rectangle2(Vector2 topLeft, Vector2 bottomRight) {
        this.topLeft = new Vector2(topLeft);
        this.bottomRight = new Vector2(bottomRight);
    }
    
    public boolean contains(Vector2 point){    	
    	return point.x >= topLeft.x && point.x <= bottomRight.x && point.y <= topLeft.y && point.y >= bottomRight.y;
    }
}
