<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="modal fade" id="modal-usuario-delete">
    <form action="Usuario?action=delete" method="POST">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">¿Eliminar Usuario?</h4>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
            <p>Confirmar si desea eliminar el usuario <b><c:out value="${usuario.getUser()}" /></b></p>
            <input type="hidden" value="${usuario.getId()}" name="id">
        </div>
        <div class="modal-footer justify-content-between">
          <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
          <button type="submit" class="btn btn-danger">Eliminar</button>
        </div>
      </div>
      <!-- /.modal-content -->
    </div>
    </form>
    <!-- /.modal-dialog -->
  </div>