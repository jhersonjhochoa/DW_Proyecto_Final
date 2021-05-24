<%-- 
    Document   : update_alumno
    Created on : 15/05/2021, 07:35:48 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<div class="modal fade" id="modal-evaluacion">
    <form id="formEvaluacion">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <c:if test="${action == 'add'}">Agregar Evaluación</c:if>
                    <c:if test="${action == 'update'}">Editar Registro</c:if>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <b>Sección: </b><c:out value="${sc.getSeccion().getFullDescripcion()}" /><br>
                        <b>Curso: </b><c:out value="${sc.getCurso().getNombre()}" /><br>
                    </div>
                    <hr class="mt-0">
                    <div class="form-group">
                        <label>Descripción:</label>
                        <input type="text" name="descripcion" class="form-control" required value="${evaluacion.getDescripcion()}">
                    </div>
                    <div class="form-group">
                        <label>Porcentaje:</label>
                        <input type="number" name="porcentaje" class="form-control" required value="${evaluacion.getPorcentaje()}" min="0" max="100">
                    </div>
                    <div class="form-group">
                        <label class="form-label">Bonus:*</label>
                        <div class="form-check">
                            <input id="rdBonusSi" type="radio" name="bonus" class="form-check-input" required value="true" <c:if test="${evaluacion.isBonus()}">checked</c:if>>
                            <label for="rdBonusSi" class="form-check-label">SI</label>
                        </div>
                        <div class="form-check">
                            <input id="rdBonusNo" type="radio" name="bonus" class="form-check-input" required value="false" <c:if test="${!evaluacion.isBonus()}">checked</c:if>>
                            <label for="rdBonusNo" class="form-check-label">NO</label>
                        </div>
                        <hr class="mb-0">
                        <i class="text-muted">Se considera bonus si es una nota adicional a la sumatoria del 100%.</i>
                        <hr class="mt-1">
                    </div>
                </div>
                <div class="modal-footer justify-content-between">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <input type="hidden" name="id_sc" value="${sc.getId()}">
                    <input type="hidden" name="action" value="${action}">
                    <c:if test="${action == 'add'}">
                        <button id="saveRegistro" type="button" class="btn btn-primary">Agregar</button>
                    </c:if>
                    <c:if test="${action == 'update'}">
                        <input type="hidden" name="id" value="${evaluacion.getId()}">
                        <button id="saveRegistro" type="button" class="btn btn-success">Actualizar</button>
                    </c:if>
                </div>
            </div>
            <!-- /.modal-content -->

        </div>

    </form>
    <!-- /.modal-dialog -->
</div>