// JavaImporter along with 'with' statement helps in
// localized Java class references

function readTextFromURL(url) {

    // equivalent to 
    // 
    //    import java.io.*;
    //    import java.net.*;
    //    import java.lang.StringBuffer;
    //
    // only inside the 'with' statement
    with (new JavaImporter(java.io,
        java.net,
        java.lang.StringBuilder)) {
        var buf = new StringBuilder()
        var u = new URL(url)
        var reader = new BufferedReader(
            new InputStreamReader(u.openStream()))
        var line = null
        try {
            while ((line = reader.readLine()) != null)
                buf.append(line).append('\n')
        } finally {
            reader.close()
        }

        return buf.toString()
    }
}

print(readTextFromURL("http://baidu.com/"))
