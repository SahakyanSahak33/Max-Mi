$(document).ready(function(){
    $('.slider_for').slick({
        arrows: false,
        dots:false,
        fade: true,
        asNavFor: '.slider_nav'
      });
    $('.slider_nav').slick({
    slidesToShow: 4,
    slidesToScroll: 1,
    asNavFor: '.slider_for',
    dots: false,
    arrows:true,
    variableWidth: true,
    variableHeight: true,
    focusOnSelect: true,
    nextArrow: '.rbtn',
    prevArrow: '.lbtn', 
    });  
});



// // console.log(x)
// for(let i = 0; i < x.length; i++){
//   x[i].addEventListener("click",() => {
//     // console.log(z);
//     z.classList.remove('pb2_main_3_1_active');
//     z.classList.add('pb2_main_3_1');
//     x[i].classList.remove('pb2_main_3_1');
//     x[i].classList.add('pb2_main_3_1_active');
//   })
// }

$(".pb_2_buttons_a").on('click', function(){
  $('.pb_2_buttons_a').removeClass('pb_2_buttons_active')
  $(this).addClass('pb_2_buttons_active')
})

$(".pb_2_ram_a").on('click', function(){
  $('.pb_2_ram_a').removeClass('pb_2_ram_active')
  $(this).addClass('pb_2_ram_active')
})

$(".pb2_main_3_1").on('click', function(){
  $('.pb2_main_3_1_active').addClass('pb2_main_3_1')
  $('.pb2_main_3_1_active').removeClass('pb2_main_3_1_active')
  $(this).addClass('pb2_main_3_1_active')
  $(this).removeClass('pb2_main_3_1')
})

// let z = document.querySelectorAll('.pb2m3_3');
// console.log(z)
// console.log(z.childNodes)
// z.childNotes[5]
// let u = document.querySelector('.pb2m3_3');
