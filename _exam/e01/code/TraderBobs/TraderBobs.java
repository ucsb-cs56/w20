public class TraderBobs {

  public static void TB20 () {
      //      Product p20 = new Product(299, "Fuzzy Dice");
      //System.out.println(p20.getPrice());
  }
    
  public static void TB21 () {
    Product p21 = new Food(249,"Kind Bar",200,1.4);
    System.out.println(p21.getName());
  }
    
  public static void TB22 () {
      //Edible e22 = new Product(299, "Fuzzy Dice");
      //System.out.println(e22.getCalories());
  }

  public static void TB23 () {
    Edible e23 = ()->99 ;
    System.out.println(e23.getCalories());
  }

  public static void TB24 () {
    Edible e24 = new Edible() {
      public int getCalories() {
        return 75;
      }
    };
    System.out.println(e24.getCalories());
  }

  public static void TB25 () {
    Food f25 = new Food(199,"Gummi Bears",520,5);
    System.out.println(f25.getWeight());
  }

  public static void TB26 () {
    FreeCandy f26 = new FreeCandy(50);
    //System.out.println(f26.getName());
  }


  public static void TB27 () {
    Product p27 = new Beverage(199,"Milk",120,6.75);
    System.out.println(p27.getName());
  }

  public static void TB28 () {
    Food f28 = new Food(99,"Peanuts",100,0.63);
    System.out.println(f28.getPrice());
  }

  public static void TB29 () {
    Edible e29 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println(e29.getCalories());
  }

  public static void TB30 () {
      // Product p30 = new FreeCandy(42);
      //System.out.println(p30.getName());
  }

  public static void TB31 () {
    Beverage b31 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println(b31.getFluidOunces());
  }

  public static void TB32 () {
    Product b32 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println(b32.getPrice());
  }

  public static void main (String [] args) {
    System.out.println("TB21");
    TB21();
    System.out.println("TB22");
    TB22();
    System.out.println("TB23");
    TB23();
    System.out.println("TB24");
    TB24();
    System.out.println("TB25");
    TB25();
    System.out.println("TB26");
    TB26();
    System.out.println("TB27");
    TB27();
    System.out.println("TB28");
    TB28();
    System.out.println("TB29");
    TB29();
    System.out.println("TB30");
    TB30();
    System.out.println("TB31");
    TB31();
    System.out.println("TB32");
    TB32();
  }
}
