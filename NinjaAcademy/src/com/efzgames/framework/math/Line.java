package com.efzgames.framework.math;

public class Line {
	
    public Vector2 start;    
    public Vector2 end;

    public Line (Vector2 start, Vector2 end)
    {
        this.start = start;
        this.end = end;
    }
   
    public Vector2 getIntersection(Line otherLine)
    {        
        float uA;
        float uB;
        
        float numeratorA;
        float numeratorB;
        float denominator;

        numeratorA = (otherLine.end.x - otherLine.start.x) * (start.y - otherLine.start.y) -
            (otherLine.end.y - otherLine.start.y) * (start.x - otherLine.start.x);
        numeratorB = (end.x - start.x) * (start.y - otherLine.start.y) -
            (end.y - start.y) * (start.x - otherLine.start.x);
        denominator = (otherLine.end.y - otherLine.start.y) * (end.x - start.x) -
            (otherLine.end.x - otherLine.start.x) * (end.y - start.y);

        if (denominator == 0)
        {
            // Lines are parallel (and possibly coincide)
            return null;
        }

        uA = numeratorA / denominator;
        uB = numeratorB / denominator;

        if (uA < 0 || uA > 1 || uB < 0 || uB > 1)
        {
            // The intersection is outside one of the line segments
            return null;
        }

        // The line segments intersect so return the intersection point
        return new Vector2(start.x + uA * (end.x - start.x), start.y + uA * (end.y - start.y));
    }
}
