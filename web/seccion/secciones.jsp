<%-- 
    Document   : alumno
    Created on : 15/05/2021, 07:14:02 PM
    Author     : Jhonatan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Blank Page</title>
  <%@include file="/componentes/css.jsp" %>
</head>
<body class="hold-transition sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">
  <!-- Navbar -->
  <%@include file="/componentes/navbar.jsp" %>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <%@include file="/componentes/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Listado Secciones</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Secciones</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="card">
        <div class="card-header">
          <button id="addSeccion" class="my-0 btn-sm btn btn-primary">Agregar</button>

          <div class="card-tools">
            <button type="button" class="btn btn-tool" data-card-widget="collapse" title="Collapse">
              <i class="fas fa-minus"></i>
            </button>
            <button type="button" class="btn btn-tool" data-card-widget="remove" title="Remove">
              <i class="fas fa-times"></i>
            </button>
          </div>
        </div>
        <div class="card-body">
            <form id="filtrosForm" method="GET" action="Seccion">
                <div class="row">
                    <div class="col-12">
                        <h4>Seleccionar años:</h4>
                        <hr class="mt-0">
                    </div>
                    <div class="form-group col-md-2 col-6">
                        <label>Desde</label>
                        <input class="form-control" type="number" name="year_from" value="${year_from}">
                    </div>
                    <div class="form-group col-md-2 col-6">
                        <label>Hasta</label>
                        <input class="form-control" type="number" name="year_to" value="${year_to}">
                    </div>
                    <div class="form-group col-12">
                        <button type="submit" class="btn btn-sm btn-outline-secondary">Filtrar</button>
                    </div>
                    <div class="col-12"><hr class="mt-0"></div>
                </div>
            </form>
            <c:if test="${error_detail != null && error_detail != ''}">
                <div class="alert alert-danger" role="alert">
                    Error. Detalle: <b><c:out value="${error_detail}"></c:out></b>
                </div>
            </c:if>
            <c:if test="${saved}">
                <div class="alert alert-success" role="alert">
                    Sección agregada correctamente.
                </div>
            </c:if>
            <c:if test="${updated}">
                <div class="alert alert-success" role="alert">
                    Sección actualizada correctamente.
                </div>
            </c:if>
            <c:if test="${deleted}">
                <div class="alert alert-success" role="alert">
                    Sección eliminada correctamente.
                </div>
            </c:if>
            <c:if test="${secciones.size() == 0}">
                <div class="alert alert-secondary" role="alert">
                  No existen secciones para los años seleccionados.
                </div>
            </c:if>
            <c:if test="${secciones.size() != 0}">
            <table id="tablaUsuarios" class="table table-bordered table-hover">
              <thead>
              <tr>
                <th>Grado</th>
                <th>Descripción</th>
                <th>Año</th>
                <th>Opciones</th>
              </tr>
              </thead>
              <tbody>
                  <c:forEach items="${secciones}" var="sec" varStatus="status">
                    <tr>
                        <td><c:out value="${sec.getGrado().getDescripcion()}" /></td>
                        <td><c:out value="${sec.getDescripcion()}" /></td>
                        <td><c:out value="${sec.getAnio()}" /></td>
                        <td>
                            <button class="editSeccion btn btn-tool" data-id="${sec.getId()}">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="deleteSeccion btn btn-tool" data-id="${sec.getId()}">
                                <i class="text-danger fas fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
            </c:if>
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
            <a href="Seccion" class="my-0 btn-sm btn btn-secondary">Actualizar</a>
        </div>
        <!-- /.card-footer-->
      </div>
      <!-- /.card -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <div id="modal-cont"></div>
  <%@include file="/componentes/footer.jsp" %>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<%@include file="/componentes/js.jsp" %>
<script>
    $('#addSeccion').on('click', () => {
        $.ajax({
          url: `Seccion`,
          type: 'GET',
          data: $('#filtrosForm').serialize()+'&action=add',
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-seccion').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error agregando sección...");
          }
        });
    });
    $('.editSeccion').on('click', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `Seccion`,
          type: 'GET',
          data: $('#filtrosForm').serialize()+'&action=update&id='+id,
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-seccion').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error actualizando seccion...");
          }
        });
    });
    $('.deleteSeccion').on('click', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `Seccion`,
          type: 'GET',
          data: $('#filtrosForm').serialize()+'&action=delete&id='+id,
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-seccion-delete').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error eliminando seccion...");
          }
        });
    });
</script>
</body>
</html>