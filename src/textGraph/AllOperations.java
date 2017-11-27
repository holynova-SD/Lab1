package textGraph;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by HolynovaSD on 2017/11/22.
 */
public class AllOperations {
    /**
     * colors stores the names of color that can be used as the color of nodes and edges.
     */
    String[] colors = {"pink", "purple", "darkcyan", "brown", "red", "mediumspringgreen", "tomato",
            "mediumslateblue", "forestgreen", "indigo", "teal", "blue", "orangered", "beige", "bisque",
            "black", "blanchedalmond", "aliceblue", "steelblue", "tan", "thistle", "blueviolet",
            "burlywood", "cadetblue", "chartreuse", "lightgoldenrodyellow", "chocolate", "coral",
            "cornflowerblue", "cornsilk", "crimson", "cyan", "darkblue", "darkgoldenrod", "aqua",
            "darkgray", "darkgreen", "darkgrey", "darkkhaki", "darkmagenta", "darkolivegreen",
            "darkorange", "darkorchid", "darkred", "darksalmon", "darkseagreen", "darkslateblue",
            "darkslategray", "darkslategrey", "darkturquoise", "darkviolet", "deeppink", "deepskyblue",
            "dimgrey", "dodgerblue", "firebrick", "fuchsia", "lightyellow", "gainsboro", "gold",
            "goldenrod", "gray", "grey", "green", "greenyellow", "honeydew", "hotpink", "indianred",
            "ivory", "khaki", "lavender", "lavenderblush", "dimgray", "lawngreen", "lemonchiffon",
            "lightblue", "lightcoral", "lightcyan", "lightgray", "lightgreen", "lightgrey", "lightpink",
            "lightsalmon", "lightseagreen", "lightskyblue", "lightslategray", "lightslategrey",
            "lightsteelblue", "lime", "linen", "magenta", "maroon", "mediumaquamarine", "mediumblue",
            "mediumorchid", "mediumpurple", "mediumseagreen", "mediumturquoise", "mediumvioletred",
            "midnightblue", "mistyrose", "moccasin", "navajowhite", "navy", "oldlace", "antiquewhite",
            "limegreen", "olive", "olivedrab", "orange", "orchid", "palegoldenrod", "palegreen",
            "paleturquoise", "palevioletred", "papayawhip", "peachpuff", "peru", "plum", "powderblue",
            "rosybrown", "royalblue", "saddlebrown", "aquamarine", "salmon", "sandybrown", "seagreen",
            "seashell", "sienna", "silver", "skyblue", "slateblue", "slategray", "slategrey", "azure",
            "springgreen", "turquoise", "violet", "wheat", "yellow", "yellowgreen"};
    /**
     * MAX_SIZE is the maximal size of the graph
     */
    public static final Integer MAX_SIZE = 20000;
    /**
     * graph is what we get from the text.
     */
    private Graph graph;
    /**
     * targetDir is where we are going to save the graph.
     */
    private String targetDir;

    private String targetName;

    private String targetType;

    public void setGraph(Graph paraGraph){
        this.graph = paraGraph;
    }

    public Graph getGraph(){
        return this.graph;
    }

    public void setTargetDir(String paraDir){
        this.targetDir = paraDir;
    }

    public String getTargetDir(){
        return this.targetDir;
    }

    public void setTargetName(String paraName){
        this.targetName = paraName;
    }

    public String getTargetName(){
        return this.targetName;
    }

    public void setTargetType(String paraType){
        this.targetType = paraType;
    }

    public String getTargetType(){
        return this.targetType;
    }

    private int[][] visited = new int[MAX_SIZE][MAX_SIZE];

    public void initVisited(){
        for(int i = 0; i < graph.n0; ++i) {
            for (int j = 0; j < graph.n0; ++j) {
                visited[i][j] = 0;
            }
        }
    }

    private int id;

    public void setId(Integer paraId){
        this.id = paraId;
    }

    public Integer getId(){
        return this.id;
    }

