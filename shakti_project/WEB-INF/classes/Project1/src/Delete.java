import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.*;

import com.mysql.cj.jdbc.Driver;

public class Delete extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        String sql = "delete from users where email='" + email + "'";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            Statement stmt = conn.createStatement();
            boolean isDeleted = stmt.execute(sql);
            PrintWriter pw = resp.getWriter();
            resp.setContentType("text/html");
            pw.println(
                    "<center>User deleted successfully<br/><a href='http://localhost:8080/shakti_project'>Go to Home Page.</a></center>");

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
