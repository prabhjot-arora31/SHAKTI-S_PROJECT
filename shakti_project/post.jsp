<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello</title>
</head>
<body>
        Add a new POST
        <%
    // Retrieve session attributes
    String username = (String) session.getAttribute("user");
    String id = (String) session.getAttribute("user_id");
    // Access other session attributes as needed
%>
    Email is: <%= username %>
    ID is: <%= id %>
        <form action="http://localhost:8080/shakti_project/post
        " method="post">
            <input type="hidden" name="email" value="<%= username %>">
            <input type="hidden" name="user_id" value="<%= id %>">
           <textarea name="content" id="content" col    s="30" rows="10"></textarea>
           <br>
           <input type="text" name="imageUrl" id="imageUrl" placeholder="Image URL (if any)">
<br>    
           <input type="submit" value="Submit" > 
        </form>
</body>
</html>