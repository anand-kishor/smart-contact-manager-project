
package com.cloud.foodpoint.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.cloud.foodpoint.modal.Product;
import com.cloud.foodpoint.service.ProductService;

@Controller
public class ProductController {

	@Value("${uploadDir}")
	private String uploadFolder;

	@Autowired
	private ProductService productService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = { "/product" }) // @GetMapping(value = {"/image","/home"})

	public String addProductPage() {
		return "product/product";
	}

	@PostMapping("/product/save")
	public @ResponseBody ResponseEntity<?> createProduct(@RequestParam("productName") String productName,

			@RequestParam("halfPrice") double halfPrice, @RequestParam("fullPrice") double fullPrice,

			@RequestParam("description") String description, Model model, HttpServletRequest request,
			final @RequestParam("productImage") MultipartFile file) {
		try {
			// String uploadDirectory = System.getProperty("user.dir")+uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName,
						HttpStatus.BAD_REQUEST);
			}
			String[] names = productName.split(",");
			String[] descriptions = description.split(",");
			Date createDate = new Date();
			log.info("productName: " + names[0] + " " + filePath);
			log.info("description: " + descriptions[0]);
			log.info(" half price: " + halfPrice);
			log.info(" half price: " + fullPrice);
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				} // Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Product product = new Product();
			product.setProductName(names[0]);
			product.setProductImage(imageData);
			product.setHalfPrice(halfPrice);
			product.setFullPrice(fullPrice);
			product.setDescription(descriptions[0]);
			product.setCreateDate(createDate);
			productService.saveProduct(product);
			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return new ResponseEntity<>("Product Saved With File - " + fileName, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/image/display/{productId}")

	@ResponseBody
	void showImages(@PathVariable("productId") Integer productId, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {
		log.info("Id :: " + productId);
		product = productService.getImageById(productId);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getProductImage());
		response.getOutputStream().close();
	}

	@GetMapping("/image/imageDetail")
	String showProductsDetails(@RequestParam("productId") int id, Optional<Product> product, Model model) {
		try {
			log.info("productId :: " + id);
			if (id != 0) {
				product = productService.getImageById(id);

				log.info("products :: " + product);
				if (product.isPresent()) {
					model.addAttribute("productId", product.get().getProductId());
					model.addAttribute("description", product.get().getDescription());
					model.addAttribute("productName", product.get().getProductName());
					model.addAttribute("fullPrice", product.get().getFullPrice());
					model.addAttribute("halfPrice", product.get().getHalfPrice());
					return "product/productDetails";
				}
				return "redirect:/home";
			}
			return "redirect:/home";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/home";
		}
	}

	@GetMapping("/product/show")
	public String showProduct(Model map) {
		List<Product> images = productService.getAllActiveImages();
		map.addAttribute("images", images);
		return "product/products";
	}
}
