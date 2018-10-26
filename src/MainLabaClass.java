
 import java.awt.*;
 public class MainLabaClass {
    // 
    private int _startPosX;
    private int _startPosY;
     // 
    private int _pictureWidth;
    private int _pictureHeight;
     // 
    private final int planeWidth = 140;
    private final int planeHeight = 100;
     // 
    private int MaxSpeed;
    private float Weight;
    private Color MainColor;
    private Color DopColor;
     public int getMaxSpeed() {
        return MaxSpeed;
    }
     public float getWeight() {
        return Weight;
    }
     public Color getMainColor() {
        return MainColor;
    }
     public Color getDopColor() {
        return DopColor;
    }
     public enum Direction
    {
        Up,
        Down,
        Left,
        Right
    }
     // 
    public MainLabaClass(int maxSpeed, float weight, Color mainColor, Color dopColor)
    {
    	//maxSpeed and weight can be const, i can do it? but don't see need in it
        MaxSpeed = 1000; //maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
    }
     // 
    public void SetPosition(int x, int y, int width, int heigth)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = heigth;
    }
     // 
    public void MoveTransport(Direction direction)
    {
        float step = MaxSpeed * 100 / Weight;
        switch (direction)
        {
             case Left:
            {
                if (_startPosX - step > 0)
                {
                    _startPosX -= step;
                }
                break;
            }
             case Right:
            {
                if (_startPosX + step + planeWidth < _pictureWidth)
                {
                    _startPosX += step;
                }
                break;
            }
             case Up:
            {
                if (_startPosY - step > 0)
                {
                    _startPosY -= step;
                }
                break;
            }
             case Down:
            {
                if (_startPosY + step + planeHeight < _pictureHeight)
                {
                    _startPosY += step;
                }
                break;
            }
        }
    }
     public void DrawPlane(Graphics g)
    {
        // 
        g.setColor(MainColor);
        g.fillRect(_startPosX, _startPosY+40, planeWidth, planeHeight-80);
        g.fillRect(_startPosX+20, _startPosY+20, planeWidth-120, planeHeight-40);
        g.fillRect(_startPosX+80, _startPosY, planeWidth-120, planeHeight);
        
        g.setColor(DopColor);
        g.fillRect(_startPosX+120, _startPosY+40, planeWidth-120, planeHeight-80);
    }
}

