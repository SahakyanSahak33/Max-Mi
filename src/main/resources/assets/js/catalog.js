let chevron_1 = document.querySelector('.chevron_1').onclick = chevron_num_1;
let chevron_1_close = document.querySelector('.chevron_1_close').onclick = closing_first;

function chevron_num_1(){
    let chevron_1_open = document.querySelector('.chevron_1');
    let content_1 = document.querySelector('.chevron_1_js');
    let chevron_1_closea = document.querySelector('.chevron_1_close');

    content_1.style.display="flex";
    chevron_1_open.style.display="none";
    chevron_1_closea.style.display="block";
    
}
function closing_first(){
    let content_1_close = document.querySelector('.chevron_1_js');
    let chevron_1_dissapear = document.querySelector('.chevron_1');
    let chevron_1_closeaw = document.querySelector('.chevron_1_close');
    content_1_close.style.display="none";
    chevron_1_dissapear.style.display="block";
    chevron_1_closeaw.style.display="none";
}


let size = document.querySelector('.chevron_2').onclick = size_open;
let size_close = document.querySelector('.chevron_2_close').onclick = size_close_func;


function size_open(){
    let size_content = document.querySelector('.global_button_sml_size');
    let size_chevron_dissapear = document.querySelector('.chevron_2');
    let chevron_appear_again2 = document.querySelector('.chevron_2_close');

    size_content.style.display="flex";
    size_chevron_dissapear.style.display="none";
    chevron_appear_again2.style.display="block";
}

function size_close_func(){
    let size_content_closing = document.querySelector('.global_button_sml_size');
    let size_chevron_dissapear_closing = document.querySelector('.chevron_2');
    let chevron_appear_again2_closing = document.querySelector('.chevron_2_close');

    size_content_closing.style.display="none";
    size_chevron_dissapear_closing.style.display="block";
    chevron_appear_again2_closing.style.display="none";
}


let color = document.querySelector('.chevron_3').onclick = color_open;
let color_close = document.querySelector('.chevron_3_close').onclick = color_close_func;


function color_open(){
    let color_content = document.querySelector('.link_buy_2__2');
    let color_chevron_dissapear = document.querySelector('.chevron_3');
    let color_appear_again2 = document.querySelector('.chevron_3_close');

    color_content.style.display="flex";
    color_chevron_dissapear.style.display="none";
    color_appear_again2.style.display="block";
}

function color_close_func(){
    let color_content_closing = document.querySelector('.link_buy_2__2');
    let color_chevron_dissapear_closing = document.querySelector('.chevron_3');
    let color_appear_again2_closing = document.querySelector('.chevron_3_close');

    color_content_closing.style.display="none";
    color_chevron_dissapear_closing.style.display="block";
    color_appear_again2_closing.style.display="none";
}

let marka = document.querySelector('.chevron_5').onclick = marka_open;
let marka_close = document.querySelector('.chevron_5_close').onclick = marka_close_func;


function marka_open(){
    let marka_content = document.querySelector('.buttons_markers');
    let marka_chevron_dissapear = document.querySelector('.chevron_5');
    let marka_appear_again2 = document.querySelector('.chevron_5_close');

    marka_content.style.display="flex";
    marka_chevron_dissapear.style.display="none";
    marka_appear_again2.style.display="block";
}
function marka_close_func(){
    let marka_content_closing = document.querySelector('.buttons_markers');
    let marka_chevron_dissapear_closing = document.querySelector('.chevron_5');
    let marka_appear_again2_closing = document.querySelector('.chevron_5_close');

    marka_content_closing.style.display="none";
    marka_chevron_dissapear_closing.style.display="block";
    marka_appear_again2_closing.style.display="none";
}

let country = document.querySelector('.chevron_6').onclick = country_open;
let country_close = document.querySelector('.chevron_6_close').onclick = country_close_func;


function country_open(){
    let country_content = document.querySelector('.obshi_ffff');
    let country_chevron_dissapear = document.querySelector('.chevron_6');
    let country_appear_again2 = document.querySelector('.chevron_6_close');

    country_content.style.display="flex";
    country_chevron_dissapear.style.display="none";
    country_appear_again2.style.display="block";
}

function country_close_func(){
    let country_content_closing = document.querySelector('.obshi_ffff');
    let country_chevron_dissapear_closing = document.querySelector('.chevron_6');
    let country_appear_again2_closing = document.querySelector('.chevron_6_close');

    country_content_closing.style.display="none";
    country_chevron_dissapear_closing.style.display="block";
    country_appear_again2_closing.style.display="none";
}


$(".numbers_padding").on('click', function(){
    $('.numbers_padding').removeClass('active_class_catalog');
    $(this).addClass('active_class_catalog');
})


let cataloglist_open = document.querySelector('.noback').onclick = cataloglist;
let cataloggrid_open = document.querySelector('.noback_2').onclick = cataloggrid;


let buttons = document.querySelector('.noback');
let buttons_2 = document.querySelector('.noback_2');


