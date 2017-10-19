package textgraph;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

//lab3 change1.1

class TextGraph {
  private JFrame mainFrame;
  protected JLabel appName;
  protected JLabel label1;
  protected JLabel label2;
  protected JLabel about;
  public static JTextField fileLocation;
  public static JTextField targetLocation;
  public static JButton browseFile;
  public static JButton browseTarget;
  public static JButton createGraph;

  /*
   * Launch the application;
   */
  public static void main(final String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          TextGraph window = new TextGraph();
          window.mainFrame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**.
   * Create the application;
   */
  public TextGraph() {
    initialize();
  }

  /**.
   * initialize the contents of the frame;
   */
  private void initialize() {

    mainFrame = new JFrame();
    mainFrame.setSize(1000, 600);
    mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setResizable(false);
    Container container = mainFrame.getContentPane();
    container.setLayout(null);

    appName = new JLabel("T-G Convertor", JLabel.CENTER);
    appName.setFont(new Font("Apple Chancery", Font.ITALIC, 96));
    appName.setForeground(Color.orange);
    appName.setBounds(50, 20, 900, 240);
    container.add(appName);

    label1 = new JLabel("Please enter or choose the path of the file:");
    label1.setBounds(50, 270, 750, 30);
    container.add(label1);

    label2 = new JLabel("Please enter or choose the path you want to save some generated files:");
    label2.setBounds(50, 330, 750, 30);
    container.add(label2);

    about = new JLabel("Developed by Holynova-SD and Dimitriwhy. Version 1.0.0 By 2017-09-12",
        JLabel.CENTER);
    about.setFont(new Font("Time New Roman", 1, 8));
    about.setBounds(50, 530, 900, 30);
    container.add(about);

    fileLocation = new JTextField();
    fileLocation.setBounds(50, 300, 750, 30);
    container.add(fileLocation);

    targetLocation = new JTextField();
    targetLocation.setBounds(50, 360, 750, 30);
    container.add(targetLocation);

    browseFile = new JButton("Select...");
    browseFile.setBounds(810, 300, 150, 30);
    browseFile.addActionListener(new BrowseAction());
    container.add(browseFile);

    browseTarget = new JButton("Select...");
    browseTarget.setBounds(810, 360, 150, 30);
    browseTarget.addActionListener(new BrowseAction());
    container.add(browseTarget);

    createGraph = new JButton("Create Graph");
    createGraph.setBounds(400, 435, 200, 50);
    createGraph.addActionListener(new AllFunction());
    container.add(createGraph);

  }

}
