$(document).ready(function () {
    var total = 0;

    $('.itemSubtotalPrice').each(function () {
        var price = $(this).text();
        total += parseFloat(price);
    });
    $('#totalPrice').html("Total $" + total);
});

$('#removeItemButton').click(function (e) {
    e.preventDefault();

    var $button = $(this);
    var $itemContainer = $button.closest('#cartItemContainer');
    var id = $itemContainer.find('input[name="id"]').val();
    var email = $itemContainer.find('input[name="email"]').val();


    $.ajax({
        url: '/async/cart/delete_item',
        type: 'POST',
        data: {
            id: id,
            email: email
        },
        success: function (res) {
            if (res) {
                $itemContainer.remove();
            }
        }
    });
});

$('#cartItemContainer #actionsContainer #refreshItemButton').click(function (e) {
    e.preventDefault();

    var $button = $(this);
    var $itemContainer = $button.closest('#cartItemContainer');
    var id = $itemContainer.find('input[name="id"]').val();
    var email = $itemContainer.find('input[name="email"]').val();
    var amount = $itemContainer.find('input[name="amount"]').val();
var subTotal = $itemContainer.find('.itemSubtotalPrice');
var price = $itemContainer.find('.itemPrice');

    $.ajax({
        url: '/async/cart/change_item_amount',
        type: 'POST',
        data: {
            id: id,
            email: email,
            amount: amount
        },
        success: function (res) {
        }
    });
});