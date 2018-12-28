import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

 class GUI_Hangar {
     private MultiLevelParking hangar;
     private final int countLevel = 5;
     private Logger logger;
     FileHandler fh;
     JFrame frame;
     private JMenuBar menuBar;
     private static JList list;
     private GUI_Hangar_Config select;

     GUI_Hangar() {
         logger = Logger.getLogger(GUI_Hangar.class.getName());
         try {

             // This block configure the logger with handler and formatter
             fh = new FileHandler("D:/logs/log.txt");
             logger.addHandler(fh);
             SimpleFormatter formatter = new SimpleFormatter();
             fh.setFormatter(formatter);
         } catch (SecurityException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
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

         Font font = new Font("Verdana", Font.PLAIN, 11);
         menuBar = new JMenuBar();
         menuBar.setFont(font);

         JMenu newMenu = new JMenu("Файл");
         newMenu.setFont(font);
         menuBar.add(newMenu);

         JMenuItem saveFileItem = new JMenuItem("Сохранить");
         saveFileItem.setFont(font);
         newMenu.add(saveFileItem);

         JMenuItem loadFileItem = new JMenuItem("Загрузить");
         loadFileItem.setFont(font);
         newMenu.add(loadFileItem);

         PanelPlane panelPlane = new PanelPlane();
         panelPlane.setBounds(10, 117, 140, 120);
         panelTakePlane.add(panelPlane);

         saveFileItem.addActionListener(e -> {
                 JFileChooser fileChoser = new JFileChooser();
                 fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                 int ret = fileChoser.showDialog(null, "Сохранить файл");
                 if (ret == JFileChooser.APPROVE_OPTION) {
                     File file = fileChoser.getSelectedFile();
                     try {
                         hangar.saveData(file.getAbsolutePath());
                         JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно");
                         logger.info("Сохранено в файл " + file.getName());

                     } catch (Exception ex) {
                         JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвсетная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
                     }
                 }
         });

         loadFileItem.addActionListener(e -> {
                 JFileChooser fileChoser = new JFileChooser();
                 fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
                 int ret = fileChoser.showDialog(null, "Открыть файл");
                 if (ret == JFileChooser.APPROVE_OPTION) {
                     File file = fileChoser.getSelectedFile();
                     try { hangar.loadData(file.getAbsolutePath());
                         JOptionPane.showMessageDialog(frame, "Загрузка прошло успешно");
                         logger.info("Загружено из файла " + file.getName());
                         panelPlane.repaint();
                         panelHangar.repaint();
                     } catch (HangarOccupiedPlaceException ex) {
                         JOptionPane.showMessageDialog(frame, ex.getMessage(), "Занятое место", JOptionPane.ERROR_MESSAGE);
                     } catch (Exception ex) {
                         JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвсетная ошибка при загрузке", JOptionPane.ERROR_MESSAGE);
                     }
                 }
         });

         frame.setJMenuBar(menuBar);



        JButton buttonTake = new JButton("Забрать");
        buttonTake.addActionListener(e -> {
            if (list.getSelectedIndex() > -1) {
                if (!textField.getText().equals("")) {
                    try {
                        ITransport pl = hangar.get(list.getSelectedIndex()).del(Integer.parseInt(textField.getText()));
                        if (pl != null) {
                            pl.SetPosition(5, 5, panelPlane.getWidth(), panelPlane.getHeight());
                            panelPlane.setTransport(pl);
                            panelPlane.repaint();
                        } else {
                            panelPlane.setTransport(null);
                            panelPlane.repaint();
                        }
                        logger.info("Украден самолет  " + pl.toString() + " с места " +
                                textField.getText());
                        panelHangar.repaint();
                    } catch (HangarNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });
        buttonTake.setBounds(10, 83, 112, 23);
         panelTakePlane.add(buttonTake);

        JButton buttonPark = new JButton();
        buttonPark.addActionListener(e -> {
            select = new GUI_Hangar_Config(frame);
            if (select.res()) {
                ITransport pl = select.pl;
                if (pl != null) {
                    try {
                        int place = hangar.get(list.getSelectedIndex()).add(pl);
                        logger.info("Добавлен самолет " + pl.toString() + " на место " + place);
                        panelHangar.repaint();
                    } catch (HangarOverflowException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        buttonPark.setLayout(null);
        JLabel label3 = new JLabel("Припарковать");
        label3.setBounds(5, 5, 100, 15);
        JLabel label4 = new JLabel("Какой-нибудь");
        label4.setBounds(5, 23, 100, 15);
        JLabel label5 = new JLabel("Самолет");
        label5.setBounds(5, 41, 100, 15);
        buttonPark.add(label3);
        buttonPark.add(label4);
        buttonPark.add(label5);
        buttonPark.setBounds(642, 175, 132, 62);
        frame.getContentPane().add(buttonPark);
        frame.setVisible(true);
    }
}
