// Usage: jjs -cp <jsoup.jar> listlinks.js -- <html_url>
// 
// Demonstrates use of external (non JDK API) jar with nashorn
// via -cp option as well as simple HTML parsing via Jsoup.
// Download jsoup jar from http://jsoup.org/download
// to run this script.
//
// Example: jjs -cp <jsoup.jar> listlinks.js -- http://jsoup.org
//
// This program is more or less nashorn port of ListLinks
// sample of jsoup html parser library - except for streams
// forEach, command line arg. handling etc.

var Jsoup
try {
    Jsoup = Java.type("org.jsoup.Jsoup")
} catch (e) {
    print("please download jsoup jar and specify in -cp option")
    exit(1)
}

if (arguments.length == 0) {
    print("Usage: jjs -cp <jsoup.jar> listlinks.js -- <url>")
    exit(2)
}

var doc = Jsoup.connect(arguments[0]).get()
var links = doc.select('a[href]')
print("Links:", links.size())
links.stream().forEach(
    function (a) {
        print("a", a.attr('abs:href'), a.text())
    })

var media = doc.select('[src]')
print("Media:", media.size())
media.stream().forEach(
    function (media) {
        print(media.tagName(), media.attr('abs:src'))
    })

var imports = doc.select('link[href]')
print("Imports:", imports.size())
imports.stream().forEach(
    function (link) {
        print('link', link.attr('abs:href'), link.attr('rel'))
    })
