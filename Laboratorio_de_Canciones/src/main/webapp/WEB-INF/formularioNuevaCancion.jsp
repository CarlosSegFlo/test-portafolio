<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Add Your Song</title>
</head>

<body class="dark-theme">
    <header>
        <h2>Start a new Song!</h2>
    </header>
    <form:form action="${pageContext.request.contextPath}/registrar/cancion" method="post" modelAttribute="canciones" class="formulario section-formularios">
        <article>
            <form:label path="nombre" class="detalle">Song Title:</form:label>
            <section>
                <form:input path="nombre" type="text" />
                <form:errors path="nombre" class="alerta" />
            </section>
        </article>
        <article>
            <form:label path="genero" class="detalle">Genre:</form:label>
            <section>
                <form:input path="genero" type="text" />
                <form:errors path="genero" class="alerta" />
            </section>
        </article>
        <article>
            <form:label path="letra" class="detalle">Add your lyrics:</form:label>
            <section class="descripcion">
                <form:textarea path="letra" rows="10" cols="30" minlength="5" required="true"></form:textarea>
                <form:errors path="letra" class="alerta" />
            </section>
        </article>
        <button type="submit">submit</button>
    </form:form>
    <a href="/home" class="cancel">Cancelar</a>
</body>

</html>
