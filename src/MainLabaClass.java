
 import java.awt.*;
 abstract public class MainLabaClass implements ITransport{
    // 
    public int _startPosX;
     public int _startPosY;
     // 
     public int _pictureWidth;
     public int _pictureHeight;
     // 
     public final int planeWidth = 140;
     public final int planeHeight = 100;
     // 
    public int MaxSpeed;
    public float Weight;
    public Color MainColor;
    public Color DopColor;
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

     public abstract void DrawPlane(Graphics g);
     public abstract void MoveTransport(Direction direction);

    public void SetPosition(int x, int y, int width, int heigth)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = heigth;
    }
     // 

}

