<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="modal-alumno-seccion-delete">
    <form id="formDeleteAlumnoSeccion" method="POST">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">¿Desvincular Usuario?</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <p>Se va a desvincular el alumno:<br> 
                <b>Documento: </b><c:out value="${as.getAlumno().getDocumento()}" /><br>
                <b>Sección: </b><c:out value="${as.getSeccion().getFullDescripcion()} (${as.getSeccion().getAnio()})" /></p>
            <input type="hidden" value="${as.getId()}" name="id">
            <input type="hidden" value="${as.getSeccion().getId()}" name="seccion">
            <input type="hidden" value="delete" name="action">
        </div>
        <div class="modal-footer justify-content-between">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          <button id="deleteAlumno" type="button" class="btn btn-danger">Desvincular</button>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
    </form>
    <!-- /.modal-dialog -->
  </div>