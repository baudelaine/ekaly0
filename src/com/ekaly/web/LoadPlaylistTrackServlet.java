package com.ekaly.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ekaly.tools.Tools;

/**
 * Servlet implementation class GetImportedKeysServlet
 */
@WebServlet("/LPLT")
public class LoadPlaylistTrackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPlaylistTrackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, Object> result = new HashMap<String, Object>();

		result.put("SESSIONID", request.getSession().getId());		
		result.put("CLIENT", request.getRemoteAddr() + ":" + request.getRemotePort());
		result.put("SERVER", request.getLocalAddr() + ":" + request.getLocalPort());
		result.put("FROM", this.getServletName());
		
		try{

			Map<String, Object> parms = Tools.fromJSON(request.getInputStream());
	        
	        if(parms != null && parms.get("track") != null) {			
				Path realPath = Paths.get(getServletContext().getRealPath("/"));
				Path track = Paths.get(realPath + "/" + parms.get("track"));
				Path sound = Paths.get(realPath + "/sounds/sound.mp3");
				
				if(Files.exists(track)) {
					Files.copy(track, sound, StandardCopyOption.REPLACE_EXISTING);
					result.put("ANSWER_0", track + " has been copied.");
					result.put("STATUS_0", "OK");
					
				}
				else {
	        		result.put("ANSWER", "No playlist directory found.");
	        		throw new Exception();
				}
				
				String recordName = ((String) parms.get("track")).split("/")[1].split("\\.")[0];
				System.out.println(recordName);
				
				request.getSession().setAttribute("recordName", recordName);
				
				Path analyze = Paths.get(realPath + "/" + "analysis/google/" + recordName + ".json");
				if(Files.exists(analyze)) {
					Files.copy(analyze, Paths.get(realPath + "/" + "analysis/ganalyze.json"), StandardCopyOption.REPLACE_EXISTING);
					result.put("ANSWER_1", analyze + " has been copied." + " to /analysis/ganalyze.json");
					result.put("STATUS_1", "OK");
					
				}

				analyze = Paths.get(realPath + "/" + "analysis/watson/" + recordName + ".json");
				if(Files.exists(analyze)) {
					Files.copy(analyze, Paths.get(realPath + "/" + "analysis/wanalyze.json"), StandardCopyOption.REPLACE_EXISTING);
					result.put("ANSWER_1", analyze + " has been copied." + " to /analysis/wanalyze.json");
					result.put("STATUS_1", "OK");
					
				}
				
	        }
	        
		}
		catch(Exception e){
			result.put("STATUS", "KO");
            result.put("EXCEPTION", e.getClass().getName());
            result.put("MESSAGE", e.getMessage());
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            result.put("STACKTRACE", sw.toString());
		}			
		
		finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(Tools.toJSON(result));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

