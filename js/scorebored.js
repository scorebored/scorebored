$(function() {
   
    var displayPage = function(pageName) {
        menuItem = "#menu-" + pageName;
        page = "#" + pageName;

        $(".menu-item-selected").removeClass("menu-item-selected");
        $(menuItem).addClass("menu-item-selected");
        $(".pages").css("display", "none");
        $(page).css("display", "block");
    };

    var displayScreenshot = function(screenshot) {
        menuItem = "#menu-" + screenshot;
        div = "#" + screenshot;

        $(".screenshot-item-selected").removeClass("screenshot-item-selected");
        $(menuItem).addClass("screenshot-item-selected");
        $(".screenshot").css("display", "none");
        $(div).css("display", "block");
    };
   
    $("#menu-screenshots").click(function() {
        displayPage("screenshots");
        displayScreenshot("dashboard");
    });

    $("#menu-about").click(function() {
        displayPage("about");
    });
    
    $("#menu-reviews").click(function() {
        displayPage("reviews");
    });
    
    $("#menu-excuses").click(function() {
        displayPage("excuses");
    });
    
    $("#menu-dashboard").click(function() {
        displayScreenshot("dashboard");
    });
    
    $("#menu-match-settings").click(function() {
        displayScreenshot("match-settings");
    });
    
    $("#menu-score").click(function() {
        displayScreenshot("score");
    });
    
    $("#menu-commentator").click(function() {
        displayScreenshot("commentator");
    });
    
    $("#menu-excuses-screenshot").click(function() {
        displayScreenshot("excuses-screenshot");
    });
    
    displayPage("about");
   
});


