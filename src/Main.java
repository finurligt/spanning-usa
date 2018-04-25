public class Main {
    public static void main(String[] args) {

    }

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
}
