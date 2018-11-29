<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<script type="text/javascript">
    var toolbar = [{
        iconCls: 'icon-edit',
        text: "添加",
        handler: function () {
            $("#dd").dialog("open");
        }
    }, '-', {
        iconCls: 'icon-help',
        text: "删除",
        handler: function () {
            var row = $("#dg").edatagrid("getSelected")
            if (row == null) {
                $.messager.alert('提示', '请选', 'info');
            } else {
                $.ajax({
                    url: "${pageContext.request.contextPath}/delete",
                    data: "id=" + row.id,
                    success: function (data) {
                        if (data) {
                            $.messager.alert('成功', '删除成功', 'right');
                            $("#dg").edatagrid("reload");
                        } else {
                            $.messager.alert('失败', '删除失败', 'error');
                        }
                    }
                })
            }
        }


    }, '-', {
        text: "修改",
        iconCls: 'icon-help',
        handler: function () {
            /*获取选中行*/
            var row = $("#dg").edatagrid("getSelected");
            alert(row)
            if (row == null) {
                $.messager.show({
                    title: '警告',
                    msg: '请选中修改行。',
                    showType: 'show',
                    style: {
                        right: '',
                        top: document.body.scrollTop + document.documentElement.scrollTop,
                        bottom: ''
                    }
                });
            } else {

                /*将当前行变成可编辑模式*/
                var index = $("#dg").edatagrid("getRowIndex", row);
                $("#dg").edatagrid("editRow", index);
            }

        }
    }, '-', {
        text: "保存",
        iconCls: 'icon-help',
        handler: function () {
            $("#dg").edatagrid("saveRow");
        }
    }];

    $(function () {
        $("#dg").edatagrid({
            url: "${pageContext.request.contextPath}/selectPricture",
            updateUrl: "${pageContext.request.contextPath}/updatePricture",
            toolbar: toolbar,
            columns: [[
                {field: 'title', title: '名字', width: 100},
                {
                    field: 'status', title: '状态', width: 100, editor: {
                        type: "text",
                        options: {
                            required: true
                        }
                    }
                },
                {field: 'desc', title: '描述', width: 100},
                {field: 'date', title: '时间', width: 100}
            ]],
            fit: true,
            fitColumns: true,
            pagination: true,
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/shouye/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>时间: ' + rowData.date + '</p>' +
                    '<p>:描述:' + rowData.desc + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });

        //添加对话框
        $("#dd").dialog({
            resizable: true,//是否可以改变对话框窗口大小
            title: "添加轮播图",//标题
            height: 500,//高
            width: 500,//宽
            closed: true,//定义是否在初始化的时候关闭面板
            collapsible: true//定义是否显示可折叠按钮
        });
        //添加对话框END

    })

    function insert() {
        $("#insertForm").form("submit", {
            url: "${pageContext.request.contextPath}/insertPricture",
            success: function (data) {
                if (data) {
                    alert("添加成功");
                    $("#dd").dialog("close");
                    $("#dg").edatagrid("reload");
                } else {
                    alert("添加失败");
                }

            }
        })
    }


</script>


<table id="dg"></table>
<div id="dd">
    <form id="insertForm" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td>title</td>
                <td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>imgPath</td>
                <td><input type="file" name="upfile"></td>
            </tr>
            <tr>
                <td>desc</td>
                <td><input type="text" name="desc"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="button" value="保存" onclick="insert()"/></td>
            </tr>
        </table>
    </form>
</div>


