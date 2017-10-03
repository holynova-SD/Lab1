package textGraph;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import textGraph.Graph;
import textGraph.features;
class mediumWindow{
	/** graph is the Graph we got just now. */
	public static Graph graph;
	/** targetPath is the directory we are going to save the pictures. */
	public static String targetPath;
	public JFrame mediumFrame;
	public JLabel label1;
	public JLabel label2;
	public static JTextField pictureName;
	/** fileTypeChooser is a combobox to select the type of the picture. */
	public static JComboBox<String> fileTypeChooser;
	/** These five buttons trigger different functions to achieve the requirements.*/
	public static JButton buttonShowDirectedGraph;
	public static JButton buttonQueryBridgeWords;
	public static JButton buttonGenerateNewText;
	public static JButton buttonCalcShortestPath;
	public static JButton buttonRandomWalk;
	String[] types = {"*", "jpg", "png", "gif", "bmp", "tiff", "ico", "svg", "pdf"};
	public mediumWindow(Graph gottenGraph, String path) {
		graph = gottenGraph;
		targetPath = path;
		initialize();
	}
	/**
	 * initialize the contents of the frame;
	 */
	private void initialize() {
		mediumFrame = new JFrame("Textfile-Graph Convertor");
		Container container = mediumFrame.getContentPane();
		mediumFrame.setSize(800, 500);
		mediumFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		mediumFrame.setLocation(100, 50);
		mediumFrame.setResizable(false);
		container.setLayout(null);
		
		label2 = new JLabel("Please enter the name of the picture:");
		label2.setBounds(420, 20, 360, 20);
		container.add(label2);
		
		pictureName = new JTextField();
		pictureName.setBounds(420, 40, 360, 20);
		container.add(pictureName);
		
		label1 = new JLabel("Please choose the format of the picture:");
		label1.setBounds(420, 70, 360, 20);
		container.add(label1);
		
		fileTypeChooser = new JComboBox<String>(types);
		fileTypeChooser.setBounds(420, 90, 360, 20);
		fileTypeChooser.setEditable(false);
		fileTypeChooser.addItemListener(chooseAction);
		container.add(fileTypeChooser);
		
		buttonShowDirectedGraph = new JButton("Show the directed graph");
		buttonShowDirectedGraph.setBounds(420, 130, 360, 50);
		buttonShowDirectedGraph.setEnabled(false);
		buttonShowDirectedGraph.addActionListener(new features());
		container.add(buttonShowDirectedGraph);
		
		buttonQueryBridgeWords = new JButton("Query bridge words");
		buttonQueryBridgeWords.setBounds(420, 200, 360, 50);
		buttonQueryBridgeWords.addActionListener(new features());
		container.add(buttonQueryBridgeWords);
		
		buttonGenerateNewText = new JButton("Generate new text");
		buttonGenerateNewText.setBounds(420, 270, 360, 50);
		buttonGenerateNewText.addActionListener(new features());
		container.add(buttonGenerateNewText);
		
		buttonCalcShortestPath = new JButton("Calculate shortest path");
		buttonCalcShortestPath.setBounds(420, 340, 360, 50);
		buttonCalcShortestPath.setEnabled(false);
		buttonCalcShortestPath.addActionListener(new features());
		container.add(buttonCalcShortestPath);
		
		buttonRandomWalk = new JButton("Random walk");
		buttonRandomWalk.setBounds(420, 410, 360, 50);
		buttonRandomWalk.addActionListener(new features());
		container.add(buttonRandomWalk);
		
	}
	/**
	 * chooseAction can set buttonShowDirectedGraph button and buttonCalcShortestPath button unavailable when the type
	 * of the picture is not selected.
	 */
	ItemListener chooseAction = new ItemListener() {
		public void itemStateChanged(ItemEvent event) {
			if(event.getStateChange() == ItemEvent.SELECTED) {
				if(fileTypeChooser.getSelectedItem() == "*") {
					mediumWindow.buttonShowDirectedGraph.setEnabled(false);
					mediumWindow.buttonCalcShortestPath.setEnabled(false);
				}
				else {
					mediumWindow.buttonShowDirectedGraph.setEnabled(true);
					mediumWindow.buttonCalcShortestPath.setEnabled(true);
				}
			}
		}
	};
	
}