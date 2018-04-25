import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    static final boolean SHOULD_PRINT_GRAPH = false;
    static final boolean SHOULD_PRINT_TIMES = false;

    static final long STARTTIME = System.currentTimeMillis();

    public static void main(String[] args) {
        if(SHOULD_PRINT_TIMES) System.err.println("start: "+ String.valueOf(System.currentTimeMillis()-STARTTIME));

        if(args.length!=2) {
            System.out.println("Wrong number of arguments. please use this program as descipted below:");
            System.out.println("\'java Main <word list file> <paths to find>\'");
            System.exit(1);
        }


        String wordFile = args[0];
        String pathsFile = args[1];

        try {
            // Reading the things.
            List<String> wordList = getStringFromStream(new FileInputStream(wordFile));
            List<String> paths = getStringFromStream(new FileInputStream(pathsFile));

            if(SHOULD_PRINT_TIMES) System.err.println("files read: "+ String.valueOf(System.currentTimeMillis()-STARTTIME));

            // Creating the graphelino, mate.
            Map< String, ArrayList<String> > neighborMap = new HashMap<>();

            for(String parent : wordList) {
                neighborMap.putIfAbsent(parent, new ArrayList<>());

                for(String child: wordList) {
                    if(shouldBeChildOf(parent, child) && !parent.equals(child)) {
                        neighborMap.get(parent).add(child);
                    }
                }
            }


            if(SHOULD_PRINT_TIMES) System.err.println("graph created: "+ String.valueOf(System.currentTimeMillis()-STARTTIME));

            // For debugging purposes.
            if(SHOULD_PRINT_GRAPH) {
                for( String s : neighborMap.keySet()) {
                    System.err.println(s);
                    neighborMap.get(s).forEach( string -> System.err.print(" "+string));
                    System.err.println("\n");
                }
            }


            // Finding the paths, kindof.
            List<Integer> results = new LinkedList<>();

            for( String path : paths ) {
                String[] split = path.split(" ");
                if(split[0].equals(split[1])) {
                    results.add(0);
                } else {
                    results.add(findPath(neighborMap, split[0], split[1]));
                }
            }

            if(SHOULD_PRINT_TIMES) System.err.println("paths calc-ed: "+ String.valueOf(System.currentTimeMillis()-STARTTIME));
            results.forEach(System.out::println);

            // Klara :D
        } catch (FileNotFoundException e) {
            System.err.println("Could not find one or any of the files.");
            System.exit(1);
        }
    }

    public static int findPath(Map<String,ArrayList<String>> neighborMap, String s, String t) {
        HashMap<String,String> pred = new HashMap<String,String>();
        HashSet<String> visited = new HashSet<String>();

        //q <- new list containing s
        Queue<String> q = new LinkedList<String>();
        q.add(s);

        //for v ∈ V visited(v) ← 0

        //visited(s) ← 1
        visited.add(s);

        //while q 6= null
        while(!q.isEmpty()) {
            //v ← take out the first element from q
            String currentNode = q.poll();
            //for w ∈ neighbor(v)
            for (String neighbor : neighborMap.get(currentNode)) {
                //if not visited(w) then
                if (!visited.contains(neighbor)) {
                    //visited(w) ← 1
                    visited.add(neighbor);
                    //add w to end of q
                    q.add(neighbor);
                    //pred(w) ← v
                    pred.put(neighbor,currentNode);
                    //if w = t then
                    if (neighbor.equals(t)) {
                        //System.err.println("found path "+s+" - "+t);
                        return backTrack(pred,s,t);
                    }
                }
            }
        }
        //System.err.println("found no path s - t");
        return -1;

    }

    private static int backTrack(Map<String, String> pred, String s, String t)  {
        int steps = 0;
        String currentNode = t;
        while (true) {
            if (pred.containsKey(currentNode)) {
                currentNode = pred.get(currentNode);
                steps++;

            } else {
                return steps;
            }
        }
    }

    public static boolean shouldBeChildOf(String p, String c) {

        String copyOfChild = String.valueOf(c);

        for(char ac : p.substring(1).toCharArray()) {
            copyOfChild = copyOfChild.replaceFirst(String.valueOf(ac), "");
        }

        return copyOfChild.length() == 1;
    }

    public static List<String> getStringFromStream(InputStream is) {
        Scanner sc = new Scanner(is);
        List<String> lines = new LinkedList();

        while(sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        return lines;
    }
}
