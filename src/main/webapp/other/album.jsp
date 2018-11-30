<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "专辑详情",
        handler: function () {
            alert(0)

            $('#album').dialog("open")
            var row = $("#tg").treegrid("getSelected")
            alert(row)
            if (row != null) {

                if (row.score != null) {
                    $("#form").form("load", row);
                    $("#album_img").prop("src", "${pageContext.request.contextPath}/shouye/pricture" + row.coverlmg)

                } else {
                    alert("请选择一个专辑")
                }
            } else {
                alert("请选择一个专辑")
            }
        }
    }, '-', {
        iconCls: 'icon-help',
        text: "添加专辑",
        handler: function () {
            $("#div_add").dialog("open")
        }
    }, '-', {
        text: "添加章节",
        iconCls: 'icon-help',
        handler: function () {
            var row = $("#tg").treegrid("getSelected");
            if (row == null) {
                alert("请先选中专辑")
            } else {
                if (row.score != null) {

                    $('#c_dd').dialog("open")
                    $("#p_id").val(row.id)

                } else {
                    alert("请先选中专辑")
                }
            }

        }
    }, '-', {
        text: "下载音频",
        iconCls: 'icon-help',
        handler: function () {
            var row = $("#tg").treegrid("getSelected");
            if (row != null) {
                if (row.size != null) {
                    location.href = "${pageContext.request.contextPath}/download?downPath=" + row.downPath + "&title=" + row.title
                }
            }
        }
    }];
    $(function () {
        $("#tg").treegrid({
            url: "${pageContext.request.contextPath}/selectBypage",
            onDblClickRow: function (row) {
                $("#audio").dialog("open")
                $("#audio_id").prop("src", "${pageContext.request.contextPath}/shouye/music/" + row.downPath)
            },
            toolbar: toolbar,
            pagination: true,
            idField: "id",
            treeField: "title",
            fit: true,
            fitColumns: true,
            columns: [[
                {field: 'title', title: '名称', width: 180},
                {field: 'downPath', title: '下载路径', width: 60, align: 'right'},
                {field: 'size', title: '章节大小', width: 80},
                {field: 'duration', title: '章节时长', width: 80}
            ]]
        });

        $("#album").dialog({
            title: '专辑详情',
            width: 500,
            height: 400,
            closed: true,
        });
        $('#audio').dialog({
            title: '播放',
            width: 400,
            height: 200,
            closed: true,
        });
        $("#div_add").dialog({
            title: '添加专辑',
            width: 500,
            height: 400,
            closed: true,
        })

        $('#c_dd').dialog({
            title: '添加章节',
            width: 400,
            height: 200,
            closed: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    addChapter();
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#c_dd").dialog("close")
                }
            }],
        });
    })

    function button_add() {
        $("#form_add").form("submit", {

            url: "${pageContext.request.contextPath}/insertAlbum",
            success: function (data) {
                if (data) {
                    alert("添加成功");
                    $("#div_add").dialog("close");
                    $("#tg").treegrid("reload");
                } else {
                    alert("添加失败");
                }

            }
        })
    }

    function addChapter() {
        alert(0)
        $("#c_ff").form("submit", {

            url: "${pageContext.request.contextPath}/insertChapter",
            success: function (data) {

                if (data) {

                    alert("添加成功++++++++++++++++++");
                    $("#c_dd").dialog("close");
                    $("#tg").treegrid("reload");
                } else {
                    alert("添加失败");
                }

            }
        })
    }
</script>

<table id="tg"></table>
<div id="album">
    <form id="form">
        <div>
            专辑标题<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            专辑作者<input class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            专辑简介<input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <div>
            专辑封面<img src="" id="album_img">
        </div>
    </form>
</div>

<div id="div_add">
    <form id="form_add" enctype="multipart/form-data" method="post">
        <div>
            专辑标题<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            专辑封面<input type="file" name="ifile" data-options="required:true"/>
        </div>
        <div>
            章节数量<input type="text" name="count" data-options="required:true"/>
        </div>
        <div>
            专辑评分<input class="easyui-validatebox" type="text" name="score" data-options="required:true"/>
        </div>
        <div>
            专辑作者<input class="easyui-validatebox" type="text" name="author" data-options="required:true"/>
        </div>
        <div>
            播音<input class="easyui-validatebox" type="text" name="broadCast" data-options="required:true"/>
        </div>
        <div>
            专辑简介<input class="easyui-validatebox" type="text" name="brief" data-options="required:true"/>
        </div>
        <div>
            <input class="easyui-validatebox" type="button" value="保存" onclick="button_add()"/>
        </div>
    </form>

</div>

<div id="c_dd">

    <form id="c_ff" method="post" enctype="multipart/form-data">
        <div>
            标题:<input class="easyui-validatebox" type="text" name="title" data-options="required:true"/>
        </div>
        <div>
            请选择:<input type="file" name="chapter1">
        </div>
        <div>
            <input type="hidden" name="aid" id="p_id">
        </div>

    </form>
</div>

<div id="audio">
    <audio id="audio_id" src="" controls="controls" loop="loop"></audio>
</div>