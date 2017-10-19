package textgraph;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
//lab3 change1.2

public class BrowseAction implements ActionListener {

  public BrowseAction() {

  }

  /**
  * .
  * 
  * @Override
  */
  public void actionPerformed(final ActionEvent event) {
    if (event.getSource().equals(TextGraph.browseFile)) {
      /*
      * create a file chooser
      */
      final JFileChooser selector = new JFileChooser();
      selector.setDialogTitle("Please choose the file...");
      final FileNameExtensionFilter filter = new FileNameExtensionFilter("text file(*.txt)", "txt");
      selector.setFileFilter(filter);
      final int returnVal = selector.showOpenDialog(null);
      /*
      * set the text in the text field using the selected path
      */
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        final String filePath = selector.getSelectedFile().getPath();
        TextGraph.fileLocation.setText(filePath);
      }
    } else if (event.getSource().equals(TextGraph.browseTarget)) {
      /*
      * create a file chooser
      */
      final JFileChooser selector = new JFileChooser();
      selector.setDialogTitle("Please choose the directory...");
      selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      final int returnVal = selector.showOpenDialog(null);
      /*
      * set the text in the text field using the selected path
      */
      if (returnVal == JFileChooser.APPROVE_OPTION) {
        final String filePath = selector.getSelectedFile().getPath();
        TextGraph.targetLocation.setText(filePath);
      }
    }
  }
}