<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">목록</button>
    <button id="btn-update" class="btn btn-warning">수정</button>
    <c:if test="${board.user.id == principal.user.id}">
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br/><br/>
    <div>
        글 번호: <span id="id"><i>${board.id}</i>  </span>
        <span> | </span>
        작성자: <span><i>${board.user.username} </i></span>
    </div>
    <br/>
    <div>
        <label for="title">title</label>
        <h3>${ board.title }</h3>
    </div>
    <div>
        <label for="content">Content</label>
        <div>${board.content}</div>
    </div>
    <hr/>
</div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>