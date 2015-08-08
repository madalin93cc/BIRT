/**
 * Created by Colezea on 08/08/2015.
 */
app.controller('reportCtrl', ['$scope','$http', '$sce', 'ReportService',
    function($scope, $http, $sce, ReportService) {

    $scope.reports = [{id:1, name:"Raport 1"}, {id:2, name:"Factura"}];
    $scope.selectedReportId = null;
    
    $scope.setSelected = function (selectedId) {
        $scope.selectedReportId = selectedId;
    }

    $scope.downloadReport = function (){
        var fileName = "CV_Colezea_Madalin.pdf";
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        ReportService.downloadReport($scope.selectedReportId).then(function(response){
            var file = new Blob([(response.data)], {type: 'application/pdf'});
            var fileURL = URL.createObjectURL(file);
            a.href = fileURL;
            a.download = fileName;
            a.click();
            //$scope.content = $sce.trustAsResourceUrl(fileURL);
            //window.open(fileURL);
        });

    }
}]);