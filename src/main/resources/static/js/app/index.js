const main = {
    init : function () {
        const _this = this;

        $('#btn-save').on('click', function() {
            _this.save();
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
            userNo : $('#user').getParameter("userNo"),
            title : $('#title').val(),
            price : $('#price').val(),
            content : $('#content').val(),
            upperCategoryNo : $('#upper-category option:selected').val(),
            lowerCategoryNo : $('#lower-category option:selected').val(),
            brandNo : $('#brand option:selected').val(),
            upperLocalNo : $('#upper-local option:selected').val(),
            lowerLocalNo : $('#lower-local option:selected').val()
        };

        const formData = new FormData();
        formData.append("dto", JSON.stringify(dto));
        formData.append("images", $('#images').files);

        $.ajax({
            type: 'POST',
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

    delete : function () {},

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
        url += `?no=${index}`;
        console.log(url);

        $.ajax({
            type: 'GET',
            datatype: 'json',
            url: url,
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