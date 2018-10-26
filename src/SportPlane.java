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
            //
            g.setColor(MainColor);
            g.fillRect(_startPosX, _startPosY+40, planeWidth, planeHeight-80);
            g.fillRect(_startPosX+20, _startPosY+20, planeWidth-120, planeHeight-40);
            g.fillRect(_startPosX+80, _startPosY, planeWidth-120, planeHeight);

            g.setColor(DopColor);
            g.fillRect(_startPosX+120, _startPosY+40, planeWidth-120, planeHeight-80);
            g.fillRect(_startPosX+100, _startPosY, planeWidth-120, planeHeight-80);
            g.fillRect(_startPosX+100, _startPosY+80, planeWidth-120, planeHeight-80);
        }
    }
