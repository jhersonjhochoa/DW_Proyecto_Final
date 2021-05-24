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
            <h1>Administrar Evaluaciones</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Evaluaciones</li>
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
            <div id="filtrosRow">
                <%@include file="/evaluacion/filtros_evaluacion.jsp" %>
            </div>
            <hr class="mt-2">
            <div id="contBtnAddEvaluacion" class="d-none">
                <button id="addEvaluacion" class="my-n2 btn btn-primary">Agregar</button>
                <hr>
            </div>
            <div id="contTablaEvaluaciones">
                <div class="alert alert-warning" role="alert">
                    Para ver detalle, realice una consulta con los filtros superiores.
                </div>
            </div>
        </div>
        <!-- /.card-body -->
        <div class="card-footer">
            <a href="Evaluacion" class="my-0 btn-sm btn btn-secondary">Actualizar</a>
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
    $('#filtrosRow').on('change', '#selectGrado', function() {
        actualizarFiltro({grado: $(this).val()});
    });

    $('#filtrosRow').on('change', '#selectSeccion', function() {
        actualizarFiltro({
            grado: $('#selectGrado').val(),
            seccion: $(this).val()
        });
    });
    
    function actualizarFiltro(data) {
        $('#filterBtn').prop('disabled', true);
        $.ajax({
          url: `Evaluacion`,
          type: 'GET',
          data:  Object.assign(data, {action: 'getFiltro'}),
          success: function (data) {
            $('#filtrosRow').html(data);
          },
          error: function (xhr, errmsg, err) {
            console.log("error filtros...");
          }
        });
    }

    $('#filtrosRow').on('change', '#selectCurso', function() {
        $('#filterBtn').prop('disabled', false);
    });

    $('#filtrosRow').on('click', '#filterBtn' ,function () {
        $('#cardMain').overlay(true);
        let seccion = $(this).val();
        $.ajax({
          url: `Evaluacion`,
          type: 'GET',
          data: $('#filtrosForm').serialize() + "&action=getEvaluaciones",
          success: function (data) {
            $('#contTablaEvaluaciones').html(data);
            $('#contBtnAddEvaluacion').removeClass("d-none");
          },
          error: function (xhr, errmsg, err) {
            console.log("error obteniendo alumnos...");
          },
          complete: function () {
            $('#cardMain').overlay(false);
          }
        });
    });
    
    $('#addEvaluacion').on('click', () => {
        let seccion = $('#selectSeccion').val();
        $.ajax({
          url: `Evaluacion`,
          type: 'GET',
          data: $('#formCurrentValues').serialize() + "&action=add",
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-evaluacion').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error agregando usuario...");
          }
        });
    });
    $('#modal-cont').on('click', '#saveRegistro', () => {
        $.ajax({
          url: `Evaluacion`,
          type: 'POST',
          data: $("#formEvaluacion").serialize(),
          success: function (data) {
            $('#contTablaEvaluaciones').html(data);
            $('#modal-evaluacion').modal('hide');
          },
          error: function (xhr, errmsg, err) {
            console.log("error guardando usuario...");
          }
        });
    });
    $('#contTablaEvaluaciones').on('click', '.editRegistro', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `Evaluacion`,
          type: 'GET',
          data: $('#formCurrentValues').serialize() + "&action=update&id=" + id,
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-evaluacion').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error eliminando ...");
          }
        });
    });    
    $('#contTablaEvaluaciones').on('click', '.deleteRegistro', function () {
        let id = $(this).data("id");
        $.ajax({
          url: `Evaluacion`,
          type: 'GET',
          data:  $('#formCurrentValues').serialize() + "&action=delete&id=" + id,
          success: function (data) {
            $('#modal-cont').html(data);
            $('#modal-evaluacion-delete').modal('show');
          },
          error: function (xhr, errmsg, err) {
            console.log("error eliminando ...");
          }
        });
    });
    $('#modal-cont').on('click', '#deleteCurso', () => {
        $.ajax({
          url: `Evaluacion`,
          type: 'POST',
          data: $("#formDeleteSeccionCurso").serialize(),
          success: function (data) {
            $('#contTablaEvaluaciones').html(data);
            $('#modal-evaluacion-delete').modal('hide');
          },
          error: function (xhr, errmsg, err) {
            console.log("error eiminando usuario...");
          }
        });
    });
</script>
</body>
</html>