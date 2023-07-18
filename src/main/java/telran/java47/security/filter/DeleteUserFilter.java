package telran.java47.security.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java47.accounting.dao.UserAccountRepository;
import telran.java47.accounting.model.UserAccount;
import telran.java47.security.model.User;

@Component
@Order(40)
@RequiredArgsConstructor
public class DeleteUserFilter implements Filter {
	final UserAccountRepository userAccountRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String path = request.getServletPath();
		if (checkPathDelete(request.getMethod(), path)) {
			String[] arr = path.split("/");
			String user = arr[arr.length - 1];
			User userName = (User)request.getUserPrincipal();
			if (!(userName.getName().equalsIgnoreCase(user) 
					|| userName.getRoles().contains("Administrator".toUpperCase()))) {
				response.sendError(403);
				return;
			}
		}
		chain.doFilter(request, response);

	}

	private boolean checkPathDelete(String method, String path) {
		return ("DELETE".equalsIgnoreCase(method) && path.matches("/account/user/\\w+/?"));
	}

}
