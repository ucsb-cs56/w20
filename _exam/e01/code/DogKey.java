import java.util.List;
import java.util.stream.Collectors;
import java.lang.StackWalker.StackFrame;

public class DogKey {

    private static DogKey bestInShow = null;
    private String name;
    
    public static void setBestInShow(DogKey b) {
	bestInShow = b;
    }
    
    public static DogKey getBestInShow() {
	return bestInShow;
    }
    
    public DogKey(String name) { this.name = name;}
    
    public static void main(String [] args) {
	
	DogKey d1 = new DogKey("Fido");
	DogKey d2 = new DogKey("Ginger");
	DogKey d3 = new DogKey("Harry");
	DogKey d4 = new DogKey("Izzy");
	DogKey d5 = new DogKey("Jack");
	DogKey d6 = d5;
	
	setBestInShow(d1); gc(get_line_number());
	d3 = d2; gc(get_line_number());
	d5 = d4;  gc(get_line_number());
	d6 = null; gc(get_line_number());
	d5 = null; gc(get_line_number());
	d4 = null; gc(get_line_number());
	d3 = null; gc(get_line_number());
	d2 = null; gc(get_line_number());
	d1 = null; gc(get_line_number());
	setBestInShow(null); gc(get_line_number());
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
	System.out.println("Line " + (i-4));
	System.gc();
	System.runFinalization ();
	System.gc();
	System.runFinalization (); 
	System.gc();
	System.runFinalization ();
    }
}
