$(document).ready(function () {
    var $form = $("#registrationForm");
    var $modal = $("#registrationModal");

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