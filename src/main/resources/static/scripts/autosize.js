function autosize() {
	try {
		var iframe = parent.document.getElementsByTagName('iframe');
		var len = iframe.length;
		if (len <= 0)
			return;
		var i;
		if (iframe[0].contentDocument) { // for ie9, ie10, edge, chrome,
			// safari, firefox
			for (i = 0; i < len; i++) {
				if (iframe[i].contentDocument == document) {
					iframe[i].style.height = document.body.offsetHeight + 'px';
					if(window.parent.autosize) window.parent.autosize();
					return;
				}
			}
		} else { // for ie6, ie7
			iframe = parent.document.frames;
			len = iframe.length;
			if (len <= 0)
				return;
			for (i = 0; i < len; i++) {
				if (iframe[i].document == document) {
					iframe[i].frameElement.style.height = document.body.scrollHeight
							+ 'px';
					if(window.parent.autosize) window.parent.autosize();
					return;
				}
			}
		}
	} catch (err) {
		alert(err.message);
	}
}