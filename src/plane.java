import java.awt.*;

public class plane extends MainLabaClass{
    public plane(int maxSpeed, float weight, Color mainColor)
    {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
    }
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
