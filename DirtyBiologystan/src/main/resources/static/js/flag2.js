/**
 * Code of Émilien Gallet
 * next step improuve teh data import to be more eco-friendly
 */
function displayFlag(boolean){
	let x = document.getElementById('leDrapeau').style.display
	if(x=="none"){
		console.log("afficher drapeau")
		document.getElementById('leDrapeau').style.display="inherit"
		document.getElementById('displayFlag').value="Cacher le drapeau"
	}else{
		console.log("cacher drapeau")
		document.getElementById('leDrapeau').style.display="none"
		document.getElementById('displayFlag').value="Afficher le drapeau"
	}
} 