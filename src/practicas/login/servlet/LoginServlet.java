package practicas.login.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import practicas.common.action.ActionServlet;
import practicas.common.action.HttpMethod;
import practicas.common.action.HttpMethodType;
import practicas.common.action.RequireLogin;
import practicas.common.action.SessionParameters;
import practicas.common.bean.Persona;
import practicas.common.bean.Usuario;
import practicas.common.dao.DAOFactory;


@WebServlet("/login")
public class LoginServlet extends ActionServlet {

	public static final String WARNING = "warning";
	public static final String LOGINUSER = "loginuser";
	public static final String USERTYPE = "loginusertype";

	
	private final String oStrUserIncorrect = "Usuario incorrecto.";
	private final String oStrPwdIncorrect = "Contraseņa incorrecta.";
	private final String oStrUserTypeIncorrect = "Seleccione un tipo de usuario correcto.";
	private final String oStrErrorSignup = "Usuario y contraseņa incorrecta.";	
	@HttpMethod(HttpMethodType.POST)
	@RequireLogin(false)
	public void signup() throws Exception {
		log.info("signup");
		String oStrUser = request.getParameter("u"); oStrUser = oStrUser==null?"":oStrUser.trim();
		String oStrPassword = request.getParameter("p"); oStrPassword = oStrPassword==null?"":oStrPassword.trim();
		boolean isCorrect = true; 
		log.info("user: " + oStrUser);
		log.info("password: " + oStrPassword);
		if(oStrUser.trim().length()==0) {
			log.info("user is incorrect.");
			request.setAttribute(WARNING, oStrUserIncorrect);
			isCorrect = false;
		}
		if(oStrPassword.trim().length()==0) {
			log.info("password incorrect.");
			request.setAttribute(WARNING, oStrPwdIncorrect);
			isCorrect = false;
		}
		if(!isCorrect) {
			request.setAttribute(LOGINUSER, oStrUser);
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}
		DAOFactory oDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
		//Persona oPersona = oDAOFactory.getGeneral().getLogin().getPersonaByUser(oStrUser, oStrPassword);
		Usuario oUsuario = oDAOFactory.getGeneral().getLogin().getUsuario(oStrUser, oStrPassword);
		if(oUsuario!=null) {
			HttpSession oSession = request.getSession(false);
			oSession.setAttribute(SessionParameters.USUARIO.text(), oUsuario);
			log.info("user logged: " + oUsuario.getPersona().toString());
			log.info("USER LOGGER ID: "+oUsuario.getPersona().getIdPersona().toString());
			log.info(request.getServletContext().getContextPath() + "/welcome");			
			response.sendRedirect(request.getServletContext().getContextPath() + "/welcome");
			//request.getServletContext().getRequestDispatcher("/welcome").forward(request, response);
		} else {
			log.info("user and password incorrect!");
			request.setAttribute(LOGINUSER, oStrUser);
			request.setAttribute(WARNING, oStrErrorSignup);
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}


	@HttpMethod(HttpMethodType.GET)
	@RequireLogin(false)
	public void logout() throws IOException {
		log.info("logout");
		HttpSession oSession = request.getSession(false);
		oSession.invalidate();
		response.sendRedirect(request.getServletContext().getContextPath() + "/index.jsp");
	}


}
