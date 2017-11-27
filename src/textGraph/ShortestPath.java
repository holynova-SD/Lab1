package textGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * create a frame to get one or two node(s) used in function calcShortestPath and deliver the
 * parameters to class showPathPicture to show the picture
 *
 * @author HolynovaSD
 *
 */

public class ShortestPath extends JFrame {
    private static final long serialVersionUID = 1L;
    private AllOperations operableGraph;
    public JFrame minimumWindow;
    public JLabel label1;
    public JLabel label2;
    public JTextField leftWord;
    public JTextField rightWord;
    public JButton query;

    public ShortestPath(AllOperations paraGraph) {
        this.operableGraph = paraGraph;
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
        query.addActionListener(new QueryWordS());
        minimumWindow.getContentPane().add(query);
    }

    private class QueryWordS implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (leftWord.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "You must enter the name of the starting node!");
            } else {
                if (rightWord.getText().isEmpty()) {
                    String startNode = leftWord.getText();
                    String dir = operableGraph.getTargetDir();
                    String name = operableGraph.getTargetName();
                    String type = operableGraph.getTargetType();
                    String fullName = dir + "/" + name + "-" + startNode + "-" + "ShortestPath." + type;
                    File out = new File(fullName);
                    if (operableGraph.getGraph().getIndex(startNode) == -1) {
                        JOptionPane.showMessageDialog(null, startNode + " is not in the graph!");
                    } else {
                        if (out.exists()) {
                            JOptionPane.showMessageDialog(null,
                                    fullName + " already exists! Please change that file's name.");
                        } else {
                            // operableGraph.ShowPathPicture(fullName, startNode);
                            ArrayList<String> paths = operableGraph.calcShortestPath(operableGraph.getGraph(), startNode);
                            ShowPathPicture oneNodePicture = new ShowPathPicture(fullName, startNode, paths);
                            oneNodePicture.microWindow.setVisible(true);
                        }
                    }
                } else {
                    String startNode = leftWord.getText();
                    String endNode = rightWord.getText();
                    String dir = operableGraph.getTargetDir();
                    String name = operableGraph.getTargetName();
                    String type = operableGraph.getTargetType();
                    String fullName = dir + "/" + name + "-" + startNode + "-" + endNode + "-"
                            + "ShortestPath." + type;
                    File out = new File(fullName);
                    if (operableGraph.getGraph().getIndex(startNode) == -1) {
                        JOptionPane.showMessageDialog(null, startNode + " is not in the graph!");
                    } else if (operableGraph.getGraph().getIndex(endNode) == -1) {
                        JOptionPane.showMessageDialog(null, endNode + " is not in the graph!");
                    } else {
                        if (out.exists()) {
                            JOptionPane.showMessageDialog(null,
                                    fullName + " already exists! Please change that file's name.");
                        } else {
                            // operableGraph.ShowPathPicture(fullName, startNode, endNode);
                            String oneLinePaths = operableGraph.calcShortestPath(operableGraph.getGraph(), startNode, endNode);
                            ShowPathPicture twoNodePicture = new ShowPathPicture(fullName, startNode, endNode, oneLinePaths);
                            twoNodePicture.microWindow.setVisible(true);
                        }
                    }
                }
            }
        }
    }
}