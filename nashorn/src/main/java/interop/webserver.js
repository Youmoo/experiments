#// Usage: jjs -scripting webserver
// or     jjs -scripting webserver -- <server_port>
//
// and then point your browser to
//
//    localhost:<port>?hello
//    localhost:<port>?echo?<query_params>
//    localhost:<port>?file?name=<file_name_in_this_dir>

// This is nashorn port of Java implementation here:
// http://neilmadden.wordpress.com/2014/04/03/single-interface-java-8-applications/
// which creates a WebServer by a single Java interface!

// java classes used
var Arrays = Java.type("java.util.Arrays")
var BufferedReader = Java.type("java.io.BufferedReader")
var Collectors = Java.type("java.util.stream.Collectors")
var File = Java.type("java.io.File")
var InputStreamReader = Java.type("java.io.InputStreamReader")
var OutputStreamWriter = Java.type("java.io.OutputStreamWriter")
var PrintWriter = Java.type("java.io.PrintWriter")
var ServerSocket = Java.type("java.net.ServerSocket")
var URLDecoder = Java.type("java.net.URLDecoder")

// static methods, field used
var groupingBy = Collectors.groupingBy
var mapping = Collectors.mapping
var toList = Collectors.toList
var asList = Arrays.toList
var UTF_8 = Java.type("java.nio.charset.StandardCharsets").UTF_8

// WebServer "class"
function WebServer(func) {
    this.handle =
            typeof func == 'function' ?
        func : (function (request, query)
    "Not Found: " + request
)
}

// factory method
WebServer.create = function (func) {
    return new WebServer(func)
}

// instance 'bind' method to chain handlers
WebServer.prototype.bind = function (path, handler) {
    // allow function or WebServer instance for 'handler'
    if (typeof handler == 'function') {
        handler = WebServer.create(handler)
    } else if (!(handler instanceof WebServer)) {
        throw new TypeError("handler is not callable or WebServer")
    }

    // create new WebServer and chain with 'this' one
    var self = this
    return new WebServer(
        function (request, query) {
            return path.equalsIgnoreCase(request) ?
                handler.handle(request, query) :
                self.handle(request, query)
        })
}

// instance method to start the web server
WebServer.prototype.start = function (port) {
    var server = new ServerSocket(port)
    while (true) {
        var client;
        try {
            client = server.accept()
            // Parse the request line and split into path and query
            var request = WebServer.request(client).split(/\?/, 2);
            var query = request.length > 1 ? request[1] : "";
            var response = this.handle(request[0],
                WebServer.parseQuery(query));

            WebServer.respond(client, response);
        } finally {
            if (client)
                client.close()
        }
    }
}

// static method read http request
WebServer.request = function (client) {
    var reader = new BufferedReader(
        new InputStreamReader(client.inputStream, UTF_8))
    return reader.readLine().split(/\s+/)[1]
}

// static method send http response
WebServer.respond = function (client, response) {
    var out = new PrintWriter(new OutputStreamWriter(
        client.outputStream, UTF_8))
    try {
        out.print("HTTP/1.1 200 OK\r\n");
        out.print("Content-Type: text/plain\r\n");
        out.print("\r\n");
        out.println(response);
    } finally {
        out.close()
    }
}

// static method to parse http query string
WebServer.parseQuery = function (queryString) {
    return Arrays.asList(queryString.split("&"))
        .stream()
        .map(function (s)
    s.split("=", 2)
    )
    .
    collect(groupingBy(function (a)
    WebServer.decode(a[0]),
        mapping(function (p)
    WebServer.decode(p.length > 1 ? p[1] : ""),
        toList()
    )))
}

// static method for URL decoding
WebServer.decode = function (encoded) {
    return URLDecoder.decode(encoded, "UTF-8")
}

// port to listen to
var port = arguments.length > 0 ? parseInt(arguments[0]) : 9090

// main
WebServer.create()
    .bind("/hello", function ()
"Hello World"
)
.
bind("/echo", function (path, query)
query.toString()
)
// simple 'file' serving extra here!
.
bind("/file", function (path, query) {
    if (query.name && query.name.size() > 0 &&
        new File(query.name[0]).isFile()) {
        return readFully(query.name[0])
    } else {
        return "File Not Found: " + query.toString()
    }
})
    .start(port)
