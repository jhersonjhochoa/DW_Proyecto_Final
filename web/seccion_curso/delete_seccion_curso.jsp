<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="modal-seccion-curso-delete">
    <form id="formDeleteSeccionCurso" method="POST">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">¿Desvincular Curso?</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <p>Se va a desvincular el Curso: <b><c:out value="${sc.getCurso().getNombre()}" /></b><br>
            <input type="hidden" value="${sc.getId()}" name="id">
            <input type="hidden" value="${sc.getSeccion().getId()}" name="seccion">
            <input type="hidden" value="delete" name="action">
        </div>
        <div class="modal-footer justify-content-between">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          <button id="deleteCurso" type="button" class="btn btn-danger">Desvincular</button>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
    </form>
    <!-- /.modal-dialog -->
  </div>