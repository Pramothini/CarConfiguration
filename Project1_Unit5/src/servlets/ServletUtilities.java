package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Servlet implementation class ServletUtilities
 * Contains static methods that will make coding shorter in other servlets
 * @author pramothinidk
 */
@WebServlet("/ServletUtilities")
public class ServletUtilities extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUtilities() {
		super();
	}
	
	/**
	 * Can be called instead of typing <html> <head> tags in other servlets - it makes coding easier
	 * @param title - title of the web page
	 * @return
	 */
	public static String headWithTitle(String title) {
		return("<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head><title>" + title + "</title></head>\n");
	} 

}
