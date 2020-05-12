var stompClient = null;

// 打开页面时建立连接
function connect() {
    var user=document.getElementById("user").value;
    console.log("我是"+user)
    var socket = new SockJS('/chat-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/msg/'+user, function (msg) {
            showGreeting(JSON.parse(msg.body).content);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}
// 发送消息
function sendMsg() {
    var sender=document.getElementById("user").value;
    var receiver=document.getElementById("receiver").value;
    console.log("发送给"+receiver);
    var msg=$("#msg").val();
    stompClient.send("/server/chat", {}, JSON.stringify({'msg': msg,'receiver':receiver,'sender':sender}));

    // 显示我发送了的消息
    $("#threadwindow").append("<div style=\"clear: both\">\n" +
        "            <img src=\"/image/avatar.png\" class=\"mr-3 list-title-img rounded\"  style=\"float: right;width: 35px;height: 35px;\">\n" +
        "            <div class=\"msg-me shadow-sm rounded\" >"+ msg +"</div>\n" +
        "        </div>");
    // 滚动到最下面的一条消息
    var window = document.getElementById('threadwindow');
    window.scrollTop = window.scrollHeight;
}

// 在页面展示消息
function showGreeting(message) {

    $("#threadwindow").append("<div style=\"clear: both\">\n" +
        "            <img src=\"/image/avatar.png\" class=\"mr-3 list-title-img rounded\"  style=\"float: left;width: 35px;height: 35px;margin: -1px 0 0 5px;\">\n" +
        "            <div class=\"msg-other shadow-sm rounded\" > "+message+"</div>\n" +
        "        </div >");
    // 滚动到最下面的一条消息
    var window = document.getElementById('threadwindow');
    window.scrollTop = window.scrollHeight;

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#button-send" ).click(function() {
        sendMsg();
        $("#msg").val("");
    });
});

