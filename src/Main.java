import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Main m = new Main();
            List<String> lines = getStringFromStream(new FileInputStream(args[0]));
            List<Edge> edges = new LinkedList<>();
            List<Node> nodes = new ArrayList<>();

            m.read(lines, edges, nodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    class MST {
        PriorityQueue possibleEdges;
        List<Node> nodes;
        List<Edge> accualEdges;

        public MST(PriorityQueue possibleEdges, List<Node> nodes) {
            this.possibleEdges = possibleEdges;
            this.nodes = nodes;
            this.accualEdges = new ArrayList<>();
        }


        /**
         * Solves this MST with prims algorithm.
         * @param start
         */
        public void solve(Node start) {
            if(!nodes.contains(start)) {
                System.err.println("Starting node not present in graph.");
                return;
            }
        }
    }

    class Node {
        String label;
        HashMap<Node, Integer> neighbors;

        Node(String label) {
            this.label = label;
            this.neighbors = new HashMap();
        }
    }

    class Edge implements Comparable<Edge>{
        int weight;
        Node first;
        Node second;

        Edge(Node first, Node second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.weight - edge.weight;
        }
    }


    /*
     * The whole files contents should be passed to this function. //TODO ska nodes vara i en lista?
     */
    public void read(List<String> string, List<Edge> edges, List<Node> nodes) {

        Boolean readingEdges=false;
        for (String row: string) {
            if (row.charAt(row.length()-1)==']') {
                readingEdges = true;
            }

            if(!readingEdges) {
                //läs nod
                
            } else {
                edges.add(parseEdgeString(row));
            }
        }
    }

    private Edge parseEdgeString(String edgeStr) {
        String[] strings = edgeStr.split(" ");

        //Stadsnamnen ligger i split[0]
        String[] labels = strings[0].split("--");
        String firstLabel = labels[0];
        String secondLabel = labels[1];

        //Weighten ligger i split[1]
        String weightStr = strings[1].replace("[", "").replace("]", "");
        int weight = Integer.parseInt(weightStr);
        return new Edge(new Node(firstLabel), new Node(secondLabel), weight);
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
