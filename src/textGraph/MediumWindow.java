package textGraph;

import java.awt.Container;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import textGraph.OperationController;
import textGraph.Graph;

public class MediumWindow {
    /**
     * operableGraph contains the graph we just got and the directory.
     */
    private AllOperations operableGraph;
    public JFrame mediumFrame;
    public JLabel label1;
    public JLabel label2;
    public static JTextField pictureName;
    /**
     * fileTypeChooser is a combobox to select the type of the picture.
     */
    public static JComboBox<String> fileTypeChooser;
    /**
     * These five buttons trigger different functions to achieve the requirements.
     */
    public static JButton buttonShowDirectedGraph;
    public static JButton buttonQueryBridgeWords;
    public static JButton buttonGenerateNewText;
    public static JButton buttonCalcShortestPath;
    public static JButton buttonRandomWalk;
    String[] types = {"*", "jpg", "png", "gif", "bmp", "tiff", "ico", "svg", "pdf"};

    public MediumWindow(AllOperations paraGraph) {
        this.operableGraph = paraGraph;
        initialize();
    }

    /*
     * initialize the contents of the frame;
     */
    private void initialize() {
        mediumFrame = new JFrame("Textfile-Graph Convertor");
        mediumFrame.setSize(420, 500);
        mediumFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mediumFrame.setLocation(100, 50);
        mediumFrame.setResizable(false);
        Container container = mediumFrame.getContentPane();
        container.setLayout(null);

        label2 = new JLabel("Please enter the name of the picture:");
        label2.setBounds(20, 20, 360, 20);
        container.add(label2);

        pictureName = new JTextField();
        pictureName.setBounds(20, 40, 360, 20);
        container.add(pictureName);

        label1 = new JLabel("Please choose the format of the picture:");
        label1.setBounds(20, 70, 360, 20);
        container.add(label1);

        fileTypeChooser = new JComboBox<String>(types);
        fileTypeChooser.setBounds(20, 90, 360, 20);
        fileTypeChooser.setEditable(false);
        fileTypeChooser.addItemListener(chooseAction);
        container.add(fileTypeChooser);

        buttonShowDirectedGraph = new JButton("Show the directed graph");
        buttonShowDirectedGraph.setBounds(20, 130, 360, 50);
        buttonShowDirectedGraph.setEnabled(false);
        buttonShowDirectedGraph.addActionListener(new OperationController(operableGraph));
        container.add(buttonShowDirectedGraph);

        buttonQueryBridgeWords = new JButton("Query bridge words");
        buttonQueryBridgeWords.setBounds(20, 200, 360, 50);
        buttonQueryBridgeWords.addActionListener(new OperationController(operableGraph));
        container.add(buttonQueryBridgeWords);

        buttonGenerateNewText = new JButton("Generate new text");
        buttonGenerateNewText.setBounds(20, 270, 360, 50);
        buttonGenerateNewText.addActionListener(new OperationController(operableGraph));
        container.add(buttonGenerateNewText);

        buttonCalcShortestPath = new JButton("Calculate shortest path");
        buttonCalcShortestPath.setBounds(20, 340, 360, 50);
        buttonCalcShortestPath.setEnabled(false);
        buttonCalcShortestPath.addActionListener(new OperationController(operableGraph));
        container.add(buttonCalcShortestPath);

        buttonRandomWalk = new JButton("Random walk");
        buttonRandomWalk.setBounds(20, 410, 360, 50);
        buttonRandomWalk.addActionListener(new OperationController(operableGraph));
        container.add(buttonRandomWalk);

    }

    /**
     * chooseAction can set buttonShowDirectedGraph button and buttonCalcShortestPath button
     * unavailable when the type of the picture is not selected.
     */
    ItemListener chooseAction = new ItemListener() {
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                if (fileTypeChooser.getSelectedItem() == "*") {
                    MediumWindow.buttonShowDirectedGraph.setEnabled(false);
                    MediumWindow.buttonCalcShortestPath.setEnabled(false);
                } else {
                    MediumWindow.buttonShowDirectedGraph.setEnabled(true);
                    MediumWindow.buttonCalcShortestPath.setEnabled(true);
                }
            }
        }
    };

}