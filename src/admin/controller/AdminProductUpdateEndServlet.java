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
 * Servlet implementation class AdminProductUpdateEndServlet
 */
@WebServlet("/admin/productUpdateEnd")
public class AdminProductUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String saveDirectory = getServletContext().getRealPath("/upload/product");
		int maxPostSize = 1024 * 1024 * 50;

		FileRenamePolicy mvcFileRenamePolicy = new MvcFileRenamePolicy();
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", mvcFileRenamePolicy);
		
		int pId = Integer.parseInt(multiReq.getParameter("pId"));
		String category = multiReq.getParameter("category");
		String pName = multiReq.getParameter("pName");
		String pInfo = multiReq.getParameter("pInfo");
		int price = Integer.parseInt(multiReq.getParameter("price"));
		double discount = Double.parseDouble(multiReq.getParameter("discount"));
		String photo = multiReq.getFilesystemName("photo");
		
		
		String oldPhotoName
				= multiReq.getParameter("oldPhotoName");
	
		if(!"".equals(oldPhotoName)) {
			
			File f = multiReq.getFile("photo");
			
			//신규첨부파일이 있는 경우, 기존첨부파일 삭제
			if(f!=null) {
				File delFile = new File(saveDirectory, oldPhotoName);
				boolean result = delFile.delete();
				System.out.println("기존첨부파일삭제: "+(result?"성공!":"실패!"));
			}
			else { //이부분을 주석처리하고 실행하면 null값이 들어가서 기존 파일들이 날라간다.
				photo = oldPhotoName;
			}
			
		}
		
		Product p = new Product(pId, category, pName, pInfo, price, discount, 0, photo);
		
		int result = new AdminService().updateProduct(p);
		
		String msg = "/product/productView";
		String loc = "/admin/productList";
		
		if(result>0) {
			msg = "상품 수정 성공!";
		}
		else {
			msg = "상품 수정 실패";
		}
		
		//3.view단 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
