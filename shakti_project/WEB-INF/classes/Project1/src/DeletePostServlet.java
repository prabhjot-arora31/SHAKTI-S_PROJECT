import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.*;

public class DeletePostServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String post_id = req.getParameter("post_id");
        System.out.println("Post ID is: " + post_id);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            Statement statement = conn.createStatement();
            String sql = "select * from posts where post_id=" + Integer.parseInt(post_id);
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                String content = rs.getString("content");
                String image_url = rs.getString("image_url");
                req.setAttribute("content", content);
                req.setAttribute("image_url", image_url);
                req.setAttribute("post_id", rs.getString("post_id"));

                RequestDispatcher dispatcher = req.getRequestDispatcher("more.jsp");
                dispatcher.forward(req, resp);
            } else {
                // Handle when no post is found with the given ID
                resp.getWriter().println("No post found with ID: " + post_id);
            }

            conn.close(); // Close the connection
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String post_id = req.getParameter("post_id");
        PrintWriter pw = null;

        String sql = "delete from posts where post_id = " + post_id;
        try {
            pw = resp.getWriter();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shakti_project", "root",
                    "avcdFtr5#%@1*fGj");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            pw.println("Post deleted Successfully");
        } catch (Exception e) {
            pw.println(e.getMessage());
            // TODO: handle exception
        }
    }
}
