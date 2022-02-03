let index = {
    init: function() {
        // function(){}, 대신에 ()=>{}를 사용했다. this를 바인딩하기 위해서 화살표 함수를 사용했다.
        // function(){}을 사용하려면 let _this = this; 를 반드시 사용해야한다.
        $("#btn-save").on("click", () => {
            this.save();
        });
        /*
        $("#btn-login").on("click", () => {
            this.login();
        });
        */

        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function() {
        // alert("user의 save함수가 호출됨");  // 함수가 실행되는지 확인
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };


        // ajax 호출 시 default는 비동기 호출
        // ajax통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청한다.
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8",// body데이터가 어떤 타입인지(MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function(resp){
            // 같은 아이디로 가입을 시도할 때 출력
            if(resp.status === 500){
                alert("회원가입에 실패했습니다.");
            }else{
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    /*
    전통적인 로그인 방식 > 사용하지 않음
    login: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("로그인이 완료되었습니다.");
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
    */

    update: function() {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("수정이 완료되었습니다.");
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },
}

index.init();