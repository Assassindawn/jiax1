;(function($, window, document,undefined) {

    var $zTree,ztreeid;
    /**
     * 树初始化方法
     * @param url 请求ztree数据的url
     * @param idName 保存选中选项id的Html标签
     * @returns
     */
    $.fn.InitddTree = function(url,idName,ulId,divId){
        var treeDiv=createzTree(ulId,divId);
        ztreeid = divId;
        $('.DropDownTree').after(treeDiv);
        $('.DropDownTree').attr("readonly", "readonly");
        initTree(url, null, $zTree,function(event, treeId, treeNode) {
            $('.DropDownTree').val(treeNode.name);
            $('#'+idName).val(treeNode.id);
            hideMenu($("#"+treeId).parent().attr("id"));
        });
        $('.DropDownTree').click(function () {
            $("body").bind("mousedown", onBodyDown);
            treeDiv.toggle();
        });
    }

    //解决一个页面多个树的问题 lhy 20210604
    $.fn.InitddTreeM = function(url,idName,idNameM,ulId,divId,fun){
        var treeDiv=createzTreeM(idNameM,ulId,divId);
        ztreeid = divId;
        $('#'+idNameM+"").after(treeDiv);
        $('#'+idNameM+"").attr("readonly", "readonly");
        initTree(url, null, $zTree,function(event, treeId, treeNode) {
            eval(fun);
            $('#'+idNameM+"").val(treeNode.name);
            $('#'+idName).val(treeNode.id);
            hideMenu($("#"+treeId).parent().attr("id"));
        });
        $('#'+idNameM+"").click(function () {
            $("body").bind("mousedown", onBodyDown);
            treeDiv.toggle();
        });
    }

    $.fn.InitddTreeNew = function(url,idName,idNameM,ulId,divId,fun){
        var treeDiv=createzTreeM(idNameM,ulId,divId);
        ztreeid = divId;
        $('#'+idNameM+"").after(treeDiv);
        $('#'+idNameM+"").attr("readonly", "readonly");
        initTree(url, null, $zTree,function(event, treeId, treeNode) {
            $('#'+idNameM+"").val(treeNode.name);
            $('#'+idName).val(treeNode.id);
            hideMenu($("#"+treeId).parent().attr("id"));
            eval(fun);
        });
        $('#'+idNameM+"").click(function () {
            $("body").bind("mousedown", onBodyDown);
            treeDiv.toggle();
        });
    }

    //带回调方法 liuhy
    $.fn.InitddTreeAndFun = function(url,idName,ulId,divId,fun){
        var treeDiv=createzTree(ulId,divId);
        ztreeid = divId;
        $('.DropDownTree').after(treeDiv);
        $('.DropDownTree').attr("readonly", "readonly");
        initTree(url, null, $zTree,function(event, treeId, treeNode) {
        	eval(fun);//带回调方法 liuhy
            $('.DropDownTree').val(treeNode.name);
            $('#'+idName).val(treeNode.id);
            hideMenu($("#"+treeId).parent().attr("id"));

        });
        $('.DropDownTree').click(function () {
            $("body").bind("mousedown", onBodyDown);
            treeDiv.toggle();
        });
    }

    $.fn.InitddTree1 = function(url,idName,ulId,divId,dwdith){
        var treeDiv=createzTreeWidth(ulId,divId,dwdith);
        //var treeDiv=createzTree(ulId,divId);
        ztreeid = divId;
        $('.DropDownTree1').after(treeDiv);
        $('.DropDownTree1').attr("readonly", "readonly");
        initTree(url, null, $zTree,function(event, treeId, treeNode) {
            $('.DropDownTree1').val(treeNode.name);
            $('#'+idName).val(treeNode.id);
            hideMenu($("#"+treeId).parent().attr("id"));
        });
        $('.DropDownTree1').click(function () {
            $("body").bind("mousedown", onBodyDown);
            treeDiv.toggle();
        });
    }

    /**
     * 隐藏菜单
     * @param id
     * @returns
     */
    function hideMenu(id) {
        $("#"+id).hide();
        $("body").unbind("mousedown", onBodyDown);
    }

    /**
     * 鼠标按下事件
     * @param event
     * @returns
     */
    function onBodyDown(event) {
        if (!( event.target.id == ztreeid || $(event.target).parents("#"+ztreeid).length > 0)) {
            hideMenu(ztreeid);
        }
    }

    //zTree相关操作
    var initTree = function (url, data, $zTree,onclickNode/*,zTreeOnClick*/) {
        //数据定义
        var setting = {
            async:{
                autoParam:["id"],
                enable:true,
                type:"post",
                url:function (treeId, treeNode) {
                    if(treeNode==undefined){
                        return url;
                    }else if(treeNode.isParent==true){
                        return url.substring(0,url.length-6)+treeNode.id+"&&selectChildren=true";
                    }else{
                        return "";
                    }

                }
            },
            data: {
                simpleData :{
                    enable:true
                },
                keep: {
                    parent: true
                }
            },
            callback: {
                onClick: onclickNode   //点击节点事件
            }

        }
        var zTree=$.fn.zTree.init($zTree, setting);
    }


    //创建zTree的DOM对象
    function createzTree(ulid,divid){
        $zTree=$('<ul id="'+ulid+'" class="ztree" style="padding: 5px;"></ul>');
        var $treeDiv=$('<div id="'+divid +'"></div>');
        var dwidth=$('.DropDownTree').css('width');
        $treeDiv.css({
            "width": dwidth, "height": "200px", "position": "absolute",
            "display": "none", "z-index": "10",
            "overflow": "auto",
            "background": "#fff",
            "border":"1px solid #ccc"
        });
        $treeDiv.append($zTree);
        return $treeDiv;
    }

    //解决一个页面多个树的问题 lhy 20210604
    function createzTreeM(idName,ulid,divid){
        $zTree=$('<ul id="'+ulid+'" class="ztree" style="padding: 5px;"></ul>');
        var $treeDiv=$('<div id="'+divid +'"></div>');
        var dwidth=$('#'+idName+"").css('width');
        $treeDiv.css({
            "width": dwidth, "height": "200px", "position": "absolute",
            "display": "none", "z-index": "10",
            "overflow": "auto",
            "background": "#fff",
            "border":"1px solid #ccc"
        });
        $treeDiv.append($zTree);
        return $treeDiv;
    }

    function createzTreeWidth(ulid,divid,wdith){
        $zTree=$('<ul id="'+ulid+'" class="ztree" style="padding: 5px;"></ul>');
        var $treeDiv=$('<div id="'+divid +'"></div>');
        var dwidth=$('.DropDownTree').css(wdith);
        $treeDiv.css({
            "width": dwidth, "height": "200px", "position": "absolute",
            "display": "none", "z-index": "10",
            "overflow": "auto",
            "background": "#fff",
            "border":"1px solid #ccc"
        });
        $treeDiv.append($zTree);
        return $treeDiv;
    }
})(jQuery, window, document);
