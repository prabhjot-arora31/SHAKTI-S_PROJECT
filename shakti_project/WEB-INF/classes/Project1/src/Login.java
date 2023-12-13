import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.servlet.http.*;

public class Login extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            Statement stmt = conn.createStatement();
            ResultSet rs1 = stmt.executeQuery("select * from users where email='" + email + "'");
            if (!rs1.next()) {
                pw.println("User not found");
            } else if (email.compareTo("admin@g.co") == 0) {

                ResultSet rs = stmt.executeQuery("select * from users where email='admin@g.co'");
                String savedPassword;
                while (rs.next()) {
                    savedPassword = rs.getString("password");
                    if (password.compareTo(savedPassword) == 0) {
                        String profile = rs.getString("profileUrl");
                        pw.println("Admin Login Success!");
                        req.setAttribute("adminProfile", profile);
                        req.setAttribute("adminName", rs.getString("name"));
                        req.setAttribute("adminEmail", rs.getString("email"));
                        // req.getRequestDispatcher("admin.jsp").forward(req, resp);
                        resp.sendRedirect("http://localhost:8080/shakti_project/admin");
                    } else {
                        pw.println("Admin Login Failed");
                    }
                }
            } else {

                ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "'");
                String savedPassword;
                while (rs.next()) {
                    savedPassword = rs.getString("password"); // database
                    if (password.equals(savedPassword)) {
                        HttpSession session = req.getSession(true);
                        session.setAttribute("user", email);
                        session.setAttribute("user_id", rs.getString("id"));
                        Statement forGettingData = conn.createStatement();
                        String id = rs
                                .getString("id");
                        int intId = Integer.parseInt(id);
                        String sqlString = "select * from posts where user_id=" + intId + "";
                        ResultSet dataResults = forGettingData.executeQuery(sqlString);
                        // String post_id = null;
                        List<Map<String, String>> combinedData = new ArrayList<>();

                        while (dataResults.next()) {
                            String post_id = dataResults.getString("post_id");
                            String imgUrl = dataResults.getString("image_url");
                            String content = dataResults.getString("content");
                            String time = dataResults.getString("created_at");
                            Map<String, String> rowData = new HashMap<>();
                            rowData.put("image_url", imgUrl);
                            rowData.put("content", content);
                            rowData.put("time", time);
                            rowData.put("post_id", post_id);

                            combinedData.add(rowData);
                        }
                        session.setAttribute("DATA", combinedData);
                        req.setAttribute("combinedData", combinedData);
                        // System.out.println("The is is:" + rs.getString("id"));
                        req.setAttribute("userProfile", rs.getString("profileUrl"));
                        req.setAttribute("userName", rs.getString("name"));
                        req.setAttribute("userEmail", rs.getString("email"));
                        req.setAttribute("userAbout", rs.getString("aboutText"));
                        // System.out.println("isSubscribed value: " +
                        // rs.getString("subscription_newsletter"));
                        // System.out.println("THE USER'S SESSION ID AVAILABLE: " +
                        // session.getAttribute("user_id"));
                        req.setAttribute("isSubscribed", rs.getString("subscription_newsletter"));
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                        // resp.sendRedirect("http://localhost:8080/shakti_project/home.jsp");
                    } else {
                        pw.println("User Login Failed");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            pw.println(e.getMessage());
        }
    }
}
