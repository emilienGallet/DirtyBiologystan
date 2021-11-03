/**
 * main.js
 */
let body = document.getElementsByTagName('body');
if(body[0].scrollHeight < document.documentElement.clientHeight){
	let el = document.getElementById('vide')
	let value = document.documentElement.clientHeight - body[0].scrollHeight 
	value = value.toString()+'px'
	el.style.height = value;
}