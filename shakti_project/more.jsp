<%@ page language="java" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>More Post</title>
</head>
<body>
    <%-- Retrieve the post data from the request attributes --%>
    <% String content = (String) request.getAttribute("content"); %>
    <% String image_url = (String) request.getAttribute("image_url"); %>
    <% String post_id = (String) request.getAttribute("post_id"); %>

    
    <form method="post" action="http://localhost:8080/shakti_project/updatepost"  style="margin-left: auto; margin-right: auto; padding: 2rem ;display: flex;  justify-content: center ; border: 2px solid black ;  width: 500px ;align-items: center ; flex-direction: column;">
        <h2>Edit Post</h2>
        <input type="hidden" name="post_id" value="<%= post_id %>">
        <textarea name="content" id="content" cols="30" rows="10" style="text-align: left;padding: 0; ">
            <%= content %>
        </textarea>
        <div style="width: 460px;">

            <img style="width: 100%;" src="
            <%= image_url %>
            " alt="">
        </div>
        <input type="text" name="image_url"  id="image_url" placeholder="New Image URL">
        <button style="margin-top: 0.7rem;">Submit</button>
    </form>
    <form action="" method="post" style="width: 100%; margin-top: 30px; display: flex;">
        <button style="margin-left: auto; margin-right: auto;">Delete this post</button>
    </form>
</body>
</html>
