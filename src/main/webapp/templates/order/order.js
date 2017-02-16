$(document).ready(function () {
    totalPrice();
    function totalPrice() {
        var total = 0;

        $('.itemSubtotalPrice').each(function () {
            var price = $(this).text();
            total += parseFloat(price);
        });
        $('#totalPrice').html("Total $" + total);

    }


    $('#cancelOrderButton').click(function (e) {
        e.preventDefault();

        var $button = $(this);
        var $itemContainer = $button.closest('.cart');
        var id = $itemContainer.find('input[name="id"]').val();
        $.ajax({
            url: '/async/order/delete_order',
            type: 'POST',
            data: {id: id},
            success: function (res) {
                if (res) {
                    location.href = "/user/order/";
                }
            }
        });
    });
});