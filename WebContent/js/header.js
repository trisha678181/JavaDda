/**
 * 
 */

$(document).ready( function() {
	const icons = document.querySelectorAll('.icon');
	icons.forEach (icon => {  
	  icon.addEventListener('click', (event) => {
	    icon.classList.toggle("open");
	  });
	});
})

$(function(){
var menu = $('#slide_menu'),
    menuBtn = $('.icon'), 
    menuWidth = menu.outerWidth(); 

var state = false;
    menuBtn.on('click',function(){

    	if(!state) {
    		menu.animate({'right' : 0 }, 300);      
        	$("#hamburger").animate({'right' : '-5%' }, 300);     
    	} else if (state) {
    		menu.animate({'right' : '-240px' }, 300);      
        	$("#hamburger").animate({'right' : '-46%' }, 300);
    	}
    	state = !state;
    });
});