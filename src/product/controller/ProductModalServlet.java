package product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import product.model.service.ProductService;
import product.model.vo.Product;

@WebServlet("/product/productModal")
public class ProductModalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pId = Integer.parseInt(request.getParameter("pId"));
		
		Product p = new Product();
		p = new ProductService().selectOneByPId(pId);
		
		JSONObject jsonProduct = new JSONObject();
		jsonProduct.put("pId", p.getpId());
		jsonProduct.put("pName", p.getpName());
		jsonProduct.put("photo", p.getPhoto());
		jsonProduct.put("price", p.getPrice());
		jsonProduct.put("discount", p.getDiscount());
		jsonProduct.put("stock", p.getStock());
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().append(jsonProduct.toString());
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
