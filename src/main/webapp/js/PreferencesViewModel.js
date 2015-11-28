(function(ko) {

	var PreferencesViewModel = function(keywords) {

		this.keywords = ko.observableArray(keywords);

		this.newKeyword = ko.observable();

		this.isExpanded = ko.observable("false");

		this.isVisible = ko.observable(false);

	};

	PreferencesViewModel.prototype.togglePreferences = function() {
		var value = !this.isVisible();
		this.isVisible(value);
		this.isExpanded(value.toString());
	};

	PreferencesViewModel.prototype.addKeyword = function() {
		this.keywords.push(this.newKeyword());
		this.newKeyword("");
	};

	PreferencesViewModel.prototype.removeKeyword = function(keyword) {
		this.keywords.remove(keyword);
	}

	window.PreferencesViewModel = PreferencesViewModel;

}(ko));