function cataloglist() {
    let cataloglistinfo = document.querySelector('.fld_column_cat_list');
    let cataloggridinfo = document.querySelector('.catalog_grid_version');


    cataloggridinfo.style.display="none";
    buttons_2.classList.remove('ct_active');
    cataloglistinfo.style.display="flex";
    buttons.classList.add('cg_active')
}
function cataloggrid() {
    let cataloglistinfo_2 = document.querySelector('.fld_column_cat_list');
    let cataloggridinfo_2 = document.querySelector('.catalog_grid_version');

    cataloggridinfo_2.style.display="flex";
    buttons_2.classList.add('ct_active');
    cataloglistinfo_2.style.display="none";
    buttons.classList.remove('cg_active')
}
$(".svg_040").on('click', function(){
    $('.svg_040').removeClass('active_class');
    $(this).addClass('active_class');
});




let removetag1 = document.querySelector('.remove_tag_number_one').onclick = removingprocess;

let removetag2 = document.querySelector('.remove_tag_number_two').onclick = removingprocess_2;


let removeallsud = document.querySelector('.click_removes').onclick = erase_all;


function removingprocess(){
    let allclass = document.querySelector('.removing_option');
    let apranq = document.querySelector('.apranqanishnery');
    apranq.style.display="none";
    allclass.style.display="none";
}

function removingprocess_2(){
    let allclass_2 = document.querySelector('.removing_option2');
    let apranq_2 = document.querySelector('.apranqanishnery_2');
    apranq_2.style.display="none";
    allclass_2.style.display="none";
}

function erase_all(){
    let allclass_delete = document.querySelector('.removing_option');
    let apranq_delete = document.querySelector('.apranqanishnery');
    let allclass_2_delete = document.querySelector('.removing_option2');
    let apranq_2_delete = document.querySelector('.apranqanishnery_2');

    allclass_delete.style.display="none";
    apranq_delete.style.display="none";
    allclass_2_delete.style.display="none";
    apranq_2_delete.style.display="none";
}


let inputt = document.querySelector('.inputrange');
let outputt = document.querySelector('#counter');


// var x = document.getElementById("myRange").value;
//   document.getElementById("counter").innerHTML = x;

outputt.innerHTML = inputt.value;

inputt.addEventListener(".inputrange", function(){
    outputt.innerHTML = inputt.value;
}, false);



$(".ddde").on('click', function(){
    $('.ddde').removeClass('active_class_marka');
    $(this).addClass('active_class_marka');
});





$(document).ready(function(){
    $('.secondblock_catalog').slick({
        dots: false,
        arrows: true,
        slidesToShow:8,
        slidesToScroll:1,
        speed:1000,
        easing:'_linear_',          //defualt
        infinite: true, 
        nextArrow: '.rrar',
        prevArrow: '.lrar',           //defualt
        autoplay:true,              
        autoplaySpeed: 5000,
        pauseOnFocus:true,          //defualt
        pauseOnHover:true,          //defualt
        pauseOnDotsHover:true,      //defualt
        draggable: true,           //for pc 'Erb vor mknikov porcum enq sharjel'
        swipe:true,                //for touch screen
        touchThresHold:5,           //defualt 'kapvaca swipe-i het'
        touchMove:true,            //sxmac chenq karox sharjel
        waitForAnimate:true,        //no defualt     
        centerMode: false,          //defualt
        variableWidth: true,       //defualt
        rows:1,                     //Defualt 'Sharqer irar tak'
        slidesPerRow: 1,            //defualt 'Sharqer irar koxq'
        // Vertical verev nerqev
        vertical:false,              //defualt
        verticalSwiping:false,
        // responsive: [
        //     {
        //         breakpoint: 768,
        //         settings: {
        //             slidesToShow: 3,
        //         }
        //     },
        //     {
        //         breakpoint: 468,
        //         settings: {
        //             slidesToShow: 2,
        //         }
        //     }
        // ],
        mobileFirst: false,         //defualt min-width isk false --- max-widts=h
        // appendArrows: $('.fb_1_arrows'), //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
        // appendDots: $('.appendArrows'),   //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
    });    
});





var rangeSlider = function () {
    var e = $(".range-slider"),
        n = $(".range-slider__range"),
        i = $(".range_slide_value");
    e.each(function () {
        n.on("input", function () {
            $(this).next(i).val(this.value),
                $(this)
                    .parents(".range-slider")
                    .children(".value_max_left")
                    .html(this.value + " դրամ․");
        });
    });
};
rangeSlider(),
    (document.getElementById("myinput").oninput = function () {
        var e = ((this.value - this.min) / (this.max - this.min)) * 100;
        this.style.background = "linear-gradient(to right, #f15c22 0%, #f15c22 " + e + "%, #F2F9FF " + e + "%, #F2F9FF 100%)";
});


$(".sml_sizes").on('click', function(){
    $('.sml_sizes').removeClass('activebutton');
    $(this).addClass('activebutton');
})

