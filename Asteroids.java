/*
 * Matthew Baisden
 * Asn 2 - Asteroids
 * CSC 313-A Graphics
 * Dr. Sanders
 */

 // p. 67 - end

 private static Boolean collisionOccursCoordinates(double p1x1, double p1y1, double p1x2, double p1y2, double p2x1, double p2y1, double p2x2, double p2y2)
 {
    Boolean ret = false;
    if(isInside(p1x1, p1y1, p2x1, p2y1, p2x2, p2y2) == true)
    {
        ret = true;
    }
    if(isInside(p1x1, p1y2, p2x1, p2y1, p2x2, p2y2) == true)
    {
        ret = true;
    }
    if(isInside(p1x2, p1y1, p2x1, p2y1, p2x2, p2y2) == true)
    {
        ret = true;
    }
    if(isInside(p1x2, p1y2, p2x1, p2y1, p2x2, p2y2) == true)
    {
        ret = true;
    }
    if(isInside(p2x1, p2y1, p1x1, p1y1, p1x2, p1y2) == true)
    {
        ret = true;
    }
    if(isInside(p2x1, p2y2, p1x1, p1y1, p1x2, p1y2) == true)
    {
        ret = true;
    }
    if(isInside(p2x2, p2y1, p1x1, p1y1, p1x2, p1y2) == true)
    {
        ret = true;
    }
    if(isInside(p2x2, p2y2, p1x1, p1y1, p1x2, p1y2) == true)
    {
        ret = true;
    }

    return ret;
 }

 private static Boolean collisionOccurs(ImageObject ojb1, ImageObject obj2)
 {
    Boolean ret = false;
    if(collisionOccursCoordinates(obj1.getX(), obj1.getY(), ojb1.getX() + obj1.getWidth(), ojb1.getY() + obj1.getHeight(),obj2.getX(), ojb2.getY(), ojb2.getX() + obj2.getWidth(), obj2.getY() + obj2.getHeight()) == true)
    {
        ret = true;
    }
    return ret;
 }

 private static class ImageObject
 {
    public ImageObject()
    {

    }

    public ImageObject(double xinput, double yinput, double xwidthinput, double yheightinput, double angleinput)
    {
        x = xinput;
        y = yinput;
        xwidth = xwidthinput;
        yheight = yheightinput;
        angle = angleinput;
        internalangle = 0.0;
        coords = new Vector<Double>();
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getWidth()
    {
        return xwidth;
    }

    public double getHeight()
    {
        return yheight;
    }

    public double getAngle()
    {
        return angle;
    }

    public double getInternalAngle()
    {
        return internalangle;
    }

    public void setAngle(double angleinput)
    {
        angle = angleinput;
    }

    public void setInternalAngle(double internalangleinput)
    {
        internalangle = internalangleinput;
    }

    // testdddd

 }