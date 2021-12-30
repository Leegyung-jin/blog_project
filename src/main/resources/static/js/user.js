let index = {
    init: function() {
        $("#btn-save").on("click", ()=>{
            this.save();
        });
    },

    save: function() {
        // alert("user의 save함수가 호출됨");  // 함수가 실행되는지 확인
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        // console.log(data);   // 값 들어오는지 확인
        $.ajax().done().fail(); // ajax통신을 이용해서 3개의 파라미터를 json으로 변경하여 insert 요청한다.
    }
}

index.init();