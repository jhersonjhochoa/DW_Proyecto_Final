<%-- 
    Document   : update_alumno
    Created on : 15/05/2021, 07:35:48 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="modal fade" id="modal-seccion">
  <form method="POST" action="Seccion?action=${action}">
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
                  <label>Grado:</label>
                  <select name="grado" class="form-control">
                      <c:forEach items="${grados}" var="g">
                          <option value="${g.getId()}" <c:if test="${g.getId() == seccion.getGrado().getId()}">selected</c:if>><c:out value="${g.getDescripcion()}" /></option>
                      </c:forEach>
                  </select>
              </div>
              <div class="form-group">
                  <label>Descripcion:</label>
                  <input name="descripcion" class="form-control" required value="${seccion.getDescripcion()}">
              </div>
              <div class="form-group">
                  <label>Año</label>
                  <select name="anio" class="form-control">
                      <c:forEach begin="${anio}" end="${anio + 2}" var="a">
                          <option><c:out value="${a}" /></option>
                      </c:forEach>
                  </select>
              </div>
              <input type="hidden" name="year_from" value="${year_from}">
              <input type="hidden" name="year_to" value="${year_to}">
      </div>
      <div class="modal-footer justify-content-between">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        
        <c:if test="${action == 'add'}">
            <button type="submit" class="btn btn-primary">Guardar</button>
        </c:if>
        <c:if test="${action == 'update'}">
            <input type="hidden" name="id" value="${seccion.getId()}">
            <button type="submit" class="btn btn-success">Actualizar</button>
        </c:if>
        
      </div>
    </div>
    <!-- /.modal-content -->
    
  </div>
  
  </form>
  <!-- /.modal-dialog -->
</div>