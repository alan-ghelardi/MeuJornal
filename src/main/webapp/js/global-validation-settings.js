/**
 * Script responsável por definir algumas configurações globais para o
 * jQuery.validate.
 * 
 */
(function($) {

	$(function() {

		$.validator.setDefaults({

			debug : true,

			highlight : function(element) {
				$(element).closest(".form-group").addClass("has-error");
			},

			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},

			errorPlacement : function(label, element) {
				$('<div class="alert-danger" role="alert"/>').append(label).insertAfter(element);
			}

		});

	});

}(jQuery));
