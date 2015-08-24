/**
 * Created by Colezea on 08/08/2015.
 */
app.controller('reportCtrl', ['$scope','$http', '$sce', '$window', 'ReportService',
    function($scope, $http, $sce, $window, ReportService) {

    $scope.reports = [];
    $scope.invoices = [];
    $http({
        method: 'GET',
        url: '/getAllReportNames',
        data: {}
    }).success(function (result) {
        $scope.reports = result;
    });

    $http({
        method: 'GET',
        url: '/getAllInvoices',
        data: {}
    }).success(function(result) {
        $scope.invoices = result;
        $scope.selectedInvoice = $scope.invoices[0];
        $scope.invoiceId = $scope.selectedInvoice.id;
    });
    $scope.selectedReport = null;
    $scope.invoiceId = null;
    
    $scope.setSelected = function (selected) {
        if ($scope.selectedReport != selected) {
            $scope.selectedReport = selected;
            $scope.content = null;
        }
    }

    $scope.setInvoice = function(invoice) {
        if ($scope.invoiceId != invoice.id){
            $scope.invoiceId = invoice.id;
        }
    }

    $scope.downloadReport = function (){
        var fileName = $scope.selectedReport.name;
        ReportService.downloadReport($scope.selectedReport.id, $scope.invoiceId).then(function(response){
            var file = new Blob([(response.data)], {type: 'application/pdf'});
            var fileURL = URL.createObjectURL(file);
            //$scope.content = $sce.trustAsResourceUrl(fileURL);
            $window.open(fileURL);
        });

    }
}]);