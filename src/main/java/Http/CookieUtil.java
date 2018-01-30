package Http;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CookieUtil {
	
	/**
     * �������ֻ�ȡcookie
     * @param request
     * @param name cookie����
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request,String name){
        Map<String,Cookie> cookieMap = ReadCookieMap(request);
        if(cookieMap.containsKey(name)){
            Cookie cookie = (Cookie)cookieMap.get(name);
            return cookie;
        }else{
            return null;
        }
    }

    /**
     * ��cookie��װ��Map����
     * @param request
     * @return
     */
    
    private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
	/**
	 * ObjectתString
	 * @param Object
	 * @return String
	 */
	public static String Object2String(Object t){
		if(t == null) return "";
		if("null".equals(String.valueOf(t))) return "";
		return String.valueOf(t);
	}
}
