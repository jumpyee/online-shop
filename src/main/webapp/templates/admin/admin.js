$(document).ready(function () {
    $('#adminPageTabs a').click(function (e) {
        e.preventDefault();
        $(this).tab('show')
    })
    $(".search").keyup(function () {
        var searchTerm = $(".search").val();
        var listItem = $('.results tbody').children('tr');
        var searchSplit = searchTerm.replace(/ /g, "'):containsi('");

        $.extend($.expr[':'], {
            'containsi': function (elem, i, match, array) {
                return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
            }
        });

        $(".results tbody tr").not(":containsi('" + searchSplit + "')").each(function (e) {
            $(this).attr('visible', 'false');
        });

        $(".results tbody tr:containsi('" + searchSplit + "')").each(function (e) {
            $(this).attr('visible', 'true');
        });

        var jobCount = $('.results tbody tr[visible="true"]').length;
        $('.counter').text(jobCount + ' item');

        if (jobCount == '0') {
            $('.no-result').show();
        }
        else {
            $('.no-result').hide();
        }
    });

    $('#userControlTab .user .ban-user').click(function (e) {
        e.preventDefault();
        $.post('/async/user/admin/ban_user', {email: email}, function (response) {
            if (response) {
                $button.removeClass('btn-success').addClass('btn-danger').text('Unban');
            }
        });

        var $button = $(this);
        var email = $button.closest('.user').find('.email').text();
        if ($button.text() == 'Ban') {
            $.post('/async/user/admin/ban_user', {email: email}, function (response) {
                if (response) {
                    $button.removeClass('btn-success').addClass('btn-danger').text('Unban');
                }
            });
        } else {
            $.post('/async/user/admin/unban_user', {email: email}, function (response) {
                if (response) {
                    $button.removeClass('btn-danger').addClass('btn-success').text('Ban');
                }
            });
        }
    });


});