$('#itemContainer #itemInfoContainer #addToCartButton').click(function (e) {
    e.preventDefault();
    var $button = $(this);
    var $itemContainer = $button.closest('#itemContainer');
    var id = $itemContainer.find('input[name="id"]').val();
    var email = $itemContainer.find('input[name="email"]').val();


    $.ajax({
        url: '/async/user/item/add_to_cart',
        type: 'POST',
        data: {
            id: id,
            email: email
        },
        success: function (res) {

        }
    });
});