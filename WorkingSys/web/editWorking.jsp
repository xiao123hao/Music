<%--
  Created by IntelliJ IDEA.
  User: 董佳豪
  Date: 2024/7/2
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑页面</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/com.js"></script>
</head>
<body>
<h1>我的工作笔记</h1>
<form onsubmit="return false;">
    <p>标题：<input type="text" name="title" /></p>
    <p>内容：<textarea name="content"></textarea></p>
    <p>类别：
        <select name="type">
            <option value="-1">请选择</option>
            <option value="1">技术</option>
            <option value="2">行政</option>
            <option value="3">人事</option>
        </select>
    </p>
    <button name="submit" >提交</button>
</form>
<script type="text/javascript">

    //获取到url中id
    var id = getQueryParam("id");
    console.info("id=" + id);
    //要不要查询？
    if(id!= null && id!=""){
        //就是修改，需要查询数据绑定表单元素
        $.ajax({
            url:"WorkingServlet",
            type:"post",
            data:{
                opr:"getworkingById",
                id:id
            },
            dataType:"json",
            success:function (data) {
                console.info(data);
                $("input[name=title]").val(data.title);
                $("textarea[name=content]").val(data.content);
                $("select[name=type]").val(data.type);
            },
            error:function (data) {
                alert("查询失败");
            }
        });

    }

    //提交的点击事件
    $("button[name=submit]").click(function() {
        //1.数据合法性校验
        var title =  $("input[name=title]").val();
        var content =  $("textarea[name=content]").val();
        var type =  $("select[name=type]").val();

        if(!title){//是不是非空
            alert("标题必须填写！！");
            return;
        }
        if(!content){//是不是非空
            alert("内容必须填写！！");
            return;
        }
        if (type==-1){
            alert("请选择类别！！");
            return;
        }
        //2.发送请求
        $.ajax({
            url:"WorkingServlet",
            type:"post",
            data:{
                opr:"edit",
                working:JSON.stringify({
                    id:id,//新增：null"" 修改 ： 2
                    title:title,
                    content:content,
                    type:type,
                    createDate:new Date().getTime()
                })
            },
            success:function (data) {
                alert(data);
                if(data>0){
                    //跳转到首页
                    window.location.href="workingIndex.jsp";
                }
            },
            error:function (data) {
                alert(data);
            }
        })
    })

</script>
</body>
</html>