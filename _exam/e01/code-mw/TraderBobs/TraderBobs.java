public class TraderBobs {

  public static void TB40 () {
    Food f40 = new Food(199,"Gummi Bears",520,5);
    System.out.println(f40.getWeight());
  }

  public static void TB41 () {
    Product b41 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println(b41.getPrice());
  }

  public static void TB42 () {
    Beverage b42 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println(b42.getFluidOunces());
  }

  public static void TB43 () {
    FreeCandy f43 = new FreeCandy(50);
    //System.out.println(f43.getName());
  }


  public static void TB44 () {
    Product p44 = new Beverage(199,"Milk",120,6.75);
    System.out.println(p44.getName());
  }

  public static void TB45 () {
    Food f45 = new Food(99,"Peanuts",100,0.63);
    System.out.println(f45.getPrice());
  }

  public static void TB46 () {
    Edible e46 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println(e46.getCalories());
  }

    
  public static void TB47 () {
      //      Product p47 = new Product(299, "Fuzzy Dice");
      //System.out.println(p47.getPrice());
  }
    
  public static void TB48 () {
    Product p48 = new Food(249,"Kind Bar",200,1.4);
    System.out.println(p48.getName());
  }
    
  public static void TB49 () {
      //Edible e49 = new Product(299, "Fuzzy Dice");
      //System.out.println(e49.getCalories());
  }

  public static void TB50 () {
    Edible e50 = ()->99 ;
    System.out.println(e50.getCalories());
  }

  public static void TB51 () {
    Edible e51 = new Edible() {
      public int getCalories() {
        return 75;
      }
    };
    System.out.println(e51.getCalories());
  }


  public static void TB52 () {
      // Product p52 = new FreeCandy(42);
      //System.out.println(p52.getName());
  }


  public static void main (String [] args) {
    System.out.println("TB40y");
    TB40();
    System.out.println("TB41");
    TB41();
    System.out.println("TB42");
    TB42();
    System.out.println("TB43");
    TB43();
    System.out.println("TB44");
    TB44();
    System.out.println("TB45");
    TB45();
    System.out.println("TB46");
    TB46();
    System.out.println("TB47");
    TB47();
    System.out.println("TB48");
    TB48();
    System.out.println("TB49");
    TB49();
    System.out.println("TB50");
    TB50();
    System.out.println("TB51");
    TB51();
    System.out.println("TB52");
    TB52();
  }
}
