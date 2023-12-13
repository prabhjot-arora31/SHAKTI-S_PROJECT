<% 
        String userName = request.getParameter("name");
        String userEmail = request.getParameter("email");
        String userProfile = request.getParameter("profile");
        String userAbout = request.getParameter("about");
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
    <form action="http://localhost:8080/shakti_project/update" method="post">
        
        <h3>Update your Details</h3>
        <!-- hidden inputs -->
        <input type="hidden" name="oldEmail" value="<%= userEmail %>">
        <!-- --------- -->
        <input type="text" name="name" id="name" placeholder="<%= userName %>">
        <input type="email" name="email" id="email" placeholder="<%= userEmail %>">
        <input type="password" name="password" id="password" placeholder="Set Password">
        <input type="text" name="profileUrl" id="profileUrl" placeholder="<%= userProfile %>">
        <textarea name="aboutText" id="aboutText" placeholder=" Enter About " cols="30" rows="10"></textarea>
        <input type="submit" value="Submit">
    </form>
</body>
</html>