import java.util.List;
import java.util.stream.Collectors;
import java.lang.StackWalker.StackFrame;







public class Dog {

    private static Dog bestInShow = null;
    private String name;
    
    public static void setBestInShow(Dog b) {
	bestInShow = b;
    }
    
    public static Dog getBestInShow() {
	return bestInShow;
    }
    
    public Dog(String name) { this.name = name;}
    
    public static void main(String [] args) {
	
	Dog d1 = new Dog("Kiki"); gc(get_line_number()-10);
	Dog d2 = new Dog("Logan"); gc(get_line_number()-10);
	Dog d3 = new Dog("Max"); gc(get_line_number()-10);
	Dog d4 = new Dog("Nicky"); gc(get_line_number()-10);
	Dog d5 = new Dog("Olive"); gc(get_line_number()-10);
	Dog d6 = d5; gc(get_line_number()-10);
	
	setBestInShow(d1); gc(get_line_number()-10);
	d2 = d3; gc(get_line_number()-10);
	d4 = d6; gc(get_line_number()-10);
	d6 = null; gc(get_line_number()-10);
	d5 = null; gc(get_line_number()-10);
	d4 = null; gc(get_line_number()-10);
	d3 = null; gc(get_line_number()-10);
	d2 = null; gc(get_line_number()-10);
	d1 = null; gc(get_line_number()-10);
	setBestInShow(null); gc(get_line_number()-10);
    } 

    @SuppressWarnings( "deprecation" )
    public void finalize() {
	System.out.println("Finalizing " + this.name);       
    }
    
    public static int get_line_number() {
	List<StackFrame> stack = StackWalker.getInstance(StackWalker.Option.SHOW_HIDDEN_FRAMES).walk((s) -> s.skip(1).collect(Collectors.toList()));
	return stack.get(0).getLineNumber();
    }

    public static void gc(int i) {
	System.out.println("Line " + i);
	System.gc();
	System.runFinalization ();
	System.gc();
	System.runFinalization ();
    }

}
