/**
 * Created by Colezea on 08/08/2015.
 */
app.service('ReportService', function ($http) {
    return {
        downloadReport: function (reportId, invoiceId) {
                return $http.get('/downloadReport/' + reportId + "/" + invoiceId,
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