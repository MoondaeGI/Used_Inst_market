function userCheck() {
    const postNo = $('#postNo').text();
    const btnSelector = $('#btn-selector');

    const loginUserName = $('#user').text();
    const postUserName = $('#postUserName').text();

    if(loginUserName === postUserName) {
        btnSelector.append(`<button type="button" class="btn btn-secondary" id="btn-soldYN" onclick="soldYNChange()">판매 완료</button>`);
        btnSelector.append(`<a href="/post/update?no=${postNo}" role="button" class="btn btn-secondary">수정</a>`);
        btnSelector.append(`<button type="button" class="btn btn-secondary" id="btn-delete" onclick="deletePost()">삭제</button>`);
    }
}

function soldYNChange() {
    const postNo = $('#postNo').text();

    $.ajax({
        type: 'GET',
        url: `/post/update/soldYN/info?no=${postNo}`,
        contentType: 'application/json; charset=UTF-8'
    }).done(function() {
        window.location.reload();
    }).fail(function (error) {
        alert(JSON.stringify(error));
    });
}

function deletePost() {
    const postNo = $('#postNo').text();

    $.ajax({
        type: 'DELETE',
        url: `/post/delete?no=${postNo}`,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8'
    }).done(function() {
        alert('글이 삭제되었습니다.');
        window.location.href = '/';
    }).fail(function (error) {
        alert(JSON.stringify(error))
    });
}