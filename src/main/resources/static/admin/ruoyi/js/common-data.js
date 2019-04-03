
var urlPrefix = "/admin";

var urlGetShipSimpleList =  "/admin/pipe/ship/selectShipSimpleList";
var urlPostBatchSimpleList = "/admin/pipe/batch/selectBatchSimpleList";
var urlPostUnitSimpleList = "/admin/pipe/unit/selectUnitSimpleList";

function ajaxGet(url,data,requestType) {
    var resultData = null;
    $.ajax({
        type: requestType,
        url: url,
        async:false,
        data: data,
        success: function(result) {
            if (result.length > 0) {
                resultData = result;
                alert(result);
            } else {
                alert(result);
            }
        }
    });
    return resultData;
}