<%-- 
    Document   : update_alumno
    Created on : 15/05/2021, 07:35:48 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="modal fade" id="modal-usuario">
    <form method="POST" action="Usuario?action=${action}">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <h4 class="modal-title">
              <c:if test="${action == 'add'}">
                  Agregar Usuario
              </c:if>
              <c:if test="${action == 'update'}">
                  Editar Usuario
              </c:if>
          </h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
              <div class="form-group">
                  <label>Rol Usuario:</label>
                  <select name="rol" class="form-control">
                      <c:forEach items="${roles_usuario}" var="ru">
                          <option value="${ru.getId()}" <c:if test="${ru.getId() == usuario.getRol().getId()}">selected</c:if>><c:out value="${ru.getDescripcion()}" /></option>
                      </c:forEach>
                  </select>
              </div>
              <div class="form-group">
                  <label>Nombre:</label>
                  <input name="nombre" class="form-control" required value="${usuario.getNombre()}">
              </div>
              <div class="form-group">
                  <label>Apellido:</label>
                  <input name="apellido" class="form-control" required value="${usuario.getApellido()}">
              </div>
              <div class="form-group">
                  <label>Telefono:</label>
                  <input name="telefono" class="form-control" required value="${usuario.getTelefono()}">
              </div>
              <div class="form-group">
                  <label>Documento:</label>
                  <input name="documento" class="form-control" required value="${usuario.getDocumento()}">
              </div>
              <div class="form-group">
                  <label>Username:</label>
                  <input name="user" class="form-control" required value="${usuario.getUser()}">
              </div>
      </div>
      <div class="modal-footer justify-content-between">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
        <c:if test="${action == 'add'}">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </c:if>
        <c:if test="${action == 'update'}">
            <input type="hidden" name="id" value="${usuario.getId()}">
            <button type="submit" class="btn btn-success">Actualizar</button>
        </c:if>
        
      </div>
    </div>
    <!-- /.modal-content -->
    
  </div>
  
  </form>
  <!-- /.modal-dialog -->
</div>