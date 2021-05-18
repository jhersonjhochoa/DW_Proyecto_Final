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
            <h1>Agregar alumnos a sección</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Alumnos sección</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Default box -->
      <div class="card" id="cardMain">
        <div class="card-header">
            <h3 class="card-title">Listado</h3>
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
            <div class="row">
                <div class="col-md-4 col-12">
                    <div class="form-group">
                        <label>Seleccionar Sección:</label>
                        <select class="form-control" id="selectSeccion">
                            <option class="d-none" selected>Seleccionar sección...</option>
                            <c:forEach items="${secciones}" var="sec">
                                <option value="${sec.getId()}"><c:out value="${sec.getFullDescripcion()} (${sec.getAnio()})" /></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </div>
            <hr class="mt-0">
            <div id="contBtnAddAlumno" class="d-none">
                <button id="addAlumno" class="my-n2 btn btn-primary">Agregar</button>
                <hr>
            </div>
            <div id="contTablaUsuario">
                <div class="alert alert-warning" role="alert">
                    Por favor seleccione una sección para poder ver los alumnos en ella.
                </div>
            </div>
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
            <a href="AlumnoSeccion" class="my-0 btn-sm btn btn-secondary">Actualizar</a>
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
    $('#selectSeccion').on('change', function () {
        $('#cardMain').overlay(true);
        let seccion = $(this).val();
        $.ajax({
          url: `AlumnoSeccion`,
          type: 'GET',
          data: {action : 'getAlumnos', seccion: seccion},
          success: function (data) {
            $('#contTablaUsuario').html(data);
            $('#contBtnAddAlumno').removeClass("d-none");
          },
          error: function (xhr, errmsg, err) {
            console.log("error obteniendo alumnos...");
          },
          complete: function () {
            $('#cardMain').overlay(false);
          }
        });
    });
    
    $('#addAlumno').on('click', () => {
        let seccion = $('#selectSeccion').val();
        $.ajax({
          url: `AlumnoSeccion`,
          type: 'GET',
          data: {action : 'add', seccion: seccion},
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-alumno-seccion').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error agregando usuario...");
          }
        });
    });
    $('#modal-cont').on('click', '#saveAlumno', () => {
        console.log("enviando data");
        $.ajax({
          url: `AlumnoSeccion`,
          type: 'POST',
          data: $("#formAlumnoSeccion").serialize(),
          success: function (data) {
            $('#contTablaUsuario').html(data);
            $('#modal-alumno-seccion').modal('hide');
          },
          error: function (xhr, errmsg, err) {
            console.log("error guardando usuario...");
          }
        });
    });
    $('#contTablaUsuario').on('click', '.deleteAlumno', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `AlumnoSeccion`,
          type: 'GET',
          data: {action : 'delete', id: id},
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-alumno-seccion-delete').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error eliminando ...");
          }
        });
    });
    $('#modal-cont').on('click', '#deleteAlumno', () => {
        $.ajax({
          url: `AlumnoSeccion`,
          type: 'POST',
          data: $("#formDeleteAlumnoSeccion").serialize(),
          success: function (data) {
            $('#contTablaUsuario').html(data);
            $('#modal-alumno-seccion-delete').modal('hide');
          },
          error: function (xhr, errmsg, err) {
            console.log("error eiminando usuario...");
          }
        });
    });
</script>
</body>
</html>