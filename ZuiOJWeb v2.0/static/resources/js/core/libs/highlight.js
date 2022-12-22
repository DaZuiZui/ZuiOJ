//
// Highlight.js
//

'use strict';

var Highlight = (function() {

	//
	// Variables
	//

	var $highlight = $('.highlight');


	//
	// Methods
	//

	function init(i, block) {
		// Insert the copy button inside the highlight block
		var btnHtml = '<button class="action-item btn-clipboard" title="Copy to clipboard"><i data-feather="copy"></i></button>'
		$(block).before(btnHtml)
		$('.btn-clipboard')
			.tooltip()
			.on('mouseleave', function() {
				// Explicitly hide tooltip, since after clicking it remains
				// focused (as it's a button), so tooltip would otherwise
				// remain visible until focus is moved away
				$(this).tooltip('hide');
			});

		// Component code copy/paste
		var clipboard = new ClipboardJS('.btn-clipboard', {
			target: function(trigger) {
				return trigger.nextElementSibling
			}
		})

		clipboard.on('success', function(e) {
			$(e.trigger)
				.attr('title', 'Copied!')
				.tooltip('_fixTitle')
				.tooltip('show')
				.attr('title', 'Copy to clipboard')
				.tooltip('_fixTitle')

			e.clearSelection()
		})

		clipboard.on('error', function(e) {
			var modifierKey = /Mac/i.test(navigator.userAgent) ? '\u2318' : 'Ctrl-'
			var fallbackMsg = 'Press ' + modifierKey + 'C to copy'

			$(e.trigger)
				.attr('title', fallbackMsg)
				.tooltip('_fixTitle')
				.tooltip('show')
				.attr('title', 'Copy to clipboard')
				.tooltip('_fixTitle')
		})

		// Initialize highlight.js plugin
		hljs.highlightBlock(block);
	}


	//
	// Events
	//

	$highlight.each(function(i, block) {
		init(i, block);
	});

})();
