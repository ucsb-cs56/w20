---
layout: examHandoutNoName
num: e01-mw
ready: true
desc: "Handout B"
qtr: W20
---

<style>
div.page1 { font-size: 100%;
 line-height: 97%;
}

div.page1 h2 {
 font-size: 105%;
 font-family: Arial, sans-serif;
 padding-top: 0px; padding-bottom: 0px;
 margin-top: 0px; margin-bottom: 0px;
}

div.page2 {
 font-size: 100%;
 line-height: 103%;
}

div.page2 h2 {
 font-size: 105%;
 font-family: Arial, sans-serif;
 padding-top: 2px; padding-bottom: 2px;
 margin-top: 2px; margin-bottom: 2px;
}


td  {
  padding-left:5px; padding-right: 5px;
}

hr { display:none;}

</style>
<div class="page1">


## FreeCandy.java

{% highlight java linenos %}
public class FreeCandy implements Edible {

    private int calories;
    
    public FreeCandy(int calories) { 
	this.calories = calories;
    }

    public int getCalories() {return this.calories;}
}
{% endhighlight %}



## Product.java


{% highlight java linenos %}
public abstract class Product {
    String name;
    int price;
    
    public int getPrice() { return price; } 
    public String getName() {return name;}

    public Product(int price, String name) {
	this.price = price;
	this.name = name;
    }
}
{% endhighlight %}


</div>

<div class="page2 page-break-before">

<h2 markdown="1">Handout B, p. 2</h2>


<style>
 div.dog { font-size: 110%;
    line-height: 110%;
        margin: 0.5em;
	    padding: 0.5em;
	         width: 65%;

 }

div.dog * td  {
  padding-left:5px; padding-right: 5px;
    }
     div.fill-in-blanks-smaller {
        width: 30%;
	   float: right;
	    }

 .fill-in-blanks-smaller table {

  }
   .fill-in-blanks-smaller table * td {
      margin: 0.5em 0.5em 0.5em 0.5em;
         padding: 0.5em 0.5em 0.5em 0.5em;
	    font-size: 150%;
	       line-height: 150%;
	        }

</style>


<div class="dog" markdown="1">
{% highlight java linenos %}
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

{% endhighlight %}

</div><!-- dog -->
