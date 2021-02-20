<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="<c:url value="https://code.jquery.com/jquery-3.2.1.js"/>"></script>

<script src="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"/>" 
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" 
		crossorigin="anonymous">
</script>

<script type="text/javascript">

//on ajouter une classe nommée black quand déplacer
$(window).on('scroll', function(){
    if($(window).scrollTop()){
        $('.navbar-insc').addClass('black');
    }
    else
    {
        $('.navbar-insc').removeClass('black');
    }
});
// on lire le contenue de Menu quand faire une click sur h4 de class menu
$(document).ready(function(){
    $(".menu h4").click(function(){
        $(".nav-menu .navbar-insc ul").toggleClass("active");
    });
});
// on lire le contenue de la division des messages
$(document).ready(function(){
    $(".prof ul .msg").click(function(){
        $("div.div-msg").toggleClass("active");
    });
    $(".sec-tab .msg").click(function(){
        $("div.div-msg").toggleClass("active");
    });
    $(".prof ul .type-vg").click(function(){
        $("div.list-vg").toggleClass("active");
    });
    $(".sec-tab .l-vg").click(function(){
        $("div.list-vg").toggleClass("active");
    });
});
// on masque le contenue de la division des messages
$(document).ready(function(){
    $(".div-msg .remove-div-msg .remove").click(function(){
        $("div.div-msg").removeClass("active")
    });
    $(".list-vg .remove-list .remove").click(function(){
        $("div.list-vg").removeClass("active")
    });
});
$(document).ready(function(){
    $(".div-msg .remove-div-msg .arrow").click(function(){
        $('.chat .chat-msg').hide();
        $('.chat .msg-form').hide();
        $('.' + $(this).data('class')).fadeIn();
    });
});
// on lire le contenue de la division de demande des clients de la page profile
$(function() {
    'use strict';
    $('.info-list svg').click(function() {
        $(this).addClass('selected').siblings('svg').removeClass('selected');
        $('.info-content div').hide();
        $('.' + $(this).data('class')).fadeIn();
    });
});
$(function() {
    'use strict';
    $('.client .div-person').click(function() {
        $(this).addClass('selected').siblings('.div-person').removeClass('selected');
        $('.chat .chat-msg').hide();
        $('.chat .msg-form').hide();
        $('.' + $(this).data('class')).fadeIn();
    });
});


</script>