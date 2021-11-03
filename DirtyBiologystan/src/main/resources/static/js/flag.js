/**
 * Code of Émilien Gallet
 * next step improuve teh data import to be more eco-friendly
 */
console.log("hello")
let app = Vue.createApp({
	data: () => ({
		path: "https://api-flag.fouloscopie.com/flag/",
		carte: {},
	}),
	mounted: async function() {
		let res;
		console.log("Récupération des données du drapeau")
		res = await fetch(this.path, {
			method: 'GET',
		})
		
		let body = await res.json();
		this.carte = new Map();
		for (let i = 0; i < body.length; i++) {
 			this.carte.set(body[i].indexInFlag,body[i].hexColor)
		}
		console.debug(this.carte)
	},
	template: 
	`
	<table>
		<tr>
			<td v-for="el in carte" :id="el[0]" :style="'background-color:'+el[1]+';'">
			
			</td>
		</tr>
	</table>
	`
});


app.mount('#flag');