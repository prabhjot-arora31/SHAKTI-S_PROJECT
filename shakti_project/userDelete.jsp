<% 
        String userEmail = request.getParameter("email");
        %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Page</title>
    <style>
        *{
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}
form{
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    gap: 20px;
    padding: 7.5px;
}
form input{
    padding: 0.4rem 3px;
    border-radius: 6px;
    border: 2px solid black;
    width: 290px;
}
form textarea{
    border: 2px solid black;
    border-radius: 6px;
    width: 290px;
}
input[type="submit"]{
    transition: .3s;
}
input[type="submit"]:hover{
    cursor: pointer;
    background-color: black;
    color: white;
}
    </style>
</head>
<body>
    <form action="http://localhost:8080/shakti_project/delete" method="post">
        
        <h3>Are you sure you want to delete your account? </h3>
        <br />
        <h4>This action can't be undone.....</h4>
<input type="hidden" name="email" value="<%= userEmail %>">
        <input type="submit" value="Submit">
    </form>
</body>
</html>