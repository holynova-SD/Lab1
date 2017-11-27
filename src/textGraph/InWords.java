package textGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * create a frame to get the two words used in function queryBridgeWords and show the bridge.
 * word(s)
 *
 * @author HolynovaSD
 */

public class InWords extends JFrame {
    private static final long serialVersionUID = 1L;
    private AllOperations operableGraph;
    public JFrame minimumWindow;
    public JLabel label;
    public JTextField leftWord;
    public JTextField rightWord;
    public JButton query;

    public InWords(AllOperations tempGraph) {
        operableGraph = tempGraph;

        minimumWindow = new JFrame();
        minimumWindow.setLocation(400, 10);
        minimumWindow.setSize(400, 200);
        minimumWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        minimumWindow.getContentPane().setLayout(null);

        label = new JLabel("Please enter two words:");
        label.setBounds(50, 50, 300, 20);
        minimumWindow.getContentPane().add(label);

        leftWord = new JTextField();
        leftWord.setBounds(50, 80, 140, 20);
        minimumWindow.getContentPane().add(leftWord);

        rightWord = new JTextField();
        rightWord.setBounds(210, 80, 140, 20);
        minimumWindow.getContentPane().add(rightWord);

        query = new JButton("Query");
        query.setBounds(100, 110, 200, 40);
        query.addActionListener(new QueryWordB());
        minimumWindow.getContentPane().add(query);
    }

    private class QueryWordB implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (leftWord.getText().isEmpty() || rightWord.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the two words!");
            } else {
                String lWord = leftWord.getText();
                String rWord = rightWord.getText();
                String result = operableGraph.queryBridgeWords(operableGraph.getGraph(), lWord, rWord);
                JOptionPane.showMessageDialog(null, result);
            }
        }
    }
}