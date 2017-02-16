$(document).ready(function () {
    $("#successAlert").hide();
    $("#dangerAlert").hide();

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
            error: function () {
                $("#dangerAlert").show();
                window.setTimeout(function () {
                    $("#dangerAlert").hide();
                }, 1500);
            },
            success: function (res) {
                $("#successAlert").show();
                window.setTimeout(function () {
                    $("#successAlert").hide();
                }, 1000);
            }

        });
    });

});