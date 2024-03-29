package practicas.common.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import practicas.common.action.ActionServlet;
import practicas.common.action.HttpMethod;
import practicas.common.action.HttpMethodType;
import practicas.common.action.RequireLogin;
import practicas.common.action.SessionParameters;
import practicas.common.bean.Persona;
import practicas.common.bean.Usuario;
import practicas.common.bean.Alumno;
import practicas.common.bean.Profesor;
import practicas.common.dao.DAOFactory;


@WebServlet("/listarAlumnos")
public class ListarAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ListarAlumnos() {
        super();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		String idAlumno = request.getParameter("idAlumno");		
		List<Persona> listaAlumnos = null;
		try {
			DAOFactory oDAOFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
			listaAlumnos = oDAOFactory.getFunctions().getPracticas().listadosalumnos();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("listaAlumnos", listaAlumnos);
		request.getRequestDispatcher("/MenuDocente/ListadoAlumnos.jsp").forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}

