import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.*;

public class Post extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();

            String content = request.getParameter("content");
            String imageUrl = request.getParameter("imageUrl");
            String email = request.getParameter("email");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String created_at = dtf.format(now);
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            System.out.println("I am from POST.JAVA, The id that we got is: " + user_id);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            String sql = "INSERT INTO posts (user_id,content,image_url,created_at) VALUES(?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if (content != null && imageUrl != null) {
                stmt.setInt(1, user_id);
                stmt.setString(2, content);
                stmt.setString(3, imageUrl);
                stmt.setString(4, created_at);
                int row = stmt.executeUpdate();
                if (row > 0) {
                    writer.println("Successfully Posted");
                }
            } else {
                writer.println("Values are not set properly");
            }
        } catch (Exception e) {
            writer.println(e.getMessage());
            // TODO: handle exception
        }
    }
}
