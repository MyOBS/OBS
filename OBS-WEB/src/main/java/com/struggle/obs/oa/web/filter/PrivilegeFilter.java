/**
 * 
 */
package com.struggle.obs.oa.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.struggle.obs.bean.user.User;
import com.struggle.obs.oa.web.util.WebUtils;
import com.struggle.obs.syscom.util.UserInThreadLocal;

/**
 * 过滤器
 * @author tangyh
 *  2014年8月23日 下午4:07:23
 */
public class PrivilegeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		User user = WebUtils.getUser(request);
		if(user == null){
			HttpServletResponse response = (HttpServletResponse)resp;
			String projectName = request.getContextPath();
			response.sendRedirect(projectName+ "/adminLogin.action");
//			request.getRequestDispatcher("/WEB-INF/jsps/user/loginUI.jsp").forward(req, resp);
		}else {
			UserInThreadLocal.setLoginName(user.getLoginName());
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void destroy() {
	}

}
