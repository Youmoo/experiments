/** @jsx React.DOM */
'use strict';

var NewEmailComponent = React.createClass({
    getInitialState() {
        return {
            to: ""
        };
    },
    render() {
        return (<div>
            <input name="to" type="email" onChange={this.updateToText} />
            <button onClick={this.createNewEmail}>New Email</button>
        </div>
        )
    },
    updateToText(event) {
        this.setState({
            to: event.target.value
        });
    },
    createNewEmail(event) {
        var to = this.state.to;
        EmailActionCreator.createNewEmail(to);
    }
});

module.exports = NewEmailComponent;