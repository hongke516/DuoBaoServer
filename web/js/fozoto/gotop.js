//$(document).ready(function () {
//    //惜时如金，获取当前时间并刷新时间
//    $("#saveTime").text(startClock());
//});

//function startClock() {
//    var myDate = new Date();
//    var hour = myDate.getHours();
//    var minute = myDate.getMinutes();
//    var sec = myDate.getSeconds();
//    var year = myDate.getFullYear();
//    var month = myDate.getMonth() + 1;
//    var day = myDate.getDate();
//    month = checkZero(month);
//    day = checkZero(day);
//    minute = checkZero(minute);
//    sec = checkZero(sec);
//    document.getElementById("saveTime").innerHTML = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + sec;
//    setTimeout("startClock()", 500);
//}