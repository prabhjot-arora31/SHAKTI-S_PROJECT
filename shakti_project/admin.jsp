<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Users List</title>
</head>
<body style="display: flex;flex-direction: column; justify-content: center;align-items: center;">
    <div style="display: flex; justify-content: space-between; width: 50%; align-items: center;flex-wrap: wrap;">
        <h1>Users List</h1>
        <button onclick="go()">Logout</button>
    </div>
    
    <table border="1">
        <tr>
            <th>Profile</th>
            <th>Name</th>
            <th>Email</th>
            <th>About Text</th>
        </tr>
        <% 
        List<List<String>> userList = (List<List<String>>) request.getAttribute("users");
        if (userList != null) {
            for (List<String> user : userList) {
        %>
        <tr>
            <td>
               <div style="width: 78.3px; height: 120.3px ;display: flex; justify-content: center; align-items: center; ">
                <img
                style="width: 100%;border-radius: 75%;"
                src="
                <%= user.get(2) %>
                
                " alt="">
               </div>
            </td>
            <td><%= user.get(0) %></td>
            <td>
                <a href="mailto:<%= user.get(1) %>" style="color: black; text-decoration: underline; ">

                    <%= user.get(1) %>
                </a>
            </td>
            <td><%= user.get(3) %></td>
        </tr>
        <% 
            }
        }
        %>
    </table>
    <script>
        function go()
        {
            document.location.href = "http://localhost:8080/shakti_project/";
        }
    </script>
</body>
</html>
