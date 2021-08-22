
/**
 *  Triangle.java represents a triangle in the Euclidean space. 
 *
 * @version 29/3/2021
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
        _point1= new Point(1,0);
        _point2= new Point(-1,0);
        _point3= new Point(0,1);
    }

    /**
     * Construct a new Triangle (from 3 points)
     * @param p1 - the first Point
     * @param p2 - the second Point
     * @param p3 - the third Point
     */
    public Triangle(Point p1,Point p2,Point p3)

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
    public Triangle(double x1,double y1,double x2,double y2,double x3,double y3)
    {

        this(new Point(x1, y1),new Point(x2, y2),new Point(x3, y3));

    }

    /**
     * Copy constructor Creates a new triangle identical to the given triangle
     * @param other the triangle to be copied
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
     * This method checks if 3 points make a valid triangle. A triangle is valid if no sum of any two sides equals the third side (with precision EPSILON)
     * @param p1 - The first point
     * @param p2 - The second point
     * @param p3 - The third point
     * @return true if the triangle(p1,p2,p3) is valid
     */
    public boolean isValid(Point p1, Point p2, Point p3)
    {

        if(equalBellowEpsilon(p1.distance(p2)+p1.distance(p3),p2.distance(p3)))
            return false;
        else if(equalBellowEpsilon(p1.distance(p2), p1.distance(p3)+p2.distance(p3)))
            return false;
        else if(equalBellowEpsilon(p1.distance(p2)+p2.distance(p3), p1.distance(p3)))
            return false;
        else return true;

    }

    /**
     * This method returns a String representation of thetTriangle. The format of the string should be: {(x1,y1),(x2,y2),(x3,y3)}
     *
     * @return a String representation of the triangle
     */
    public String toString() {
        return "{" + _point1 + "," + _point2 + "," + _point3+ "}";
    }

    /**
     * This method returns the triangle's perimeter.
     * @return the triangle's perimeter
     */
    public double getPerimeter()
    {
        return(firstSideLength()+secondSideLength()+thirdSideLength());

    }

    /**
     * This method returns the triangle's area (using Heron's formula).
     * @return the triangle's area
     */
    public double getArea()
    {
        double a=firstSideLength();
        double b=secondSideLength();
        double c=thirdSideLength();

        return((Math.sqrt((a+b+c)*(a+b-c)*(b+c-a)*(c+a-b))/4));
    }

    /**
     * This method returns true if the triangle is an isosceles triangle(with precision EPSILON).
     * @return true if the triangle is an isosceles triangle
     */
    public boolean isIsosceles()
    {
        return(equalBellowEpsilon(firstSideLength(),secondSideLength())||equalBellowEpsilon(secondSideLength(),thirdSideLength())||
            equalBellowEpsilon(firstSideLength(),thirdSideLength()));
    }

    /**
     * This method returns true if the triangle is a right-angled triangle.
     * @return true if the triangle is a right-angled triangle
     */
    public boolean isPythagorean()
    {
        double side1 = firstSideLength();
        double side2 = secondSideLength();
        double side3 = thirdSideLength();

        // return(Math.sqrt(side1 + side2) == Math.sqrt(side3) || Math.sqrt(side1 + side3) == Math.sqrt(side2) || Math.sqrt(side2 + side3) == Math.sqrt(side1) );
        return(pythagorean(side1,side2,side3)||pythagorean(side1,side3,side2)||pythagorean(side2,side3,side1));
    }

    /**
     * This method returns true if the triangle is in a given circle.
     * @param x - the x value of the point which is the center of the circle
     * @param y - the y value of the point which is the center of the circle
     * @param r - the radius of the circle
     * @return true if the triangle is in a given circle
     */
    public boolean isContainedInCircle(double x , double y, double r) {
        return(PointInCircle(x,y,r,_point1)&&PointInCircle(x,y,r,_point2)&&PointInCircle(x,y,r,_point3));
    }

    /**
     * This method returns the lowest vertex of the triangle.
     * @return the lowest vertex
     */
    public Point lowestPoint() {  
        Point lowerBetweenP1AndP2 = morelower(_point1, _point2);
        return morelower(lowerBetweenP1AndP2, _point3);
    }

    /**
     * This method returns the highest vertex of the triangle
     * @return the highest Vertex
     */
    public Point highestPoint() {        
        Point higherBetweenP1AndP2 = moreHigher(_point1, _point2);
        return moreHigher(higherBetweenP1AndP2, _point3);
    }

    /**
     * This method returns true if the triangle is entirely in one quadrant.
     * @return true if the triangle is entirely in one quadrant
     */
    public boolean isLocated() {
        return(_point1.quadrant() == _point2.quadrant() &&  _point1.quadrant() ==  _point3.quadrant());
    }

    /**
     * Check if this triangle is completely above a received triangle.
     * @param other - the triangle to check if this triangle is above
     * @return true if this triangle is above the other triangle
     */
    public boolean isAbove(Triangle other)  {
        return((this.lowestPoint()).isAbove(other.highestPoint()));
    }

    /**
     * Check if this triangle is completely below a received triangle.
     * @param other - the triangle to check if this triangle is below
     * @return true if this triangle is below the other triangle
     */
    public boolean isUnder(Triangle other)  {
        return other.isAbove(this);
    }

    /**
     * Check if the given triangle is equal to this triangle.
     * @param other - the triangle we are checking equality with
     * @return true if the triangle other is equal to this triangle
     */
    public boolean equals(Triangle other)  {       
        return(this._point1.equals(other._point1) && this._point2.equals(other._point2) & this._point3.equals(other._point3));
    }

    /**
     * Check if the given triangle is congruent to this triangle.
     * @param other - the triangle we are checking congruency with
     * @return true if the triangle other is congruent to this triangle
     */

    public boolean isCongruent(Triangle other)  {
        double a = firstSideLength();
        double b = secondSideLength();
        double c = thirdSideLength();
        double x = other.firstSideLength();
        double y = other.secondSideLength();       
        double z = other.thirdSideLength();

        if(equalBellowEpsilon(a,x) && equalBellowEpsilon(b,y) && equalBellowEpsilon(c,z)){ 
            return true;
        }else if(equalBellowEpsilon(a,x) && equalBellowEpsilon(b,z) && equalBellowEpsilon(c,y)){
            return true;
        }else if(equalBellowEpsilon(a,z) && equalBellowEpsilon(b,y) && equalBellowEpsilon(c,x)){
            return true;
        }else if(equalBellowEpsilon(a,z) && equalBellowEpsilon(b,x) && equalBellowEpsilon(c,y)){
            return true;
        }else if(equalBellowEpsilon(a,y) && equalBellowEpsilon(b,x) && equalBellowEpsilon(c,z)){
            return true;
        }else if(equalBellowEpsilon(a,y) && equalBellowEpsilon(b,z) && equalBellowEpsilon(c,x)){
            return true;
        }
        return false;

    }

    /**
     * This method checks if the difference between two numbers is EPSILON precision.
     * @param a - The new first number
     * @param b - the second number
     * @return True if the difference is smaller then EPSILON
     */
    private boolean equalBellowEpsilon (double a , double b) {
        return (Math.abs(a-b)<EPSILON);
    }

    private double getSideLength (Point a , Point b) {
        return(a.distance(b)); // get the distance between point a and b - the side lenght
    }

    private double firstSideLength() {
        return getSideLength(_point1, _point2); // get the lenght of the first side - the distance between _point1 and _point2 
    }

    private double secondSideLength() {
        return getSideLength(_point2, _point3); // get the lenght of the first side - the distance between _point2 and _point3
    }

    private double thirdSideLength() {
        return getSideLength(_point1, _point3); // get the lenght of the first side - the distance between _point1 and _point3
    }

    private Point morelower(Point firstP, Point secondP) {
        if (firstP.isUnder(secondP)) // first under second
            return new Point(firstP);
        else if (firstP.isAbove(secondP))  // second under first
            return new Point(secondP);
        else // that means y values are equal , check who's more on the right.
            return moreLeft(firstP, secondP);
    }

    private Point moreHigher(Point firstP, Point secondP) {
        if (firstP.isAbove(secondP)) // first above second
            return new Point(firstP);
        else if (firstP.isUnder(secondP))  // second under first
            return new Point(secondP);
        else // that means y values are equal , check who's more on the right.
            return moreLeft(firstP, secondP);
    }

    private Point moreLeft(Point p1, Point p2) {
        boolean pMoreLeft = p1.isLeft(p2); // assuming p1 is more on the left then p2.
        if (pMoreLeft)
            return new Point(p1);
        else
            return new Point(p2);
    }

    private boolean pythagorean(double side1,double side2,double side3)
    {
        return(Math.pow(side1, 2) + Math.pow(side2, 2) == (Math.pow(side3, 2))); // calculate with pythagorean sentence a^2+b^2=c^ ==> side1^2+side2^2=side3^3 if this is true return true
    }

    private boolean PointInCircle(double x , double y, double r,Point p) {
        Point center = new Point(x,y);
        return((equalBellowEpsilon(p.distance(center),r)||p.distance(center)<=r)); // check if the distance between the point and the center the circle is less radius of the circle 
    }
}

