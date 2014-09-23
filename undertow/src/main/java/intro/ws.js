var ws = new WebSocket("ws://localhost:8080/api/ws");

ws.send("hello ws");

ws.onmessage = function (e) {
    console.log(e);
}