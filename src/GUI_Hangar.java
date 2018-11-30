import javax.swing.*;
import java.awt.*;

 class GUI_Hangar {
     private MultiLevelParking hangar;
     private final int countLevel = 5;
     JFrame frame;
     private static JList list;
     private GUI_Hangar_Config select;

     public void getPlane() {
         select = new GUI_Hangar_Config(frame);
         if (select.res()) {
             ITransport pl = select.plane;
             int place = hangar.get(list.getSelectedIndex()).add(pl);
             if (place < 0) {
                 JOptionPane.showMessageDialog(null, "No free place");
             }
         }
     }
     GUI_Hangar() {
        JFrame frame = new JFrame();
        frame.setBounds(100, 100, 815, 510);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

         JPanel panelTakePlane = new JPanel();
         panelTakePlane.setBounds(642, 257, 210, 220);
         frame.getContentPane().add(panelTakePlane);
         panelTakePlane.setLayout(null);


        PanelHangar panelHangar = new PanelHangar();
         hangar = new MultiLevelParking(countLevel, panelHangar.getWidth(), panelHangar.getHeight());
         panelHangar.setHangar(hangar.get(0));
        panelHangar.setBounds(10, 11, 622, 429);
        frame.getContentPane().add(panelHangar);

         DefaultListModel listModel = new DefaultListModel();
         for (int i = 1; i <= countLevel; i++) {
             listModel.addElement("Уровень " + i);
         }
         list = new JList(listModel);
         list.setBounds(642, 11, 132, 107);
         frame.getContentPane().add(list);
         list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         list.setSelectedIndex(0);
         list.addListSelectionListener(e -> {
             panelHangar.setHangar(hangar.get(list.getSelectedIndex()));
             panelHangar.repaint();
         });

        JLabel label = new JLabel("Забрать");
        label.setBounds(10, 11, 72, 14);
         panelTakePlane.add(label);

        JLabel lblNewLabel = new JLabel("самолет");
        lblNewLabel.setBounds(10, 27, 93, 14);
         panelTakePlane.add(lblNewLabel);

        JLabel label_1 = new JLabel("Место:");
        label_1.setBounds(10, 55, 46, 14);
         panelTakePlane.add(label_1);

        JTextField textField = new JTextField();
        textField.setBounds(55, 52, 67, 20);
         panelTakePlane.add(textField);
        textField.setColumns(10);

        PanelPlane panelPlane = new PanelPlane();
        panelPlane.setBounds(10, 117, 140, 120);
         panelTakePlane.add(panelPlane);

        JButton buttonTake = new JButton("Забрать");
        buttonTake.addActionListener(e -> {
            int planePosition = Integer.parseInt(textField.getText());
            ITransport pl;
            if ((pl = hangar.get(list.getSelectedIndex()).del(planePosition)) != null) {
                    pl.SetPosition(0, 20, panelPlane.getWidth(), panelPlane.getHeight());
                    panelPlane.setTransport(pl);
                } else {
                    panelPlane.setTransport(null);
                }
            panelPlane.repaint();
            panelHangar.repaint();
        });
        buttonTake.setBounds(10, 83, 112, 23);
         panelTakePlane.add(buttonTake);

        /*JButton buttonParkPlane = new JButton();
        buttonParkPlane.addActionListener(e -> {
            Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
            ITransport pl = new plane(
                    (int) (Math.random() * 200) + 100,
                    (int) (Math.random() * 1000) + 1000,
                    firstColor);
            hangar.get(list.getSelectedIndex()).add(pl);;
            panelHangar.repaint();
        });
        buttonParkPlane.setLayout(null);
        JLabel label1 = new JLabel("Припарковать");
        label1.setBounds(5, 5, 100, 15);
        JLabel label2 = new JLabel("Самолет");
        label2.setBounds(5, 23, 100, 15);
        buttonParkPlane.add(label1);
        buttonParkPlane.add(label2);
        buttonParkPlane.setBounds(642, 121, 132, 43);
        frame.getContentPane().add(buttonParkPlane);
*/
        JButton buttonParkSportPlane = new JButton();
        buttonParkSportPlane.addActionListener(e -> {
            /*Color firstColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
            Color secondColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
            ITransport pl = new SportPlane(
                    (int) (Math.random() * 200) + 100,
                    (int) (Math.random() * 1000) + 1000,
                    firstColor,
                    secondColor);
            hangar.get(list.getSelectedIndex()).add(pl);
            */
            getPlane();
            panelHangar.repaint();
        });
        buttonParkSportPlane.setLayout(null);
        JLabel label3 = new JLabel("Припарковать");
        label3.setBounds(5, 5, 100, 15);
        JLabel label4 = new JLabel("Спортивный");
        label4.setBounds(5, 23, 100, 15);
        JLabel label5 = new JLabel("Самолет");
        label5.setBounds(5, 41, 100, 15);
        buttonParkSportPlane.add(label3);
        buttonParkSportPlane.add(label4);
        buttonParkSportPlane.add(label5);
        buttonParkSportPlane.setBounds(642, 175, 132, 62);
        frame.getContentPane().add(buttonParkSportPlane);
        frame.setVisible(true);
    }
}
