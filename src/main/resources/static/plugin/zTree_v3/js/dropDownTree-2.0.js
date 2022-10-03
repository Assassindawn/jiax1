;(function($, window, document, undefined){
	/**
	 * 初始化树形结构
	 * @param url
	 * @param idName：需要赋值的input的id名称，为隐藏域
	 * @constructor
	 */
	$.fn.dropDownTree = function(url, idName){
		var ulId = 'ul' + idName;
		var divId = 'div' + idName;
		var thisobj = this;
		var treeDiv = createzTree(this, ulId, divId);
		$(thisobj).after(treeDiv);
		$(thisobj).attr("readonly", "readonly");
		$(thisobj).css('background', '#fff');
		initTree(url, null, $('#' + ulId), function(event, treeId, treeNode){
			$(thisobj).val(treeNode.name);
			// 如果通过id找不着元素，则使用调用 dropDownTree函数的元素兄弟节点
			if($('#' + idName)[0]){
				$('#' + idName).val(treeNode.id);
			}else{
				$(thisobj).siblings('[name="' + idName + '"]').val(treeNode.id);
			}
			hideMenu($("#" + treeId).parent().attr("id"));
		});
		$(thisobj).click(function(){
			$("body").on("mousedown.dropDownTree", function(event){
				onBodyDown(event, divId);
			});
			treeDiv.toggle();
		});
	};
	
	/**
	 * 隐藏菜单
	 * @param id
	 * @returns
	 */
	function hideMenu(id){
		$("#" + id).hide();
		$("body").off("mousedown.dropDownTree");
	}
	
	/**
	 * 鼠标按下事件
	 * @param event
	 * @param ztreeid
	 * @returns
	 */
	function onBodyDown(event, ztreeid){
		if(!(event.target.id == ztreeid || $(event.target).parents("#" + ztreeid).length > 0)){
			hideMenu(ztreeid);
		}
	}
	
	/**
	 * zTree相关操作
	 * @param url
	 * @param data
	 * @param $zTree
	 * @param onclickNode
	 */
	function initTree(url, data, $zTree, onclickNode){
		$.fn.zTree.init($zTree, {
			async: {
				autoParam: ["id"],
				enable: true,
				type: "post",
				url: function(treeId, treeNode){
					if(treeNode == undefined){
						return url;
					}else if(treeNode.isParent == true){
						return url.substring(0, url.length - 6) + treeNode.id + "&selectChildren=true";
					}else{
						return "";
					}
				}
			},
			data: {
				simpleData: {
					enable: true
				},
				keep: {
					parent: true
				}
			},
			callback: {
				onClick: onclickNode
			}
		});
	}
	
	/**
	 * 创建zTree的DOM对象
	 * @param context: 调用 dropDownTree函数的元素对象
	 * @param ulid: ul元素ID
	 * @param divid: 最外层元素ID
	 * @returns {*|jQuery|HTMLElement}
	 */
	function createzTree(context, ulid, divid){
		var $treeDiv = $('<div id="' + divid + '"></div>');
		var $zTree = $('<ul id="' + ulid + '" class="ztree" style="padding: 5px;"></ul>');
		var dwidth = $(context).css('width');
		$treeDiv.css({
			"width": dwidth, "height": "200px", "position": "absolute",
			"display": "none", "z-index": "10",
			"overflow": "auto",
			"background": "#fff",
			"border": "1px solid #ccc"
		});
		$treeDiv.append($zTree);
		return $treeDiv;
	}
})(jQuery, window, document);
