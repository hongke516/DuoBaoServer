function checkData(v) {
    var  entry = { "'": '&apos;', '"': '&quot;', '<': '&lt;', '>': '&gt;' };
    v = v.replace(/(['")-><&\\\/\.])/g, function ($0) { return entry[$0] || $0; });
    return v;
}

$(function(){
    //获取要定位元素距离浏览器顶部的距离
    var oldPlaceH = $(".stick").offset().top;

    //滚动条事件
    $(window).scroll(function(){

        //获取滚动条的滑动距离
        var scrollH = $(this).scrollTop();
        //滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
        if(scrollH>=oldPlaceH){
            $(".stick").css({"position":"fixed","top":0});
        }else if(scrollH<oldPlaceH){
            $(".stick").css({"position":"static"});
        }
    })
});

function checkZero(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}