'use strict';

/**
 *
 * @name UserDashboardCtrl
 * @description
 * # UserDashboardCtrl
 *
 * Controller for the User Dashboard view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('UserDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        /***** Variables *****/
        $scope.streetStat = [];
        $scope.zip = '';
        $scope.state = '';
        $scope.city = '';

        // Startup actions
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });

        /***** Callbacks *****/
        $scope.search = function () {
            var url = '/infrastructure/stat/' + $scope.state + '/' + $scope.city;
            console.log("Prepare the call to url:" + url);
            $http.get(url)
                .success(function(data){
                    console.log('Getting data from server', data);
                    $scope.streetStat = data;
                })
                .error(function(error){
                    console.error(error);
                });
        };

        /***** Internal functions *****/
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.user-dashboard-container').height(height -120);
        }
    }
]);