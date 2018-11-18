'use strict';

/**
 *
 * @name IoTManagerDashboardCtrl
 * @description
 * # IoTManagerDashboardCtrl
 *
 * Controller for the IoT Manager Dashboard view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('IoTManagerDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.iot-manager-dashboard-container').height(height -120);
        }
    }
]);