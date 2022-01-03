let index = {
    init: function() {
        // function(){}, 대신에 ()=>{}를 사용했다. this를 바인딩하기 위해서 화살표 함수를 사용했다.
        // function(){}을 사용하려면 let _this = this; 를 반드시 사용해야한다.
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    save: function() {
        // alert("user의 save함수가 호출됨");  // 함수가 실행되는지 확인
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // console.log(data);   // 값 들어오는지 확인

        // ajax 호출 시 default는 비동기 호출
        // ajax통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청한다.
        $.ajax({
            type: "POST",
            url: "/blog/api/user",
            data: JSON.stringify(data),  // http body데이터 / java script Object 를 바로 받아들일 수 없기 때문에 JSON문자열로 변환해준다.
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json"
            // 서버로 보낸 요청에 대한 응답이 왔을 때 기본 적으로 모든 것이 String(문자열)이다.
            // 타입을 기재했을 때 응답된 데이터의 형식이 json이라면 java script object로 변경해준다.
        }).done(function(resp){
            alert("회원 가입이 완료되었습니다.");
            console.log(resp)
            location.href="/blog"
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    }
}

index.init();