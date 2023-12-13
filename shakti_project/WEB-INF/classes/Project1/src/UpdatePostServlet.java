import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.http.*;

public class UpdatePostServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter pw = null;
        String post_id = req.getParameter("post_id");
        String content = req.getParameter("content");
        String image_url = req.getParameter("image_url");
        System.out.println("Content is: " + content);
        try {
            pw = resp.getWriter();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            String sql = "UPDATE posts SET content = ?, image_url = ? WHERE post_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, content);
            statement.setString(2, image_url);
            statement.setInt(3, Integer.parseInt(post_id));
            statement.execute();
            pw.println("Post Updated Successfully");
        } catch (Exception e) {
            pw.println(e.getMessage());
            // TODO: handle exception
        }
    }
}
