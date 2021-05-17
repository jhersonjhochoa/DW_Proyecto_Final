<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${error_detail != null && error_detail != ''}">
    <div class="alert alert-danger" role="alert">
        No se pudo agregar. Detalle: <b><c:out value="${error_detail}"></c:out></b>
    </div>
</c:if>
<c:if test="${saved}">
    <div class="alert alert-success" role="alert">
        Alumno agregado correctamente.
    </div>
</c:if>
<c:if test="${deleted}">
    <div class="alert alert-success" role="alert">
        Alumno desvinculado correctamente.
    </div>
</c:if>
<c:if test="${alumnos_sec.size() == 0}">
    <div class="alert alert-secondary" role="alert">
      Esta sección todavía no tiene alumnos.
    </div>
</c:if>
<c:if test="${alumnos_sec.size() != 0}">
    <table id="tablaUsuarios" class="table table-bordered table-hover">
      <thead>
      <tr>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Teléfono</th>
        <th>Documento</th>
        <th>Username</th>
        <th>Opciones</th>
      </tr>
      </thead>
      <tbody>
            <c:forEach items="${alumnos_sec}" var="u" varStatus="status">
              <tr>
                  <td><c:out value="${u.getAlumno().getNombre()}" /></td>
                  <td><c:out value="${u.getAlumno().getApellido()}" /></td>
                  <td><c:out value="${u.getAlumno().getTelefono()}" /></td>
                  <td><c:out value="${u.getAlumno().getDocumento()}" /></td>
                  <td><c:out value="${u.getAlumno().getUser()}" /></td>
                  <td>
                      <button class="deleteAlumno btn btn-tool" data-id="${u.getId()}">
                          <i class="text-danger fas fa-trash"></i>
                      </button>
                  </td>
              </tr>
            </c:forEach>
      </tbody>
    </table>
</c:if>