    private String tempRandomText;

    public void setTempRandomText(String paraTempRandomText){
        this.tempRandomText = paraTempRandomText;
    }

    public String getTempRandomText(){
        return this.tempRandomText;
    }

    AllOperations(){

    }

    String getFullName(){
        return this.targetDir + "/" + this.targetName + "." + this.targetType;
    }

    /**
    * read content of the selected file and generate an object of class Graph
    * @return an object of class Graph
    */
    Graph createDriectedGraph(String filename) {
        File file = new File(filename);
        /*
         * allWordsNumber is total number of the words appeared in the text, including repeated ones.
         * finalWordsNumber is the number of distinct words in the text. words is a structure of
         * two-dimensional string array to store the text content. set is a set to store these distinct
         * words.
         */
        int allWordsNumber = 0;
        int finalWordsNumber;
        String[] words = new String[200000];
        Set<String> set = new HashSet<String>();
        try {
        /*
         * word is the temporary word we have got by reading the file by character.
         */
            FileReader fr = new FileReader(file);
            int ch;
            String word = "";
            while ((ch = fr.read()) != -1) {
            /*
             * when the character is uppercase, transform it into lowercase and add it to the end of word
             * to generate a single word
             */
                if (ch >= 65 && ch <= 90) {
                    ch += 32;
                    word += (char) ch;
                } else if (ch >= 97 && ch <= 122) {
                /*
                 * when the character is lowercase, just add it to the end of word
                 */
                    word += (char) ch;
                } else {
                /*
                 * When the character is not in A-Z or a-z, a single word has been gotten. Then add this
                 * word to words and set.
                 */
                    if (!"".equals(word)) {
                        set.add(word);
                        words[allWordsNumber] = word;
                        allWordsNumber++;
                        word = "";
                    } else {
                        continue;
                    }
                }
            }
            if (!"".equals(word)) {
                set.add(word);
                words[allWordsNumber] = word;
                allWordsNumber++;
                word = "";
            }
            fr.close();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Unknown Errors!");
        }
        /*
         * Create a graph
         */
        finalWordsNumber = set.size();
        Graph g = new Graph(finalWordsNumber);
        int iterator = 0;
        /*
         * name every distinct word in the graph with a unique index.Index start from 0.
         */
        for (String name : set) {
            g.namingNode(iterator, name);
            iterator++;
        }
        /*
         * record the adjacency between two words using their corresponding index.
         */
        int x = g.getIndex(words[0]);
        int y;
        for (int i = 1; i < allWordsNumber; ++i) {
            y = g.getIndex(words[i]);
            g.addEdge(x, y, 1);
            x = y;
        }
        return g;
    }

