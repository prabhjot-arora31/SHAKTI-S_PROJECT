
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.*;
// package src;
// class User {
//     String name, email, profileUrl, aboutText;

//     public User(String name, String email, String profileUrl, String aboutText) {
//         this.name = name;
//         this.email = email;
//         this.profileUrl = profileUrl;
//         this.aboutText = aboutText;
//     }
// }

public class Admin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter pw = null;
        List<List<String>> userList = new ArrayList<>();

        try {
            pw = response.getWriter();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();

            String sql2 = "select * from users where email='admin@g.co'";
            String sql = "select * from users";
            ResultSet rs2 = stmt.executeQuery(sql2);
            ResultSet rs = stmt2.executeQuery(sql);

            // Move the redirection after setting attributes and forwarding the request
            while (rs.next()) {
                List<String> user = new ArrayList<>();
                user.add(rs.getString("name"));
                user.add(rs.getString("email"));
                user.add(rs.getString("profileUrl"));
                user.add(rs.getString("aboutText"));
                userList.add(user);
            }

            if (rs2.next()) {
                request.setAttribute("adminProfile", rs2.getString("profileUrl"));
            }

            request.setAttribute("users", userList);
            request.getRequestDispatcher("admin.jsp").forward(request, response);

        } catch (Exception e) {
            pw.println(e.getMessage());
        }
    }
}
