package com.cloud.foodpoint.controller;




import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	
	@GetMapping("/")
	public String homePage()
	{
		return "home";
	}
	@GetMapping("/index")
	public String indexPage()
	{
		return "index";
	}
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	@GetMapping("/logout")
	public String logoutPage()
	{
		return "logout";
	}
	@GetMapping("/register")
	public String registerPage()
	{
		return "register_customer";
	}
	
	@GetMapping("/index/veg")
	public String vegPage()
	{
		return "normal/veg";
	}
	@GetMapping("/index/nonveg")
	public String nonvegPage()
	{
		return "normal/nonveg";
	}
	@GetMapping("/index/tandoorikawab")
	public String tandooriKawabPage()
	{
		return "normal/tandoorikawab";
	}
	@GetMapping("/index/mutton")
	public String muttonPage()
	{
		return "normal/mutton";
	}
	@GetMapping("/index/nantandooriroti")
	public String nontandooriRotiPage()
	{
		return "normal/nantandooriroti";
	}
	@GetMapping("/index/chinesnonveg")
	public String chinesnonvegPage()
	{
		return "normal/chinesnonveg";
	}

	
	  @GetMapping("/index/chinesveg")
	  public String chinesvegPage() { 
		  return "normal/chinesveg"; }
	 
	
	@GetMapping("/index/roll")
	public String rollPage()
	{
		return "normal/roll";
	}
	@GetMapping("/index/soup")
	public String soupPage()
	{
		return "normal/soup";
	}
	@GetMapping("/index/pulao")
	public String pulaoPage()
	{
		return "normal/pulao";
	}
	@GetMapping("/index/coffee")
	public String coffeePage()
	{
		return "normal/coffee";
	}
	@GetMapping("/index/spiceitems")
	public String spiceItemsPage()
	{
		return "normal/spiceitems";
	}
	@GetMapping("/index/allproducts")
	public String allproductsPage()
	{
		return "normal/allproducts";
	}
	@GetMapping("/index/contacts")
	public String contactsPage()
	{
		return "normal/contacts";
	}
	@GetMapping("/imageform")
	public String imageForm()
	{
		return "productimage/imageform";
	}

}
