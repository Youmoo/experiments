#// Usage: jjs -scripting heredoc.js

// Nashorn supports Shell script like here-documents
// in -scripting mode. Here-docs are multi-line strings
// that are possibly interpolated with ${} expressions
// See also http://en.wikipedia.org/wiki/Here_document

var sender = "Buffy the Vampire Slayer";
var recipient = "Spike";

print(<
    <END

    Dear ${recipient},

    I wish you to leave Sunnydale and never return.

    Not Quite Love,
    ${sender}

    END);
