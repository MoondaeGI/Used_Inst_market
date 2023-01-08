const main = {
    init : function () {
        const _this = this;

        $('#btn-save').on('click', function() {
            _this.save();
        });

        $('#btn-update').on('click', function() {
            _this.update();
        });

        $('#btn-search').on('click', function () {
            _this.search();
        });

        $('#upper-category').on('change', function() {
            _this.categorySelect('upper-category', 'lower-category');
        });

        $('#lower-category').on('change', function () {
            _this.categorySelect('lower-category', 'brand');
        });

        $('#upper-local').on('change', function () {
            _this.categorySelect('upper-local', 'lower-local');
        });
    },

    save : function () {
        const dto = {
            userNo : parseInt($('#user').attr("name")),
            title : $('#title').val(),
            content : $('#content').val(),
            price : parseInt($('#price').val()),
            upperCategoryNo : parseInt($('#upper-category option:selected').val()),
            lowerCategoryNo : parseInt($('#lower-category option:selected').val()),
            brandNo : parseInt($('#brand option:selected').val()),
            upperLocalNo : parseInt($('#upper-local option:selected').val()),
            lowerLocalNo : parseInt($('#lower-local option:selected').val())
        };
        const jsonDTO = JSON.stringify(dto);
        const blob = new Blob([jsonDTO], {type: "application/json"});
        const images = $('#images').get(0).files[0];

        const formData = new FormData();
        formData.append("dto", blob);
        formData.append("images", images);

        $.ajax({
            type: 'POST',
            url: '/post/save/info',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            data: formData
        }).done(function () {
            alert('글이 등록되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error))
        }).always(function () {
            window.location.href = '/';
        });
    },

    update : function () {
        const dto = {
            postNo : parseInt($('#postNo').val()),
            userNo : parseInt($('#user').attr("name")),
            title : $('#title').val(),
            content : $('#content').val(),
            price : parseInt($('#price').val()),
            upperCategoryNo : parseInt($('#upper-category option:selected').val()),
            lowerCategoryNo : parseInt($('#lower-category option:selected').val()),
            brandNo : parseInt($('#brand option:selected').val()),
            upperLocalNo : parseInt($('#upper-local option:selected').val()),
            lowerLocalNo : parseInt($('#lower-local option:selected').val())
        };
        const jsonDTO = JSON.stringify(dto);
        const blob = new Blob([jsonDTO], {type: "application/json"});
        const images = $('#images').get(0).files[0];

        const formData = new FormData();
        formData.append("dto", blob);
        formData.append("images", images);

        $.ajax({
            type: 'PUT',
            url: '/post/update/info',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            data: formData
        }).done(function () {
            alert('글이 수정되었습니다.');
        }).fail(function (error) {
            alert(JSON.stringify(error))
        }).always(function () {
            window.location.href = '/';
        });
    },

    search : function () {
        const dto = {
            upperCategoryNo: $('#upper-category option:selected').val(),
            lowerCategoryNo: $('#lower-category option:selected').val(),
            brandNo: $('#brand option:selected').val(),
            upperLocalNo: $('#upper-local option:selected').val(),
            lowerLocalNo: $('#lower-local option:selected').val()
        }


    },

    categorySelect : function (mainBoxId, subBoxId) {
        const index = $(`#${mainBoxId} option:selected`).val();
        let url;

        switch (subBoxId) {
            case 'lower-category':
                url = `/category/lower/info/upper`;
                break;
            case 'brand':
                url = `/category/brand/info/lower`;
                break;
            case 'lower-local':
                url = `/local/lower/info/upper`;
                break;
        }

        $.ajax({
            type: 'GET',
            datatype: 'json',
            url: url + `?no=${index}`,
            contentType: 'application/json; charset=UTF-8'
        }).done(function (result) {
            let option;
            $(`#${subBoxId}`).children().remove();
            result.forEach(function(element) {
                switch(subBoxId) {
                    case 'lower-category':
                        option = `<option value=${element.lowerCategoryNo}>${element.name}</option>`;
                        break;
                    case 'brand':
                        option = `<option value=${element.brandNo}>${element.name}</option>`;
                        break;
                    case 'lower-local':
                        option = `<option value=${element.lowerLocalNo}>${element.name}</option>`;
                        break;
                }
                $(`#${subBoxId}`).append(option);
            })
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};

main.init();