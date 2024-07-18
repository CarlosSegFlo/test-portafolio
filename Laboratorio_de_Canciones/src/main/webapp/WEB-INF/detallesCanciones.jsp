<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Details</title>
</head>
<body class="dark-theme">
    <header>
        <h2>${canciones.nombre}</h2>
        <h3>Started by ${canciones.usuario.nombre}</h3>
    </header>
    
    <section class="details">
        <article>
            <h3>Genre:</h3>
            <p>${canciones.genero}</p>
        </article>
        
        <article>
            <h3>Lyrics :</h3>
            <p>${canciones.letra}</p>
        </article>
    </section>
    <footer>
        <button><a href="/home" class="cancel">Volver</a></button>
        <button><a href="/songs/${canciones.id}/edit" class="cancel"> < Contribute</a></button>
    </footer>
</body>
</html>
