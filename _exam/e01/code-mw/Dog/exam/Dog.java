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
	
	Dog d1 = new Dog("Kiki");
	Dog d2 = new Dog("Logan");
	Dog d3 = new Dog("Max");
	Dog d4 = new Dog("Nicky");
	Dog d5 = new Dog("Olive");
	Dog d6 = d5;
	
	setBestInShow(d1);
	d2 = d3;
	d4 = d6;
	d6 = null;
	d5 = null;
	d4 = null;
	d3 = null;
	d2 = null;
	d1 = null;
	setBestInShow(null);
    }
}
