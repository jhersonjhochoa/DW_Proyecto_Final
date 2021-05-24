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
            <h1>Listado Usuarios</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Usuarios</li>
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
          <button id="addUsuario" class="my-0 btn-sm btn btn-primary">Agregar</button>

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
            <c:if test="${error_detail != null && error_detail != ''}">
                <div class="alert alert-danger" role="alert">
                    Error. Detalle: <b><c:out value="${error_detail}"></c:out></b>
                </div>
            </c:if>
            <c:if test="${saved}">
                <div class="alert alert-success" role="alert">
                    Usuario agregado correctamente.
                </div>
            </c:if>
            <c:if test="${updated}">
                <div class="alert alert-success" role="alert">
                    Usuario actualizado correctamente.
                </div>
            </c:if>
            <c:if test="${deleted}">
                <div class="alert alert-success" role="alert">
                    Usuario eliminado correctamente.
                </div>
            </c:if>
            <table id="tablaUsuarios" class="table table-bordered table-hover">
              <thead>
              <tr>
                <th>Rol</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Teléfono</th>
                <th>Documento</th>
                <th>Username</th>
                <th>Opciones</th>
              </tr>
              </thead>
              <tbody>
                  <c:forEach items="${usuarios}" var="u" varStatus="status">
                    <tr>
                        <td><c:out value="${u.getRol().getDescripcion()}" /></td>
                        <td><c:out value="${u.getNombre()}" /></td>
                        <td><c:out value="${u.getApellido()}" /></td>
                        <td><c:out value="${u.getTelefono()}" /></td>
                        <td><c:out value="${u.getDocumento()}" /></td>
                        <td><c:out value="${u.getUser()}" /></td>
                        <td>
                            <button class="editUsuario btn btn-tool" data-id="${u.getId()}">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="deleteUsuario btn btn-tool" data-id="${u.getId()}">
                                <i class="text-danger fas fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
            <a href="Usuario" class="my-0 btn-sm btn btn-secondary">Actualizar</a>
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
    $('#addUsuario').on('click', () => {
        $.ajax({
          url: `Usuario`,
          type: 'GET',
          data: {action : 'add'},
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-usuario').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error agregando usuario...");
          }
        });
    });
    $('.editUsuario').on('click', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `Usuario`,
          type: 'GET',
          data: {action : 'update', id: id},
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-usuario').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error agregando usuario...");
          }
        });
    });
    $('.deleteUsuario').on('click', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `Usuario`,
          type: 'GET',
          data: {action : 'delete', id: id},
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-usuario-delete').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error agregando usuario...");
          }
        });
    });
</script>
</body>
</html>