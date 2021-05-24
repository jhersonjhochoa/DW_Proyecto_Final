<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error_detail != null && error_detail != ''}">
    <div class="alert alert-danger" role="alert">
        Error. Detalle: <b><c:out value="${error_detail}"></c:out></b>
    </div>
</c:if>
<c:if test="${saved}">
    <div class="alert alert-success" role="alert">
        Curso agregado correctamente.
    </div>
</c:if>
<c:if test="${updated}">
    <div class="alert alert-success" role="alert">
        Registro actualizado correctamente.
    </div>
</c:if>
<c:if test="${deleted}">
    <div class="alert alert-success" role="alert">
        Curso desvinculado correctamente.
    </div>
</c:if>
Mostrando resultados de <b><c:out value="${sc.getSeccion().getFullDescripcion()}"/>,</b> curso <b><c:out value="${sc.getCurso().getNombre()}"/></b>
<hr class="my-1">
<c:if test="${evaluaciones.size() == 0}">
    <div class="alert alert-warning" role="alert">
      Esta sección todavía no tiene cursos asignados.
    </div>
</c:if>
<c:if test="${evaluaciones.size() != 0}">
    <table id="tablaUsuarios" class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>Descripción</th>
        <th>Porcentaje</th>
        <th>¿Es bonus?</th>
        <th>Opciones</th>
      </tr>
      </thead>
      <tbody>
            <c:forEach items="${evaluaciones}" var="e" varStatus="status">
              <tr>
                  <td><c:out value="${e.getDescripcion()}" /></td>
                  <td><c:out value="${e.getPorcentaje()} %" /></td>
                  <td>
                      <c:if test="${e.isBonus()}">SI</c:if>
                      <c:if test="${!e.isBonus()}">NO</c:if>
                  </td>
                  <td>
                      <button class="editRegistro btn btn-tool" data-id="${e.getId()}">
                          <i class="fas fa-edit"></i>
                      </button>
                      <button class="deleteRegistro btn btn-tool" data-id="${e.getId()}">
                          <i class="text-danger fas fa-trash"></i>
                      </button>
                  </td>
              </tr>
            </c:forEach>
      </tbody>
    </table>
    La sumatoria de las evaluaciones que no son bonus deben ser del 100%.<br>
    Actualmente las tareas suman <b><c:out value="${suma_porcentaje}"/>%</b> y los bonus <b><c:out value="${suma_bonus}"/>%</b></b>.
    <hr class="my-1">
</c:if>
<form id="formCurrentValues">
    <input type="hidden" name="id_sc" value="${sc.getId()}">
</form>