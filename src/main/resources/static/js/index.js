const main = {
    init : function () {
        const _this = this;

        $('#btn-comment').on('click', function () {
            _this.commentSave();
        })

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

    commentSave : function () {
        const dto = {
            content: $('#comment').val(),
            postNo: parseInt($('#postNo').text()),
            userNo: parseInt($('#user').text().split(" ")[0])
        };

        $.ajax({
            type: 'POST',
            data: JSON.stringify(dto),
            dataType: 'json',
            url: '/comment/info/save',
            contentType: 'application/json; charset=UTF-8;'
        }).done(function () {
            alert("댓글이 등록되었습니다.");
        }).fail(function (error) {
            alert(JSON.stringify(error));
        }).always(function () {
            window.location.reload();
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
            url: '/post/info/save',
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
            url: '/post/info/update',
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
        function selectResult(select) {
            return select === "전체" ? null : select;
        }

        const dto = {
            upperCategory : selectResult($('#upper-category option:selected').val()),
            lowerCategory : selectResult($('#lower-category option:selected').val()),
            brand : selectResult($('#brand option:selected').val()),
            upperLocal : selectResult($('#upper-local option:selected').val()),
            lowerLocal : selectResult($('#lower-local option:selected').val()),
            keyword : $('#keyword').val(),
            minPrice : $('#minPrice').val(),
            maxPrice : $('#maxPrice').val(),
            keywordType : $('#keyword-select option:selected').val()
        }

        console.log(dto);

        $.ajax({
            type: 'POST',
            url: '/search/page',
            dataType: 'json',
            data: JSON.stringify(dto),
            contentType: 'application/json; charset=UTF-8'
        }).fail(function (error) {
            alert(JSON.stringify(error))
        }).always(function () {
            window.location.href = '/';
        });
    },

    categorySelect : function (mainBoxId, subBoxId) {
        const index = $(`#${mainBoxId} option:selected`).val();

        if (index === "전체") {
            const subBoxSelect = $(`#${subBoxId}`);
            subBoxSelect.children().remove();
            subBoxSelect.append(`<option>전체</option>`);

            switch (mainBoxId) {
                case 'upper-category':
                    $('#lower-category option:eq(0)').prop("selected", true);
                    break;
                case 'lower-category':
                    $('#brand option:eq(0)').prop("selected", true);
                    break;
                case 'upper-local':
                    $('#lower-local option:eq(0)').prop("selected", true);
                    break;
            }
        } else {
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
                contentType: 'application/json; charset=UTF-8',
                success: function(data) {
                    console.log(data.result)
            }
            }).done(function (result) {
                console.log(result);
                const subBoxSelect = $(`#${subBoxId}`);
                subBoxSelect.children().remove();
                subBoxSelect.append(`<option>전체</option>`);

                let option;
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

        if (subBoxId === 'lower-category') {
            const brandBoxSelect = $('#brand');

            brandBoxSelect.children().remove();
            brandBoxSelect.append(`<option>전체</option>`);
        }
    }
};

main.init();