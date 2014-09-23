//当前目录
print("This script is in the directory: " + __DIR__)

// nashorn supports pseudo global variables __FILE__
// and __LINE__ that expands to currently executed
// script file name and current script line number

// prints current file and line number
print("executing " + __FILE__ + " @ " + __LINE__)


// print all option names and values
for (i in $OPTIONS) {
    print(i, '=', $OPTIONS[i])
}


// nashorn supports expression closures extension of
// Mozilla JavaScript 1.8. See also
// https://developer.mozilla.org/en-US/docs/Web/JavaScript/New_in_JavaScript/1.8

// leave {, } and 'return' keyword

function sqr(x)
x * x

// prints 289 (= 17*17)
print(sqr(17))


// In nashorn -scripting mode,
// "$ENV" object exposes process
// environment variables

//打印环境变量
print($ENV.PATH)
print($ENV.JAVA_HOME)

for (i in $ENV) {
    print(i, "->", $ENV[i])
}