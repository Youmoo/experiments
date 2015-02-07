var Router = ReactRouter;
var Link = Router.Link;
var Route = Router.Route
var RouteHandler = Router.RouteHandler;
var App = React.createClass({displayName: 'App',
    render: function () {
        return (
            React.createElement("div", null, 
                React.createElement("header", null, 
                    React.createElement("ul", null, 
                        React.createElement("li", null, 
                            React.createElement(Link, {to: "app"}, "Dashboard")
                        ), 
                        React.createElement("li", null, 
                            React.createElement(Link, {to: "about"}, "About")
                        )
                    ), 
                "Logged in as Jane"
                ), 

        /* this is the important part */
                React.createElement(RouteHandler, null)
            )
        );
    }
});

var About = React.createClass({displayName: 'About',
    render: function () {
        return React.createElement("p", null, "我叫尤慕")
    }
});

var routes = (
    React.createElement(Route, {name: "app", path: "/", handler: App}, 
        React.createElement(Route, {name: "about", path: "about", handler: About})
    )
);

Router.run(routes, function (Handler) {
    React.render(React.createElement(Handler, null), document.body);
});