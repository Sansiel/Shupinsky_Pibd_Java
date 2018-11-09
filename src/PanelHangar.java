import javax.swing.*;
import java.awt.*;

public class PanelHangar extends JPanel {
    private Hangar<ITransport> hangar;

    public void setHangar(Hangar<ITransport> depo) {
        this.hangar = depo;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (hangar != null) {
            hangar.Draw(g);
        }
    }
}
