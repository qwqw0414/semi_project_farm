package admin.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import admin.model.service.AdminService;
import common.MvcFileRenamePolicy;
import product.model.vo.Product;

/**
 * Servlet implementation class AdminProductDeleteServlet
 */
@WebServlet(urlPatterns="/admin/productDelete", name ="AdminProductDeleteServlet")
public class AdminProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		String saveDirectory = getServletContext().getRealPath("/upload/product");
	
		int pId = Integer.parseInt(request.getParameter("pId"));
		
		//String oldPhotoName	= multiReq.getParameter("oldPhotoName");
		String oldPhotoName	= request.getParameter("oldPhotoName");
	
		
		
		if(!"".equals(oldPhotoName)) {
			//파일 삭제
			File delFile = new File(saveDirectory, oldPhotoName);
			boolean result = delFile.delete();
			System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));
			
		}
		
		
		
		Product p = new Product();
		p.setpId(pId);
		
		int result = new AdminService().deleteProduct(p);
		
		//System.out.println("result@AdminProductDeleteServlet="+result);
	
		
		String msg = "";
		//String loc = "/board/boardView?boardNo="+boardNo;
		String loc = "/admin/productList";
		
		if(result>0) {
			msg = "상품 삭제 성공!";
		}
		else {
			msg = "상품 삭제 실패";
		}
		
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
