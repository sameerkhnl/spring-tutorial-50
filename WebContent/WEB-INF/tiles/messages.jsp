<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="messages">

</div>

<script type="text/javascript">
    var timer;

    $(document).ready(onLoad);

    function onLoad() {
        //alert("document is ready");
        displayMessages();
        startTimer();
    }

    function startTimer() {
        timer = window.setInterval(displayMessages, 10000);
    }

    function stopTimer() {
        timer = window.clearInterval(timer);
    }

    function displayMessages() {
        $.getJSON("<c:url value='/getmessages' />", updateMessages);
    }

    function showReplyForm(i) {
        stopTimer();
        $("#replyform" + i).toggle();
    }

    function sendMessage(i, name, email, subject) {
        var text = $("#textarea" + i).val();

        var csrfHeader = $("meta[name='_csrf_header']").attr("content");
        var csrfToken = $("meta[name='_csrf']").attr("content");

        var headers = {};
        headers[csrfHeader] = csrfToken;

        $.ajax({
            type: 'POST',
            url: '<c:url value="/sendmessage" />',
            data: JSON.stringify({"text": text, "id": i, "name":name, "email": email, "subject": subject}),
            success: sent,
            error: error,
            headers: headers,
            contentType: "application/json",
            dataType: "json",
        });
    }

    function sent(data) {
        $("#replyform" + data.target).toggle();
        startTimer();
        $("#messagesenttext" + data.target).text("Message sent!");

    }

    function error(data) {
        alert("error sending message");
    }

    function updateMessages(data) {
        $("#messages").html("");
        for (var i = 0; i < data.number; i++) {
            var message = data.messages[i];

            var messageDiv = document.createElement("div");
            messageDiv.setAttribute("class", "message");

            var subjectDiv = document.createElement("div");
            subjectDiv.setAttribute("class", "messagesubject");
            subjectDiv.appendChild(document.createTextNode(message.subject));

            var bodyDiv = document.createElement("div");
            bodyDiv.setAttribute("class", "messagebody");
            bodyDiv.appendChild(document.createTextNode(message.content));

            var nameSpan = document.createElement("span");
            nameSpan.setAttribute("class", "messagename");
            nameSpan.appendChild(document.createTextNode(message.name));

            var replyLink = document.createElement("a");
            replyLink.setAttribute("class", "replylink");
            replyLink.appendChild(document.createTextNode(' ('));
            replyLink.setAttribute("href", "#");
            replyLink.appendChild(document.createTextNode(message.email));
            replyLink.appendChild((document.createTextNode(')')));

            replyLink.setAttribute("onclick", "showReplyForm" + "(" + i + ")");

            var messageSent = document.createElement("div");
            messageSent.setAttribute("class", "messagesenttext");
            messageSent.setAttribute("id", "messagesenttext" + i);
            //messageSent.appendChild(document.createTextNode("Message sent!"));

            var replyForm = document.createElement("form");
            replyForm.setAttribute("class", "replyform");
            replyForm.setAttribute("id", "replyform" + i);

            var textArea = document.createElement("textarea");
            textArea.setAttribute("id", "textarea" + i);
            replyForm.appendChild(textArea);

            var replyBtn = document.createElement("input");
            replyBtn.setAttribute("type", "button");
            replyBtn.setAttribute("class", "replybtn");
            replyBtn.setAttribute("value", "Reply");
            replyBtn.onclick = (function (j, k, l, m) {
                return function () {
                    sendMessage(j, k, l, m);
                }
            })(i, message.name, message.email, message.subject);

            replyForm.appendChild(replyBtn);

            messageDiv.appendChild(subjectDiv);
            messageDiv.appendChild(bodyDiv);
            messageDiv.appendChild(nameSpan);
            messageDiv.appendChild(replyLink);
            messageDiv.appendChild(messageSent);
            messageDiv.appendChild(replyForm);

            $("#messages").append(messageDiv);
        }
    }

</script>