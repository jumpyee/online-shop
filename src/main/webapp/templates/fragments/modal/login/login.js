$(document).ready(function () {
    var $form = $("#loginForm");
    var $modal = $("#loginModal");

    $form.on("submit", function (e) {
        e.preventDefault();
        $.post(
            $form.attr("action"),
            $form.serialize(),
            function (response) {
                if (response.status) {
                    $modal.modal("hide");
                    $form.trigger('reset');
                    $form.find(".form-group").removeClass("has-error");
                    location.reload(); //to update header user info
                } else {
                    $form.find(".form-group").removeClass("has-error");
                    for (let error of response.result) {
                        $form.find("input[name='" + error.field + "']")
                            .parent().addClass("has-error")
                            .find(".help-block").text(error.defaultMessage);
                    }
                }
            }
        )
    });
});