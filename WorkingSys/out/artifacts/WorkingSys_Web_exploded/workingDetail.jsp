<%--
  Created by IntelliJ IDEA.
  User: 董佳豪
  Date: 2024/7/3
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>详细页面</title>
    <script src="js/jquery.js"></script>
    <script src="js/com.js"></script>
    <script src="js/workingUtil.js"></script>
</head>
<body>
<h1>我的工作笔记</h1>
<div id="content"></div>
<button><a href="workingIndex.jsp">返回上一页</a></button>
<script>

    $(function () {
        //获取到url中id
        var id = getQueryParam("id");
        console.info("id=" + id);
        //根据id把详细信息查询出来
        var initWorkingDetail = function () {
            $.ajax({
                type:"post",
                url:"WorkingServlet",
                data:{
                    opr:"getworkingById",
                    id:id
                },
                dataType:"json",//对象和List   text:int string char
                success:function (data) {
                    console.info(data);
                    //{"content":"做新员工培训","createDate":1406102400000,"id":1,"title":"做新员工培训","type":3}
                    $("#content").append($("<p></p>").html("标题："+data.title));
                    $("#content").append($("<p></p>").html("内容："+data.content));
                    $("#content").append($("<p></p>").html("类别："+getTypeStr(data.type)));
                    $("#content").append($("<p></p>").html("时间："+convertSecondsToDate(data.createDate)));//根据时间戳转换日期的方法 YYYY-MM-DD格式
                },
                error:function (data) {
                    alert(data);
                }
            })
        }
        initWorkingDetail();
    })
</script>
</body>
</html>