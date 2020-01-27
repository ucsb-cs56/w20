---
layout: examHandoutNoName
num: h07
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
</div>