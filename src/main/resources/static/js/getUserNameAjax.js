<!--従業員コードのロストフォーカスで従業員名を取得する -->
$(window).on('load', function() {
  $(".get_username_ajax").on('blur',function() {
    getUserName(this);
  });
  $(".get_username_ajax").each(function() {
    getUserName(this);
  });
});

function getUserName(target) {
  var inputUserId = $(target).val();
  var targetId = $(target).attr("id");
  $.ajax({
      type : 'POST',
      contentType: 'application/json;charset=UTF-8',
      url : '/ajax/getUserName',
      data : JSON.stringify({userId : inputUserId}),
      success : function(result) {
        $('#'+targetId+'_name').val(result.fullNameKanji);
        $('#'+targetId+'_name').text(result.fullNameKanji);
        $('#'+targetId+'_corpCode').val("");
        $('#'+targetId+'_empNp').val("");
          },
    error : function(e){
          }
      });
}
