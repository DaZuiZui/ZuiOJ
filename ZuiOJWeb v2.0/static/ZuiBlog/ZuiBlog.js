   //è®¾ç½®cookie
   function setCookie (name, value) {
    if (value) {
        var Days = 365
        var exp = new Date()
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000)
        document.cookie = name + '=' + escape(value) + ';expires=' + exp.toGMTString()+"; path=/";
    }
    }
    //èŽ·å–cookie
    function  getCookie(NameOfCookie) {
                if (document.cookie.length > 0) {
                    begin = document.cookie.indexOf(NameOfCookie + "=");
                    if (begin !== -1) {
                        begin += NameOfCookie.length + 1;
                        end = document.cookie.indexOf(";", begin);
                        if (end === -1) end = document.cookie.length;
                        return unescape(document.cookie.substring(begin, end));
                    }
                }
                return null;
            }

    //èŽ·å–urlå‚æ•°
    function getQueryVariable(variable)
    {
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i=0;i<vars.length;i++) {
            var pair = vars[i].split("=");
            if(pair[0] == variable){return pair[1];}
        }
        return(false);
    }