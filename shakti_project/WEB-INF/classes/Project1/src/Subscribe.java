import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.*;

public class Subscribe extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter pw = null;
        try {
            pw = res.getWriter();
            String email = req.getParameter("email");
            String sql = "update users set subscription_newsletter='yes' where email='" + email + "'";
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            pw.println("Subscription to our newsletter added successfully!");
        } catch (Exception e) {
            // TODO: handle exception
            // e.printStackTrace();
            pw.println(e.getMessage());
        }
    }
}