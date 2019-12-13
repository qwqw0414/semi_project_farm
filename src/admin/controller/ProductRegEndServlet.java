package admin.controller;

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

@WebServlet("/admin/productRegEnd")
public class ProductRegEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String saveDirectory = getServletContext().getRealPath("/upload/product");
		int maxPostSize = 1024 * 1024 * 50;

		FileRenamePolicy mvcFileRenamePolicy = new MvcFileRenamePolicy();
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", mvcFileRenamePolicy);
		
		String category = multiReq.getParameter("category");
		String pName = multiReq.getParameter("pName");
		String pInfo = multiReq.getParameter("pInfo");
		int price = Integer.parseInt(multiReq.getParameter("price"));
		String photo = multiReq.getFilesystemName("photo");
		
		Product p = new Product(0, category, pName, pInfo, price, 0, 0, photo);
		
		int result = new AdminService().productReg(p);
		
		request.getRequestDispatcher("/index.jsp")
		   	   .forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
