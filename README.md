<p align="center" width="100%">
  <img src="images/logo.svg" width="196">
</p>

# [TopJava](https://javaops.ru/view/topjava) Graduation Project


## Lunch Voting System API

-------------------------------------------------------------
This is a RESTful web service for deciding where to have lunch.
<br>**The project acts as a backend API without a frontend.**
<br>It allows administrators to manage restaurant menus on a daily basis and enables users to vote for their choice,
with a business rule that prevents changing votes after 11:00 AM.

## Technical Requirements

-------------------------------------------------------------

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote for a restaurant they want to have lunch at today
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

[Original Task Definition](https://github.com/JavaOPs/topjava/blob/master/graduation.md)

## Technologies

-------------------------------------------------------------

<a href="https://www.java.com/en/">
  <img src="https://www.svgrepo.com/show/303388/java-4-logo.svg" width="50" height="50">
</a>

<a href="https://spring.io/projects/spring-boot">
  <img src="https://www.svgrepo.com/show/354380/spring-icon.svg" width="50" height="50">
</a>

<a href="https://spring.io/projects/spring-data-jpa">
  <img src="https://spring.io/img/projects/spring-data.svg" width="50" height="50">
</a>

<a href="https://hibernate.org/">
  <img src="https://cdn.worldvectorlogo.com/logos/hibernate.svg" width="50" height="50">
</a>

<a href="https://junit.org/">
  <img src="https://logo.svgcdn.com/devicon/junit-original-wordmark.png" width="50" height="50">
</a>

<a href="https://maven.apache.org/">
  <img src="https://www.svgrepo.com/show/354051/maven.svg" width="50" height="50">
</a>

<a href="https://projectlombok.org/">
  <img src="https://avatars.githubusercontent.com/u/45949248?s=280&v=4" width="50" height="50">
</a>

<a href="https://swagger.io/">
  <img src="https://upload.wikimedia.org/wikipedia/commons/a/ab/Swagger-logo.png" width="50" height="50">
</a>

<a href="https://github.com/Skapud/topjava_boot_graduation">
  <img src="https://cdn.worldvectorlogo.com/logos/github-icon.svg" width="50" height="50">
</a>

## Get started

-------------------------------------------------------------

```
git clone https://github.com/Skapud/topjava_boot_graduation.git
```  

Run: `mvn spring-boot:run` in root directory.

-----------------------------------------------------
[REST API documentation](http://localhost:8080/)

Authorization: Basic Auth

Credentials:

```
Admin: admin@gmail.com / admin
User:  user@yandex.ru / password
Guest: guest@gmail.com / guest
```
