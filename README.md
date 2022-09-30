# Edu-Site - Educational Site with Different Features
### This is a sample project to practice & implement different Spring Technologies. 
![This is an image](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![This is an image](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![This is an image](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![This is an image](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)

### Implemeted Following REST APIs for Courses

1) **GET**    -  `http://localhost:8080/api/courses`  <sub> (_Get All Courses From Database_) -  Access - Authenticated Users only <sub/> 
2) **GET**   -  `http://localhost:8080/api/course/1` <sub> (_Get Course By Course ID_)       -  Access - Authenticated Users only <sub/> 
3) **POST**   -  `http://localhost:8080/api/course`  <sub> (_Add New Course To Database_)    -  Access - API Access to ADMIN only <sub/>  
4) **PUT**    -  `http://localhost:8080/api/course`   <sub> (_Update Existing Course From Database_)      - Access - ADMIN only <sub/>   
6) **DELETE** -  `http://localhost:8080/api/course/2` <sub> (_Delete Courses From Database By Course ID_) - Access -  ADMIN only <sub/>   

### Added User Roles to the project i.e ROLE_STUDENT, ROLE_ADMIN with their APIs
1) **POST**    -  `http://localhost:8080/login`            <sub> (_Log In to App_)         - Access -  All <sub/> 
2) **POST**    -  `http://localhost:8080/api/user`    <sub> (_Save New User to DB_)   - Access - ADMIN only <sub/> 
3) **POST**   -  `http://localhost:8080/api/role`     <sub> (_Create New Role_)       - Access -  ADMIN only <sub/> 
4) **POST**   -  `http://localhost:8080/api/role/add-role-to-user` <sub> (_Specify User Roles_)    - Access -  ADMIN only <sub/> 
6) **DELETE** -  `http://localhost:8080/api/user?username` <sub> (_Delete User From Database By Username_) - ADMIN only <sub/> 
7) **GET**    -  `http://localhost:8080/api/user`  <sub> (_Get All Users With Their Roles_) - Access - ADMIN only <sub/> 

### Database Entity Relationship Diagram
![image](https://user-images.githubusercontent.com/88383863/186356933-a2f3b7a8-f372-473d-8ab1-6b98068a7f06.png)

