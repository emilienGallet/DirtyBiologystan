/**
 * Code of Émilien Gallet
 * next step improuve teh data import to be more eco-friendly
 */
console.log("hello")
let app = Vue.createApp({
	data: () => ({
		path: "https://api-flag.fouloscopie.com/flag/",
		carte: {},
		up: 0
	}),
	mounted: async function() {
		/* fonction pour zoomer sur le drapeau */
		
		let canvas = document.getElementById('zoom');
		let ctx = canvas.getContext('2d');
		ctx.fillStyle = 'green';
		ctx.fillRect(10, 10, 100, 100);
		ctx.fillStyle = 'red';
		ctx.fillRect(10, 20, 100, 100);
		/*
		Non utilisée, a été dev avant que j'ai fini'
		Import de l'image : https://codati.ovh
		let res;
		console.log("Récupération des données du drapeau")
		res = await fetch(this.path, {
			method: 'GET',
		})

		let body = await res.json();
		this.carte = new Map();
		for (let i = 0; i < body.length; i++) {
			this.carte.set(body[i].indexInFlag, body[i].hexColor)
		}
		this.up = Math.floor(this.carte.size / 2)
		console.debug(this.carte, this.up)
		*/
		
		let stack = document.getElementById('leDrapeau');
		stack.style.display = "inherit";/*
		let tbody = stack.children[0];
		let firstLine = stack.children[0].children[0];
		let nbElements = firstLine.childElementCount;
		for(let i=0 ; i<nbElements;i++){
			let unPixel = firstLine.children[i];
			let str = unPixel.getAttribute('id');
			let pos =str.split(':') //rappel : colone:ligne mais on les traite inverrsement
			if(pos[0]!='1'){
				unPixel.remove();
				if(tbody.childElementCount<Number.parseInt(pos[0])){
					tbody.appendChild(document.createElement('tr'));
					let otherLine = tbody.children[Number.parseInt(pos[0])-1]
					otherLine.appendChild(unPixel);
				}
			}
		}
		
		stack.style.display = "inherit"*/
	},
	template:
		`
	
	<!--
	Abandonnée car déjà crée par un autre dev
	
		<table>
		<tr>
			<td v-for="(el,index) in carte" :id="el[0]" :style="'background-color:'+el[1]+';'">
			</td>
		</tr>
	</table>-->
	`,
	methods: {
		mouse_position: function(){
		    var e = window.event;
		
		    var posX = e.clientX;
		    var posY = e.clientY;
		
		    var t = setTimeout(mouse_position,100);
		}
		
	}
	
});


app.mount('#flag');