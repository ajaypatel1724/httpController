package com.elitecore.elitejson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class EliteJSONControllerServlet extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Executing Do Post....");
		Map<String, String> requestMap = null;
		Map<String, String> responseMap = null;
		StringBuffer sb = new StringBuffer();
		BufferedReader br = null;
		String line = null;
		JSONObject responseJSON;
		
		try{
			br = new BufferedReader(new InputStreamReader(req.getInputStream()));
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			
			System.out.println("Received Content : " + sb.toString());
			
			requestMap = JSONObject.fromObject(sb.toString());
			Set<String> keySet = requestMap.keySet();
			System.out.println("Request = " + requestMap);
			
			Operation operation = Operation.getOperation(requestMap.get("RequestType"));
			
			switch(operation){
			case LOGIN:
				responseMap = buildLoginResponse(requestMap);
				break;
			case LOGOUT:
				break;				
			default:
				break;
			}
			
			responseJSON = new JSONObject();
			responseJSON.putAll(responseMap);
			
			System.out.println("Response = " + responseMap);
			
			resp.setContentType("application/json");
			resp.getWriter().write(responseJSON.toString());
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	private Map<String, String> buildLoginResponse(Map<String,String> loginReqMap){
		Map<String, String> mapLoginResponse = new HashMap<String, String>();
		
/*		Login
		Request
		{"Vendor":"ruckus","RequestPassword":"^Z0LscgLTZ2013!NBPI","APIVersion":"1.0","RequestCategory":"UserOnlineControl",
		"RequestType":"Login","UE-IP":"ENC7ab85be61b9578971e1177d3fffaab82","UE-Proxy":"0","UE-Username":"263783208458","UE-Password":"516112"}

		Response
		{"ReplyMessage":"Login succeeded","UE-Username":"263783208458","UE-IP":"ENC7ab85be61b9578971e1177d3fffaab82","APIVersion":"1.0",
		"GuestUser":"0","ResponseCode":201,"AP-MAC":"54:3D:37:3E:B4:E0","SmartClientMode":"enable",
		"UE-MAC":"ENC99b77f62b464f7de4a2cf7039e501d332d0bf93f9f60a7d8","Vendor":"Ruckus","SSID":"ECONET HOTSPOT",
		"SmartClientInfo":"","UE-Proxy":0}
*/
		try{
			mapLoginResponse.putAll(loginReqMap);
			
			mapLoginResponse.put("ReplyMessage", "Login succeeded");
			mapLoginResponse.put("ResponseCode", String.valueOf(201) );
			mapLoginResponse.put("GuestUser", "0");
			mapLoginResponse.put("AP-MAC", "54:3D:37:3E:B4:E0");
			mapLoginResponse.put("SmartClientMode", "enable");			
			mapLoginResponse.put("UE-MAC", "ENC99b77f62b464f7de4a2cf7039e501d332d0bf93f9f60a7d8");			
			mapLoginResponse.put("SSID", "HOTSPOT");
			mapLoginResponse.put("SmartClientInfo", "");
			mapLoginResponse.put("UE-Proxy", String.valueOf(0));
			
			mapLoginResponse.remove("RequestPassword");
			mapLoginResponse.remove("RequestCategory");
			mapLoginResponse.remove("RequestType");
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapLoginResponse;
	}
	
	private Map<String, String> buildLogoutResponse(Map<String,String> logoutReqMap){
		Map<String, String> mapLogoutResponse = new HashMap<String, String>();
		
/*		Logout
		Request
		{
		    "Vendor": "ruckus",
		    "RequestPassword": "^Z0LscgLTZ2013!NBPI",
		    "APIVersion": "1.0",
		    "RequestCategory": "UserOnlineControl",
		    "RequestType": "Logout",
		    "UE-IP": "ENC7ab85be61b957897ff3ee878df9d727d",
		}		
		Response
		{
	       "SmartClientMode": "enable",
	       "UE-Username": "ecoasr",
	       "ReplyMessage": "OK",
	       "UE-MAC": "ENC409ebb1629160ac24ebe4d47c6bdaeeaadc5e47c3dbe2157",
	       "SmartClientInfo": "",
	       "SSID": "ECONET HOTSPOT",
	       "Vendor": "Ruckus",
	       "UE-IP": "ENC7ab85be61b957897ff3ee878df9d727d",
	       "APIVersion": "1.0",
	       "GuestUser": "0",
	       "AP-MAC": "54:3D:37:3E:B4:E0",
	       "ResponseCode": 200
	    }
*/		
		try{			
			mapLogoutResponse.putAll(logoutReqMap);
			
			mapLogoutResponse.put("ReplyMessage", "OK");
			mapLogoutResponse.put("ResponseCode", String.valueOf(200) );
			mapLogoutResponse.put("GuestUser", "0");
			mapLogoutResponse.put("AP-MAC", "54:3D:37:3E:B4:E0");
			mapLogoutResponse.put("SmartClientMode", "enable");			
			mapLogoutResponse.put("UE-MAC", "ENC99b77f62b464f7de4a2cf7039e501d332d0bf93f9f60a7d8");			
			mapLogoutResponse.put("SSID", "HOTSPOT");
			mapLogoutResponse.put("SmartClientInfo", "");
			
			mapLogoutResponse.remove("RequestPassword");
			mapLogoutResponse.remove("RequestCategory");
			mapLogoutResponse.remove("RequestType");
			mapLogoutResponse.remove("UE-Proxy");
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapLogoutResponse;
	}
	
}
