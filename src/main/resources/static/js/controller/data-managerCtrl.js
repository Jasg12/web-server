'use strict';

/**
 *
 * @name DataManagerDashboardCtrl
 * @description
 * # DataManagerDashboardCtrl
 *
 * Controller for the Data Manager Dashboard view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('DataManagerDashboardCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.data-manager-dashboard-container').height(height -120);
        }
    }
]);