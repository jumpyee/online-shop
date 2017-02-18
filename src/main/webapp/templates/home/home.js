$('.card .card-block  #deleteItemButton').click(function (e) {
    e.preventDefault();

    var $button = $(this);
    var $itemContainer = $button.closest('.card');
    var id = $itemContainer.find('input[name="id"]').val();

    $.ajax({
        url: '/async/user/admin/delete_item',
        type: 'POST',
        data: {id: id},
        success: function (res) {
            if (res) {
                $itemContainer.remove();
            }
        }
    });
});