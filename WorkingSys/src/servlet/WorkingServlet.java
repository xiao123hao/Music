package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Working;
import service.WorkingService;
import service.impl.WorkingServiceImpl;
@WebServlet(urlPatterns = "/WorkingServlet")
public class WorkingServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		WorkingService workingService = new WorkingServiceImpl();
		String opr = req.getParameter("opr")==null?"":req.getParameter("opr");
		if ("edit".equals(opr)){
			String jsonbjeStr = req.getParameter("working");
			Working working = JSONObject.parseObject(jsonbjeStr,Working.class);
			//新增
			int count =0;
			if (working.getId()==0){
				count=workingService.addWorking(working);
			}else{
				count=workingService.updateWorking(working);
			}
			out.print(count);
		}else if ("delete".equals(opr)){
			int id = Integer.parseInt(req.getParameter("id"));
			int count = workingService.delWorking(id);
			resp.sendRedirect("workingIndex.jsp");
		}else if ("selectAll".equals(opr)){
			List<Working> workingList = workingService.selectAll();
			String jsonstr = JSONArray.toJSONString(workingList);
			out.print(jsonstr);
		}else if ("getworkingById".equals(opr)){
			int id = Integer.parseInt(req.getParameter("id"));
			Working working = workingService.getWorkingById(id);
			String jsonstr = JSONObject.toJSONString(working);
			out.print(jsonstr);
		}
		out.flush();//刷新输出流
		out.close();//关闭输出流
//		String type = req.getParameter("type");
//		if("details".equals(type)){
//			//查询所有
//			String id =  req.getParameter("id");
//			Working working = workingService.getWorkingById(Integer.parseInt(id));
//			req.setAttribute("working", working);
//			req.getRequestDispatcher("details.jsp").forward(req, resp);
//		}else if("add".equals(type)){
//			String title = req.getParameter("title");
//			String content = req.getParameter("content");
//			String t = req.getParameter("type2");
//			Working w = new Working();
//			w.setTitle(title);
//			w.setContent(content);
//			w.setType(Integer.parseInt(t));
//			int num = workingService.addWorking(w);
//			out.print(num);
//			out.flush();
//			out.close();
//		}else if("validate".equals(type)){
////			String title =  req.getParameter("title");
////			int count =  workingService.GetWorkingByTitle(title);
////			if(count>0){
////				out.print("true");
////			}else{
////				out.print("false");
////			}
////			out.flush();
////			out.close();
//		}else{
//			//查询所有
//			List<Working> list =  workingService.selectAll();
//			req.setAttribute("list", list);
//			req.getRequestDispatcher("index.jsp").forward(req, resp);
//		}
	}
}
