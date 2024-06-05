package bbs.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybatis.dao.BbsDAO;
import mybatis.vo.BbsVO;

public class ListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse reponse) {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String bname = request.getParameter("bname");

		
		
		String cPage = request.getParameter("cPage");
		
		if(cPage == null) {
			cPage = "1";
		}
		
		int c_page = Integer.parseInt(cPage);
		
		// 한 페이지에 보여질 게시물 수
		int numPerPage = 10;

		int begin = (c_page-1)*numPerPage+1;
		int end = c_page*numPerPage;
		
		
		BbsVO[] b_ar = BbsDAO.getList(bname,begin,end);
		
		// 위에서 얻어낸 사원들의 목록을 request에 "emp"라는 이름으로 저장
		request.setAttribute("bbs", b_ar);
		
		return "/jsp/"+bname+"/list.jsp"; // 뷰페이지의 경로를 반환한다.
	}

}
