package textGraph;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class ShowPathPicture {
    public JFrame microWindow;

    public ShowPathPicture(String dir, String lWord, String rWord, String oneLinePaths){
        if (oneLinePaths.equals("")) {
            JOptionPane.showMessageDialog(null, lWord + " does not point to " + rWord + " !");
        } else {
            microWindow = new JFrame();
            // microWindow.setVisible(true);
            microWindow.setLocation(700, 10);
            microWindow.setSize(1200, 800);
            microWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            microWindow.getContentPane().setLayout(null);

            JTextArea pathsShowing = new JTextArea();
            pathsShowing.setSize(600, 800);
            pathsShowing.setLineWrap(true);
            pathsShowing.setEditable(false);
            pathsShowing.setWrapStyleWord(true);
            pathsShowing.setText(oneLinePaths);
            pathsShowing.setFont(new Font("Time New Roman", Font.BOLD, 16));

            JScrollPane jsp = new JScrollPane(pathsShowing);
            jsp.setBounds(600, 0, 600, 800);
            microWindow.getContentPane().add(jsp);

            JScrollPane picscrollPane = new JScrollPane();
            picscrollPane.setBounds(0, 0, 600, 780);
            microWindow.getContentPane().add(picscrollPane);

            JLabel picture = new JLabel();

            picscrollPane.setViewportView(picture);
            ImageIcon icon = new ImageIcon(dir);
            picture.setIcon(icon);
            picture.setHorizontalAlignment(SwingConstants.CENTER);
        }
    }

    public ShowPathPicture(String dir, String word, ArrayList<String> paths){
        String oneLinePaths = "";
        for (String pathElements : paths) {
            oneLinePaths += pathElements;
        }
        if (oneLinePaths.equals("")) {
            JOptionPane.showMessageDialog(null, word + " points to no word in the graph!");
        } else {
            microWindow = new JFrame();
            // microWindow.setVisible(true);
            microWindow.setLocation(700, 10);
            microWindow.setSize(1200, 800);
            microWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            microWindow.getContentPane().setLayout(null);

            JTextArea pathsShowing = new JTextArea();
            pathsShowing.setSize(600, 800);
            pathsShowing.setLineWrap(true);
            pathsShowing.setEditable(false);
            pathsShowing.setWrapStyleWord(true);
            pathsShowing.setText(oneLinePaths);
            pathsShowing.setFont(new Font("Time New Roman", Font.BOLD, 16));

            JScrollPane jsp = new JScrollPane(pathsShowing);
            jsp.setBounds(600, 0, 600, 800);
            microWindow.getContentPane().add(jsp);

            JScrollPane picscrollPane = new JScrollPane();
            picscrollPane.setBounds(0, 0, 600, 780);
            microWindow.getContentPane().add(picscrollPane);

            JLabel picture = new JLabel();

            picscrollPane.setViewportView(picture);
            ImageIcon icon = new ImageIcon(dir);
            picture.setIcon(icon);
            picture.setHorizontalAlignment(SwingConstants.CENTER);
        }

    }
}
