var contextPath = "";
if(window.top.document.getElementById("contextPath")) {
    contextPath = window.top.document.getElementById("contextPath").getAttribute("data-path");
}
document.write('<script type="text/javascript" src="resources/ezfm/jquery-easyui-1.4.5/jquery.min.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/boot.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/jquery.ajax.httplistener.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/jquery.serializeObject.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/list.commons.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/window.commons.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/component.commons.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/zTree/js/jquery.ztree.core-3.5.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/zTree/js/jquery.ztree.excheck-3.5.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/role.button.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/files.js"></script>');
document.write('<script type="text/javascript" src="resources/ezfm/js/pub/valid_types.js"></script>');