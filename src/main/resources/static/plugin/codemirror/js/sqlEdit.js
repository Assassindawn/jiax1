//根据DOM元素的id构造出一个编辑器
var editor=CodeMirror.fromTextArea(document.getElementById("code"),{
    mode:"text/c-mysql", //实现Java代码高亮
    lineNumbers:true,
    theme:"default",
    keyMap: "default",
    extraKeys:{"Tab":"autocomplete"},
    hint: CodeMirror.hint.sql,
    lineWrapping: true,         //是否换行
    foldGutter: true,           //是否折叠
    gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"] //添加行号栏，折叠栏
});

//SQL编辑框赋值
//editor.setValue("");
//setValue2CM(editor, "select *  from aa  where b = '{#tcbh#}' and c = '{$getAab301$}' ");

//将一整段文字（如SQL）加载到codemirror中 如果其中包含参数 参数就以块状显示
function setValue2CM(editor, cmcontent,tableParam,tableSession){
    var i = 0;
    var buffer = "";
    //当flag为0 buffer中的值是普通字符  flag 为1 buffer中的值是参数ID
    var flag = 0;
    while(i < cmcontent.length){
        if((cmcontent[i]+cmcontent[i+1]) == "{#"){
            i = i+2;
            appendValue2CM(editor, buffer, flag,tableParam,tableSession);
            buffer = "";
            flag = 1;
        }else if((cmcontent[i]+cmcontent[i+1]) == "#}"){
            i = i+2;
            appendValue2CM(editor, buffer, flag,tableParam,tableSession);
            buffer = "";
            flag = 0;
        }else if((cmcontent[i]+cmcontent[i+1]) == "{$"){
            i = i+2;
            appendValue2CMsession(editor, buffer, flag,tableParam,tableSession);
            buffer = "";
            flag = 1;
        }else if((cmcontent[i]+cmcontent[i+1]) == "$}"){
            i = i+2;
            appendValue2CMsession(editor, buffer, flag,tableParam,tableSession);
            buffer = "";
            flag = 0;    
        }else{
            buffer += cmcontent[i++];
        }
    }
    appendValue2CM(editor, buffer, 0,tableParam,tableSession);
}


//将value附加到codemirror中
function appendValue2CM(editor, value, mark,tableParam,tableSession){
    var lastCursor = editor.getCursor({line: editor.lineCount(), ch: 99999});
    if(mark==1){
        editor.replaceRange("{#"+value+"#}", lastCursor, lastCursor);        
        var paramterName = ""; 
    	for(var i = 0; i<tableParam.length; i++){
    		if( tableParam[i].colid == value){
    			paramterName = tableParam[i].colname;
    			break;
    		}
    	}
        if(paramterName==undefined){
            //如果替换失败 则不替换
            editor.replaceRange(value, lastCursor, lastCursor);
        }else{
            var dom = $("<span class='divEditorBtn'>"+paramterName+"</span>").get(0);
            var endCursor = editor.getCursor({line: editor.lineCount(), ch: 99999});
            editor.markText(lastCursor, endCursor,{
                replacedWith: dom,
                className: "divEditorBtn"
            });
        }
    }else{
        editor.replaceRange(value, lastCursor, lastCursor);
    }
}

//将value附加到codemirror中 会话中的值
function appendValue2CMsession(editor, value, mark,tableParam,tableSession){
    var lastCursor = editor.getCursor({line: editor.lineCount(), ch: 99999});
    if(mark==1){
        editor.replaceRange("{$"+value+"$}", lastCursor, lastCursor);
        var paramterName = "";
        
    	for(var i=1; i<tableSession.length; i++){
    		if( tableSession[i].sessionValue == value){
    			paramterName = tableSession[i].sessionText;
    			break;
    		}
    	}    	
        if(paramterName==undefined){
            //如果替换失败 则不替换
            editor.replaceRange(value, lastCursor, lastCursor);
        }else{
            var dom = $("<span class='divEditorSessionBtn'>"+paramterName+"</span>").get(0);
            var endCursor = editor.getCursor({line: editor.lineCount(), ch: 99999});
            editor.markText(lastCursor, endCursor,{
                replacedWith: dom,
                className: "divEditorSessionBtn"
            });
        }
    }else{
        editor.replaceRange(value, lastCursor, lastCursor);
    }
}

editor.setSize('height','600px');

//刷新SQL编辑器
var tmp = function() {
    editor.refresh();
};
setTimeout(tmp, 100);
 