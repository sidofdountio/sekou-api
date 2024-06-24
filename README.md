<h1>sekou-api</h1>
    <hr>
    <p>This repository content the API of Sekou API</p>
    <hr>
    <p>If you want to run Junit Test</p>
    <h2>Junit test</h2>
    <p>Open the app with any IDE and run Junit test method or run following command</p>
    <p>Make sure to be in the app current directory</p>
    <ul><li><b>$ mvn test -X </b></li></ul>
    <h2>HOW TO RUN THE API</h2>
    <hr>
    <h3>Requirement</h3>
    <ul><li>Docker version 24.0.7</li><li>Docker compose v2 </li> <li>Optional Docker compose v1 </li></ul>
    <hr>
    <h3>Install or Run step</h3>
    <p>Make sure you are on main directory of the app</p>
    <ol>
        <p>To create database image based on Postgres</p>
        <li><b>$ docker compose up -d</b></li>
        <p>Optional if you have docker compose v1 instead of v2</p>
        <li><b>$ docker-compose up -d</b><small style="color: green"> optional</small></li>
        <p>Now Create the database named <b>dbsekou</b></p>
        <li><b>$ docker exec -it  postgre-sekou-db bash</b></li>
        <p>Execute and Access the  database on iterative mode</p>
        <li><b>$ psql -U sidof</b></li>
        <p>You are now on the DATABASE </p>
        <li><b>$ CREATE DATABASE dbsekou;</b> </li>
        <p>The database it now created. you can the following command to see it</p>
        <li><b>$ \l</b></li>
        <p> You will be able to see the database on list.</p>
        <p>Exit.</p>
        <p>Now let run the api using maven</p>
        <li><b>$ mvn spring-boot:run </b></li>
        <p>At this step the app it's running</p>
    </ol>
    <hr>
    <h4>The end point</h4>
    <p>You can use Postman or any Test api you like</p>
    <ol>
        <li>
            <a href="http://localhost:8086/api/v1/sekou">
                http://localhost:8086/api/v1/beco-sekou
            </a>
        </li>
    </ol>
