# spring_rest_api
This is a sample project to practice & implement REST APIs using spring framework and Spring Security

### Implemeted Following REST APIs for Courses

1) **GET**    -  `http://localhost:8080/courses`  <sub> (_Get All Courses From Database_) -  Access - Authenticated Users only <sub/> 
2) **GET**   -  `http://localhost:8080/courses/1` <sub> (_Get Course By Course ID_)       -  Access - Authenticated Users only <sub/> 
3) **POST**   -  `http://localhost:8080/courses`  <sub> (_Add New Course To Database_)    -  Access - API Access to ROLE_ADMIN only <sub/>  
4) **PUT**    -  `http://localhost:8080/course`   <sub> (_Update Existing Course From Database_)      - Access - ROLE_ADMIN only <sub/>   
6) **DELETE** -  `http://localhost:8080/course/2` <sub> (_Delete Courses From Database By Course ID_) - Access -  ROLE_ADMIN only <sub/>   

### Added User Roles to the project i.e ROLE_STUDENT, ROLE_ADMIN with their APIs
1) **POST**    -  `http://localhost:8080/login`            <sub> (_Log In to App_)         - Access -  All <sub/> 
2) **POST**    -  `http://localhost:8080/api/user/save`    <sub> (_Save New User to DB_)   - Access - ROLE_ADMIN only <sub/> 
3) **POST**   -  `http://localhost:8080/api/role/save`     <sub> (_Create New Role_)       - Access -  ROLE_ADMIN only <sub/> 
4) **POST**   -  `http://localhost:8080/api/role/addtouser` <sub> (_Specify User Roles_)    - Access -  ROLE_ADMIN only <sub/> 
6) **DELETE** -  `http://localhost:8080/api/user/delete?username` <sub> (_Delete User From Database By Username_) - Access -OLE_ADMIN only <sub/> 
7) **GET**    -  `http://localhost:8080/api/users`  <sub> (_Get All Users With Their Roles_) - Access - ROLE_ADMIN only <sub/> 
