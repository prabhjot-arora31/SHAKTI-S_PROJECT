import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.*;

public class Update extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String profileUrl = req.getParameter("profileUrl");
        String aboutText = req.getParameter("aboutText");
        String oldEmail = req.getParameter("oldEmail");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            String sql = "update users set name='" + name + "', email='" + email + "', password='" + password
                    + "', profileUrl='" + profileUrl + "', aboutText='" + aboutText + "'where email='" + oldEmail + "'";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            PrintWriter pw = res.getWriter();
            // pw.println("Updation Successful<br/>");
            res.setContentType("text/html");

            pw.println(
                    "<center>User updated successfully<br/><a href='http://localhost:8080/shakti_project'>Go to Home Page.</a></center>");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
