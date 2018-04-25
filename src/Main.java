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
            HashMap<String,Node> nodes = new HashMap<>();


            m.read(lines, nodes);
            MST mst = new MST();
            HashSet a = new HashSet<Node>();
            for (Map.Entry n : nodes.entrySet()) {
                a.add(n.getValue());
            }

            System.out.println(mst.solve(nodes.values().iterator().next(), a));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class MST {
        PriorityQueue<Edge> possibleEdges;
        HashSet<Node> nodes;
        //List<Edge> actualEdges;

        public MST(PriorityQueue<Edge> possibleEdges, HashSet<Node> nodes) {
            this.possibleEdges = possibleEdges;
            this.nodes = nodes;
            //this.actualEdges = new ArrayList<>();
        }

        public MST() {
            this.possibleEdges = new PriorityQueue<>();
            this.nodes = new HashSet<>();
            //this.actualEdges = new ArrayList<>();
        }


        /**
         * Solves this MST with prims algorithm.
         * @param start
         */
        public int solve(Node start, Set<Node> nodeSet) {     //allready changed ID when called upon, maybe in creation of edge?


            if(!nodeSet.contains(start)) {
                System.err.println("Starting node not present in graph.");
                return -1;
            }
                                                           //allready changed ID here
            possibleEdges.addAll(start.neighbors);
            nodeSet.remove(start);

            int counter = 0;
            while(!nodeSet.isEmpty()) {
                Edge newEdge = possibleEdges.poll();

                if (nodeSet.contains(newEdge.second)) {    //this never happens, nodes have changed ID by this point
                    //new node is not visited

                    nodeSet.remove(newEdge.second);
                    possibleEdges.addAll(newEdge.second.neighbors);

                    counter += newEdge.weight;

                }

            }
            return counter;

        }
    }

    class Node {
        String label;
        List<Edge> neighbors;

        Node(String label) {
            this.label = label;
            this.neighbors = new LinkedList<Edge>();
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
        @Override
        public String toString() {
            return this.first.label + " -- " +this.second.label;
        }
    }


    /*
     * The whole files contents should be passed to this function. //TODO ska nodes vara i en lista?
     */
    //public void read(List<String> string, List<Edge> edges, List<Node> nodes) {


    public void read(List<String> stringList, HashMap<String,Node> nodeMap) {
        Boolean readingEdges=false;
        for (String row: stringList) {
            row=row.trim();
            if (row.charAt(row.length()-1)==']') {
                readingEdges = true;
            }

            if(!readingEdges) {
                //reading nodes
                nodeMap.put(row,new Node(row.trim()));
            } else {
                Edge edge = parseEdgeString(row,nodeMap);



                nodeMap.get(edge.first.label).neighbors.add(edge);
                nodeMap.get(edge.second.label).neighbors.add(new Edge(edge.second,edge.first,edge.weight));
                //flipping edge for second node
                //if node in map can't be find something is wrong in reading nodes
            }
        }
    }

    private Edge parseEdgeString(String edgeStr, HashMap<String, Node> nodeMap) {
        edgeStr = edgeStr.replace("]","");
        String[] strings = edgeStr.split("--|\\[");
        //strings ser nu ut som ("Namn1", ""Längre namn2" ", "123"), notice the space in name 2

        String firstLabel = strings[0];
        String secondLabel = strings[1].trim();
        String weightStr = strings[2];
        int weight = Integer.parseInt(weightStr);
        return new Edge(nodeMap.get(firstLabel), nodeMap.get(secondLabel), weight);

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
