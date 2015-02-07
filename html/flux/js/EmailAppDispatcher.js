/** @jsx React.DOM */
'use strict';

var EmailAppDispatcher = assign(new Dispatcher(), {
    handleServerAction(action) {
        var payload = {
            source: PayloadSources.SERVER_ACTION,
            action: action
        };
        this.dispatch(payload);
    },
    handleViewAction(action) {
        var payload = {
            source: PayloadSources.VIEW_ACTION,
            action: action
        };
        this.dispatch(payload);
    }
});

module.exports = EmailAppDispatcher;