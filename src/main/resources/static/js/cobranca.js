$(document).ready(function(){
	  $('#vencimento-modal').mask('00/00/0000');
});	

$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>' + descricaoTitulo + '</strong>?');
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
		
		var response = $.ajax({
			url: urlReceber,
			type: 'PUT'
		});
		
		
		response.done(function(e) {
			var codigoTitulo = botaoReceber.data('codigo');
			$('[data-role=' + codigoTitulo + ']').html('<span class="label label-success">' + e + '</span>');
			botaoReceber.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro recebendo cobrança');
		});
		
	});
});

$('.js-contraste-toggle').bind('click',function(){
	
//	$('.js-navbar').toggleClass('navbar-inverse');
}
		
);

$( ".js-btn-novo" ).on( "click", function novo() {
	
	$('#tituloModal').on('show.bs.modal', function(event){
	
		if (event.relatedTarget === undefined) return;
		
		var modal = $(this);
		var form = modal.find('form');
		
		var codigo = document.getElementById('codigo-modal');
		var descricao = document.getElementById('descricao-modal');
		var datavencimento = document.getElementById('vencimento-modal');
		var valor = document.getElementById('valor-modal');
		var status = document.getElementById('status');

		codigo.value = '';
		descricao.value = '';
	    datavencimento.value = '';
		valor.value = '';
		status.value = 'PENDENTE';
		
	});
});
		
$( ".js-btn-editar" ).on( "click", function editar() {
	
	$('#tituloModal').on('show.bs.modal', function(event){
	
		if (event.relatedTarget === undefined) return;	
		
		    var button = $(event.relatedTarget);	
			var codigoJS = button.data('codigo');
			var descricaoJS = button.data('descricao');
			var dataVencimentoJS = button.data('vencimento');
			var valorJS = button.data('valor');
			var statusJS = button.data('status');

	var modal = $(this);
	
	var form = modal.find('form');	
	var codigo = document.getElementById('codigo-modal');
	var descricao = document.getElementById('descricao-modal');
	var datavencimento = document.getElementById('vencimento-modal');
	var valor = document.getElementById('valor-modal');
	var status = document.getElementById('status');

	codigo.value = codigoJS;
	descricao.value = descricaoJS;
    datavencimento.value = moment(dataVencimentoJS, "YYYY-MM-DD").format("DD/MM/YYYY");
	valor.value = valorJS;
	status.value = statusJS;

	});
});




$('#sandbox-container .input-group.date').datepicker({
    format: "dd/mm/yyyy",
    todayBtn: "linked",
    language: "pt-BR",
    orientation: "bottom auto",
    autoclose: true,
    todayHighlight: true
});
//var $datepicker = $('#sandbox-container .input-group.date');
//$datepicker.datepicker('setDate', '27/05/2003');
