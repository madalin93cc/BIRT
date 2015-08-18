/**
 * Created by Colezea on 08/08/2015.
 */
app.controller('reportCtrl', ['$scope','$http', '$sce', 'ReportService',
    function($scope, $http, $sce, ReportService) {

    $scope.reports = [];
    $http({
        method: 'GET',
        url: '/getAllReportNames',
        data: {}
    }).success(function (result) {
        $scope.reports = result;
    });
    $scope.selectedReport = null;
    
    $scope.setSelected = function (selected) {
        if ($scope.selectedReport != selected) {
            $scope.selectedReport = selected;
            $scope.content = null;
        }
    }

    $scope.downloadReport = function (){
        var fileName = $scope.selectedReport.name;
        var a = document.createElement("a");
        document.body.appendChild(a);
        a.style = "display: none";
        ReportService.downloadReport($scope.selectedReport.id).then(function(response){
            var file = new Blob([(response.data)], {type: 'application/pdf'});
            var fileURL = URL.createObjectURL(file);
            a.href = fileURL;
            a.download = fileName;
            a.click();
            $scope.content = $sce.trustAsResourceUrl(fileURL);
            //window.open(fileURL);
        });

    }
}]);