package textGraph;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.util.*;
import textGraph.Graph;
//lab3 chengs 2.2
import textGraph.mediumWindow;
//lab3 change 2.1
public class allFunction implements ActionListener {
	Graph originalGraph;
	
	public void actionPerformed(ActionEvent event) {
		/*
		* when the text field is empty
		*/
		if(TextGraph.fileLocation.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please choose a file!");
		}
		else if(TextGraph.targetLocation.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please choose a directory to save generated files!");
		}
		/*
		* when the text field is not empty
		*/
		else {
			String sourcePath = TextGraph.fileLocation.getText();
			String targetPath = TextGraph.targetLocation.getText();
			File file = new File(sourcePath);
			File dir = new File(targetPath);
			/*
			* when we can get a text file using the provided path
			*/
			if(file.exists() && dir.isDirectory()) {
				originalGraph = createDriectedGraph(sourcePath);
				mediumWindow meWindow = new mediumWindow(originalGraph, targetPath);
				meWindow.mediumFrame.setVisible(true);
			}
			/*
			* when we can not get a text file using the provided path
			*/
			else if(!file.exists()) {
				JOptionPane.showMessageDialog(null, "No such file to create a graph!");
			}
			else {
				JOptionPane.showMessageDialog(null, "No such directory to save generated files!");
			}
		}
	}
	/**
	 * read content of the selected file and generate an object of class Graph
	 * @param filename
	 * @return an object of class Graph
	 */
	Graph createDriectedGraph(String filename) {
		File file = new File(filename);
		/*
		* allWordsNumber is total number of the words appeared in the text, including repeated ones.
		* finalWordsNumber is the number of distinct words in the text.
		* words is a structure of two-dimensional string array to store the text content.
		* set is a set to store these distinct words.
		*/
	    int allWordsNumber = 0, finalWordsNumber;
		String[] words = new String[200000];
		Set<String> set = new HashSet<String>();
		try {
			/*
			* word is the temporary word we have got by reading the file by character.
			*/
			FileReader fr = new FileReader(file);
			int ch;
			String word = "";
			while((ch = fr.read()) != -1) {
				/*
				* when the character is uppercase，transform it into lowercase
				* and add it to the end of word to generate a single word
				*/
				if(ch >= 65 && ch <= 90) {
					ch += 32;
					word += (char)ch;
				}
				/*
				* when the charactrer is lowercase, just add it to the end of word
				*/
				else if(ch >= 97 && ch <= 122){
					word += (char)ch;
				}
				/*
				* When the character is not in A-Z or a-z, a single word has been gotten.
				* Then add this word to words and set.
				*/
				else {
					if(word != "") {
						set.add(word);
						words[allWordsNumber] = word;
						allWordsNumber ++;
						word = "";
					}
					else {
						continue;
					}
				}
			}
			if(word != "") {
				set.add(word);
				words[allWordsNumber] = word;
				allWordsNumber ++;
				word = "";
			}
			fr.close();
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "Unkonwn Errors！");
		}
		/*
		* Create a graph
		*/
		finalWordsNumber = set.size();
		Graph graph = new Graph(finalWordsNumber);
		int iterator = 0;
		/*
		* name every distinct word in the graph with a unique index.Index start from 0.
		*/
		for(String name : set) {
			graph.namingNode(iterator, name);
			iterator ++;
		}
		/*
		* record the adjacency between two words using their corresponding index.
		*/
		int x = graph.getIndex(words[0]), y;
		for(int i = 1; i < allWordsNumber; ++i) {
			y = graph.getIndex(words[i]);
			graph.addEdge(x,  y,  1);
			x = y;
		}
		return graph;
	}

}
