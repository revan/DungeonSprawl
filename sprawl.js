var WebSocketServer = require('ws').Server, wss = new WebSocketServer({port: 8081});
wss.on('connection', function(ws) {
	var keypress = require('keypress');
	keypress(process.stdin);
	process.stdin.on('keypress', function(ch, key) {
		if (key && key.ctrl && key.name == 'c') {
			process.stdin.pause();
			process.exit();
		}

		if (key && 'wasd'.indexOf(key.name) > -1) {
			/*for (var i in this.clients) {
				this.clients[i].send(key.name);
			}*/
			ws.send(key.name);
		}
	});
	process.stdin.setRawMode( true );
	process.stdin.resume();

	ws.on('message', function(message) {
		console.log('received: %s', message);
	});
	ws.send('something');

});

