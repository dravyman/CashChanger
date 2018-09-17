var app = angular.module('rev',[]);
app.controller('cashChanger', ['$scope', '$http', function ($scope, $http) {
    $scope.us = "123123";
    $scope.allUsers = [{"login": "Em", "cash" : 10000}, {"login": "Em2", "cash" : 12000}];
    console.log("I am working");
}]);