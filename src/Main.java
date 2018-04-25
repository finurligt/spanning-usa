import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Main m = new Main();
            List<String> lines = getStringFromStream(new FileInputStream(args[0]));
            m.read(lines);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }


   
    private class Node {
        int setId;
        String label;

        public Node(int setId, String label) {
            this.setId = setId;
            this.label = label;
        }
    }

    private class Edge implements Comparable<Edge>{
        int weight;
        Node first;
        Node second;

        public Edge(int weight, Node first, Node second) {
            this.weight = weight;
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }


    /*
     * The whole files contents should be passed to this function
     */
    public void read(List<String> stringList) {

        Boolean readingEdges=false;
        Map<Node,Edge> neighborMap = new HashMap<Node,Edge>();
        for (String row: stringList) {
            if (row.charAt(row.length()-1)==']') {
                readingEdges = true;
            }

            if(!readingEdges) {
                //läs nod
                neighborMap.put(new Node())
            } else {
                //läs edge

            }
        }
    }


    /*
     * This function reads a file 
     */
    public static List<String> getStringFromStream(InputStream is) {
        Scanner sc = new Scanner(is);
        List<String> lines = new LinkedList();

        while(sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        return lines;
    }
}
