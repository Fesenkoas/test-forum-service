package telran.java47.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import telran.java47.security.model.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java47.accounting.dao.UserAccountRepository;
import telran.java47.accounting.model.UserAccount;

@Component
@Order(20)
@RequiredArgsConstructor
public class AdminManagingRolesFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		if(checkPathRoles(request.getMethod(), request.getServletPath())) {
			User user = (User)request.getUserPrincipal();
			if(!user.getRoles().contains("Administrator".toUpperCase())) {
				response.sendError(403);
				return;
			}
		}
		chain.doFilter(request, response);
//		boolean isAdmin;
//		UserAccount userAccount =userAccountRepository.findById(request.getUserPrincipal().getName()).get();
//		if (checkPathRoles(request.getMethod(), request.getServletPath())) {
////			isAdmin = userAccount.getRoles().stream().anyMatch(r -> r.equals("Administrator"));
//			isAdmin = userAccount.getRoles().contains("Administrator".toUpperCase());
//			if (isAdmin)
//				chain.doFilter(request, response);
//
//			else
//				response.sendError(401);
//		}

	}

	private boolean checkPathRoles(String method, String path) {
		return (("DELETE".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method))
				&& path.matches("/account/user/\\w+/role/\\w+/?"));
	}
}
