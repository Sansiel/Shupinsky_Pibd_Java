import java.awt.*;
import java.io.*;

public class plane extends MainLabaClass implements Serializable, Comparable<plane> {
    public plane(int maxSpeed, int weight, Color mainColor)
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
    public void setMainColor(Color value) {
        MainColor = value;
    }

    public void DrawPlane(Graphics g)
    {
        g.setColor(MainColor);
        g.fillOval(_startPosX, _startPosY+15, planeWidth, planeHeight-80);
        g.fillOval(_startPosX+20, _startPosY + 5, planeWidth-120, planeHeight-60);
        g.fillOval(_startPosX+80, _startPosY - 15, planeWidth-120, planeHeight - 20);

    }
    public plane(String info) {
        String[] parameters = info.split(";");
        if (parameters.length == 3) {
            MaxSpeed = Integer.parseInt(parameters[0]);
            Weight = Integer.parseInt(parameters[1]);
            getMainColor(parameters[2]);
        }
    }
    public String toString() {
        String str = "";
            if (MainColor.equals(Color.WHITE)) {
                str = "white";
            } else if (MainColor.equals(Color.BLACK)) {
                str = "black";
            } else if (MainColor.equals(Color.RED)) {
                str = "red";
            } else if (MainColor.equals(Color.YELLOW)) {
                str = "yellow";
            } else if (MainColor.equals(Color.ORANGE)) {
                str = "orange";
            } else if (MainColor.equals(Color.BLUE)) {
                str = "blue";
            } else if (MainColor.equals(Color.GRAY)) {
                str = "gray";
            } else if (MainColor.equals(Color.GREEN)) {
                str = "green";
            }
            else str = "white";
        return MaxSpeed + ";" + 100 + ";" + str;
    }
    public void getMainColor(String colorName) {
        switch (colorName) {
            case "yellow":
                MainColor = Color.YELLOW;
                break;
            case "blue":
                MainColor = Color.BLUE;
                break;
            case "red":
                MainColor = Color.RED;
                break;
            case "green":
                MainColor = Color.GREEN;
                break;
            case "black":
                MainColor = Color.BLACK;
                break;
            case "orange":
                MainColor = Color.ORANGE;
                break;
            case "gray":
                MainColor = Color.GRAY;
                break;
            case "white":
                MainColor = Color.WHITE;
                break;
        }
    }

    @Override
    public int compareTo(plane another) {
        if (another == null) {
            return 1;
        }
        if (MaxSpeed != another.MaxSpeed) {
            return Integer.compare(MaxSpeed,another.MaxSpeed);
        }
        if (Weight != another.Weight) {
            return Float.compare(Weight,another.Weight);
        }
        if (MainColor != another.MainColor) {
            return Integer.compare(MainColor.getRGB(),another.MainColor.getRGB());
        }
        return 0;
    }

    @Override
    public boolean equals(Object another) {
        if (another == null) return false;
        if (!(another instanceof plane)) return false;
        plane planeObj = (plane) another;
        return equals(planeObj);
    }

    public boolean equals(plane another) {
        if (another == null) return false;
        if (MaxSpeed != another.MaxSpeed) return false;
        if (Weight != another.Weight) return false;
        if (MainColor != another.MainColor) return false;
        return true;
    }
}
