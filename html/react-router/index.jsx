var Router = ReactRouter;
var Link = Router.Link;
var Route = Router.Route
var RouteHandler = Router.RouteHandler;
var App = React.createClass({
    render: function () {
        return (
            <div>
                <header>
                    <ul>
                        <li>
                            <Link to="app">Dashboard</Link>
                        </li>
                        <li>
                            <Link to="about">About</Link>
                        </li>
                    </ul>
                Logged in as Jane
                </header>

        {/* this is the important part */}
                <RouteHandler/>
            </div>
        );
    }
});

var About = React.createClass({
    render: function () {
        return <p>我叫尤慕</p>
    }
});

var routes = (
    <Route name="app" path="/" handler={App}>
        <Route name="about" path="about" handler={About}/>
    </Route>
);

Router.run(routes, function (Handler) {
    React.render(<Handler/>, document.body);
});