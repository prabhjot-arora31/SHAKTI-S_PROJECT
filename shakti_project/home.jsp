
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <style>
        *{
            font-family: Verdana, Geneva, Tahoma, sans-serif;
        }
        .imgContainer{
            width: 200px;
        }
        .imgContainer img{
            width: 100%;
        }
    </style>
</head>
<body style="background-color: #F4F2EE;">
    <div
    style="display: flex; justify-content: center; align-items: center; flex-direction: column;"
    >

    <div style="width: 180px; border-radius: 50%; overflow: hidden; height: 180px; ">
        <img src="${userProfile}" style="width: 100%;" alt="Admin Image">
    </div>
  <h4>Name: ${userName}</h4>
  <h4>Email: ${userEmail}</h4>

<form id="updateForm" action="userUpdate.jsp" method="post">
    <input type="hidden" name="name" value="${userName}">
    <input type="hidden" name="email" value="${userEmail}">
    <input type="hidden" name="profile" value="${userProfile}">
    <input type="hidden" name="about" value="${userAbout}">
    <button onclick="submitForm()">Update Account</button>
</form>
<form id="deleteForm" action="userDelete.jsp" method="post">
    <input type="hidden" name="email" value="${userEmail}">

    <button onclick="deleteForm()">Delete Account</button>
</form>
<% 
String isSubscribedVar =(String) request.getAttribute("isSubscribed");
if(!"yes".equals(isSubscribedVar)){
%>
<form id="subscription" action="http://localhost:8080/shakti_project/subscribe" method="post">
    <input type="hidden" name="email" value="${userEmail}">
    <button onclick="subscribe()">Subscribe to our newsletter</button>
</form>
<%
}
else
{
%>
<div style="background-color: lightgreen; color: black; width: 290px; padding: 0.35rem; margin-bottom: 1.5rem; ">Subscribed to the newsletter.......</div>
<%  }%>
<form action="http://localhost:8080/shakti_project/logout" method="get">
    <input type="submit" value="Logout">
</form>
<button onclick="addPost()">Add a new Post</button>
<br>
</div>

<div style="padding: 2rem; background-color: white; width: 450px; margin-left: auto; margin-right: auto; ">
<h3 style="text-align: center;">

    All Posts:
</h3>
<div
style="color: black; display: flex; justify-content: center; flex-direction: column ;align-items: center; flex-wrap: wrap; gap: 25px;"
>

<% 
List<Map<String, String>> combinedData = (List<Map<String, String>>) request.getAttribute("combinedData");

if (combinedData != null && !combinedData.isEmpty()) {
    for (Map<String, String> row : combinedData) {
%>
        <div style=" width: 380px;; border: 2px solid grey; padding: 0.65rem; border-radius: 20px;display: flex; flex-direction: column; align-items: center; justify-content: center;">
            <div style="width: 100%; display: flex; align-items: center; justify-content: space-around;">
            <div style="display: flex; align-items: center; gap: 13px;">
                <div style="width: 60px; border-radius: 50%; overflow: hidden; height: 60px; ">
                    <img src="${userProfile}" style="width: 100%;" alt="Admin Image">
                </div>
                <p style=" font-size: 13px; font-weight: bolder; ">
                    <!-- Post id is: <%= row.get("post_id") %> -->
                    ${userName}</p>
            </div>
            <div>
                <span class="material-symbols-outlined" style="cursor: pointer;position: relative;" id="moreButton">
                    more_horiz
                    </span>
                    <div id="postDetails" style="display: none;position: relative; top: 20px;">
                        <a id="editPostLink" href="#">Edit Post</a>
                    </div>
            </div>
        </div>

            <!-- <p><% row.get("time"); %></p> -->
            <% String post_id = row.get("post_id"); %>
            
<a href='DeletePostServlet?post_id=<%= row.get("post_id") %>' style="text-decoration: none; color:  black;">


           <p style="text-align: center; font-size: 13px; "><%= row.get("content") %></p>

            <div style="width: 330px; margin-left: auto; margin-right: auto; overflow: hidden; border-radius: ; ">
                <img style="width: 100%;" src='
                <%= row.get("image_url") %>' alt="">
            </div>
            <div style="display: flex;width: 100%; justify-content: center; gap: 23px; padding: 0.65rem; font-size: 13px; "> 
                <div style="cursor: pointer;">
                    <i class="fa-regular fa-thumbs-up"></i>
                    Like</div>
                <div style="cursor: pointer;">
                    <i class="fa-regular fa-comment"></i>
                    Comment</div>
            </div>


        </a>
        

             </div>
<%
    }
} else {
%>
    <p>No data available.</p>
<%
}
%>
</div>
</div>

<br>


  <script>
   document.getElementById("${post_id}").addEventListener("click",()=>{
    console.log("Clicked")
    document.location.href = 'http://localhost:8080/shakti_project/post_id={post_id}';
   })
    function addPost()
    {
        document.location.href = "http://localhost:8080/shakti_project/post.jsp";
    }
    function submitForm() {
        document.getElementById("updateForm").submit();
    }
    function submitForm() {
        document.getElementById("deleteForm").submit();
    }
    function subscribe()
    {
        document.getElementById("subscription").submit();
    }
</script>

</body>
</html>