//控制窗口状态
var itsSystemOpen = true;
var devInfoOpen = true;
var contentRightOpen = true;
var contentLeftOpen = true;

var defaultH = 30;
var contentLeft = document.getElementById("content_left");
var contentLeftItem = document.getElementById("content_left_item");
var itsSystem = document.getElementById("its_system");
var contentLeftTitle = document.getElementById("left_title");
var devInfo = document.getElementById("dev_info");
var iteSss = document.getElementById("itesss")
var contentCenter = document.getElementById("content_center");
var contentRight = document.getElementById("content_right");
var contentRightTitle = document.getElementById("right_title");
var contentRightItem = document.getElementById("content_right_item");
var devVvv = document.getElementById("devvv");

//所属系统点击事件
function startMove1(event) {
    var e = event || window.event;
    var obj = event.target.parentNode.parentNode;
    if(itsSystemOpen) {
        obj.style.height = '30px';
        contentLeft.style.height = "calc(100% - 30px)"
        event.target.style.backgroundPosition = '-16px -16px';
    } else {
        obj.style.height = '30%';
        contentLeft.style.height = "70%"
        event.target.style.backgroundPosition = '-16px 0';
    }
    itsSystemOpen = !itsSystemOpen;
}

//设备详情点击事件
function startMove2(event) {
    var e = event || window.event;
    var obj = event.target.parentNode.parentNode;
    if(devInfoOpen) {
        obj.style.height = '30px';
        contentRight.style.height = "calc(100% - 30px)"
        contentCenter.style.height = "calc(100% - 30px)"
        event.target.style.backgroundPosition = '-16px -16px';
    } else {
        obj.style.height = '30%';
        contentRight.style.height = "70%"
        contentCenter.style.height = "70%"
        event.target.style.backgroundPosition = '-16px 0';
    }
    devInfoOpen = !devInfoOpen;
}

//空间详情点击事件
function startMove3(event) {
    var e = event || window.event;
    var obj = event.target.parentNode.parentNode;
    if(contentRightOpen) {
        obj.style.width = '30px';
        obj.style.backgroundColor = "rgb(242,242,242)";
        event.target.style.backgroundPosition = '0 0';
        contentRightTitle.style.display = "none";
        contentRightItem.style.display = "none";
    } else {
        obj.style.width = '20%';
        event.target.style.backgroundPosition = '0 -16px';
        contentRightTitle.style.display = "block";
        obj.style.backgroundColor = "white";
        contentRightItem.style.display = "block";
    }
    contentRightOpen = !contentRightOpen;
}

//空间点击事件
function startMove4(event) {
    var e = event || window.event;
    var obj = event.target.parentNode.parentNode;
    if(contentLeftOpen) {
        obj.style.width = "30px";
        obj.style.backgroundColor = "rgb(242,242,242)";
        obj.style.height = "100%";
        event.target.style.backgroundPosition = "0 -16px";
        contentLeftTitle.style.display = "none";
        contentLeftItem.style.display = "none";
        itsSystem.style.width = "30px";
        itsSystem.style.height = "0";
        devInfo.style.left = "0";
        devInfo.style.width = "100%";
    } else {
        obj.style.width = "20%";
        obj.style.backgroundColor = "white";
        obj.style.height = "70%";
        event.target.style.backgroundPosition = "0 0";
        contentLeftTitle.style.display = "block";
        contentLeftItem.style.display = "block";
        itsSystem.style.width = "20%";
        itsSystem.style.height = "30%";
        devInfo.style.left = "20%";
        devInfo.style.width = "calc(100% - 20%)";
        itsSystemOpen = true;
        iteSss.style.backgroundPosition = '-16px 0';
    }
    contentLeftOpen = !contentLeftOpen;
}

//所属系统缩放
function divZoom1(event, divId) {
    var e = event || window.event;
    var totalH = document.body.offsetHeight;
    iteSss.style.backgroundPosition = "-16px 0";
    itsSystemOpen = true;
    var zoomItem = e.target;
    var zoomDiv = document.getElementById(divId);
    var oldTop = zoomItem.offsetTop;
    var oldY = e.clientY;
    var oldHeight = zoomDiv.offsetHeight;
    var zoomHr = document.createElement("div");
    zoomHr.className = "zoom_hr";
    zoomHr.style.width = itsSystem.style.width || "20%";
    document.body.appendChild(zoomHr);
    zoomHr.style.bottom = totalH - e.clientY - 2 + 'px';
    zoomHr.onmouseup = function(event){
        var e = event || window.event;
        if(zoomHr.style.bottom === totalH - oldY - 2 + 'px'){
            zoomHr.remove();
            document.onmousemove = null;
            document.onmouseup = null;
        }
    }
    document.onmousemove = function(event) {
        var e = event || window.event;
        zoomHr.style.bottom = totalH - e.clientY - 2 + 'px';
        var newY = e.clientY;
        var h = oldY - newY + oldTop + oldHeight;
        h = Math.max(h, defaultH);
        h = Math.min(h, totalH - 30);

        document.onmouseup = function() {
            zoomHr.remove();
            zoomDiv.style.height = h + "px";
            h = totalH - h + "px";
            contentLeft.style.height = h;
            document.onmousemove = null;
            document.onmouseup = null;
        }
    }
}

//设备，空间列表缩放
function divZoom2(event, divId) {
    var e = event || window.event;
    var totalH = document.body.offsetHeight;
    devVvv.style.backgroundPosition = "-16px 0";
    devInfoOpen = true;
    var zoomItem = e.target;
    var zoomDiv = document.getElementById(divId);
    var oldTop = zoomItem.offsetTop;
    var oldY = e.clientY;
    var oldHeight = zoomDiv.offsetHeight;
    var zoomHr = document.createElement("div");
    zoomHr.className = "zoom_hr";
    zoomHr.style.width = devInfo.style.width || "80%";
    document.body.appendChild(zoomHr);
    zoomHr.style.right = "0";
    zoomHr.style.bottom = totalH - e.clientY - 2 + 'px';
    zoomHr.onmouseup = function(event){
        var e = event || window.event;
        if(zoomHr.style.bottom === totalH - oldY - 2 + 'px'){
            zoomHr.remove();
            document.onmousemove = null;
            document.onmouseup = null;
        }
        $(document).trigger("bottompanel.resize");
    }
    document.onmousemove = function(event) {
        var e = event || window.event;
        var newY = e.clientY;
        var h = oldY - newY + oldTop + oldHeight;
        h = Math.max(h, defaultH);
        h = Math.min(h, totalH - 30);
        zoomHr.style.bottom = totalH - e.clientY - 2 + 'px';

        document.onmouseup = function() {
            zoomHr.remove();
            zoomDiv.style.height = h + "px";
            h = totalH - h + "px";
            contentCenter.style.height = h;
            contentRight.style.height = h;
            document.onmousemove = null;
            document.onmouseup = null;
        }
    }
}

function stopZoom(event) {
    e = event || window.event;
    e.stopPropagation();
    if(e.target.id === 'itesss'){
        devVvv.style.backgroundPosition = "-16px 0";
        devInfoOpen = true;
    }
    else if(e.target.id === 'devvv'){
        devVvv.style.backgroundPosition = "-16px 0";
        itsSystemOpen = true;
    }
}
