package com.commons.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharacterEncodingFilter implements Filter {

	/**
	 * default character set
	 */
	private String encoding = "UTF-8";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(new EncodingHttpServletRequest(
				(HttpServletRequest) request, encoding), response);
		response.setCharacterEncoding("text/html;charset=" + encoding);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		final String charset = filterConfig.getInitParameter("encoding");
		if (charset != null) {
			encoding = charset;
		}
	}

	private class EncodingHttpServletRequest extends HttpServletRequestWrapper {

		private String encoding = "UTF-8";
		private final String GET = "GET";
		private HttpServletRequest request;

		public EncodingHttpServletRequest(HttpServletRequest request,
				String encoding) {
			super(request);
			this.request = request;
			this.encoding = encoding;
		}

		@Override
		public String getParameter(String name) {
			if (isEmpty(name)) {
				return null;
			} else {
				return getEncodedValue(super.getParameter(name));
			}
		}

		@SuppressWarnings("rawtypes")
		@Override
		public Map getParameterMap() {
			try {
				Map params = paramsOfRequest();
				return params;
			} catch (Exception e) {
				return super.getParameterMap();
			}
		}

		@Override
		public String[] getParameterValues(String name) {
			String[] newValues = getEncodedValue(super.getParameterValues(name));
			return newValues;
		}

		private String encode(String value) {
			try {
				if (isEmpty(value)) {
					return null;
				}
				return new String(value.getBytes("ISO-8859-1"), encoding);
			} catch (UnsupportedEncodingException e) {
				return value;
			}
		}

		private boolean isEmpty(String name) {
			if (isNull(name) || name.trim().length() == 0) {
				return true;
			} else {
				return false;
			}

		}

		private boolean isEmpty(Object[] array) {
			if (null == array || array.length == 0) {
				return true;
			} else {
				return false;
			}

		}
		private boolean isNull(Object obj){
			return null == obj;
		}
		private String getEncodedValue(String value) {
			if (!isEmpty(value)) {
				String newValue = null;
				if (GET.equalsIgnoreCase(request.getMethod())) {
					newValue = encode(value);
				} else {
					newValue = value;
				}
				return newValue;
			} else {
				return null;
			}
		}

		private String[] getEncodedValue(String[] values) {
			List<String> list = new ArrayList<String>();
			if (!isEmpty(values)) {
				for (String value : values) {
					String newValue = getEncodedValue(value);
					if (null != newValue) {
						list.add(newValue);
					}
				}
			} else {
				return null;
			}
			return list.toArray(new String[0]);
		}

		@SuppressWarnings("unchecked")
		private Map<String, Object> paramsOfRequest() throws Exception {
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, Object> orignalParams = super.getParameterMap();
			for (Map.Entry<String, Object> entry : orignalParams.entrySet()) {
				final String name = entry.getKey();
				final Object value = entry.getValue();
				if (value.getClass() == String.class) {
					String newValue = this.getParameter(name);
					if (newValue != null) {
						params.put(name, newValue);
					}
				} else if (value.getClass() == String[].class) {
					String[] newValue = this.getParameterValues(name);
					if (newValue != null && newValue.length > 0) {
						params.put(name, newValue);
					}
				}
			}
			return params;
		}
	}
}
