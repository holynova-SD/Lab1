package textGraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;

public class GenGraphController implements ActionListener {
    /**
     * .
     *
     * @Override
     */
    public void actionPerformed(ActionEvent event) {
        /*
         * when the text field is empty
         */
        if (TextGraph.fileLocation.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose a file!");
        } else if (TextGraph.targetLocation.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please choose a directory to save generated files!");
        } else {
            /*
             * when the text field is not empty
             */
            String sourcePath = TextGraph.fileLocation.getText();
            String targetPath = TextGraph.targetLocation.getText();
            File file = new File(sourcePath);
            File dir = new File(targetPath);
            /*
             * when we can get a text file using the provided path
             */
            if (file.exists() && dir.isDirectory()) {
                AllOperations operableGraph = new AllOperations();
                Graph originalGraph = operableGraph.createDriectedGraph(sourcePath);
                operableGraph.setGraph(originalGraph);
                operableGraph.setTargetDir(targetPath);
                MediumWindow meWindow = new MediumWindow(operableGraph);
                meWindow.mediumFrame.setVisible(true);
            } else if (!file.exists()) {
                /*
                 * when we can not get a text file using the provided path
                 */
                JOptionPane.showMessageDialog(null, "No such file to create a graph!");
            } else {
                JOptionPane.showMessageDialog(null, "No such directory to save generated files!");
            }
        }
    }
}
