#// usage: jjs -scripting weather.js

// Simple nashorn example showing back-quote exec process,
// JSON and Java8 streams

var Arrays = Java.type("java.util.Arrays")

// use curl to download JSON weather data from the net
// use backquote -scripting mode syntax to exec a process

`
curl
http://api.openweathermap.org/data/2.5/forecast/daily?q=Chennai&amp;mode=json&amp;units=metric&amp;cnt=7`

// parse JSON
    var weather = JSON.parse($OUT)

// pull out humidity as array
var humidity = weather.list.map(function (curVal) {
    return curVal.humidity
})

// Stream API to print stat
print("Humidity")
print(Arrays["stream(int[])"](humidity).summaryStatistics())

// pull maximum day time temperature
var temp = weather.list.map(function (curVal) {
    return curVal.temp.max
})

// Stream API to print stat
print("Max Temperature")
print(Arrays["stream(double[])"](temp).summaryStatistics())
