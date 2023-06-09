$('.items__main__item__menu__tab__text__count__plus').click(function() {
    let el = $(this).parent().children('input').attr('value');
    $(this).parent().children('input').attr('value', parseInt(el) + 1)
});
$('.items__main__item__menu__tab__text__count__minus').click(function() {
    let el = $(this).parent().children('input').attr('value');
    if (parseInt(el) > 0) {
        $(this).parent().children('input').attr('value', parseInt(el) - 1)
    }
});