    /**
     * use g and class GraphViz to generate a picture
     */
    void showDirectedGraph(Graph g) {

        GraphViz gv = new GraphViz();
        GraphViz.setdir(targetDir);
        gv.addln(gv.startgraph());
        String line = "";
        for (int i = 0; i < g.n0; ++i) {
            line = "";
            line += g.getName(i);
            line += ";";
            gv.addln(line);
        }
        gv.addln();
        for (int i = 0; i < g.n0; ++i) {
            for (int j = 0; j < g.n0; ++j) {
                if (g.queryWeight(i, j) > 0) {
                    line = "";
                    line += g.getName(i);
                    line += " -> ";
                    line += g.getName(j);
                    line += " [ label = \"";
                    line += Integer.toString(g.queryWeight(i, j));
                    line += "\" ];";
                    gv.addln(line);
                }
            }
        }
        gv.addln(gv.endgraph());

        String fullName = getFullName();
        File out = new File(fullName);
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), targetType), out);
        if (targetType.equals("jpg") || targetType.equals("png") || targetType.equals("gif")) {
            JFrame showPicture = new JFrame(targetName + "." + targetType);
            showPicture.setVisible(true);
            showPicture.setSize(600, 1000);
            showPicture.setLocation(1000, 10);
            showPicture.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            JScrollPane picscrollPane = new JScrollPane();

            picscrollPane.setBounds(0, 0, 600, 1000);
            showPicture.getContentPane().add(picscrollPane);

            JLabel label = new JLabel();

            picscrollPane.setViewportView(label);
            ImageIcon icon = new ImageIcon(fullName);

            label.setIcon(icon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            JOptionPane.showMessageDialog(null, fullName
                    + " has been saved to the choosen path. But it can not been shown in this window.");
        }
    }

    /*
     * get the bridge word in graph G between word1 and word2
     */
    String queryBridgeWords(Graph g, String word1, String word2) {
        ArrayList<Integer> bridgeList = g.getBridges(word1, word2);
        String output;
        if (bridgeList.size() == 0) {
            output = "No bridge word from " + word1 + " to " + word2 + "!";
        } else if (bridgeList.size() == 1) {
            output = "The bridge word from " + word1 + " to " + word2 + " is: "
                    + g.getName(bridgeList.get(0)) + ".";
        } else {
            output = "The bridge words from " + word1 + " to " + word2 + " are:";
            for (int i = 0; i < bridgeList.size(); i++) {
                if (i < bridgeList.size() - 2) {
                    output += " " + g.getName(bridgeList.get(i)) + ",";
                } else if (i == (bridgeList.size() - 2)) {
                    output += " " + g.getName(bridgeList.get(i));
                } else {
                    output += " and " + g.getName(bridgeList.get(i)) + ".";
                }
            }
        }
        return output;
    }

    /**
     * use bridge word to generate new text from input.
     */
    String generateNewText(Graph g, String input) {
        String output = "";
        String[] words = input.split(" ");
        int len = words.length;
        ArrayList<Integer> bridgeList;
        String iWord = words[0];
        String rWord = "";
        for (int i = 1; i < len; ++i) {
            rWord = words[i];
            output += iWord;
            output += " ";
            if (g.getIndex(iWord) != -1 && g.getIndex(rWord) != -1) {
                bridgeList = g.getBridges(iWord, rWord);
                if (bridgeList.size() != 0) {
                    output += g.getName(bridgeList.get(0));
                    output += " ";
                }
            }
            iWord = rWord;
        }
        output += iWord;
        return output;
    }

    /**
     * get the shortest paths from word to all other words in g.
     */
    ArrayList<String> calcShortestPath(Graph g, String word) {
        String dir = targetDir;

        GraphViz gv = new GraphViz();
        GraphViz.setdir(dir);
        gv.addln(gv.startgraph());
        ArrayList<String> paths = new ArrayList<String>();
        String path;
        String line;
        int i;
        int j;
        i = g.getIndex(word);
        for (int m = 0; m < g.n0; ++m) {
            for (int k = 0; k < g.n0; ++k) {
                visited[m][k] = 0;
            }
        }
        for (j = 0; j < g.n0; ++j) {
            path = "";
            if (j == i) {
                continue;
            } else {
                ArrayList<Integer> result = g.getShortestPath(i, j);
                if (result.get(result.size() - 1) == -1) {
                    continue;
                } else {
                    result.add(j);
                    int x = 0;
                    int y;
                    path += g.getName(result.get(x));
                    for (y = 1; y < result.size(); ++y) {
                        line = "";
                        line += g.getName(result.get(x));
                        line += " -> ";
                        line += g.getName(result.get(y));
                        line += " [ label = \"";
                        line += Integer.toString(g.queryWeight(result.get(x), result.get(y)));
                        line += "\", color = \"";
                        line += colors[j];
                        line += "\" ];";
                        gv.addln(line);
                        path += " ";
                        path += g.getName(result.get(y));
                        visited[result.get(x)][result.get(y)]++;
                        x = y;
                    }
                    path += "\n";
                    paths.add(path);
                }
            }
        }
        for (i = 0; i < g.n0; ++i) {
            for (j = 0; j < g.n0; ++j) {
                if (g.queryWeight(i, j) > 0 && visited[i][j] == 0) {
                    line = "";
                    line += g.getName(i);
                    line += " -> ";
                    line += g.getName(j);
                    line += " [ label = \"";
                    line += Integer.toString(g.queryWeight(i, j));
                    line += "\", style = dotted ];";
                    gv.addln(line);
                }
            }
        }
        gv.addln(gv.endgraph());
        String name = targetName;
        String type = targetType;
        String fullName = dir + "/" + name + "-" + word + "-" + "ShortestPath." + type;
        File out = new File(fullName);
        gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
        return paths;
    }


    /**
     * get all shortest paths from word1 to word2 in g.
     */
    String calcShortestPath(Graph g, String word1, String word2) {
        String dir = targetDir;
        String name = targetName;
        String type = targetType;
        String fullName = dir + "/" + name + "-" + word1 + "-" + word2 + "-" + "ShortestPath." + type;
        String path = "";
        String line = "";
        for (int m = 0; m < g.n0; ++m) {
            for (int k = 0; k < g.n0; ++k) {
                visited[m][k] = 0;
            }
        }
        GraphViz gv = new GraphViz();
        GraphViz.setdir(dir);
        gv.addln(gv.startgraph());

        int x = g.getIndex(word1);
        int y = g.getIndex(word2);
        ArrayList<ArrayList<Integer>> result = g.getShortestPathList(x, y);
        if (result.size() != 0) {
            line = "";
            line += "LEN";
            line += " [ label = \"length = ";
            line += Integer.toString(result.get(0).get(0));
            line += "\"  shape = plaintext] ";
            gv.addln(line);
            for (int i = 0; i < result.size(); ++i) {
                path += g.getName(result.get(i).get(1));
                for (int j = 2; j < result.get(i).size(); ++j) {
                    line = "";
                    line += g.getName(result.get(i).get(j - 1));
                    line += " -> ";
                    line += g.getName(result.get(i).get(j));
                    line += " [ label = \"";
                    line += Integer.toString(g.queryWeight(result.get(i).get(j - 1), result.get(i).get(j)));
                    line += "\", color = \"";
                    line += colors[i];
                    line += "\" ];";
                    gv.addln(line);
                    path += " ";
                    path += g.getName(result.get(i).get(j));
                    visited[result.get(i).get(j - 1)][result.get(i).get(j)]++;
                }
                path += "\n";
            }
            for (int i = 0; i < g.n0; ++i) {
                for (int j = 0; j < g.n0; ++j) {
                    if (g.queryWeight(i, j) > 0 && visited[i][j] == 0) {
                        line = "";
                        line += g.getName(i);
                        line += " -> ";
                        line += g.getName(j);
                        line += " [ label = \"";
                        line += Integer.toString(g.queryWeight(i, j));
                        line += "\", style = dotted ];";
                        gv.addln(line);
                    }
                }
            }
            gv.addln(gv.endgraph());
            File out = new File(fullName);
            gv.writeGraphToFile(gv.getGraph(gv.getDotSource(), type), out);
        }
        return path;
    }

    /**
     * generate new text by random walk in G.
     */
    String randomWalk(Graph g) {
        Random rand = new Random();
        String output = tempRandomText;
        int u;
        if (id == -1) {
            u = rand.nextInt(g.n0);
            id = u;
            output += g.getName(u);
            output += " ";
            return output;
        } else {
            u = id;
            ArrayList<Integer> nextNodes = new ArrayList<Integer>();
            nextNodes.clear();
            for (int i = 0; i < g.n0; ++i) {
                if (g.queryWeight(u, i) > 0) {
                    nextNodes.add(i);
                }
            }
            if (nextNodes.size() == 0) {
                id = -1;
            } else {
                id = nextNodes.get(rand.nextInt(nextNodes.size()));
                output += g.getName(id);
                output += " ";
                if (visited[u][id] == 1) {
                    id = -1;
                } else {
                    visited[u][id] = 1;
                }
            }
            return output;
        }
    }


}
