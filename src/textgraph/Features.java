package textgraph;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Features implements ActionListener {
  /** colors stores the names of color that can be used as the color of nodes and edges. */
  String[] colors = { "pink", "purple", "darkcyan", "brown", "red", "mediumspringgreen", "tomato",
      "mediumslateblue", "forestgreen", "indigo", "teal", "blue", "orangered", "beige", "bisque",
      "black", "blanchedalmond", "aliceblue", "steelblue", "tan", "thistle", "blueviolet",
      "burlywood", "cadetblue", "chartreuse", "lightgoldenrodyellow", "chocolate", "coral",
      "cornflowerblue", "cornsilk", "crimson", "cyan", "darkblue", "darkgoldenrod", "aqua",
      "darkgray", "darkgreen", "darkgrey", "darkkhaki", "darkmagenta", "darkolivegreen",
      "darkorange", "darkorchid", "darkred", "darksalmon", "darkseagreen", "darkslateblue",
      "darkslategray", "darkslategrey", "darkturquoise", "darkviolet", "deeppink", "deepskyblue",
      "dimgrey", "dodgerblue", "firebrick", "fuchsia", "lightyellow", "gainsboro", "gold",
      "goldenrod", "gray", "grey", "green", "greenyellow", "honeydew", "hotpink", "indianred",
      "ivory", "khaki", "lavender", "lavenderblush", "dimgray", "lawngreen", "lemonchiffon",
      "lightblue", "lightcoral", "lightcyan", "lightgray", "lightgreen", "lightgrey", "lightpink",
      "lightsalmon", "lightseagreen", "lightskyblue", "lightslategray", "lightslategrey",
      "lightsteelblue", "lime", "linen", "magenta", "maroon", "mediumaquamarine", "mediumblue",
      "mediumorchid", "mediumpurple", "mediumseagreen", "mediumturquoise", "mediumvioletred",
      "midnightblue", "mistyrose", "moccasin", "navajowhite", "navy", "oldlace", "antiquewhite",
      "limegreen", "olive", "olivedrab", "orange", "orchid", "palegoldenrod", "palegreen",
      "paleturquoise", "palevioletred", "papayawhip", "peachpuff", "peru", "plum", "powderblue",
      "rosybrown", "royalblue", "saddlebrown", "aquamarine", "salmon", "sandybrown", "seagreen",
      "seashell", "sienna", "silver", "skyblue", "slateblue", "slategray", "slategrey", "azure",
      "springgreen", "turquoise", "violet", "wheat", "yellow", "yellowgreen" };
  /** id is the node id used in function randomWalk .*/
  int id;
  /**
   * visited is a two-dimensional integer list to record which edges have been visited in function
   * calcShortestPath and randomWalk
   */
  int[][] visited = new int[MediumWindow.graph.n0][MediumWindow.graph.n0];
  /** SS is the current string used in function randomWalk .*/
  String doubles;

  /**
  *@Override.
   */
  public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(MediumWindow.buttonShowDirectedGraph)) {
      /*
       * when the picture name is empty
       */
      if (MediumWindow.pictureName.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter a picture name!");
      } else {
        /*
         * when the picture name is not empty
         */
        String d = MediumWindow.targetPath;
        String n = MediumWindow.pictureName.getText();
        String t = (String) MediumWindow.fileTypeChooser.getSelectedItem();
        String allName = d + "/" + n + "." + t;
        File check = new File(allName);
        if (check.exists()) {
          JOptionPane.showMessageDialog(null, allName + " has already existed!");
        } else {
          showDirectedGraph(MediumWindow.graph);
        }
      }
    } else if (event.getSource().equals(MediumWindow.buttonQueryBridgeWords)) {
      InWords readWords = new InWords();
      readWords.minimumWindow.setVisible(true);
    } else if (event.getSource().equals(MediumWindow.buttonGenerateNewText)) {
      NewText newTextGenerated = new NewText(MediumWindow.graph);
      newTextGenerated.minimumWindow.setVisible(true);
    } else if (event.getSource().equals(MediumWindow.buttonCalcShortestPath)) {
      ShortestPath pathEnds = new ShortestPath(MediumWindow.graph);
      pathEnds.minimumWindow.setVisible(true);

    } else if (event.getSource().equals(MediumWindow.buttonRandomWalk)) {
      doubles = "";
      id = -1;
      for (int i = 0; i < MediumWindow.graph.n0; ++i) {
        for (int j = 0; j < MediumWindow.graph.n0; ++j) {
          visited[i][j] = 0;
        }
      }
      WalkText randomWalkText = new WalkText();
      randomWalkText.minimumWindow.setVisible(true);
    }
  }

  /**
   * create a frame to get the two words used in function queryBridgeWords and show the bridge.
   * word(s)
   * 
   * @author HolynovaSD
   *
   */

  private class InWords extends JFrame {
    private static final long serialVersionUID = 1L;
    public JFrame minimumWindow;
    public JLabel label;
    public JTextField leftWord;
    public JTextField rightWord;
    public JButton query;

    public InWords() {
      minimumWindow = new JFrame();
      minimumWindow.setLocation(400, 10);
      minimumWindow.setSize(400, 200);
      minimumWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      minimumWindow.getContentPane().setLayout(null);

      label = new JLabel("Please enter two words:");
      label.setBounds(50, 50, 300, 20);
      minimumWindow.getContentPane().add(label);

      leftWord = new JTextField();
      leftWord.setBounds(50, 80, 140, 20);
      minimumWindow.getContentPane().add(leftWord);

      rightWord = new JTextField();
      rightWord.setBounds(210, 80, 140, 20);
      minimumWindow.getContentPane().add(rightWord);

      query = new JButton("Query");
      query.setBounds(100, 110, 200, 40);
      query.addActionListener(new QueryWord());
      minimumWindow.getContentPane().add(query);
    }

    private class QueryWord implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        if (leftWord.getText().isEmpty() || rightWord.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Please enter the two words!");
        } else {
          String lword = leftWord.getText();
          String rword = rightWord.getText();
          String result = queryBridgeWords(MediumWindow.graph, lword, rword);
          JOptionPane.showMessageDialog(null, result);
        }
      }
    }
  }

  /**
   * create a frame to get the original text used in function generateNewText
   *  and show the generated.
   * text in the window
   * 
   * @author HolynovaSD
   *
   */

  private class NewText extends JFrame {
    private static final long serialVersionUID = 1L;
    public JFrame minimumWindow;
    public JTextArea inText;
    public JTextArea outText;
    public JScrollPane jspin;
    public JScrollPane jspout;
    public JButton write;
    public Graph gph;

    public NewText(Graph graph) {
      gph  = graph;

      minimumWindow = new JFrame();
      minimumWindow.setLocation(800, 10);
      minimumWindow.setSize(900, 650);
      minimumWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      minimumWindow.getContentPane().setLayout(null);

      inText = new JTextArea();
      inText.setSize(400, 530);
      inText.setLineWrap(true);
      inText.setEditable(true);
      inText.setWrapStyleWord(true);
      inText.setFont(new Font("Time New Roman", Font.BOLD, 16));

      jspin = new JScrollPane(inText);
      jspin.setBounds(30, 0, 400, 530);
      minimumWindow.getContentPane().add(jspin);

      outText = new JTextArea();
      outText.setSize(400, 530);
      outText.setLineWrap(true);
      outText.setEditable(false);
      outText.setWrapStyleWord(true);
      outText.setFont(new Font("Time New Roman", Font.BOLD, 16));

      jspout = new JScrollPane(outText);
      jspout.setBounds(470, 0, 400, 530);
      minimumWindow.getContentPane().add(jspout);

      write = new JButton("GenerateNewText");
      write.setBounds(230, 540, 200, 25);
      write.addActionListener(new WriteText());
      minimumWindow.getContentPane().add(write);
    }

    private class WriteText implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        if (inText.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null, "Please enter the text!");
        } else {
          String itext = inText.getText();
          String rtext = generateNewText(gph, itext);
          outText.setText(rtext);
        }
      }
    }
  }

  /*
   * create a frame to get one or two node(s) used in function calcShortestPath and deliver the
   * parameters to class showPathPicture to show the picture
   * 
   * @author HolynovaSD
   *
   */

  private class ShortestPath extends JFrame {
    private static final long serialVersionUID = 1L;
    public JFrame minimumWindow;
    public JLabel label1;
    public JLabel label2;
    public JTextField leftWord;
    public JTextField rightWord;
    public JButton query;
    public Graph gph;

    public ShortestPath(Graph graph) {
      gph = graph;
      minimumWindow = new JFrame();
      minimumWindow.setLocation(400, 10);
      minimumWindow.setSize(550, 200);
      minimumWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      minimumWindow.getContentPane().setLayout(null);

      label1 = new JLabel("start word(necessary):");
      label1.setBounds(50, 50, 200, 20);
      minimumWindow.getContentPane().add(label1);

      label2 = new JLabel("end word(not necessary):");
      label2.setBounds(300, 50, 200, 20);
      minimumWindow.getContentPane().add(label2);

      leftWord = new JTextField();
      leftWord.setBounds(50, 80, 200, 20);
      minimumWindow.getContentPane().add(leftWord);

      rightWord = new JTextField();
      rightWord.setBounds(300, 80, 200, 20);
      minimumWindow.getContentPane().add(rightWord);

      query = new JButton("Calculate");
      query.setBounds(150, 110, 250, 40);
      query.addActionListener(new QueryWord());
      minimumWindow.getContentPane().add(query);
    }

    private class QueryWord implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        if (leftWord.getText().isEmpty()) {
          JOptionPane.showMessageDialog(null, "You must enter the name of the starting node!");
        } else {
          if (rightWord.getText().isEmpty()) {
            String startnode = leftWord.getText();
            String dir = MediumWindow.targetPath;
            String name = MediumWindow.pictureName.getText();
            String type = (String) MediumWindow.fileTypeChooser.getSelectedItem();
            String fullName = dir + "/" + name + "-" + startnode + "-" + "ShortestPath." + type;
            File out = new File(fullName);
            if (gph.getIndex(startnode) == -1) {
              JOptionPane.showMessageDialog(null, startnode + " is not in the graph!");
            } else {
              if (out.exists()) {
                JOptionPane.showMessageDialog(null,
                    fullName + " already exists! Please change that file's name.");
              } else {
                ShowPathPicture sp = new ShowPathPicture(gph, fullName, startnode);
                sp.microWindow.setVisible(true);
              }
            }
          } else {
            String startnode = leftWord.getText();
            String endnode = rightWord.getText();
            String dir = MediumWindow.targetPath;
            String name = MediumWindow.pictureName.getText();
            String type = (String) MediumWindow.fileTypeChooser.getSelectedItem();
            String fullName = dir + "/" + name + "-" + startnode + "-" + endnode + "-"
                + "ShortestPath." + type;
            File out = new File(fullName);
            if (gph.getIndex(startnode) == -1) {
              JOptionPane.showMessageDialog(null, startnode + " is not in the graph!");
            } else if (gph.getIndex(endnode) == -1) {
              JOptionPane.showMessageDialog(null, endnode + " is not in the graph!");
            } else {
              if (out.exists()) {
                JOptionPane.showMessageDialog(null,
                    fullName + " already exists! Please change that file's name.");
              } else {
                ShowPathPicture sp = new ShowPathPicture(gph, fullName, startnode, endnode);
                sp.microWindow.setVisible(true);

              }
            }
          }
        }
      }
    }
  }

  /**
   * create a frame to show the shortest path picture.
   * 
   * @author HolynovaSD
   *
   */

  private class ShowPathPicture extends JFrame {
    private static final long serialVersionUID = 1L;
    public JFrame microWindow;
    public JLabel picture;
    public JTextArea pathsShowing;
    public JScrollPane jsp;
    public String oneLinePaths;

    public ShowPathPicture(Graph graph, String name, String word) {
      oneLinePaths = "";
      ArrayList<String> paths = calcShortestPath(graph, word);
      for (String patheElements : paths) {
        oneLinePaths += patheElements;
      }
      if (oneLinePaths.equals("")) {
        JOptionPane.showMessageDialog(null, word + " points to no word in the graph!");
      } else {
        microWindow = new JFrame();
        microWindow.setLocation(700, 10);
        microWindow.setSize(1200, 800);
        microWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        microWindow.getContentPane().setLayout(null);

        pathsShowing = new JTextArea();
        pathsShowing.setSize(600, 800);
        pathsShowing.setLineWrap(true);
        pathsShowing.setEditable(false);
        pathsShowing.setWrapStyleWord(true);
        pathsShowing.setText(oneLinePaths);
        pathsShowing.setFont(new Font("Time New Roman", Font.BOLD, 16));

        jsp = new JScrollPane(pathsShowing);
        jsp.setBounds(600, 0, 600, 800);
        microWindow.getContentPane().add(jsp);

        picture = new JLabel();
        picture.setSize(600, 770);
        microWindow.getContentPane().add(picture);
        ImageIcon icon = new ImageIcon(name);

        int imgWidth = icon.getIconWidth();
        int imgHeight = icon.getIconHeight();
        int conWidth = picture.getWidth();
        int conHeight = picture.getHeight();
        int reImgWidth;
        int reImgHeight;
        if (((double) imgWidth / imgHeight) > ((double) conWidth / conHeight)) {
          reImgWidth = conWidth;
          reImgHeight = imgHeight * conWidth / imgWidth;
        } else {
          reImgHeight = conHeight;
          reImgWidth = imgWidth * conHeight / imgHeight;
        }
        Image img = icon.getImage();
        img = img.getScaledInstance(reImgWidth, reImgHeight, Image.SCALE_DEFAULT);
        icon.setImage(img);
        picture.setIcon(icon);
        picture.setHorizontalAlignment(SwingConstants.CENTER);
      }
    }

    public ShowPathPicture(Graph graph, String name, String word1, String word2) {
      oneLinePaths = calcShortestPath(graph, word1, word2);
      if (oneLinePaths.equals("")) {
        JOptionPane.showMessageDialog(null, word1 + " does not point to " + word2 + " !");
      } else {
        microWindow = new JFrame();
        microWindow.setLocation(700, 10);
        microWindow.setSize(1200, 800);
        microWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        microWindow.getContentPane().setLayout(null);

        pathsShowing = new JTextArea();
        pathsShowing.setSize(600, 800);
        pathsShowing.setLineWrap(true);
        pathsShowing.setEditable(false);
        pathsShowing.setWrapStyleWord(true);
        pathsShowing.setText(oneLinePaths);
        pathsShowing.setFont(new Font("Time New Roman", Font.BOLD, 16));

        jsp = new JScrollPane(pathsShowing);
        jsp.setBounds(600, 0, 600, 800);
        microWindow.getContentPane().add(jsp);

        picture = new JLabel();
        picture.setSize(600, 770);
        microWindow.getContentPane().add(picture);
        ImageIcon icon = new ImageIcon(name);

        int imgWidth = icon.getIconWidth();
        int imgHeight = icon.getIconHeight();
        int conWidth = picture.getWidth();
        int conHeight = picture.getHeight();
        int reImgWidth;
        int reImgHeight;
        if (((double) imgWidth / imgHeight) > ((double) conWidth / conHeight)) {
          reImgWidth = conWidth;
          reImgHeight = imgHeight * conWidth / imgWidth;
        } else {
          reImgHeight = conHeight;
          reImgWidth = imgWidth * conHeight / imgHeight;
        }
        Image img = icon.getImage();
        img = img.getScaledInstance(reImgWidth, reImgHeight, Image.SCALE_DEFAULT);
        icon.setImage(img);
        picture.setIcon(icon);
        picture.setHorizontalAlignment(SwingConstants.CENTER);
      }
    }
  }

  /**
   * create a frame show the random walk text.
   * 
   * @author HolynovaSD
   *
   */

  private class WalkText extends JFrame {
    private static final long serialVersionUID = 1L;
    public JFrame minimumWindow;
    public JTextArea wtext;
    public JScrollPane jsp;
    public Timer timer;
    public JButton goOn;
    public JButton pause;
    public JButton clear;

    public WalkText() {
      minimumWindow = new JFrame();
      minimumWindow.setLocation(1000, 10);
      minimumWindow.setSize(800, 400);
      minimumWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      minimumWindow.getContentPane().setLayout(null);

      wtext = new JTextArea();
      wtext.setSize(800, 300);
      wtext.setLineWrap(true);
      wtext.setEditable(false);
      wtext.setWrapStyleWord(true);
      wtext.setFont(new Font("Time New Roman", Font.BOLD, 16));

      jsp = new JScrollPane(wtext);
      jsp.setBounds(0, 0, 800, 300);
      minimumWindow.getContentPane().add(jsp);

      timer = new Timer();

      goOn = new JButton("Start");
      goOn.setBounds(70, 325, 160, 25);
      goOn.addActionListener(new TextGenerating());
      goOn.setEnabled(true);
      minimumWindow.getContentPane().add(goOn);

      pause = new JButton("Pause");
      pause.setBounds(300, 325, 160, 25);
      pause.addActionListener(new TextGenerating());
      pause.setEnabled(false);
      minimumWindow.getContentPane().add(pause);

      clear = new JButton("Clear");
      clear.setBounds(550, 325, 160, 25);
      clear.addActionListener(new TextGenerating());
      clear.setEnabled(false);
      minimumWindow.getContentPane().add(clear);
    }

    private class TextGenerating implements ActionListener {
      public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(goOn)) {
          goOn.setEnabled(false);
          pause.setEnabled(true);
          clear.setEnabled(false);
          if (timer == null) {
            timer = new Timer();
          }
          timer.schedule(new MyTask(), 0, 500);
        } else if (event.getSource().equals(pause)) {
          pause.setEnabled(false);
          clear.setEnabled(true);
          timer.cancel();
          timer = null;
          if (id == -1) {
            goOn.setEnabled(false);
            goOn.setText("Start");
          } else {
            goOn.setEnabled(true);
            goOn.setText("Go on");
          }
        } else if (event.getSource().equals(clear)) {
          goOn.setEnabled(true);
          pause.setEnabled(false);
          clear.setEnabled(false);
          doubles = "";
          id = -1;
          wtext.setText(doubles);
          goOn.setText("Start");
          for (int i = 0; i < MediumWindow.graph.n0; ++i) {
            for (int j = 0; j < MediumWindow.graph.n0; ++j) {
              visited[i][j] = 0;
            }
          }
        }
      }

      class MyTask extends TimerTask {
        public void run() {
          doubles = randomWalk(MediumWindow.graph);
          wtext.setText(doubles);
          if (id == -1) {
            pause.doClick(1);
          }
        }
      }
    }
  }

  /*
   * use g and class GraphViz to generate a picture
   */
  void showDirectedGraph(Graph g) {
    String dir = MediumWindow.targetPath;
   
    
    GraphViz gv = new GraphViz();
    GraphViz.setdir(dir);
    gv.addln(gv.startgraph());
    String line = "";
    for (int i = 0; i < g.n0; ++i) {
      line = "";
      line += g.getName(i);
      line += ";";
      gv.addln(line);
    }
    gv.addln();
    for (int i = 0; i < g.n0; ++i) {
      for (int j = 0; j < g.n0; ++j) {
        if (g.queryWeight(i, j) > 0) {
          line = "";
          line += g.getName(i);
          line += " -> ";
          line += g.getName(j);
          line += " [ label = \"";
          line += Integer.toString(g.queryWeight(i, j));
          line += "\" ];";
          gv.addln(line);
        }
      }
    }
    gv.addln(gv.endgraph());
    
    String name = MediumWindow.pictureName.getText();
    String type = (String) MediumWindow.fileTypeChooser.getSelectedItem();
    String fullName = dir + "/" + name + "." + type;
    File out = new File(fullName);
    gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    if (type == "jpg" || type == "png" || type == "gif") {
      JFrame showPicture = new JFrame(name + "." + type);
      showPicture.setVisible(true);
      showPicture.setSize(600, 1000);
      showPicture.setLocation(1000, 10);
      showPicture.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      JScrollPane picscrollPane = new JScrollPane();

      picscrollPane.setBounds(0, 0, 600, 1000);
      showPicture.getContentPane().add(picscrollPane);

      JLabel label = new JLabel();
      label.setSize(570, 970);
      picscrollPane.setViewportView(label);
      ImageIcon icon = new ImageIcon(fullName);

      int imgWidth = icon.getIconWidth();
      int imgHeight = icon.getIconHeight();
      int conWidth = label.getWidth();
      int conHeight = label.getHeight();
      int reImgWidth;
      int reImgHeight;
      if (((double) imgWidth / imgHeight) > ((double) conWidth / conHeight)) {
        reImgWidth = conWidth;
        reImgHeight = imgHeight * conWidth / imgWidth;
      } else {
        reImgHeight = conHeight;
        reImgWidth = imgWidth * conHeight / imgHeight;
      }
      Image img = icon.getImage();
      img = img.getScaledInstance(reImgWidth, reImgHeight, Image.SCALE_DEFAULT);
      icon.setImage(img);
      label.setIcon(icon);
      label.setHorizontalAlignment(SwingConstants.CENTER);
    } else {
      JOptionPane.showMessageDialog(null, fullName
          + " has been saved to the choosen path. But it can not been shown in this window.");
    }
  }

  /*
   * get the bridge word in graph G between word1 and word2
   */
  String queryBridgeWords(Graph g, String word1, String word2) {
    ArrayList<Integer> bridgeList = g.getBridges(word1, word2);
    String output;
    if (bridgeList.size() == 0) {
      output = "No bridge word from " + word1 + " to " + word2 + "!";
    } else if (bridgeList.size() == 1) {
      output = "The bridge word from " + word1 + " to " + word2 + " is: "
          + g.getName(bridgeList.get(0));
    } else {
      output = "The bridge words from " + word1 + " to " + word2 + " are: ";
      for (int i = 0; i < bridgeList.size(); i++) {
        if (i != bridgeList.size() - 1) {
          output += " " + g.getName(bridgeList.get(i)) + ",";
        } else {
          output += " and " + g.getName(bridgeList.get(i)) + ".";
        }
      }
    }
    return output;
  }

  /**
   * use bridge word to generate new text from input.
   */
  String generateNewText(Graph g, String input) {
    String output = "";
    String[] words = input.split(" ");
    int len = words.length;
    ArrayList<Integer> bridgeList;
    String iword = words[0];
    String rword = "";
    for (int i = 1; i < len; ++i) {
      rword = words[i];
      output += iword;
      output += " ";
      if (g.getIndex(iword) != -1 && g.getIndex(rword) != -1) {
        bridgeList = g.getBridges(iword, rword);
        if (bridgeList.size() != 0) {
          output += g.getName(bridgeList.get(0));
          output += " ";
        }
      }
      iword = rword;
    }
    output += iword;
    return output;
  }

  /**
   * get the shortest paths from word to all other words in g.
   */
  ArrayList<String> calcShortestPath(Graph g, String word) {
    String dir = MediumWindow.targetPath;
    
    GraphViz gv = new GraphViz();
    GraphViz.setdir(dir);
    gv.addln(gv.startgraph());
    ArrayList<String> paths = new ArrayList<String>();
    String path;
    String line;
    int i;
    int j;
    i = g.getIndex(word);
    for (int m = 0; m < g.n0; ++m) {
      for (int k = 0; k < g.n0; ++k) {
        visited[m][k] = 0;
      }
    }
    for (j = 0; j < g.n0; ++j) {
      path = "";
      if (j == i) {
        continue;
      } else {
        ArrayList<Integer> result = g.getShortestPath(i, j);
        if (result.get(result.size() - 1) == -1) {
          continue;
        } else {
          result.add(j);
          int x = 0;
          int y;
          path += g.getName(result.get(x));
          for (y = 1; y < result.size(); ++y) {
            line = "";
            line += g.getName(result.get(x));
            line += " -> ";
            line += g.getName(result.get(y));
            line += " [ label = \"";
            line += Integer.toString(g.queryWeight(result.get(x), result.get(y)));
            line += "\", color = \"";
            line += colors[j];
            line += "\" ];";
            gv.addln(line);
            path += " ";
            path += g.getName(result.get(y));
            visited[result.get(x)][result.get(y)]++;
            x = y;
          }
          path += "\n";
          paths.add(path);
        }
      }
    }
    for (i = 0; i < g.n0; ++i) {
      for (j = 0; j < g.n0; ++j) {
        if (g.queryWeight(i, j) > 0 && visited[i][j] == 0) {
          line = "";
          line += g.getName(i);
          line += " -> ";
          line += g.getName(j);
          line += " [ label = \"";
          line += Integer.toString(g.queryWeight(i, j));
          line += "\", style = dotted ];";
          gv.addln(line);
        }
      }
    }
    gv.addln(gv.endgraph());
    String name = MediumWindow.pictureName.getText();
    String type = (String) MediumWindow.fileTypeChooser.getSelectedItem();
    String fullName = dir + "/" + name + "-" + word + "-" + "ShortestPath." + type;
    File out = new File(fullName);
    gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    return paths;
  }

  /**
   * get all shortest paths from word1 to word2 in g.
   */
  String calcShortestPath(Graph g, String word1, String word2) {
    String dir = MediumWindow.targetPath;
    String name = MediumWindow.pictureName.getText();
    String type = (String) MediumWindow.fileTypeChooser.getSelectedItem();
    String fullName = dir + "/" + name + "-" + word1 + "-" + word2 + "-" + "ShortestPath." + type;
    String path = "";
    String line = "";
    for (int m = 0; m < g.n0; ++m) {
      for (int k = 0; k < g.n0; ++k) {
        visited[m][k] = 0;
      }
    }
    GraphViz gv = new GraphViz();
    GraphViz.setdir(dir);
    gv.addln(gv.startgraph());
    
    int x = g.getIndex(word1);
    int y = g.getIndex(word2);
    ArrayList<ArrayList<Integer>> result = g.getShortestPathList(x, y);
    if (result.size() != 0) {
      line = "";
      line += "LEN";
      line += " [ label = \"length = ";
      line += Integer.toString(result.get(0).get(0));
      line += "\"  shape = plaintext] ";
      gv.addln(line);
      for (int i = 0; i < result.size(); ++i) {
        path += g.getName(result.get(i).get(1));
        for (int j = 2; j < result.get(i).size(); ++j) {
          line = "";
          line += g.getName(result.get(i).get(j - 1));
          line += " -> ";
          line += g.getName(result.get(i).get(j));
          line += " [ label = \"";
          line += Integer.toString(g.queryWeight(result.get(i).get(j - 1), result.get(i).get(j)));
          line += "\", color = \"";
          line += colors[i];
          line += "\" ];";
          gv.addln(line);
          path += " ";
          path += g.getName(result.get(i).get(j));
          visited[result.get(i).get(j - 1)][result.get(i).get(j)]++;
        }
        path += "\n";
      }
      for (int i = 0; i < g.n0; ++i) {
        for (int j = 0; j < g.n0; ++j) {
          if (g.queryWeight(i, j) > 0 && visited[i][j] == 0) {
            line = "";
            line += g.getName(i);
            line += " -> ";
            line += g.getName(j);
            line += " [ label = \"";
            line += Integer.toString(g.queryWeight(i, j));
            line += "\", style = dotted ];";
            gv.addln(line);
          }
        }
      }
      gv.addln(gv.endgraph());
      File out = new File(fullName);
      gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
    }
    return path;
  }

  /**
   * generate new text by random walk in G.
   */
  String randomWalk(Graph g) {
    Random rand = new Random();
    String output = doubles;
    int u;
    if (id == -1) {
      u = rand.nextInt(g.n0);
      id = u;
      output += g.getName(u);
      output += " ";
      return output;
    } else {
      u = id;
      ArrayList<Integer> nextNodes = new ArrayList<Integer>();
      nextNodes.clear();
      for (int i = 0; i < g.n0; ++i) {
        if (g.queryWeight(u, i) > 0) {
          nextNodes.add(i);
        }
      }
      if (nextNodes.size() == 0) {
        id = -1;
      } else {
        id = nextNodes.get(rand.nextInt(nextNodes.size()));
        output += g.getName(id);
        output += " ";
        if (visited[u][id] == 1) {
          id = -1;
        } else {
          visited[u][id] = 1;
        }
      }
      return output;
    }
  }
}