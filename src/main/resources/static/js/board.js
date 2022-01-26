let index = {
    init: function() {
        $("#btn-save").on("click", () => {
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },

    save: function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("게시글이 등록되었습니다.");
            console.log(resp)
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },

    deleteById: function() {
        var id = $("#id").text();
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function(resp){
            alert("게시글이 삭제되었습니다.");
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },

    update: function() {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("게시글이 수정되었습니다.");
            console.log(resp)
            location.href="/"
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },
    replySave: function() {
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };
        let boardId= $("#boardId").val();

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(resp){
            alert("댓글이 등록되었습니다.");
            location.href=`/board/${data.boardId}`
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },
}

index.init();