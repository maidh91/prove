$(window).load(function() {
    initMain();
});


function getGridBlockHover(hoverElement) {
    $(hoverElement).each(function() {
        var imageWidth = $(this).find('img').width(),
                itemWidth = $(this).width(),
                itemHeight = $(this).height(),
                imageHeight = $(this).find('img').height();
        /*$(this).width(imageWidth);*/
        if (imageHeight < itemHeight) {
            $(this).find('img').parent().css({
                'overflow': 'hidden',
                'display': 'block',
                'width': itemWidth + 'px',
                'height': '100%'
            });
            $(this).find('img').css({
                'height': '100%',
                'width': 'auto',
                'display': 'block'
            });
        } else {
            $(this).find('img').parent().css({
                'overflow': 'hidden',
                'display': 'block',
                'height': itemHeight + 'px'
            });
            $(this).find('img').css({
                'height': 'auto',
                'width': '100%',
                'display': 'block'
            });
        }
    });

    $(hoverElement).hover(hoverOver, hoverOut);

    function hoverOver(event) {
        if (!$(this).hasClass('active')) {
            $('.text', this).css({'visibility': 'hidden'});
            var textHeight = $('.text', this).css({'height': 'auto'}).outerHeight();
            $('.text', this).filter(':not(:animated)').css({'visibility': 'visible'});
            var negative_textHeight = '-' + textHeight + 'px';
            $(this).css('float', 'left');
            $('h4', this).filter(':not(:animated)').animate({bottom: textHeight}, 'normal', 'easeOutQuad');
            $('.text', this).filter(':not(:animated)').css({
                height: 0
            }).animate({
                height: textHeight
            }, 'normal', 'easeOutQuad');
            $('img', this).filter(':not(:animated)').animate({
                'margin-top': '-10px'
            }, 'normal', 'easeOutQuad');
        }
        return false;
    }

    function hoverOut(event) {
        if (!$(this).hasClass('active')) {
            var textHeight = $('.text', this).outerHeight() * -1;
            $('h4', this).animate({bottom: 0}, 'fast', 'easeOutQuad');
            $('.text', this).animate({height: 0}, 'fast', 'easeOutQuad', function() {
            });
            $('img', this).animate({
                'margin-top': '0'
            }, 'fast', 'easeOutQuad');
        }
        return false;
    }
}



function initMain() {

    if ($('#portfolio').get(0)) {
        getGridBlockHover('ul#portfolio li .inner-item');
    }

}
