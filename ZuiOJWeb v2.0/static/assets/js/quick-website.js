//
// Popover
//

'use strict';

var Popover = (function() {

	// Variables

	var $popover = $('[data-toggle="popover"]');


	// Methods

	function init($this) {
		var popoverClass = '';

		if ($this.data('color')) {
			popoverClass = ' popover-' + $this.data('color');
		}

		var options = {
			trigger: 'focus',
			template: '<div class="popover' + popoverClass + '" role="tooltip"><div class="arrow"></div><h3 class="popover-header"></h3><div class="popover-body"></div></div>'
		};

		$this.popover(options);
	}


	// Events

	if ($popover.length) {
		$popover.each(function() {
			init($(this));
		});
	}

})();

//
// Tooltip
//

'use strict';

var Tooltip = (function() {

	// Variables

	var $tooltip = $('[data-toggle="tooltip"]');


	// Methods

	function init() {
		$tooltip.tooltip();
	}


	// Events

	if ($tooltip.length) {
		init();
	}

})();

var CopyType = (function() {

	// Variables

	var $element = '.btn-type-clipboard',
		$btn = $($element);


	// Methods

	function init($this) {
		$this.tooltip().on('mouseleave', function() {
			// Explicitly hide tooltip, since after clicking it remains
			// focused (as it's a button), so tooltip would otherwise
			// remain visible until focus is moved away
			$this.tooltip('hide');
		});

		var clipboard = new ClipboardJS($element);

		clipboard.on('success', function(e) {
			$(e.trigger)
				.attr('title', 'Copied!')
				.tooltip('_fixTitle')
				.tooltip('show')
				.attr('title', 'Copy to clipboard')
				.tooltip('_fixTitle')

			e.clearSelection()
		});
	}


	// Events
	if ($btn.length) {
		init($btn);
	}

})();

//
// Forms
//

'use strict';


//
// Form control
//

var FormControl = (function() {

	// Variables

	var $input = $('.form-control'),
		$indeterminateCheckbox = $('[data-toggle="indeterminate"]');


	// Methods

	function init($this) {
		$this.on('focus blur', function(e) {
        	$(this).parents('.form-group').toggleClass('focused', (e.type === 'focus'));
    	}).trigger('blur');
	}


	// Events

	if ($input.length) {
		init($input);
	}

	// Add indeterminate state to a checkbox
	if($indeterminateCheckbox.length) {
		$indeterminateCheckbox.each(function() {
			$(this).prop('indeterminate', true)
		})
	}

})();


//
// Custom input file
//

var CustomInputFile = (function() {

	// Variables

	var $customInputFile = $('.custom-input-file');


	// Methods

	function change($input, $this, $e) {
		var fileName,
			$label = $input.next('label'),
			labelVal = $label.html();

		if ($this && $this.files.length > 1) {
			fileName = ($this.getAttribute('data-multiple-caption') || '').replace('{count}', $this.files.length);
		}
		else if ($e.target.value) {
			fileName = $e.target.value.split('\\').pop();
		}

		if (fileName) {
			$label.find('span').html(fileName);
		}
		else {
			$label.html(labelVal);
		}
	}

	function focus($input) {
		$input.addClass('has-focus');
	}

	function blur($input) {
		$input.removeClass('has-focus');
	}


	// Events

	if ($customInputFile.length) {
		$customInputFile.each(function() {
			var $input = $(this);

			$input.on('change', function(e) {
				var $this = this,
					$e = e;

				change($input, $this, $e);
	        });

	        // Firefox bug fix
	        $input.on('focus', function() {
	            focus($input);
	        })
	        .on('blur', function() {
	            blur($input);
	        });
		});
	}
})();

//
// Sticky Navbar
//

var NavbarSticky = (function() {

	// Variables

	var $nav = $('.navbar-sticky'),
        navOffsetTop = 0,
		scrolling = false;


	// Methods

	function init($this) {

		// our current vertical position from the top
		var scrollTop = $(window).scrollTop(),
			navHeight = $this.outerHeight();

		if (scrollTop > (navOffsetTop + 200)) {
			$this.addClass('sticky');
			$("body").css("padding-top", navHeight + "px");
		} else {
			$this.removeClass('sticky');
			$("body").css("padding-top", "0");
		}
	}


	// Events

	if ($nav.length) {

		navOffsetTop = $nav.offset().top;
		
		$(window).on({
			'scroll': function() {
				scrolling = true;

				setInterval(function() {
					if (scrolling) {
						scrolling = false;

						// Sticky navbar init
                        init($nav);
					}
				}, 250);
			}
		})
	}
})();

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

//# sourceMappingURL=quick-website.js.map
