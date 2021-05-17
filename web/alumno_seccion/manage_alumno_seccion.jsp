<%-- 
    Document   : update_alumno
    Created on : 15/05/2021, 07:35:48 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="modal fade" id="modal-alumno-seccion">
  <form id="formAlumnoSeccion">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
          <h4 class="modal-title">
             Agregar Alumno
          </h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
              <div class="form-group">
                  <label>Documento:</label>
                  <input name="documento" class="form-control" required>
              </div>
      </div>
      <div class="modal-footer justify-content-between">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
        <button id="saveAlumno" type="button" class="btn btn-primary">Agregar</button>
        <input type="hidden" name="seccion" value="${seccion}">
        <input type="hidden" name="action" value="add">
      </div>
    </div>
    <!-- /.modal-content -->
    
  </div>
  
  </form>
  <!-- /.modal-dialog -->
</div>