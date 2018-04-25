public class Parser {
    public void read(String string) {

        String[] split = string.split("\n");
        Boolean readingEdges=false;
        for (String row: split) {
            if (row.charAt(row.length())==']') {
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
