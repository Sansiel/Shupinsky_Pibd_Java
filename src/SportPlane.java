import java.awt.Color;
import java.awt.Graphics;

    public class SportPlane extends plane {

        private Color DopColor;

        public Color getDopColor() {
            return DopColor;
        }

        public void setDopColor(Color value) {

            DopColor = value;

        }

        public SportPlane(int maxSpeed, float weight, Color mainColor, Color dopColor) {
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
    }
