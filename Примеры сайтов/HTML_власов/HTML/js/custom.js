<!-- Live Effects -->
    	
        // DROPDOWN MENU INSPIRED FROM http://www.sohtanaka.com/web-design/examples/drop-down-menu/
        
		$(document).ready(function(){
			
			$("#top_menu ul .dropper").click(function() { //When trigger is clicked...
				
				//Following events are applied to the subnav itself (moving subnav up and down)
				$(this).parent().find("ul.sub_menu").slideDown('fast').show(); //Drop down the subnav on click
		
				$(this).parent().hover(function() {
				}, function(){	
					$(this).parent().find("ul.sub_menu").slideUp('fast'); //When the mouse hovers out of the subnav, move it back up
				});
		
				//Following events are applied to the trigger (Hover events for the trigger)
				}).hover(function() { 
					$(this).addClass("subhover"); //On hover over, add class "subhover"
				}, function(){	//On Hover Out
					$(this).removeClass("subhover"); //On hover out, remove class "subhover"
			});
		
		});
    	
		// Hover effect Sidebar list
            $(function(){
                $('.sidebar li')
                    .css( {backgroundPosition: "0 3px"} )
                    .mouseover(function(){
                        $(this).stop().animate(
							 { backgroundPosition:"(9px 3px)"},
							 { queue: false,
							   duration: 300,
							   easing: 'easeOutElastic'
						})
                    })
                    .mouseout(function(){
                        $(this).stop().animate({backgroundPosition:"(0 3px)"}, {duration:400, complete:function(){
                            $(this).css({backgroundPosition: "0 3px"})
                        }})
                    })
            });




        // JFlow Slider
            $(document).ready(function(){
                $("#slider").jFlow({
                    slides: "#slider_img",
                    controller: ".jFlowControl", // must be class, use . sign
                    slideWrapper : "#jFlowSlide", // must be id, use # sign
                    selectedWrapper: "jFlowSelected",  // just pure text, no sign
                    width: "818px",
                    height: "150px",
                    easing: "swing",
                    duration: 700,
                    prev: ".jFlowPrev", // must be class, use . sign
                    next: ".jFlowNext" // must be class, use . sign
                });
            });
    
	
	
	
        // Teaser Slider

		/*** 
			Simple jQuery Slideshow Script
			Released by Jon Raasch (jonraasch.com) under FreeBSD license: free to use or modify, not responsible for anything, etc.  Please link out to me if you like it :)
		***/
		
		function slideSwitch() {
			var $active = $('#slideshow IMG.active');
		
			if ( $active.length == 0 ) $active = $('#slideshow IMG:last');
		
			// use this to pull the images in the order they appear in the markup
			var $next =  $active.next().length ? $active.next()
				: $('#slideshow IMG:first');
		
			// uncomment the 3 lines below to pull the images in random order
			
			// var $sibs  = $active.siblings();
			// var rndNum = Math.floor(Math.random() * $sibs.length );
			// var $next  = $( $sibs[ rndNum ] );
		
		
			$active.addClass('last-active');
		
			$next.css({opacity: 0.0})
				.addClass('active')
				.animate({opacity: 1.0}, 1000, function() {
					$active.removeClass('active last-active');
				});
		}
		
		$(function() {
			setInterval( "slideSwitch()", 4000 );
		});
		
	<!-- /Live Effects -->