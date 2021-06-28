const main = {
    init : function () {
        const _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        const formData = $('#form').serialize()

        $.ajax({
            url: '/posts/save',
            type: 'POST',
            cache: false,
            data: formData,
            success: function(data){
            },
            error: function (request, status, error){
            }
        });
    },
    update : function () {
        const formData = $('#form').serialize()

        const id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/posts/' + id,
            cache: false,
            data: formData,
            success: function(data){
            },
            error: function (request, status, error){
                alert(error);
            }
        });
    },
    delete : function () {
        const id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/posts/' + id,
            cache: false,
            success: function(data){
            },
            error: function (request, status, error){
                alert(error);
            }
        });
    }
};

main.init();