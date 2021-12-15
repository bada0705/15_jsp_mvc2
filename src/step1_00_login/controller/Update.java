package step1_00_login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import step1_00_login.dao.MemberDao;
import step1_00_login.dto.MemberDto;

@WebServlet("/update.do")
public class Update extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		MemberDto mdto = MemberDao.getInstance().getOneMemberInfo(id);
		
		if (mdto.getField() != null) {  // 지원분야가 없으면 > 최초지원
		
			String[] skills = mdto.getSkill().split(",");
		
			for (String skill : skills) {
				if (skill.equals("html")) 		request.setAttribute("html", true);
				if (skill.equals("css")) 		request.setAttribute("css", true);
				if (skill.equals("javascript")) request.setAttribute("javascript", true);
				if (skill.equals("java")) 		request.setAttribute("java", true);
				if (skill.equals("jsp")) 		request.setAttribute("jsp", true);
				if (skill.equals("spring")) 	request.setAttribute("spring", true);
			}
			
			request.setAttribute("mdto", mdto);
			request.setAttribute("isFirstApply", false);
			
		}
		else {
			request.setAttribute("isFirstApply", true);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/10_update.jsp");
		dis.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id     = (String)session.getAttribute("id");
		
        String pw     = request.getParameter("pw");
        String name   = request.getParameter("name");
        String tel    = request.getParameter("tel");
        String email  = request.getParameter("email");
        String field  = request.getParameter("field");
        String major  = request.getParameter("major");
        String[] temp = request.getParameterValues("skill");
        String skill  = "";
        for (int i=0; i<temp.length; i++) {
        	skill += temp[i];
        	if (i != temp.length - 1) {
        		skill += ",";
        	}
        }

        MemberDto mdto = new MemberDto();
        mdto.setPw(pw);
        mdto.setName(name);
        mdto.setTel(tel);
        mdto.setEmail(email);
        mdto.setField(field);
        mdto.setSkill(skill);
        mdto.setMajor(major);
        
		MemberDao.getInstance().updateMember(id, mdto);
		
		RequestDispatcher dis = request.getRequestDispatcher("step1_01_loginEx/11_updateAction.jsp");
		dis.forward(request, response);
		
	}
	
	public void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
