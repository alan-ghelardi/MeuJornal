(function($) {

	$(function() {
		var selectCategory = $('#categories'), newCategory = $('#new-category');

		$('#feeds-form').validate({
			submitHandler : function(form) {
				var attrName = 'feed.categoria';

				if (newCategory.is(':hidden')) {
					selectCategory.attr('name', attrName);
				} else {
					newCategory.attr('name', attrName);
				}

				form.submit();
			}
		});

		selectCategory.on('change', function() {
			if (selectCategory.val() === 'other') {
				newCategory.closest('.form-group').show();
				newCategory.focus();
			} else if (!newCategory.is(':hidden')) {
				newCategory.closest('.form-group').hide();
			}
		});

	});

}(jQuery));
