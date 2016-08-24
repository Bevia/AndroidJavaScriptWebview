# AndroidJavaScriptWebview
Binding JavaScript code to Android code

Ref: https://developer.android.com/guide/webapps/webview.html

    Binding JavaScript code to Android code
    
    When developing a web application that's designed specifically for the WebView in your Android application, 
    you can create interfaces between your JavaScript code and client-side Android code. For example, 
    your JavaScript code can call a method in your Android code to display a Dialog, instead of using JavaScript's alert() function.
    
    To bind a new interface between your JavaScript and Android code, call addJavascriptInterface(), 
    passing it a class instance to bind to your JavaScript and an interface name that your JavaScript 
    can call to access the class.

    Java Script Html file in assets.
