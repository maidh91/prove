// Modernizr.load loading the right scripts only if you need them
Modernizr.load([
    {
        // Let's see if we need to load selectivizr
        test: Modernizr.borderradius,
        // Modernizr.load loads selectivizr and Respond.js for IE6-8
        nope: ['libs/selectivizr-min.js']
    }
]);

/* jQuery Delay - A delay function * Copyright (c) 2009 Clint Helfers - chelfers(at)gmail(dot)com | http://blindsignals.com * Dual licensed under MIT and GPL. * Date: 7/01/2009 * @author Clint Helfers * @version 1.0.0 * http://blindsignals.com/index.php/2009/07/jquery-delay/ */
$.fn.delay = function(time, name) {
    return this.queue((name || "fx"), function() {
        var self = this;
        setTimeout(function() {
            $.dequeue(self);
        }, time);
    });
};

