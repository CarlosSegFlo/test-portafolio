<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Editar Canción</title>
</head>
<body class="dark-theme">
    <header>
        <h2>Editando: ${canciones.nombre}</h2>
        <h3>Iniciada por: ${canciones.usuario.nombre}</h3>
    </header>
    
    <form:form action="${pageContext.request.contextPath}/songs/${canciones.id}/edit" method="post" modelAttribute="canciones" class="formulario section-formularios">
        <input type="hidden" name="_method" value="put"/>
        
        <article class="form-group">
            <form:label path="nombre" class="detalle">Song Title:</form:label>
            <section class="form-field">
                <form:input path="nombre" type="text" value="${canciones.nombre}"/>
                <form:errors path="nombre" class="alerta"/>
            </section>
        </article>
        
        <article class="form-group">
            <form:label path="genero" class="detalle">Genre:</form:label>
            <section class="form-field">
                <form:input path="genero" type="text" value="${canciones.genero}"/>
                <form:errors path="genero" class="alerta"/>
            </section>
        </article>
        
        <article class="form-group">
            <form:label path="letra" class="detalle">Add your lyrics:</form:label>
            <section class="form-field">
                <form:textarea path="letra" rows="10" cols="30" minlength="5" required="true" value="${canciones.letra}"></form:textarea>
                <form:errors path="letra" class="alerta"/>
            </section>
        </article>
        
        <button type="submit">Submit</button>
    </form:form>
    
    <a href="${pageContext.request.contextPath}/home" class="cancel">Cancel</a>
    
    <form:form action="${pageContext.request.contextPath}/delete/song/${canciones.id}" method="post" class="formulario section-formularios">
        <input type="hidden" name="_method" value="delete"/>
        <button>DELETE</button>
    </form:form>
</body>
</html>
