//------ Keep track of whether the navigation is fixed in place.
var SNAPPED = false;
var SCROLLELEMENT = 'html, body';
var SCROLLPADDING = 0;
function scrollToElement(el) {
    $(SCROLLELEMENT).stop().animate({
        scrollTop: el.offset().top - SCROLLPADDING
    }, 600);
}

//In Page Scroll, Portfolio filter
$(document).ready(function($) {
    //prettyphoto
    $("a[class^='prettyPhoto']").prettyPhoto({
        social_tools: false
    });

    //Superfish menu
    $("ul.sf-menu").supersubs({
        minWidth: 12, // minimum width of sub-menus in em units
        maxWidth: 50, // maximum width of sub-menus in em units
        extraWidth: 3 // extra width can ensure lines don't sometimes turn over
                // due to slight rounding differences and font-family
    }).superfish();
    // call supersubs first, then superfish, so that subs are
    // not display:none when measuring. Call before initialising
    // containing tabs for same reason.

    //flex slider
    $('.flexslider_small').flexslider({
        directionNav: false,
        controlNav: true,
        slideshow: true,
    });

    //Tooltips
    function showSocialToolTip(e, img) {
        var path = "images/" + img;
        $('#tooltip').css({
            'top': e.pageY + 10,
            'left': e.pageX - 110,
            'background': 'none'
        });
        $('#tooltip').html('<img src="' + path + '" alt="tooltip">');
        $('#tooltip').show();
    }

    function hideSocialToolTip() {
        $('#tooltip').hide();
    }

    function moveSocialToolTip(e) {
        $('#tooltip').show();
        $('#tooltip').css({
            'top': e.pageY + 10,
            'left': e.pageX - 110
        });
    }


    $('.facebook').on('mouseenter', function(e) {
        $(this).find('img').attr('src', 'images/facebook_roll.png');
        showSocialToolTip(e, $(this).attr('data-tooltip'));
    });

    $('.facebook').on('mouseleave', function(e) {
        $(this).find('img').attr('src', 'images/facebook.png');
        hideSocialToolTip();
    });

    $('.facebook').on('mousemove', function(e) {
        moveSocialToolTip(e);
    });

    $('.twitter').on('mouseenter', function(e) {
        $(this).find('img').attr('src', 'images/twitter_roll.png');
        showSocialToolTip(e, $(this).attr('data-tooltip'));
    });

    $('.twitter').on('mouseleave', function(e) {
        $(this).find('img').attr('src', 'images/twitter.png');
        hideSocialToolTip();
    });

    $('.twitter').on('mousemove', function(e) {
        moveSocialToolTip(e);
    });

    $('.flickr').on('mouseenter', function(e) {
        $(this).find('img').attr('src', 'images/flickr_roll.png');
        showSocialToolTip(e, $(this).attr('data-tooltip'));
    });

    $('.flickr').on('mouseleave', function(e) {
        $(this).find('img').attr('src', 'images/flickr.png');
        hideSocialToolTip();
    });

    $('.flickr').on('mousemove', function(e) {
        moveSocialToolTip(e);
    });

    //address change
    $.address.change(function(event) {
        //------ Retrieve element at first path component.
        //------ Scroll to point.
        if (event.pathNames[0]) {
            //------ Other points.
            scrollToElement($("#" + event.pathNames[0]));
            $.address.title("Metrofy - " + event.pathNames[0].toUpperCase());
        }
    });
    //-------Portfolio filter function
    $('ul#portfolio-nav a').click(function() {
        $(this).css('outline', 'none');
        $('ul#portfolio-nav .current').removeClass('current');
        $(this).parent().addClass('current');

        var filterVal = $(this).text().toLowerCase().replace(/ /g, '-');

        if (filterVal == 'all') {
            $('ul#portfolio li.hidden').fadeIn('slow').removeClass('hidden');
        } else {
            $('ul#portfolio li').each(function() {
                if (!$(this).hasClass(filterVal)) {
                    $(this).fadeOut('normal').addClass('hidden');
                } else {
                    $(this).fadeIn('slow').removeClass('hidden');
                }
            });
        }

        return false;
    });
    //------ Setup in-page scrolling for .internal links.
    $('.internal').click(function(event) {
        event.preventDefault();
        //------ Retrieve subject, trading on the fact that selector & named anchor syntax both use leading # symbols.
        var target = $(this.hash);
        if (target.length && this.hash) {
            //------ Allow the user to scroll and still make use of the navigation...
            var before = $.address.value();
            $.address.value(target.attr('id'));
            //------ ...Manually invoke if there is no change event.
            if (before == $.address.value())
                scrollToElement(target);
        } else {
            $.address.value($(this).attr('href'));
        }

        //Set class to 'active'
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
        } else {
            $(this).parents().siblings().find('a').removeClass('active');
            $(this).addClass('active');
        }
        return false;
    });
    //------ Listen on scroll event to modify the navigation's position as needed.
    var nav = $('#nav');
    var the_window = $(window);
    var topmost_point = nav.offset().top;
    //------ Capture the height of the navigation bar.
    SCROLLPADDING = nav.height() + $('#topheader').height();
    //------ Sniff for whether to use html or body element for scrolling.
    $('html, body').each(function() {
        var initScrollTop = $(this).attr('scrollTop');
        $(this).attr('scrollTop', initScrollTop + 1);
        if ($(this).attr('scrollTop') == initScrollTop + 1) {
            SCROLLELEMENT = this.nodeName.toLowerCase();
            $(this).attr('scrollTop', initScrollTop);
            return false;
        }
    });
    //Tiles hover animation
    $('.tile').each(function() {
        var $span = $(this).children('span');
        $span.css('bottom', "-" + $span.outerHeight() + 'px');
    });

    var bottom = 0;

    $('.tile').hover(function() {
        var $span = $(this).children('span');
        if (!$span.data('bottom'))
            $span.data('bottom', $span.css('bottom'));
        $span.stop().animate({
            'bottom': 0
        }, 250);
    }, function() {
        var $span = $(this).children('span');
        $span.stop().animate({
            'bottom': $span.data('bottom')
        }, 250);
    });
});

$(document).ready(function() {
    $(".tweet").tweet({
        username: "themetroguy",
        count: 3,
        loading_text: "loading tweets..."
    });
});

$(document).ready(function() {
    $('div.flickr_feed.').flickrush({
        limit: 6,
        id: '64941093@N06',
        random: true
    });
});

$(document).ready(function() {

    // Create the dropdown bases
    $("<select />").appendTo(".navigation nav");

    // Create default option "Go to..."
    $("<option />", {
        "selected": "selected",
        "value": "",
        "text": "Go to..."
    }).appendTo("nav select");

    // Populate dropdowns with the first menu items
    $(".navigation nav li a").each(function() {
        var el = $(this);
        $("<option />", {
            "value": el.attr("href"),
            "text": el.text()
        }).appendTo("nav select");
    });

    //make responsive dropdown menu actually work
    $("nav select").change(function() {
        window.location = $(this).find("option:selected").val();
    });

});

$(document).ready(function() {
    //$("#navLinks").load("menu.html");
    $("#navLinks").html(
            '<li>' +
            '<a href="index.html">Home</a>' +
            '</li>' +
            '<li>' +
            '<a href="submission.html">Submission</a>' +
            '</li>' +
            '<li>' +
            '<a href="sponsorship.html">Sponsor & Host</a>' +
            '</li>' +
            '<li>' +
            '<a href="committees.html">Committees</a>' +
            '</li>'
            );
});
