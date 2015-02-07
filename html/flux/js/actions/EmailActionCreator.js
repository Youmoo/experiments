/** @jsx React.DOM */
'use strict';

var ActionTypes = require('./EmailActions');

module.exports = {
    createNewEmail(to) {
        EmailAppDispatcher.handleViewAction({
            type: ActionTypes.CREATE_EMAIL,
            to: to
        });

        EmailWebService.createEmail({
            to: to
        });
    },
    emailCreated(mail) {
        EmailAppDispatcher.handleServerAction({
            type: ActionTypes.EMAIL_CREATED,
            mail: mail
        });
    }
};
