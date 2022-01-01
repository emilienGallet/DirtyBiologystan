/**
 * Code of Émilien Gallet
 */

/**
 * On or off about the coordinate display
 */
function displayCoordinate() {
	console.log('isok')
	let selected = document.getElementById('roles').options.selectedIndex;
	if (selected === 0 || selected === '0') { /* JS is chit sometimes */
		document.getElementById('colone').style.display = "none"
		document.getElementById('ligne').style.display = "none"
	} else {
		document.getElementById('colone').style.display = "inline-block"
		document.getElementById('ligne').style.display = "inline-block"
	}
}

function isPhone() {
	let str = navigator.userAgent;
	//alert(str)
	if (str.includes("Windows") || str.includes("Linux")) {
		document.getElementById('colone').style.display = "none"
		document.getElementById('ligne').style.display = "none"
		return false;
	}
	return true;
}

function forPhone() {
	if (isPhone()) {
		let element = document.getElementById('roles'); //erreur a ce niveau la sur emulateur et pas sur PC
		element.addEventListener('change', function() {
			displayCoordinate();
		}, false);
	}
}

window.onloadend = forPhone();