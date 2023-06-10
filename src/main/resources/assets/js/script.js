$(document).ready(function(){
    $('.fb_1_main').slick({
        dots: false,
        arrows:true,
        slidesToShow:1,
        slidesToScroll:1,
        speed:1000,
        easing:'_linear_',          //defualt
        infinite: true, 
        nextArrow: '.fb_1_arrow_2',
        prevArrow: '.fb_1_arrow_1',           //defualt
        autoplay:true,              
        autoplaySpeed: 5000,
        pauseOnFocus:true,          //defualt
        pauseOnHover:true,          //defualt
        pauseOnDotsHover:true,      //defualt
        draggable: true,           //for pc 'Erb vor mknikov porcum enq sharjel'
        swipe:true,                //for touch screen
        touchThresHold:5,           //defualt 'kapvaca swipe-i het'
        touchMove:false,            //sxmac chenq karox sharjel
        waitForAnimate:true,        //no defualt     
        centerMode: false,          //defualt
        variableWidth: false,       //defualt
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
        appendArrows: $('.fb_1_arrows'), //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
        // appendDots: $('.appendArrows'),   //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
    });    
});
$(document).ready(function(){
    $('.secondblock').slick({
        dots: false,
        arrows:false,
        slidesToShow:11,
        slidesToScroll:1,
        speed:1000,
        easing:'_linear_',          //defualt
        infinite: true, 
        // nextArrow: '.fb_1_arrow_1',
        // prevArrow: '.fb_1_arrow_2',           //defualt
        autoplay:true,              
        autoplaySpeed: 1000,
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
$(document).ready(function(){
    $('.slider_2').slick({
        dots: true,
        arrows:true,
        slidesToShow:1,
        slidesToScroll:1,
        speed:1000,
        easing:'_linear_',          //defualt
        infinite: true, 
        nextArrow: '.slider_2_arrows_2',
        prevArrow: '.slider_2_arrows_1',           //defualt
        autoplay:true,              
        autoplaySpeed: 5000,
        pauseOnFocus:true,          //defualt
        pauseOnHover:true,          //defualt
        pauseOnDotsHover:true,      //defualt
        draggable: true,           //for pc 'Erb vor mknikov porcum enq sharjel'
        swipe:true,                //for touch screen
        touchThresHold:5,           //defualt 'kapvaca swipe-i het'
        touchMove:false,            //sxmac chenq karox sharjel
        waitForAnimate:true,        //no defualt     
        centerMode: false,          //defualt
        variableWidth: false,       //defualt
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
        appendArrows: $('.slider_2_arrows'), //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
        // appendDots: $('.appendArrows'),   //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
    });    
});
$(document).on('click',function(){
    $('.hsin_posa').removeClass('hsin_posa_active');
})
$(document).ready(function(){
    $('.slider_3').slick({
        dots: true,
        arrows:true,
        slidesToShow:1,
        slidesToScroll:1,
        speed:1000,
        easing:'_linear_',          //defualt
        infinite: true,
        nextArrow: '.slider_3_arrows_2',
        prevArrow: '.slider_3_arrows_1',           //defualt
        autoplay:true,
        autoplaySpeed: 5000,
        pauseOnFocus:true,          //defualt
        pauseOnHover:true,          //defualt
        pauseOnDotsHover:true,      //defualt
        draggable: true,           //for pc 'Erb vor mknikov porcum enq sharjel'
        swipe:true,                //for touch screen
        touchThresHold:5,           //defualt 'kapvaca swipe-i het'
        touchMove:false,            //sxmac chenq karox sharjel
        waitForAnimate:true,        //no defualt
        centerMode: false,          //defualt
        variableWidth: false,       //defualt
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
        appendArrows: $('.slider_3_arrows'), //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
        // appendDots: $('.appendArrows'),   //html-i mej sarqum es div et classi anunov  kam urish anunov u iran pahum dra mej
    });
});
// let ddsa = document.querySelector('.header_signed_in').addEventListener("click",signedIn);
// let header_signed_in_duble = document.querySelector('.header_signed_in').addEventListener("dblclick",cloosingsignedIn);
let kxz = document.querySelector('.hsin_posa');
function signedIn(e){
    kxz.classList.remove("hsin_posa_no_active");
    setTimeout(() => {
        kxz.classList.add("hsin_posa_active");
    },1);
    // e.stopImmediatePropagation();
    // this.removeEventListener("click", signedIn);
    // document.onclick = cloosingsignedIn;
};
function cloosingsignedIn(x){
    kxz.classList.remove("hsin_posa_active");
    setTimeout(() => {
        kxz.classList.add("hsin_posa_no_active");
    },1);
    // x.stopImmediatePropagation();
    // this.removeEventListener("click", cloosingsignedIn);

};

$('.header_signed_in').on('click',function(e){
    $('.hsin_posa').toggleClass('hsin_posa_active')
    e.stopPropagation();
 })
