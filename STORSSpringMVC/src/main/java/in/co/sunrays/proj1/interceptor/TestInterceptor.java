package in.co.sunrays.proj1.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		System.out.println("in TestInterceptor.afterCompletion()");
		// super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// we can add attributes in the modelAndView and use that in the view
		// page
		System.out.println("in TestInterceptor.postHandle()");
		// super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// if returned false, we need to make sure 'response' is sent
		HttpSession session = request.getSession();
		System.out.println("in TestInterceptor.preHandle()");
		System.out.println("request uri is :: " + request.getRequestURI());
		System.out.println("session in interceptor :"
				+ session.getAttribute("user"));

		if (session.getAttribute("user") == null) {

			System.out.println("session null in interceptor");
			
			
			System.out.println("forword : "+request.getContextPath()+"/Test/Login/display");
			response.sendRedirect(request.getContextPath()+"/Login/display");
		}

		// return super.preHandle(request, response, handler);
		return true;
	}

}
