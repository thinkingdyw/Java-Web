package com.company.common.utils.web;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.tools.view.CookieTool.SugarCookie;

public final class CookieUtils {

	public static List<Cookie> getCookies(HttpServletRequest request) {
		List<Cookie> cookies;
		Cookie[] array = request.getCookies();
		if (array == null || array.length == 0) {
			return null;
		}
		cookies = new ArrayList<Cookie>(array.length);
		for (Cookie c : array) {
			Cookie sc = new SugarCookie(c);
			cookies.add(sc);
		}
		return cookies;
	}

	public static Cookie get(String name, HttpServletRequest request) {
		List<Cookie> all = getCookies(request);
		if (all != null) {
			for (Cookie cookie : all) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static void add(Cookie cookie, HttpServletResponse response) {
		if (cookie == null) {
			return;
		}
		response.addCookie(cookie);
	}

	public static void delete(String name, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = get(name, request);
		if (cookie == null) {
			return;
		}
		cookie.setMaxAge(0);
		add(cookie, response);
	}
	public static void add(String name, String value,HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		add(cookie, response);
	}
}
