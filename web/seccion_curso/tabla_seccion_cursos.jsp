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
<c:if test="${seccion_cursos.size() == 0}">
    <div class="alert alert-secondary" role="alert">
      Esta sección todavía no tiene cursos asignados.
    </div>
</c:if>
<c:if test="${seccion_cursos.size() != 0}">
    <table id="tablaUsuarios" class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>Curso</th>
        <th>Docente</th>
        <th>Opciones</th>
      </tr>
      </thead>
      <tbody>
            <c:forEach items="${seccion_cursos}" var="cs" varStatus="status">
              <tr>
                  <td><c:out value="${cs.getCurso().getNombre()}" /></td>
                  <td><c:out value="${cs.getDocente().getFullName()}" /></td>
                  <td>
                      <button class="editRegistro btn btn-tool" data-id="${cs.getId()}">
                          <i class="fas fa-edit"></i>
                      </button>
                      <button class="deleteRegistro btn btn-tool" data-id="${cs.getId()}">
                          <i class="text-danger fas fa-trash"></i>
                      </button>
                  </td>
              </tr>
            </c:forEach>
      </tbody>
    </table>
</c:if>