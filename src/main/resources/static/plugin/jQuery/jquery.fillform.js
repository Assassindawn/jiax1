/*
 * jQuery Auto Fill Form Plugin
 * @requires jQuery v1.1 or later
 * @author Andy
 */
(function($) {
    /**
     * $.autofillform() provides a mechanism for fill form automatically, no more script or code, very convenient.
     *
     * $.autofillform() accepts a single option object argument, the following attributes are supported:
     *
     *  fillformobj:  Identifies the data to fill form.
     */
    $.autofillform = function(options) {
        var settings = {};
        options = $.extend(settings, options);

        var fillforms = options.fillformobj?options.fillformobj:new Object();
        var fillgrids = options.fillgridstr?options.fillgridstr:new Object();

        for( var f in fillforms ) {
            $("#"+f).fillform(fillforms[f]);
        }
        for( var f in fillgrids ) {
            $("#"+f).fillgrid(fillgrids[f]);
        }
    };

    $.fn.fillform = function(fillData) {
        this.each(function(i){
            var frm = $(this);
            for( var fi in fillData ) {
                $("#"+fi, frm).each(function(i){
                    var el=this, tag = el.tagName.toLowerCase(), v=fillData[fi];

                    var t = el.type, val = el.value;
                    switch( tag ) {
                        case "input":
                            switch(t) {
                                case "text":
                                case "hidden":
                                    $(el).attr("value", v);
                                    break;
                                case "checkbox":
                                    if( v == val )
                                        el.checked = true;
                                    break;
                                case "radio":
                                    $("input[@type=radio]", frm).each(function() {
                                        if((this.id == fi || this.name == fi) && v == this.value && !this.checked)
                                            this.checked = true;
                                    });
                            }
                            break;
                        case "textarea":
                            $(el).attr("value", v);
                            var t=el.value;
                            while(t.indexOf("<br/>")!=-1){
                                t=t.replace("<br/>","\r\n");
                            }
                            el.value=t;
                            break;
                        case "select":
                            switch(t) {
                                case "select-one":
                                    $(el).attr("value", v);
                                    break;
                                case "select-multiple":
                                    var ops = el.options;
                                    var sv = v.split(",");
                                    for(var i= 0; i < ops.length; i++) {
                                        var op = ops[i];
                                        // extra pain for IE...
                                        var opv = $.browser.msie && !(op.attributes['value'].specified) ? op.text : op.value;
                                        for(var j=0; j < sv.length; j++) {
                                            if (opv == sv[j]) {
                                                op.selected = true;
                                            }
                                        }
                                    }
                            }
                            break;
                        default:
                            $(el).text(v);
                    }
                });
            }
        });
    }

    $.fn.fillgrid = function(d) {
        this.each(function(i){
            var t=this.tagName.toLowerCase(),e = $(this);
            switch(t) {
                case "table":
                    var b = $("tbody",e), r = e.attr("cgridtpl"), rs = new Array();
                    if(!r) {
                        r = $("tr",b);
                        r.remove();
                        e.attr("cgridtpl", r);
                    }
                    for(var j=0;j<d.length;j++) {
                        var fd = d[j], cr = r.clone();
                        cr.attr("data", fd);
                        b.append(cr);
                        $("td",cr).each(function(i){
                            var tt=this;
                            if(this.dataIndex){
                                $(this).text(fd[this.dataIndex]);
                            }
                            else {
                                var i=0;
                                $("div",$(this)).each(function(i){
                                    var pd=$(this);
                                    var stateString=fd[tt.photoIndex];
                                    var state;
                                    if(i<=stateString.length){
                                        state=stateString.charAt(i);
                                        if('0'==state){
                                            pd.addClass("redState");
                                        }else if('1'==state){
                                            pd.addClass("greenState");
                                        }else if('2'==state){
                                            pd.addClass("yellowState");
                                        }
                                    }
                                    i=i+1;
                                });
                            }

                        });
                        cr.show();
                        $("input,textarea,select",cr).each(function(){
                            $(this).attr("ignore",true);
                        });
                        rs.push(cr);
                    }
                    e.attr("cgriddata", d);
                    e.attr("cgridrows", rs);
                    break;
                case "div":
                    var g = e.attr("egrid");
                    if(g)
                        g.loadData(d);
                    else
                        e.attr("egdata", d);
            }
        });
    }
})(jQuery);