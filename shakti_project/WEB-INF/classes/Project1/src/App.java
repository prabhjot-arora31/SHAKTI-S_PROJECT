import java.sql.Statement;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.http.*;
public class App extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter pw = null;

        String userName, userEmail, userPassword, userProfileUrl, userAboutText;
        userName = req.getParameter("userName");
        userEmail = req.getParameter("email");
        userPassword = req.getParameter("password");
        userProfileUrl = req.getParameter("profileUrl");
        userAboutText = req.getParameter("aboutText");
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            String sql = "insert into users(name,email,password,profileUrl,aboutText) values ('" + userName + "','"
                    + userEmail
                    + "','" + userPassword + "','" + userProfileUrl + "','" + userAboutText + "')";
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            pw = res.getWriter();
            pw.println("Registration Successful.");
        } catch (Exception e) {
            // TODO: handle exception
            try {
                pw = res.getWriter();

            } catch (Exception e1) {
                // TODO: handle exception
            }
            pw.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
