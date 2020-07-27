$(function () {
    // submitボタンにloading-buttonクラスを設定した場合、
    // 親フォームが送信される際に data-loading-text の表示に変え、
    // ボタンの二通しを防ぐ
    $('.loading-button').on('click', function () {
        var $this = $(this);
        var form = $(this).parents('form:first');
        var target_form_name = $(this).data('target-form-name');
        if (target_form_name) {
            form = $("form[name=" + target_form_name + "]");
        }

        // ローディング表示にする
        var loadingText = $(this).data('loading-text');
        if ($(this).html() !== loadingText) {
            $(this).data('original-text', $(this).html());
            $(this).data('original-val', $(this).val());
            $(this).html(loadingText);
            $(this).val(loadingText);
            $(this).prop('disabled', true);
            $(this).addClass('disabled');
            // submitボタンの場合は、hiddenに退避
            if ($(this).attr("type") == "submit") {
                $('<input>').attr({
                    type: 'hidden',
                    name: $(this).attr("name"),
                }).appendTo(form);
            }
            
            // 画面遷移を伴わない場合は、ボタンが使えないまま残るため、復活させる
            setTimeout(function() {
                $this.html($this.data('original-text'));
                $this.val($this.data('original-val'));
                $this.prop('disabled', false);
                $this.removeClass('disabled');
            }, 2000);
        }
    });
});