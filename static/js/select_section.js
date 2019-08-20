

function toggle_nav_items(o) {
    /*  o has form { "text": "W19 Mirza", "url": "..."} */
    $("#offerings-dropdown").text(o.text);
    $(".nav-item").each( function() {
	var term=$(this).data("term");
	if (term) {
	    if (term==o.text) {
		$(this).removeClass("d-none");
	    } else {
		$(this).addClass("d-none");
	    }
	}
    });
}

$( document ).ready(function() {
    $(".offerings-dropdown-item").click(
	function(){
	    var o = {
		"text" : $(this).text(),
		"url" : $(this).data("url")
	    };
	    toggle_nav_items(o);
	    var offering = JSON.stringify(o);
	    console.log("offering="+offering);
	    localStorage.setItem("offering",offering);
	    window.location = o.url;
	}
    );
});
