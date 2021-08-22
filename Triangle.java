
/**
 * Triangle.java represents a triangle in the Euclidean space.
 *
 * @author Yuval Ben Eliyahu
 * @version 19/3/2021
 */
public class Triangle
{
        private Point _point1;
        private Point _point2;
        private Point _point3;
        static final double EPSILON = 0.001;

    /**
     * Construct a new Triangle (default constructor) from 3 default Points:(1,0),(-1,0),(0,1)
     */
    public Triangle()
    {
     _point1 = new Point(1,0);
     _point2 = new Point(-1,0);
     _point3 = new Point(0,1);
        
    }
    
    /**
     * Construct a new Triangle (from 3 points)
     * @param p1 - the first Point
     * @param p2 - the second Point
     * @param p3 - the third Point
     */
    public Triangle(Point p1, Point p2, Point p3)
    {
    this();
    if(isValid(p1,p2,p3)){
     _point1 = new Point(p1);
     _point2 = new Point(p2);
     _point3 = new Point(p3);
        }
    
    }

        /**
     * Construct a new Triangle (from 6 reals)
     * @param x1 - the x coordinate of the first Point
     * @param y1 - the y coordinate of the first Point
     * @param x2 - the x coordinate of the second Point
     * @param y2 - the y coordinate of the second Point
     * @param x3 - the x coordinate of the third Point
     * @param y3 - the y coordinate of the third Point
     */
    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3)
    {
     this(new Point(x1, y1),new Point(x2, y2),new Point(x3, y3));
    }

    /**
     * Copy constructor Creates a new triangle identical to the given triangle
     * @param other - the triangle to be copied
     */
    public Triangle(Triangle other)
    {
     _point1 = new Point(other._point1);
     _point2 = new Point(other._point2);
     _point3 = new Point(other._point3);
        
    }
    
        /**
     * This method returns the triangle's first point
     * @return The first point of the triangle
     */
    public Point getPoint1()
    {
     return new Point(_point1);
    }
    
            /**
     * This method returns the triangle's second point
     * @return The second point of the triangle
     */
    public Point getPoint2()
    {
     return new Point(_point2);
    }
    
            /**
     * This method returns the triangle's third point
     * @return The third point of the triangle
     */
    public Point getPoint3()
    {
     return new Point(_point3);
    }
    
            /**
     * This method sets the first point of the triangle.
     * @param p - The new first point
     */
    public void setPoint1(Point p)
    {
    if(isValid(p, _point2 ,_point3))
        _point1 = new Point(p);
    }
    
            /**
     * This method sets the second point of the triangle.
     * @param p - The second first point
     */
    public void setPoint2(Point p)
    {
    if(isValid(_point1, p, _point3))
        _point2 = new Point(p);
    }
    
            /**
     * This method sets the third point of the triangle.
     * @param p - The new third point
     */
    public void setPoint3(Point p)
    {
    if(isValid(_point1, _point2, p))
     _point3 = new Point(p);
    }
    
        /**
     * This method checks if the difference between two numbers is EPSILON precision.
     * @param a - The new first number
     * @param b - the second number
     * @return True if the difference is smaller then EPSILON
     */
    private boolean isEpsilonPrecision(double a, double b)
    {
         return (Math.abs(a-b)<EPSILON);
    }
    
    /**
     * This method checks if 3 points make a valid triangle. A triangle is valid if no sum of any two sides equals the third side (with precision EPSILON)
     * @param p1 - The first point
     * @param p2 - The second point
     * @param p3 - The third point
     * @return true if the triangle(p1,p2,p3) is valid
     */
    public boolean isValid(Point p1, Point p2, Point p3)
    {
    if(isEpsilonPrecision(p1.distance(p2),p3.distance(p1)+p3.distance(p2)))
         return false;
    else if(isEpsilonPrecision(p2.distance(p3),p1.distance(p2)+p1.distance(p3))) 
        return false;
    else if(isEpsilonPrecision(p3.distance(p1),p2.distance(p1)+p2.distance(p3))) 
        return false;
    else return true;
    }
    
    /**
     * This method returns a String representation of thetTriangle. The format of the string should be: {(x1,y1),(x2,y2),(x3,y3)}
     *
     * @return a String representation of the triangle
     */
    public String toString()
    {
        return ("{" + _point1 + "," + _point2 + "," + _point3 + "}");
    }
    
    /**
     * This method returns the triangle's perimeter.
     * @return the triangle's perimeter
     */
    public double getPerimeter()
    {
    return (_point1.distance(_point2)+ _point1.distance(_point3) + _point3.distance(_point2));
    }
    
    /**
     * This method returns the triangle's area (using Heron's formula).
     * @return the triangle's area
     */
    public double getArea()
    {
    double s = (this.getPerimeter()/2);
    double area = Math.sqrt(s*(s-_point1.distance(_point2))*(s-_point1.distance(_point3))*(s-_point3.distance(_point2)));
    return area;
    }

    /**
     * This method returns true if the triangle is an isosceles triangle(with precision EPSILON).
     * @return true if the triangle is an isosceles triangle
     */
    public boolean isIsosceles()
    {
    if(isEpsilonPrecision(_point1.distance(_point2),_point1.distance(_point3)))
        return true;
    if(isEpsilonPrecision(_point3.distance(_point1),_point3.distance(_point2)))
        return true;
    if(isEpsilonPrecision(_point2.distance(_point1),_point2.distance(_point3)))
        return true;
    else return false;
    }
    
    /**
     * This method returns true if the triangle is a right-angled triangle.
     * @return true if the triangle is a right-angled triangle
     */
    public boolean isPythagorean()
    {
    if(isEpsilonPrecision(this.getArea(),(_point1.distance(_point2)*_point1.distance(_point3)/2)))
        return true;
    else if(isEpsilonPrecision(this.getArea(),(_point2.distance(_point1)*_point2.distance(_point3)/2)))
        return true;
    else if(isEpsilonPrecision(this.getArea(),(_point3.distance(_point1)*_point3.distance(_point2)/2)))
        return true;
    else return false;
    }
    
    /**
     * This method returns true if the triangle is in a given circle.
     * @param x - the x value of the point which is the center of the circle
     * @param y - the y value of the point which is the center of the circle
     * @param r - the radius of the circle
     * @return true if the triangle is in a given circle
     */
    public boolean isContainedInCircle(double x , double y, double r)
    {
    Point center = new Point(x,y);
    if(center.distance(_point1)<=r || center.distance(_point2)<=r || center.distance(_point3)<=r)
        return true;
    else return false;
    }
    
    /**
     * This method returns the lowest vertex of the triangle.
     * @return the lowest vertex
     */
    public Point lowestPoint()
    {
    if(_point1.isUnder(_point2) && _point1.isUnder(_point3))
        return _point1;
    else if(_point2.isUnder(_point1) && _point2.isUnder(_point3))
        return _point2;
    else if(_point3.isUnder(_point1) && _point3.isUnder(_point2))
        return _point3;
    else if(_point1.isUnder(_point2)){
        if(_point1.isLeft(_point3))
            return _point1;
        else return _point3;   
    }
    else if(_point1.isUnder(_point3)){
        if(_point1.isLeft(_point2))
            return _point1;
        else return _point2; 
    }
    else if(_point2.isLeft(_point3))
            return _point2;
        else return _point3;  
    }
    
        /**
     * This method returns the highest vertex of the triangle
     * @return the highest Vertex
     */
    public Point highestPoint()
    {
    if(_point1.isAbove(_point2) && _point1.isAbove(_point3))
        return _point1;
    else if(_point2.isAbove(_point1) && _point2.isAbove(_point3))
        return _point2;
    else if(_point3.isAbove(_point1) && _point3.isAbove(_point2))
        return _point3;
    else if(_point1.isAbove(_point2)){
        if(_point1.isLeft(_point3))
            return _point1;
        else return _point3;   
    }
    else if(_point1.isAbove(_point3)){
        if(_point1.isLeft(_point2))
            return _point1;
        else return _point2; 
    }
    else if(_point2.isLeft(_point3))
            return _point2;
        else return _point3; 
    }
    
    /**
     * This method returns true if the triangle is entirely in one quadrant.
     * @return true if the triangle is entirely in one quadrant
     */
    public boolean isLocated()
    {
    return (_point1.quadrant() == _point2.quadrant() && _point2.quadrant() == _point3.quadrant());
    }
    
    /**
     * Check if this triangle is completely above a received triangle.
     * @param other - the triangle to check if this triangle is above
     * @return true if this triangle is above the other triangle
     */
    public boolean isAbove(Triangle other)
    {
    return (((this.lowestPoint()).getY()) > ((other.highestPoint()).getY()));
    }
    
    /**
     * Check if this triangle is completely below a received triangle.
     * @param other - the triangle to check if this triangle is below
     * @return true if this triangle is below the other triangle
     */
    public boolean isUnder(Triangle other)
    {
    return other.isAbove(this);
    }
    
    /**
     * Check if the given triangle is equal to this triangle.
     * @param other - the triangle we are checking equality with
     * @return true if the triangle other is equal to this triangle
     */
    public boolean equals(Triangle other)
    {
    return (_point1.equals(other._point1) && _point2.equals(other._point2) && _point3.equals(other._point3));
    }
    
    /**
     * Check if the given triangle is congruent to this triangle.
     * @param other - the triangle we are checking congruency with
     * @return true if the triangle other is congruent to this triangle
     */
    public boolean isCongruent(Triangle other)
    {
        return true;
    }
}