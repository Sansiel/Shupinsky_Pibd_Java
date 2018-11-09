import java.awt.*;
public class Program {
    public static GUI_Plane window;
    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    window = new GUI_Plane();
//                    window.frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        new GUI_Hangar();
    }
}