public class Problem {

    private String thing;
    private int num;

    public Problem (String thing, int num) {
	this.thing = thing;
	this.num = num;
    }
    
    public static void main (String [] args) {
	Problem p = new Problem("Potato",42);
	System.out.println(p);
    }

}
