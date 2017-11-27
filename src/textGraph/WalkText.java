package textGraph;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * create a frame show the random walk text.
 *
 * @author HolynovaSD
 */

public class WalkText extends JFrame {
    private static final long serialVersionUID = 1L;
    private AllOperations operableGraph;
    public JFrame minimumWindow;
    public JTextArea wtext;
    public JScrollPane jsp;
    public java.util.Timer timer;
    public JButton goOn;
    public JButton pause;
    public JButton clear;

    public WalkText(AllOperations paraGraph) {
        this.operableGraph = paraGraph;

        minimumWindow = new JFrame();
        minimumWindow.setLocation(1000, 10);
        minimumWindow.setSize(800, 400);
        minimumWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        minimumWindow.getContentPane().setLayout(null);

        wtext = new JTextArea();
        wtext.setSize(800, 300);
        wtext.setLineWrap(true);
        wtext.setEditable(false);
        wtext.setWrapStyleWord(true);
        wtext.setFont(new Font("Time New Roman", Font.BOLD, 16));

        jsp = new JScrollPane(wtext);
        jsp.setBounds(0, 0, 800, 300);
        minimumWindow.getContentPane().add(jsp);

        timer = new java.util.Timer();

        goOn = new JButton("Start");
        goOn.setBounds(70, 325, 160, 25);
        goOn.addActionListener(new TextGenerating());
        goOn.setEnabled(true);
        minimumWindow.getContentPane().add(goOn);

        pause = new JButton("Pause");
        pause.setBounds(300, 325, 160, 25);
        pause.addActionListener(new TextGenerating());
        pause.setEnabled(false);
        minimumWindow.getContentPane().add(pause);

        clear = new JButton("Clear");
        clear.setBounds(550, 325, 160, 25);
        clear.addActionListener(new TextGenerating());
        clear.setEnabled(false);
        minimumWindow.getContentPane().add(clear);
    }

    private class TextGenerating implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource().equals(goOn)) {
                goOn.setEnabled(false);
                pause.setEnabled(true);
                clear.setEnabled(false);
                if (timer == null) {
                    timer = new java.util.Timer();
                }
                timer.schedule(new MyTask(), 0, 500);
            } else if (event.getSource().equals(pause)) {
                pause.setEnabled(false);
                clear.setEnabled(true);
                timer.cancel();
                timer = null;
                if (operableGraph.getId() == -1) {
                    goOn.setEnabled(false);
                    goOn.setText("Start");
                } else {
                    goOn.setEnabled(true);
                    goOn.setText("Go on");
                }
            } else if (event.getSource().equals(clear)) {
                goOn.setEnabled(true);
                pause.setEnabled(false);
                clear.setEnabled(false);
                // doubles = "";
                operableGraph.setTempRandomText("");
                // id = -1;
                operableGraph.setId(-1);
                // wtext.setText(doubles);
                wtext.setText(operableGraph.getTempRandomText());
                goOn.setText("Start");
//                for (int i = 0; i < MediumWindow.graph.n0; ++i) {
//                    for (int j = 0; j < MediumWindow.graph.n0; ++j) {
//                        visited[i][j] = 0;
//                    }
//                }
                operableGraph.initVisited();
            }
        }

        class MyTask extends TimerTask {
            public void run() {
                // doubles = randomWalk(MediumWindow.graph);
                operableGraph.setTempRandomText(operableGraph.randomWalk(operableGraph.getGraph()));
                // wtext.setText(doubles);
                wtext.setText(operableGraph.getTempRandomText());
                if (operableGraph.getId() == -1) {
                    pause.doClick(1);
                }
            }
        }
    }
}