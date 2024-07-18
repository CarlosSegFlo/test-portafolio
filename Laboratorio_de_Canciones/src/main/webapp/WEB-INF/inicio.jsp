<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio</title>
</head>

<body class="dark-theme">
    <header>
        <h1>Lyrics Lab</h1>
    </header>
    <section class="contenido">
        <article class="col-1">
            <h2>Register</h2>
            <form:form action="/registrar/usuario" method="post" modelAttribute="usuario" class="formulario">
                <div class="section-formularios">
                    <form:label path="nombre" class="detalle">Name:</form:label>
                    <section>
                        <form:input path="nombre" type="text" name="nombre" />
                        <form:errors path="nombre" class="alerta" />
                    </section>
                </div>
                <div class="section-formularios">
                    <form:label path="correo" class="detalle">Email:</form:label>
                    <section>
                        <form:input path="correo" type="text" name="correo" />
                        <form:errors path="correo" class="alerta" />
                    </section>
                </div>
                <div class="section-formularios">
                    <form:label path="contraseña" class="detalle">Password:</form:label>
                    <section>
                        <form:input path="contraseña" type="password" name="contraseña" />
                        <form:errors path="contraseña" class="alerta" />
                    </section>
                </div>
                <div class="section-formularios">
                    <form:label path="confirmarContraseña" class="detalle">Confirm Password:</form:label>
                    <section>
                        <form:input path="confirmarContraseña" type="password" name="confirmarContraseña" />
                        <form:errors path="confirmarContraseña" class="alerta" />
                    </section>
                </div>
                <section>
                    <button>Register</button>
                </section>
            </form:form>
        </article>
        <article class="col-2">
            <h2>Log in</h2>
            <form:form action="/procesa/login" method="post" modelAttribute="loginUsuario" class="formulario">
                <div class="section-formularios">
                    <form:label path="usuarioCorreo" class="detalle">Email:</form:label>
                    <section>
                        <form:input path="usuarioCorreo" type="text" name="usuarioCorreo" />
                        <form:errors path="usuarioCorreo" class="alerta" />
                    </section>
                </div>
                <div class="section-formularios">
                    <form:label path="usuarioContraseña" class="detalle">Password:</form:label>
                    <section>
                        <form:input path="usuarioContraseña" type="password" name="usuarioContraseña" />
                        <form:errors path="usuarioContraseña" class="alerta" />
                    </section>
                </div>
                <section>
                    <button>Login</button>
                </section>
            </form:form>
        </article>
    </section>
</body>

</html>
