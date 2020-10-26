let join_btn = document.querySelector("#join_btn");
join_btn.addEventListener('click', function(event){
    let data = {
        username: $('#username').val(),
        password: $('#password').val(),
        email: $('#email').val()
    }

    $.ajax({
        type: 'POST',
        url: '/api/v1/user/join',
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(function(data) {
        if(data == -1){
            alert("이미 존재하는 회원입니다");
        }else{
            window.location.href = '/';
            alert("회원가입을 축하합니다.");
        }
    }).fail(function(){
        console.log('fail');
    });
});

