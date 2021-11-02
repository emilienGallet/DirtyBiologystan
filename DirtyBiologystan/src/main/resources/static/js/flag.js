console.log("hello")
let app = Vue.createApp({
	data: () => ({
		path: "https://api-flag.fouloscopie.com/flag/"
	}),
	mounted: async function() {
		let res;
		console.log("Récupération des données du drapeau")
		res = await fetch(this.path, {
			method: 'GET',
		})
		
		let body = await res.json();
		console.debug(body)
		/* TODO */
	},
});

app.mount('#flag');