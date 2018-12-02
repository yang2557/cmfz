<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head>
    <script type="text/javascript" src="../js/echarts.min.js"></script>
    <script type="text/javascript">
    </script>
</head>

<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    $.ajax({
        type: 'post',	//传输类型

        url: '${pageContext.request.contextPath}/selectCount',	//web.xml中注册的Servlet的url-pattern
        data: {},
        dataType: 'JSON', //返回数据形式为json
        success: function (result) {
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '持明活跃用户'
                },
                tooltip: {},
                legend: {
                    data: ['活跃人数']
                },
                xAxis: {
                    data: result.type
                },
                yAxis: {},
                series: [{
                    name: '活跃人数',
                    type: 'bar',
                    data: result.data
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    });

</script>
</body>
</html>
