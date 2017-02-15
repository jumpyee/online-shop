$(document).ready(function () {
    countTotalPrice();
    function countTotalPrice() {
        var total = 0;

        $('.itemSubtotalPrice').each(function () {
            var price = $(this).text();
            total += parseFloat(price);
        });
        $('#totalPrice').html("Total $" + total);
    }


    $('.removeItemButton').click(function (e) {
        e.preventDefault();

        var $button = $(this);
        var $itemContainer = $button.closest('.cartItemContainer');
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

    $('.refreshItemButton').click(function (e) {
        e.preventDefault();
        var $button = $(this);
        var $itemContainer = $button.closest('.cartItemContainer');
        var id = $itemContainer.find('input[name="id"]').val();
        var email = $itemContainer.find('input[name="email"]').val();
        var amount = parseFloat($itemContainer.find('input[name="amount"]').val());
        var $subTotal = $itemContainer.find('.itemSubtotalPrice');
        var price = parseFloat($itemContainer.find('.itemPrice').text().substring(1));

        $.ajax({
            url: '/async/cart/change_item_amount',
            type: 'POST',
            data: {
                id: id,
                email: email,
                amount: amount
            },
            success: function (res) {
                $subTotal.text(price * amount);
                countTotalPrice();
            }
        });
    });

    $('#confirmOrderButton').click(function (e) {
        e.preventDefault();
        var $button = $(this);
        var $itemContainer = $button.closest('#globalPageControl');
        var email = $itemContainer.find('input[name="email"]').val();


        $.ajax({
            url: '/async/order/add_order',
            type: 'POST',
            data: {
                email: email
            },
            success: function (res) {
                if (res) {
                    location.href = "/user/orders/";
                }
            }
        });

    });

});

