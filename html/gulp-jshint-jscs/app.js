var Person = {
    firstName : 'Fred',
    sayName : function() {
        console.log('Hello, my name is ' + this.firstName);
    }
};

Person.sayName(); //note no blank line at the end of the file