<!-- jQuery -->
<script src="template/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="template/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="template/js/adminlte.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="template/js/demo.js"></script>

<script>
    jQuery.fn.extend({
      overlay: function (val) {
        this.each(function () {
          let id = $(this).attr('id');
          $(this).find(`#${id}_generated_overlay`).remove();
          if (val) {
            if ($(this).hasClass('modal')) {
              var ov = `<div id="${id}_generated_overlay" class="overlay justify-content-center align-items-center d-flex">
                    <i class="fas fa-2x fa-sync-alt fa-spin"></i>
                  </div>`
              $(this).find('.modal-content').prepend(ov);
            } else if ($(this).hasClass('card')) {
              var ov = `<div id="${id}_generated_overlay" class="overlay dark">
                    <i class="fas fa-2x fa-sync-alt fa-spin"></i>
                  </div>`
              $(this).prepend(ov);
            }
          }
        });
      },
    });
</script>