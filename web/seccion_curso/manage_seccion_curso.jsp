<%-- 
    Document   : update_alumno
    Created on : 15/05/2021, 07:35:48 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="modal fade" id="modal-seccion-curso">
  <form id="formSeccionCurso">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
            <c:if test="${action == 'add'}">
                Agregar Curso
            </c:if>
            <c:if test="${action == 'update'}">
                Editar Registro
            </c:if>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="form-group">
            <label>Curso:</label>
            <select name="curso" class="form-control">
                <c:forEach items="${cursos}" var="c">
                    <option value="${c.getId()}" <c:if test="${c.getId() == sc.getCurso().getId()}">selected</c:if>><c:out value="${c.getNombre()}" /></option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label>Docente:</label>
            <select name="docente" class="form-control">
                <c:forEach items="${docentes}" var="d">
                    <option value="${d.getId()}" <c:if test="${d.getId() == sc.getDocente().getId()}">selected</c:if>><c:out value="${d.getFullName()}" /></option>
                </c:forEach>
            </select>
        </div>
      </div>
      <div class="modal-footer justify-content-between">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        <input type="hidden" name="seccion" value="${seccion}">
        <input type="hidden" name="action" value="${action}">
        <c:if test="${action == 'add'}">
            <button id="saveRegistro" type="button" class="btn btn-primary">Agregar</button>
        </c:if>
        <c:if test="${action == 'update'}">
            <input type="hidden" name="id" value="${sc.getId()}">
            <button id="saveRegistro" type="button" class="btn btn-success">Actualizar</button>
        </c:if>
      </div>
    </div>
    <!-- /.modal-content -->
    
  </div>
  
  </form>
  <!-- /.modal-dialog -->
</div>