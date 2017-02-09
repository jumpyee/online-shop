$('#itemContainer .thumbnail #deleteItemButton').click(function (e) {
    e.preventDefault();
    var $button = $(this);
    var $itemContainer = $button.closest('.item-container');
    var id = $itemContainer.find('input[name="id"]').val();

    debugger;
    $.ajax({
        url: '/async/user/admin/delete_item',
        type: 'POST',
        data: {id:id},
        success: function (res) {
            if(res) {
                $itemContainer.remove();
            }
        }
    });
});