package textGraph;

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

public class OperationController implements ActionListener {
    private AllOperations operableGraph;

    OperationController(AllOperations paraGraph){
        this.operableGraph = paraGraph;
    }

    /**
     * @Override.
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
                // String d = operableGraph.getTargetDir();
                // String n = MediumWindow.pictureName.getText();
                // String t = (String) MediumWindow.fileTypeChooser.getSelectedItem();
                // String allName = d + "/" + n + "." + t;
                operableGraph.setTargetName(MediumWindow.pictureName.getText());
                operableGraph.setTargetType((String) MediumWindow.fileTypeChooser.getSelectedItem());
                String allName = operableGraph.getFullName();
                File check = new File(allName);
                if (check.exists()) {
                    JOptionPane.showMessageDialog(null, allName + " has already existed!");
                } else {
                    operableGraph.showDirectedGraph(operableGraph.getGraph());
                }
            }
        } else if (event.getSource().equals(MediumWindow.buttonQueryBridgeWords)) {
            InWords readWords = new InWords(operableGraph);
            readWords.minimumWindow.setVisible(true);
        } else if (event.getSource().equals(MediumWindow.buttonGenerateNewText)) {
            NewText newTextGenerated = new NewText(operableGraph);
            newTextGenerated.minimumWindow.setVisible(true);
        } else if (event.getSource().equals(MediumWindow.buttonCalcShortestPath)) {
            operableGraph.setTargetName(MediumWindow.pictureName.getText());
            operableGraph.setTargetType((String) MediumWindow.fileTypeChooser.getSelectedItem());
            ShortestPath pathEnds = new ShortestPath(operableGraph);
            pathEnds.minimumWindow.setVisible(true);
        } else if (event.getSource().equals(MediumWindow.buttonRandomWalk)) {
            // doubles = "";
            operableGraph.setTempRandomText("");
            // id = -1;
            operableGraph.setId(-1);
//            for (int i = 0; i < MediumWindow.graph.n0; ++i) {
//                for (int j = 0; j < MediumWindow.graph.n0; ++j) {
//                    visited[i][j] = 0;
//                }
//            }
            operableGraph.initVisited();
            WalkText randomWalkText = new WalkText(operableGraph);
            randomWalkText.minimumWindow.setVisible(true);
        }
    }
}