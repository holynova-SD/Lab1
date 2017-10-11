package textGraph;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
//lab3 change 2.2
import javax.swing.filechooser.FileNameExtensionFilter;
//lab3 change1.2

//lab3 chang 2.3 and 2.1

public class browseAction implements ActionListener{
	public void actionPerformed(ActionEvent event) {
		if(event.getSource().equals(TextGraph.browseFile)) {
			/*
			 * create a file chooser
			 */
			JFileChooser selector = new JFileChooser();
			selector.setDialogTitle("Please choose the file...");
			FileNameExtensionFilter filter = new FileNameExtensionFilter("text file(*.txt)", "txt");
			selector.setFileFilter(filter);
			int returnVal = selector.showOpenDialog(null);
			/*
			 * set the text in the text field using the selected path
			 */
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = selector.getSelectedFile().getPath();
				TextGraph.fileLocation.setText(filePath);
			}
		}
		else if(event.getSource().equals(TextGraph.browseTarget)){
			/*
			 * create a file chooser
			 */
			JFileChooser selector = new JFileChooser();
			selector.setDialogTitle("Please choose the directory...");
			selector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = selector.showOpenDialog(null);
			/*
			 * set the text in the text field using the selected path
			 */
		    if (returnVal == JFileChooser.APPROVE_OPTION) {
		        String filePath = selector.getSelectedFile().getPath();
		        TextGraph.targetLocation.setText(filePath);
		    }
		}
	}
}