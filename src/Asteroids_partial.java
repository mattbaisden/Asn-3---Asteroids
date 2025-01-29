package src;
Asteroids_partialtthew Baisden
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

    public Vector<Double> getCoords()
    {
        return coords;
    }

    public void setCoords(Vector<Double> coordsinput)
    {
        coords = coordsinput;
        generateTriangles();
    }

    public void generateTriangles()
    {
        triangles = new Vector<Double>();
        // format : (0, 1), (2, 3), (4, 5) is the (x, y) coords of a triangle

        // Get center point of all coordinates
        comX = getComX();
        comY = getComY();

        for(int i = 0; i < coords.size(); i = i + 2)
        {
            triangles.addElement(coords.elementAt(i));
            triangles.addElement(coords.elementAt(i + 1));

            triangles.addElement(coords.elementAt((i + 2) % coords.size()));
            triangles.addElement(coords.elementAt((i + 3) % coords.size()));

            triangles.addElement(comX);
            triangles.addElement(comY);
        }
    }

    public void printTriangles()
    {
        for(int i = 0; i < triangles.size(); i = i + 6)
        {
            System.out.print("p0x: " + triangles.elementAt(i) + ", p0y: " + triangles.elementAt(i + 1));
            System.out.print(" p1x: " + triangles.elementAt(i + 2) + ", p1y: " + triangles.elementAt(i + 3));
            System.out.print(" p2x: " + triangles.elementAt(i + 4) + ", p2y: " + triangles.elementAt(i + 5));
        }
    }

    public double getComX()
    {
        double ret = 0;
        if(coords.size() > 0)
        {
            for(int i = 0; i < coords.size(); i = i + 2)
            {
                ret = ret + coords.elementAt(i);
            }
            ret = ret / (coords.size() / 2.0);
        }
        return ret;
    }

    public double getComY()
    {
        double ret = 0;
        if(coords.size() > 0)
        {
            for(int i = 1; i < coords.size(); i = i + 2)
            {
                ret = ret + coords.elementAt(i);
            }
            ret = ret / (coords.size() / 2.0);
        }
        return ret;
    }

    public void move(double xinput, double yinput)
    {
        x = x + xinput;
        y = y + yinput;
    }

    public void moveto(double xinput, double yinput)
    {
        x = xinput;
        y = yinput;
    }

    public void screenWrap(double leftEdge, double rightEdge, double topEdge, double bottomEdge)
    {
        if(x > rightEdge)
        {
            moveto(leftEdge, getY());
        }
        if(x < leftEdge)
        {
            moveto(rightEdge, getY());
        }
        if(y > bottomEdge)
        {
            moveto(getX(), topEdge);
        }
        if(y < topEdge)
        {
            moveto(getX(), bottomEdge);
        }
    }

    // Rotation for our image object
    public void rotate(double angleinput)
    {
        angle = angle + angleinput;
        while(angle > twoPi)
        {
            angle = angle - twoPi;
        }

        while(angle < 0)
        {
            angle = angle + twoPi;
        }
    }

    public void spin(double internalangleinput)
    {
        internalangle = internalangle + internalangleinput;
        while(internalangle > twoPi)
        {
            internalangle = internalangle - twoPi;
        }

        while(internalangle < 0)
        {
            internalangle = internalangle + twoPi;
        }
    }

    private double x;
    private double y;
    private double xwidth;
    private double yheight;
    private double angle; // in Radians
    private double internalangle; // in Radians
    private Vector<Double> coords;
    private Vector<Double> triangles;
    private double comX;
    private double comY;
}

private static void bindKey(JPanel myPanel, String input)
{
    myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed " + input), input + " pressed");
    myPanel.getActionMap().put(input + "pressed", new KeyPressed(input));

    myPanel.getInputMap(IFW).put(KeyStroke.getKeyStroke("released" + input), input + " released");
    myPanel.getActionMap().put(input + " released", new KeyReleased(input));
}

public static void main(String[] args)
{
    // Initializes our global variables
    setup();
    appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    appFrame.setSize(501, 585);

    JPanel myPanel = new JPanel();

    String[] levels = { "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};
    JComboBox<String> levelMenu = new JComboBox<String>(levels);
    levelMenu.setSelectedIndex(2);
    levelMenu.addActionListener(new GameLevel());
    myPanel.add(levelMenu);

    JButton newGameButton = new JButton("New Game");
    newGameButton.addActionListener(new StartGame());
    myPanel.add(newGameButton);

    JButton quitButton = new JButton("Quit Game");
    quitButton.addActionListener(new QuitGame());
    myPanel.add(quitButton);

    bindKey(myPanel, "UP");
    bindKey(myPanel, "DOWN");
    bindKey(myPanel, "LEFT");
    bindKey(myPanel, "RIGHT");
    bindKey(myPanel, "F");

    appFrame.getContentPane().add(myPanel, "South");
    appFrame.setVisible(true);
}

private static Boolean endgame;
private static Boolean enemyAlive;
private static BufferedImage background;
private static BufferedImage player;

private static Boolean upPressed;
private static Boolean downPressed;
private static Boolean leftPressed;
private static Boolean rightPressed;
private static Boolean firePressed;

private static ImageObject p1;
private static double p1width;
private static double p1height;
private static double p1originalX;
private static double p1originalY;
private static double p1velocity;

private static ImageObject enemy;
private static BufferedImage enemyShip;
private static BufferedImage enemyBullet;
private static Vector<ImageObject> enemyBullets;
private static Vector<Long> enemyBulletsTimes;
private static Long enemybulletlifetime;

private static Vector<ImageObject> playerBullets;
private static Vector<Long> playerBulletsTimes;
private static double bulletWidth;
private static BufferedImage playerBullet;
private static Long playerbulletlifetime;
private static double playerbulletgap;

private static ImageObject flames;
private static BufferedImage flame1;
private static BufferedImage flame2;
private static BufferedImage flame3;
private static BufferedImage flame4;
private static BufferedImage flame5;
private static BufferedImage flame6;
private static int flamecount;
private static double flamewidth;

private static int level;

private static Vector<ImageObject> asteroids;
private static Vector<Integer> asteroidsTypes;
private static BufferedImage ast1;
private static BufferedImage ast2;
private static BufferedImage ast3;
private static double ast1width;
private static double ast2width;
private static double ast3width;

private static Vector<ImageObject> explosions;
private static Vector<Long> explosionsTimes;
private static Long explosionlifetime;
private static BufferedImage exp1;
private static BufferedImage exp2;

private static int XOFFSET;
private static int YOFFSET;
private static int WINWIDTH;
private static int WINHEIGHT;

private static double pi;
private static double twoPi;

private static JFrame appFrame;

private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
