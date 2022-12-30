const main = {
    init : function () {
        const _this = this;

        $('#btn-save').on('click', function() {
            _this.save();
        });
    },

    save : function () {
        const dto = {
            userNo : $('#user').getParameter("userNo"),
            title : $('#title').val(),
            price : $('#price').val(),
            content : $('#content').val(),
            upperCategoryNo : $('#upper-category option:selected').getParameter("upperCategoryNo"),
            lowerCategoryNo : $('#lower-category option:selected').getParameter("lowerCategoryNo"),
            brandNo : $('#brand option:selected').getParameter("brandNo"),
            upperLocalNo : $('#upper-local option:selected').getParameter("upperLocalNo"),
            lowerLocalNo : $('#lower-local option:selected').getParameter("lowerLocalNo")
        };

        const formData = new FormData();
        formData.append("dto", JSON.stringify(dto));
        formData.append("images", $('#images').files);

        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            dataType: 'json',
            url: '/post/info',
            processData: false,
            contentType: false,
            data: formData
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    },

    update : function () {
        const dto = {}
    },

    delete : function () {}
};

main.init();