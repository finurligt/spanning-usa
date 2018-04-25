public class Main {

    public static void main(String[] args) {
        List<String> lines = getStringFromStream(new FileInputStream(args[0]));
        
    }
   



    /*
     * The whole files contents should be passed to this function
     */
    public void read(String string) {
        String[] split = string.split("\n");
        Boolean readingEdges=false;
        for (String row: split) {
            if (row.charAt(row.length()-1)==']') {
                readingEdges = true;
            }

            if(!readingEdges) {
                //läs nod
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
