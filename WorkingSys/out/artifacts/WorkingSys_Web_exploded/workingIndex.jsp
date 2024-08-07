<%--
  Created by IntelliJ IDEA.
  User: 董佳豪
  Date: 2024/7/2
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>工作日记</title>
    <script src="js/com.js"></script>
    <script src="js/jquery.js"></script>
    <script src="js/workingUtil.js"></script>
</head>
<body>
<h1><a href="editWorking.jsp">新增工作日记</a></h1>
<table border="1">
    <thead>
    <tr>
        <th>工作标题</th>
        <th>工作时间</th>
        <th>类型</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody></tbody>
</table>
<script>
    $(function () {
        //编写初始化事件
        var initWorking = function (){
            $.ajax({
                type:"post",
                url:"WorkingServlet",
                data:{
                    opr:"selectAll"
                },
                dataType:"json",
                success:function (data) {
                    var tbody = $("tbody");
                    tbody.empty();//添加前先清空
                    $.each(data,function (index,item) {
                        var tr = $("<tr></tr>");
                        tr.append("<td>"+item.title+"</td>");
                        tr.append("<td>"+convertSecondsToDate(item.createDate)+"</td>");
                        tr.append("<td>"+getTypeStr(item.type)+"</td>");
                        tr.append("<td><a href='workingDetail.jsp?id="+item.id+"'>查看</a><a href='editWorking.jsp?id="+item.id+"'>修改</a><a href='WorkingServlet?opr=delete&id="+item.id+"'>删除</a></td>");
                        tbody.append(tr);
                    })
                }
            })
        }
        initWorking();
        //转换分类的方法
    })
</script>
</body>
</html>
