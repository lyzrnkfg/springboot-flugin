<!--グループ名のロストフォーカスでグループIDを取得する -->
$(window).on('load', function() {
  $(".get_groupid_ajax").on('blur',function() {
    getGroupId(this);
  });
  $(".get_groupid_ajax").each(function() {
    getGroupId(this);
  });
});

function getGroupId(target) {
  var inputGroupName = $(target).val();
  var targetId = $(target).attr("id");
  $.ajax({
      type : 'POST',
      contentType: 'application/json;charset=UTF-8',
      url : '/ajax/getGroupId',
      data : JSON.stringify({groupName : inputGroupName}),
      success : function(result) {
        $('#'+targetId+'_id').val(result);
        $('#'+targetId+'_id').text(result);
          },
    error : function(e){
          }
      });
}
