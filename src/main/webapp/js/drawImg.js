function DrawImg(boxWidth, boxHeight) {
    var imgWidth = $("#img1").width();
    var imgHeight = $("#img1").height();
    /*比较imgBox的长宽比与img的长宽比大小*/
    if ((boxWidth / boxHeight) >= (imgWidth / imgHeight)) {
        //重新设置img的width和height        
        $("#img1").width((boxHeight * imgWidth) / imgHeight);
        $("#img1").height(boxHeight);
        //让图片居中显示       
        var margin = (boxWidth - $("#img1").width()) / 2;
        $("#img1").css("margin-left", margin);

    } else {
        //重新设置img的width和height        
        $("#img1").width(boxWidth);
        $("#img1").height((boxWidth * imgHeight) / imgWidth);
        //让图片居中显示       
        var margin = (boxHeight - $("#img1").height()) / 2;
        $("#img1").css("margin-top", margin);
    }
}
