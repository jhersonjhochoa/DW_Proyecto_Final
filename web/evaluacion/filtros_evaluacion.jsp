<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="POST" id="filtrosForm">
    <div class="row">
    <div class="col-md-4 col-12">
        <div class="form-group">
            <label>Seleccionar Grado:</label>
            <select name="grado" class="form-control" id="selectGrado">
                <option class="d-none">Seleccionar grado...</option>
                <c:forEach items="${grados}" var="g">
                    <option value="${g.getId()}" <c:if test="${g.getId() == grado}">selected</c:if>><c:out value="${g.getDescripcion()}" /></option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-4 col-12">
        <div class="form-group">
            <label>Seleccionar Sección:</label>
            <select name="seccion" class="form-control" id="selectSeccion" <c:if test="${secciones == null}">disabled</c:if>>
                <option class="d-none" value="0">Seleccionar sección...</option>
                <c:forEach items="${secciones}" var="sec">
                    <option value="${sec.getId()}" <c:if test="${sec.getId() == seccion}">selected</c:if>><c:out value="${sec.getDescripcion()}" /></option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-4 col-12">
        <div class="form-group">
            <label>Seleccionar Curso:</label>
            <select name="curso" class="form-control" id="selectCurso" <c:if test="${s_cursos == null}">disabled</c:if>>
                <option class="d-none" value="0">Seleccionar curso...</option>
                <c:forEach items="${s_cursos}" var="c">
                    <option value="${c.getCurso().getId()}" <c:if test="${c.getId() == curso}">selected</c:if>><c:out value="${c.getCurso().getNombre()}" /></option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-12">
        <button type="button" class="btn btn-secondary" id="filterBtn" disabled>Consultar</button> 
    </div>
    </div>
</form>