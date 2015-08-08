/**
 * Created by Colezea on 08/08/2015.
 */
app.service('ReportService', function ($http) {
    //this.downloadReport = function(reportId){
    //    if (reportId !== null) {
    //        console.log("Downoading report with id " + reportId);
    //        $http.get('/downloadReport/' + reportId,
    //            {responseType:'arraybuffer'})
    //            .success(function (response) {
    //                return response;
    //                //debugger;
    //                //var file = new Blob([(response)], {type: 'application/pdf'});
    //                //var fileURL = URL.createObjectURL(file);
    //                //window.open(fileURL);
    //                //$scope.content = $sce.trustAsResourceUrl(fileURL);
    //            })
    //            .error(function(){
    //                console.log("Error downloading report");
    //            });
    //    }
    //}

    return {
        downloadReport: function (reportId) {
                return $http.get('/downloadReport/' + reportId,
                            {responseType:'arraybuffer'})
                        .success(function (response) {
                            return response;
                        })
                        .error(function(){
                            console.log("Error downloading report");
                        });
        }
    }
});