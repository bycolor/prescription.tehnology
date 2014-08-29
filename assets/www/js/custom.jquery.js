function classLocalStorage() {
	this.logged = 0;
	this.oid = 0;
};

function onDeviceReady() {	
	var $responseHolder2 = $('#response-holder2');
	var $responseHolder3 = $('#response-holder3');
	var messages = '';
	
	/* Class Local Storage definitions */
	classLocalStorage.prototype.IsLogged = function() {
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

	classLocalStorage.prototype.SetLoggedData = function(data) {
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
	

	classLocalStorage.prototype.SetCurrentOrder = function(oid) {
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
	
	classLocalStorage.prototype.GetCurrentOrder = function() {
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

	classLocalStorage.prototype.GetLoggedData = function() {
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

	classLocalStorage.prototype.ClearSession = function() {
		/* Clear everything */
		window.localStorage.clear();
		this.logged = 0;
		if (typeof window.localStorage.getItem("logged") == 'undefined') { return true } else { return false };
	};
	/* End Local Storage */
	
	//var oLocalStorage = new classLocalStorage();
};

$(function() {
    document.addEventListener("deviceready", onDeviceReady, true);       
});