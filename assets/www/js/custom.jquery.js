function constants() {
    this.left_menu_view_Id = "left_menu";
    this.updateCartEventName = "updatecartEvent";
};
var static = new constants();
function classLocalStorage() {
    this.logged = 0;
    this.oid = 0;
};

function onDeviceReady() {
    var $responseHolder2 = $('#response-holder2');
    var $responseHolder3 = $('#response-holder3');
    var messages = '';

    /* Class Local Storage definitions */
    classLocalStorage.prototype.IsLogged = function () {
        /* Check if it's logged */
        /*
        $responseHolder2.append('<p class="warning">window.localStorage.getItem("logged"): ' + window.localStorage.getItem("logged") + '</p>')
                        .fadeIn();
        */

        this.logged = 0;
        if (typeof window.localStorage.getItem("logged") != 'undefined' && window.localStorage.getItem("logged") == 1) {
            this.logged = 1;
        }
        return this.logged;
    };

    classLocalStorage.prototype.SetLoggedData = function (data) {
        window.localStorage.clear();
        /* Set data */
        var noError = true;
        if (typeof data == 'undefined') { noError = false; };
        if (typeof data.user.username != 'undefined') { window.localStorage.setItem('username', data.user.username); } else { noError = false; };
        if (typeof data.user.firstname != 'undefined') { window.localStorage.setItem('firstname', data.user.firstname); } else { noError = false; };
        if (typeof data.user.lastname != 'undefined') { window.localStorage.setItem('lastname', data.user.lastname); } else { noError = false; };
        if (typeof data.user.dobday != 'undefined') { window.localStorage.setItem('dobday', data.user.dobday); } else { noError = false; };
        if (typeof data.user.dobmonth != 'undefined') { window.localStorage.setItem('dobmonth', data.user.dobmonth); } else { noError = false; };
        if (typeof data.user.dobyear != 'undefined') { window.localStorage.setItem('dobyear', data.user.dobyear); } else { noError = false; };
        if (typeof data.user.age != 'undefined') { window.localStorage.setItem('age', data.user.age); } else { noError = false; };
        if (typeof data.user.weight != 'undefined') { window.localStorage.setItem('weight', data.user.weight); } else { noError = false; };
        if (typeof data.user.heightfeet != 'undefined') { window.localStorage.setItem('heightfeet', data.user.heightfeet); } else { noError = false; };
        if (typeof data.user.heightinches != 'undefined') { window.localStorage.setItem('heightinches', data.user.heightinches); } else { noError = false; };
        if (typeof data.user.sex != 'undefined') { window.localStorage.setItem('sex', data.user.sex); } else { noError = false; };
        if (typeof data.user.dayphone != 'undefined') { window.localStorage.setItem('dayphone', data.user.dayphone); } else { noError = false; };
        if (typeof data.user.nightphone != 'undefined') { window.localStorage.setItem('nightphone', data.user.nightphone); } else { noError = false; };
        if (typeof data.user.shipaddress != 'undefined') { window.localStorage.setItem('shipaddress', data.user.shipaddress); } else { noError = false; };
        if (typeof data.user.shipaddress2 != 'undefined') { window.localStorage.setItem('shipaddress2', data.user.shipaddress2); } else { noError = false; };
        if (typeof data.user.ShipCity != 'undefined') { window.localStorage.setItem('ShipCity', data.user.ShipCity); } else { noError = false; };
        if (typeof data.user.ShipState != 'undefined') { window.localStorage.setItem('ShipState', data.user.ShipState); } else { noError = false; };
        if (typeof data.user.ShipCountry != 'undefined') { window.localStorage.setItem('ShipCountry', data.user.ShipCountry); } else { noError = false; };
        if (typeof data.user.ShipZip != 'undefined') { window.localStorage.setItem('ShipZip', data.user.ShipZip); } else { noError = false; };
        if (typeof data.user.BillAddress != 'undefined') { window.localStorage.setItem('BillAddress', data.user.BillAddress); } else { noError = false; };
        if (typeof data.user.BillAddress2 != 'undefined') { window.localStorage.setItem('BillAddress2', data.user.BillAddress2); } else { noError = false; };
        if (typeof data.user.BillCity != 'undefined') { window.localStorage.setItem('BillCity', data.user.BillCity); } else { noError = false; };
        if (typeof data.user.BillState != 'undefined') { window.localStorage.setItem('BillState', data.user.BillState); } else { noError = false; };
        if (typeof data.user.BillCountry != 'undefined') { window.localStorage.setItem('BillCountry', data.user.BillCountry); } else { noError = false; };
        if (typeof data.user.BillZip != 'undefined') { window.localStorage.setItem('BillZip', data.user.BillZip); } else { noError = false; };
        if (typeof data.order.productcount != 'undefined') { window.localStorage.setItem('productcount', data.order.productcount); } else { noError = false; };
        if (typeof data.order.xmlorder != 'undefined') { window.localStorage.setItem('xmlorder', data.order.xmlorder); } else { noError = false; };
        this.logged = 1;
        window.localStorage.setItem('logged', this.logged);

        if (!this.GetLoggedData()) {
            noError = false;
            this.logged = 0;
            window.localStorage.setItem('logged', this.logged);
        };

        /*
        messages += '<p class="warning">data.firstname: ' + data.firstname + '</p>'
                        + '<p class="warning">data.lastname: ' + data.lastname + '</p>'
                        + '<p class="warning">data.username: ' + data.username + '</p>'
                        + '<p class="warning">noError: ' + noError + '</p>';
        $responseHolder2.append(messages).fadeIn();
        */
        return noError;
    };


    classLocalStorage.prototype.SetCurrentOrder = function (oid) {
        /* Set current order data */
        var noError = true;
        if (!this.IsLogged()) {
            noError = false;
        } else {
            if (typeof oid == 'undefined') {
                noError = false;
                this.oid = 0;
            } else {
                window.localStorage.setItem('oid', oid);
                this.oid = oid;
            };
        }

        /*
        messages += ''
                    + <p class="warning">data.oid: ' + data.oid + '</p>'
                    ;
        $responseHolder2.append(messages).fadeIn();
        */
        return noError;
    };

    classLocalStorage.prototype.GetCurrentOrder = function () {
        /* Check if it's logged */
        this.oid = window.localStorage.getItem("oid");
        this.logged = window.localStorage.getItem('logged');

        /*
        messages += ''
                    + '<p class="error">1 this.oid: ' + this.oid + '</p>'
                    + '<p class="error">1 this.logged: ' + this.logged + '</p>';
        $responseHolder3.append(messages).fadeIn();
        */

        /* Check if saved local storage userData is ok */
        if (typeof this.oid == 'undefined') { return false; };
        if (typeof this.logged == 'undefined') { this.ClearSession(); return false; };

        /*
        messages += ''
                    + '<p class="error">a2 this.oid: ' + this.oid + '</p>'
                    + '<p class="error">a2 this.logged: ' + this.logged + '</p>';
        $responseHolder3.append(messages).fadeIn();
        */

        return true;
    };

    classLocalStorage.prototype.GetLoggedData = function () {
        /* Check if it's logged */
        this.username = window.localStorage.getItem("username");
        this.firstname = window.localStorage.getItem('firstname');
        this.lastname = window.localStorage.getItem('lastname');
        this.dobday = window.localStorage.getItem('dobday');
        this.dobmonth = window.localStorage.getItem('dobmonth');
        this.dobyear = window.localStorage.getItem('dobyear');
        this.age = window.localStorage.getItem('age');
        this.weight = window.localStorage.getItem('weight');
        this.heightfeet = window.localStorage.getItem('heightfeet');
        this.heightinches = window.localStorage.getItem('heightinches');
        this.sex = window.localStorage.getItem('sex');
        this.dayphone = window.localStorage.getItem('dayphone');
        this.nightphone = window.localStorage.getItem('nightphone');
        this.shipaddress = window.localStorage.getItem('shipaddress');
        this.shipaddress2 = window.localStorage.getItem('shipaddress2');
        this.ShipCity = window.localStorage.getItem('ShipCity');
        this.ShipState = window.localStorage.getItem('ShipState');
        this.ShipCountry = window.localStorage.getItem('ShipCountry');
        this.ShipZip = window.localStorage.getItem('ShipZip');
        this.BillAddress = window.localStorage.getItem('BillAddress');
        this.BillAddress2 = window.localStorage.getItem('BillAddress2');
        this.BillCity = window.localStorage.getItem('BillCity');
        this.BillState = window.localStorage.getItem('BillState');
        this.BillCountry = window.localStorage.getItem('BillCountry');
        this.BillZip = window.localStorage.getItem('BillZip');
        this.productcount = window.localStorage.getItem('productcount');
        this.xmlorder = window.localStorage.getItem('xmlorder');
        this.logged = window.localStorage.getItem('logged');

        /*
        messages += '<p class="error">1 this.firstname: ' + this.firstname + '</p>'
                    + '<p class="error">1 this.lastname: ' + this.lastname + '</p>'
                    + '<p class="error">1 this.dayphone: ' + this.dayphone + '</p>'
                    + '<p class="error">1 this.username: ' + this.username + '</p>'
                    + '<p class="error">1 window.localStorage.getItem(logged): ' + window.localStorage.getItem('logged') + '</p>';
                    + '<p class="error">1 this.logged: ' + this.logged + '</p>';
        $responseHolder3.append(messages).fadeIn();
        */


        /* Check if saved local storage userData is ok */
        if (typeof this.username == 'undefined') { this.ClearSession(); return false; };
        if (typeof this.firstname == 'undefined') { this.ClearSession(); return false; };
        if (typeof this.lastname == 'undefined') { this.ClearSession(); return false; };
        if (typeof this.dayphone == 'undefined') { this.ClearSession(); return false; };
        if (typeof this.logged == 'undefined') { this.ClearSession(); return false; };

        /*
        messages += '<p class="error">2 this.firstname: ' + this.firstname + '</p>'
                    + '<p class="error">2 this.lastname: ' + this.lastname + '</p>'
                    + '<p class="error">2 this.dayphone: ' + this.dayphone + '</p>'
                    + '<p class="error">2 this.username: ' + this.username + '</p>'
                    + '<p class="error">2 this.logged: ' + this.logged + '</p>';
        $responseHolder3.append(messages).fadeIn();
        */

        return true;
    };

    classLocalStorage.prototype.ClearSession = function () {
        /* Clear everything */
        window.localStorage.clear();
        this.logged = 0;
        if (typeof window.localStorage.getItem("logged") == 'undefined') { return true } else { return false };
    };
    classLocalStorage.prototype.UpdatePageElements = function (_this, $domTree, append) {
        if (typeof _this == 'undefined') {
            var _this = this;
        };
        if (typeof $domTree == 'undefined') {
            var $domTree = $body;
        }
        //console.log('$domTree.class: ' + $domTree.prop('class'));
        $domTree.find('.autoupdate').each(function () {
            var $this = $(this);
            //alert(_this[$this.data('term')]);
            //$this.hide();
            if ($this.is('input') || $this.is('select') || $this.is('textarea')) {
                if (typeof append !== "undefined" && append == true) $this.val($this.val() + _this[$this.data('term')]);
                else $this.val(_this[$this.data('term')]);

            } else {
                if (typeof append !== "undefined" && append == true) $this.html($this.html() + _this[$this.data('term')]);
                else $this.html(_this[$this.data('term')]);
            }
            //$this.show();

            // TEMP
            //console.log('$this.data(term): ' + $this.data('term'));
            /*
            if ($this.data('term') == 'firstname') {
                console.log('Detected firstname replacement');
                console.log('$this.html(): ' + $this.html());
            }
            */
        });
    };
    /* End Local Storage */

    //var oLocalStorage = new classLocalStorage();       
    //extends form elements
    HTMLElement.prototype.autoSubmit = function (doneCallback, failCallback, options) {
        var defaults = {
            bypassdecoding: false,
            defaultErrorsHolderId: ' #errors-holder',
            defaultpagePreloader: '#page-preloader'
        };
        if (typeof options !== "undefined") {
            if (typeof options.bypassdecoding !== "undefined")
                defaults.bypassdecoding = options.bypassdecoding;
            if (typeof options.defaultErrorsHolderId !== "undefined")
                defaults.defaultErrorsHolderId = options.defaultErrorsHolderId;
            if (typeof options.defaultpagePreloader !== "undefined")
                defaults.defaultpagePreloader = options.defaultpagePreloader;
        }
        var $defaultErrorsHolder = $(defaults.defaultErrorsHolderId);
        var $pagePreloader = $(defaults.defaultpagePreloader);
        var error = new Error();
        if (this.nodeName === "FORM") {
            var $this = $(this);
            console.log($this.serialize());
            if (typeof $pagePreloader !== "undefined")
                $pagePreloader.show();
            //submit the form 
            $.ajax({
                url: $this.attr('action'),
                type: $this.attr('method'),
                data: $this.serialize(),
                contentType: "application/x-www-form-urlencoded",
                beforeSend: function (xhr) {
                    if (typeof prescription != 'undefined')
                        xhr.setRequestHeader('Authorization', prescription.auth().getToken());
                    else
                        error.throwContextNotReady();

                }
            }).fail(function (r, s, e) {
                console.log(r.responseText);
                if (typeof failCallback !== "undefined")
                    failCallback(r, s, e);
                else
                    $defaultErrorsHolder.addErrorMessage(r.responseText);
            }).done(function (response) {
                if (typeof doneCallback !== "undefined") {
                    if (!defaults.bypassdecoding) {
                        var result = JSON.parse(response);
                        doneCallback(result);
                    } else
                        doneCallback(response);
                } else
                    error.throwNotimplementedMethod("doneCallback");
            }).always(function () {
                if (typeof $pagePreloader !== "undefined")
                    $pagePreloader.hide();
            });
        }
    }
    $.fn.extend({
        addErrorMessage: function (msj) {
            if (typeof msj !== "undefined") {
                var span = "<span class='alert error'>" + msj + "</span>"
                $(this).append(span);
            }
        },
        emptyClonableHolder: function (exclusionList) {
            if (typeof exclusionList !== "undefined") {
                exclusionList += ",.clonable";
            } else
                exclusionList = ".clonable";
            $(this).find("li:not(" + exclusionList + ")").each(function () {
                $(this).remove();
            });
        }
    });
    Error.prototype.throwNotimplementedMethod = function (methodName) {
        throw new Error("Exception: Not implemented method " + methodName);
    }
    Error.prototype.throwContextNotReady = function () {
        throw new Error("Exception: Context is not ready!");
    }
    $(document).on('click', '.submit', function (e) {
        e.preventDefault();
        var $frm = $(this).parents('form');
        $frm[0].autoSubmit();
    });
    $('#nav-toggle').on('touchstart click', function (e) {
        e.preventDefault();
        if (typeof prescription !== "undefined")
            prescription.OnDrawerAction("open");
        else {
            var err = new Error();
            err.throwContextNotReady();
        }

    });
};
function isFromCache() {
    var isfc = window.location.hash.replace('#fromCache=', ''); //alert("isfc:" + isfc);
    if (typeof isfc !== "undefined" && isfc == "true")
        return true;
    else
        return false;
}
function getQueryString() {
    var params = new Object();
    var paramsstr = window.location.href.substring(window.location.href.indexOf('?') + 1, window.location.href.length);
    //split by &
    var ps = paramsstr.split('&')
    for (var i = 0 ; i < ps.length; i++) {
        var nv = ps[i].split('=');
        if (nv.length > 2)
            throw new Error("invalid query string");
        var name = nv[0]; console.log(name);
        var value = nv[1]; console.log(value);
        params[name] = value;
    }
    return params;
}
document.addEventListener("deviceready", onDeviceReady, true);