/*
 * jQuery Color Animations
 * Copyright 2007 John Resig
 * Released under the MIT and GPL licenses.
 */
!function (r) {
    function e(e) {
        var a;
        return e && e.constructor == Array && 3 == e.length ? e : (a = /rgb\(\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*,\s*([0-9]{1,3})\s*\)/.exec(e)) ? [parseInt(a[1]), parseInt(a[2]), parseInt(a[3])] : (a = /rgb\(\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*,\s*([0-9]+(?:\.[0-9]+)?)\%\s*\)/.exec(e)) ? [2.55 * parseFloat(a[1]), 2.55 * parseFloat(a[2]), 2.55 * parseFloat(a[3])] : (a = /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})/.exec(e)) ? [parseInt(a[1], 16), parseInt(a[2], 16), parseInt(a[3], 16)] : (a = /#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])/.exec(e)) ? [parseInt(a[1] + a[1], 16), parseInt(a[2] + a[2], 16), parseInt(a[3] + a[3], 16)] : t[r.trim(e).toLowerCase()]
    }

    function a(a, t) {
        var o;
        do {
            if (o = r.curCSS(a, t), "" != o && "transparent" != o || r.nodeName(a, "body")) break;
            t = "backgroundColor"
        } while (a = a.parentNode);
        return e(o)
    }

    r.each(["backgroundColor", "borderBottomColor", "borderLeftColor", "borderRightColor", "borderTopColor", "color", "outlineColor"], function (t, o) {
        r.fx.step[o] = function (r) {
            0 == r.state && (r.start = a(r.elem, o), r.end = e(r.end)), r.elem.style[o] = "rgb(" + [Math.max(Math.min(parseInt(r.pos * (r.end[0] - r.start[0]) + r.start[0]), 255), 0), Math.max(Math.min(parseInt(r.pos * (r.end[1] - r.start[1]) + r.start[1]), 255), 0), Math.max(Math.min(parseInt(r.pos * (r.end[2] - r.start[2]) + r.start[2]), 255), 0)].join(",") + ")"
        }
    });
    var t = {
        aqua: [0, 255, 255],
        azure: [240, 255, 255],
        beige: [245, 245, 220],
        black: [0, 0, 0],
        blue: [0, 0, 255],
        brown: [165, 42, 42],
        cyan: [0, 255, 255],
        darkblue: [0, 0, 139],
        darkcyan: [0, 139, 139],
        darkgrey: [169, 169, 169],
        darkgreen: [0, 100, 0],
        darkkhaki: [189, 183, 107],
        darkmagenta: [139, 0, 139],
        darkolivegreen: [85, 107, 47],
        darkorange: [255, 140, 0],
        darkorchid: [153, 50, 204],
        darkred: [139, 0, 0],
        darksalmon: [233, 150, 122],
        darkviolet: [148, 0, 211],
        fuchsia: [255, 0, 255],
        gold: [255, 215, 0],
        green: [0, 128, 0],
        indigo: [75, 0, 130],
        khaki: [240, 230, 140],
        lightblue: [173, 216, 230],
        lightcyan: [224, 255, 255],
        lightgreen: [144, 238, 144],
        lightgrey: [211, 211, 211],
        lightpink: [255, 182, 193],
        lightyellow: [255, 255, 224],
        lime: [0, 255, 0],
        magenta: [255, 0, 255],
        maroon: [128, 0, 0],
        navy: [0, 0, 128],
        olive: [128, 128, 0],
        orange: [255, 165, 0],
        pink: [255, 192, 203],
        purple: [128, 0, 128],
        violet: [128, 0, 128],
        red: [255, 0, 0],
        silver: [192, 192, 192],
        white: [255, 255, 255],
        yellow: [255, 255, 0]
    }
}(jQuery);