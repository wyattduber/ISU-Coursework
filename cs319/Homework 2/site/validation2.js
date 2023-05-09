function validate2() {
    valCheck = true;
    validateEmail();
	validatePhone();
	validateAddress();
}

function validateEmail() {
	var resultEmailCheck = emailCheck(document.forms["oinfo"]["email"].value);
    var image1 = getImage(Boolean(resultEmailCheck), "email");
    var labelNotifyEmail1=getNotification(Boolean(resultEmailCheck), "email") ;
    document.getElementById("Email").appendChild(image1);
    document.getElementById("Email").appendChild(labelNotifyEmail1);
}

function validatePhone() {
	var resultPhoneCheck = phoneCheck(document.forms["oinfo"]["phone"].value);
	var image1 = getImage(Boolean(resultPhoneCheck), "phone");
	var labelNotifyPhone = getNotification(Boolean(resultPhoneCheck), "phone");
	document.getElementById("Phone").appendChild(image1);
	document.getElementById("Phone").appendChild(labelNotifyPhone);
}

function validateAddress() {
	var resultAddressCheck = addressCheck(document.forms["oinfo"]["address"].value);
	var image1 = getImage(Boolean(resultAddressCheck), "address");
	var labelNotifyAddress = getNotification(Boolean(resultAddressCheck), "address");
	document.getElementById("Address").appendChild(image1);
	document.getElementById("Address").appendChild(labelNotifyAddress);
}

function getNotification(bool, ID) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        label.setAttribute( 'class', 'errorMessage' );
      }
	
	if (ID == "email") {
		label.innerHTML = bool ? "" : "Email should be in form xxx@xxx.xxx, which x should be alphanumeric!";
	} else if (ID == "phone") {
		label.innerHTML = bool ? "" : "Must be in the form xxx-xxx-xxxx or xxxxxxxxxx. Number should be numeric!";
	} else if (ID == "address") {
		label.innerHTML = bool ? "" : "Must be in the form of city & state and contain only alphabetic characters. example: Ames,IA";
	}
    return label;
}


function getImage(bool, ID) {
    var image = document.getElementById("image" + ID);
    if (image == null) {
        image = new Image(15, 15);
        image.id = "image" + ID;
    }
    image.src = bool ? './correct.png' : './wrong.png';
    return image;
}

function emailCheck(email) {
    atSplit = email.split('@');
    if (atSplit.length == 2 && alphaNumCheck(atSplit[0])) {
        periodSplit = atSplit[1].split('.')
        if (periodSplit.length == 2 && alphaNumCheck(periodSplit[0] + periodSplit[1])) {
            return true;
        }
    }
    valCheck = false;
    return false;
}

function phoneCheck(phone) {
	atSplit = phone.split('-');
	if (atSplit.length == 3 && numCheck(atSplit[0]) && numCheck(atSplit[1]) && numCheck(atSplit[2])) {
		if (atSplit[0].length == 3 && atSplit[1].length == 3 && atSplit[2].length == 4) {
			return true;
		}
	}
	return false;
}

function addressCheck(address) {
	atSplit = address.split(',');
	if (atSplit.length == 2 && alphaCheck(atSplit[0]) && alphaCheck(atSplit[1]) && atSplit[1].length == 2) {
		return true;
	} else {
		return false;
	}
}

function alphaNumCheck(entry) {
    let regex = /^[a-z0-9]+$/i;
    if (entry != null && entry.match(regex)) {
        return true;
    } else {
        return false;
    }
}

function alphaCheck(entry) {
	let regex = /^[A-Za-z]+$/i;
	if (entry != null && entry.match(regex)) {
		return true;
	} else {
		return false;
	}
}

function numCheck(entry) {
	let regex = /^[0-9]+$/i;
	if (entry != null && entry.match(regex)) {
		return true;
	} else {
		return false;
	}
}

function deleteCookie( name ) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}