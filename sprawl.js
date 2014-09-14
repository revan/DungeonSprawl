var WebSocketServer = require('ws').Server, wss = new WebSocketServer({port: 8081});

	wss.clientIndex = 0;
	wss.selectNewClient = function(){
		this.clientIndex++;
		if (this.clientIndex >= this.clients.length) {
			this.clientIndex = 0;
		}

		// TODO Notify new client.
		//this.clients[clientIndex].send(
	}

wss.on('connection', function(ws) {

	var keypress = require('keypress');
	keypress(process.stdin);
	process.stdin.on('keypress', function(ch, key) {
		if (key && key.ctrl && key.name == 'c') {
			process.stdin.pause();
			process.exit();
		}

		if (key && 'wasd'.indexOf(key.name) > -1) {
			// We have one listener per client, so ignore others
			if (ws === wss.clients[wss.clientIndex]) {
				//console.log('clientIndex: ' + wss.clientIndex);
				ws.send(key.name);
			}
		}
	});
	process.stdin.setRawMode( true );
	process.stdin.resume();

	// We get messaged total gold on room end.
	// Negative -> game over
	ws.on('message', function(message) {
		console.log('received: %s', message);
		if (ws === wss.clients[wss.clientIndex]) {
			var gold = parseInt(message);

			if (gold < 0) {
				console.log('You died, with a score of ' + -1 * gold);
				process.stdin.pause();
				process.exit();
			} else {
				wss.selectNewClient();
			}
		} else {
			console.log('Message out of turn -- ignored');
		}
	});
});

