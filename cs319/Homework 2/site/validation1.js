function validate1() {
	var b1 = validatefname();
	var b2 = validatelname();
	var b3 = validateGender();
	var b4 = validateState();
	// Wait for 3 seconds, then go to the next website
	if (b1 && b2 && b3 && b4) {
		setTimeout(loadNext, 2000);
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

function loadNext() {
	window.location.href = 'validation2.html';
}

function validatefname() {
  var x = document.forms["pinfo"]["fname"].value;
  if (x == "" || !alphaNumCheck(x)) {
	document.getElementById("Firstname").appendChild(getImage(false, "Firstname"));
	document.getElementById("Firstname").appendChild(getMessageFL(false, "Firstname"));
    return false;
  } else {
	document.getElementById("Firstname").appendChild(getImage(true, "Firstname"));
	document.getElementById("Firstname").appendChild(getMessageFL(true, "Firstname"));
	return true;
  }
} 

function validatelname() {
  var x = document.forms["pinfo"]["lname"].value;
  if (x == "" || !alphaNumCheck(x)) {
	document.getElementById("Lastname").appendChild(getImage(false, "Lastname"));
	document.getElementById("Lastname").appendChild(getMessageFL(false, "Lastname"));
    return false;
  } else {
	document.getElementById("Lastname").appendChild(getImage(true, "Lastname"));
	document.getElementById("Lastname").appendChild(getMessageFL(true, "Lastname"));
	return true;
  }
} 

function validateGender() {
	var x = document.forms["pinfo"]["gender"].value;
	if (x == "select") {
	document.getElementById("Gender").appendChild(getImage(false, "Gender"));
	document.getElementById("Gender").appendChild(getMessageGS(false, "Gender"));
    return false;
	} else {
	document.getElementById("Gender").appendChild(getImage(true, "Gender"));
	document.getElementById("Gender").appendChild(getMessageGS(true, "Gender"));
	return true;
  }
}

function validateState() {
	var x = document.forms["pinfo"]["state"].value;
	if (x == "select") {
	document.getElementById("State").appendChild(getImage(false, "State"));
	document.getElementById("State").appendChild(getMessageGS(false, "State"));
    return false;
  } else {
	document.getElementById("State").appendChild(getImage(true, "State"));
	document.getElementById("State").appendChild(getMessageGS(true, "State"));
	return true;
  }
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

function getMessageFL(bool, ID) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        label.setAttribute( 'class', 'errorMessage' );
      }

    label.innerHTML = bool ? "" : "Name must be filled out with alphanumeric characters only!";
    return label;
}

function getMessageGS(bool, ID) {
    var label = document.getElementById("labelNotify" + ID);
    if (label == null) {
        label = document.createElement("LABEL");
        label.id = "labelNotify" + ID;
        label.setAttribute( 'class', 'errorMessage' );
      }

    label.innerHTML = bool ? "" : "Select an entry from the given list.";
    return label;
}