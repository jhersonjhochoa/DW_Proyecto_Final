<%-- 
    Document   : update_alumno
    Created on : 15/05/2021, 07:35:48 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="modal fade" id="modal-curso">
  <form method="POST" action="Curso?action=${action}">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <h4 class="modal-title">
              <c:if test="${action == 'add'}">
                  Agregar Sección
              </c:if>
              <c:if test="${action == 'update'}">
                  Editar Sección
              </c:if>
          </h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
            <label>Nombre:</label>
            <input name="nombre" class="form-control" required value="${curso.getNombre()}">
        </div>
      </div>
      <div class="modal-footer justify-content-between">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
        <c:if test="${action == 'add'}">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </c:if>
        <c:if test="${action == 'update'}">
            <input type="hidden" name="id" value="${curso.getId()}">
            <button type="submit" class="btn btn-success">Actualizar</button>
        </c:if>
        
      </div>
    </div>
    <!-- /.modal-content -->
    
  </div>
  
  </form>
  <!-- /.modal-dialog -->
</div>