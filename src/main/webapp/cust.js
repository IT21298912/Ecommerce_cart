

jQuery(document).ready(function () {

	$('#btn-print').click(function () {
		// window.print();

		let wspFrame = document.getElementById('frame').contentWindow;
		wspFrame.focus();
		wspFrame.print();
	});
	
	});
