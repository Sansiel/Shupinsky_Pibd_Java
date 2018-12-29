import java.awt.*;
import java.io.*;

    public class SportPlane extends plane  implements Serializable {

        private Color DopColor;

        public Color getDopColor() {
            return DopColor;
        }

        public void setDopColor(Color value) {

            DopColor = value;

        }
        public void DopColorSet(String colorName) {
            switch (colorName) {
                case "yellow":
                    DopColor = Color.YELLOW;
                    break;
                case "blue":
                    DopColor = Color.BLUE;
                    break;
                case "red":
                    DopColor = Color.RED;
                    break;
                case "green":
                    DopColor = Color.GREEN;
                    break;
                case "black":
                    DopColor = Color.BLACK;
                    break;
                case "orange":
                    DopColor = Color.ORANGE;
                    break;
                case "gray":
                    DopColor = Color.GRAY;
                    break;
                case "white":
                    DopColor = Color.WHITE;
                    break;
            }
        }

        public SportPlane(int maxSpeed, int weight, Color mainColor, Color dopColor) {
            super(maxSpeed, weight, mainColor);
            DopColor = dopColor;
        }
        public void DrawPlane(Graphics g)
        {
            super.DrawPlane(g);
            g.setColor(DopColor);

            g.fillOval(_startPosX+100, _startPosY-5, planeWidth-120, planeHeight-80);
            g.fillOval(_startPosX+100, _startPosY+35, planeWidth-120, planeHeight-80);
        }
        public SportPlane(String info) {
            super(info);
            String[] parameters = info.split(";");
            if (parameters.length == 4) {
                MaxSpeed = Integer.parseInt(parameters[0]);
                Weight = Integer.parseInt(parameters[1]);
                getMainColor(parameters[2]);
                DopColorSet(parameters[3]);
            }
        }

        public String toString() {
            String str = "";
            if (DopColor.equals(Color.WHITE)) {
                str = "white";
            } else if (DopColor.equals(Color.BLACK)) {
                str = "black";
            } else if (DopColor.equals(Color.RED)) {
                str = "red";
            } else if (DopColor.equals(Color.YELLOW)) {
                str = "yellow";
            } else if (DopColor.equals(Color.ORANGE)) {
                str = "orange";
            } else if (DopColor.equals(Color.BLUE)) {
                str = "blue";
            } else if (DopColor.equals(Color.GRAY)) {
                str = "gray";
            } else if (DopColor.equals(Color.GREEN)) {
                str = "green";
            }
            else str = "white";
            return (super.toString() + ";" + str);
        }

        public int compareTo(SportPlane another) {
            if (another == null) return 1;
            if (MaxSpeed != another.MaxSpeed) return Integer.compare(MaxSpeed,another.MaxSpeed);
            if (Weight != another.Weight) return Float.compare(Weight,another.Weight);
            if (MainColor != another.MainColor) return Integer.compare(MainColor.getRGB(),another.MainColor.getRGB());
            if (DopColor != another.DopColor) return Integer.compare(DopColor.getRGB(),another.DopColor.getRGB());
            return 0;
        }
        public boolean equals(Object another) {
            if (another == null) {
                return false;
            }
            if (!(another instanceof SportPlane)) {
                return false;
            }
            SportPlane planeObject = (SportPlane) another;
            return equals(planeObject);
        }

        public boolean equals(SportPlane another) {
            if (another == null) return false;
            if (MaxSpeed != another.MaxSpeed) return false;
            if (Weight != another.Weight) return false;
            if (MainColor != another.MainColor) return false;
            if (DopColor != another.DopColor) return false;
            return true;
        }
    }
