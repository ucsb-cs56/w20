public class TraderBobs {

  public static void TB01 () {
    Product p1 = new Beverage(99,"Coke",150,12.0);
    System.out.println("p1: " + p1.getName());
  }
  public static void TB02 () {
    Edible e2 = new Food(249,"Kind Bar",200,1.4);
    //System.out.println("e2: " + e2.getName());
  }

  public static void TB03 () {
    Edible e03 = ()->42 ;
    System.out.println("e03: " + e03.getCalories());
  }

  public static void TB04 () {
    Food f4 = new Food(199,"Gummi Bears",520,5);
    System.out.println("f4: " + f4.getPrice());
  }

  public static void TB05 () {
    FreeCandy f5 = new FreeCandy(50);
    System.out.println("f5: " + f5.getCalories());
  }

  public static void TB06 () {
    Food f6 = new Food(99,"Peanuts",100,0.63);
    System.out.println("f6: " + f6.getName());
  }

  public static void TB07 () {
    Beverage b7 = new Beverage(89,"Diet Coke",0,12.0);
    System.out.println("b7: " + b7.getCalories());
  }

  public static void TB08 () {
    Product p8 = new Beverage(199,"Milk",120,6.75);
    //System.out.println("p8: " + p8.getCalories());
  }

  public static void TB09 () {
    Edible e9 = new FreeCandy(42);
    System.out.println("e9: " + e9.getCalories());
  }


  public static void TB10 () {
    Edible e10 = new Edible() {
      public int getCalories() {
        return 100;
      }
    };
    System.out.println("e10: " + e10.getCalories());
  }


  public static void TB11 () {
      //Product p11 = new Product(299,"Ziploc Bags");
      //System.out.println("p11: " + p11.getPrice());
  }

  public static void TB12 () {
    Food f12 = new Food(99,"Peanuts",100,0.63);
    //System.out.println("f12: " + f12.getFluidOunces());
  }


  public static void main (String [] args) {
    System.out.println("TB01");
    TB01();
    System.out.println("TB02");
    TB02();
    System.out.println("TB03");
    TB03();
    System.out.println("TB04");
    TB04();
    System.out.println("TB05");
    TB05();
    System.out.println("TB06");
    TB06();
    System.out.println("TB07");
    TB07();
    System.out.println("TB08");
    TB08();
    System.out.println("TB09");
    TB09();
    System.out.println("TB10");
    TB10();
    System.out.println("TB11");
    TB11();
    System.out.println("TB12");
    TB12();
  }
}
