package textGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * create a frame to get the original text used in function generateNewText
 * and show the generated.
 * text in the window
 *
 * @author HolynovaSD
 */

public class NewText extends JFrame {
    private static final long serialVersionUID = 1L;
    public JFrame minimumWindow;
    public JTextArea inText;
    public JTextArea outText;
    public JScrollPane jspin;
    public JScrollPane jspout;
    public JButton write;
    private AllOperations operableGraph;

    public NewText(AllOperations paraGraph) {
        operableGraph = paraGraph;

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
                String iText = inText.getText();
                String oText = operableGraph.generateNewText(operableGraph.getGraph(), iText);
                outText.setText(oText);
            }
        }
    }
}