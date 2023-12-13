import java.io.IOException;

import javax.servlet.http.*;

public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("http://localhost:8080/shakti_project/login.html");
    }
}
