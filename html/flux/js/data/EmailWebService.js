/** @jsx React.DOM */
'use strict';

module.exports = {
    createEmail: function (options) {
        $.ajax({
            url: "/api/email",
            type: "POST"
        }).done(function (id) {
            var mail = {
                to: options.to,
                id: id
            };

            EmailActionCreator.emailCreated(mail);
        });
    }
};