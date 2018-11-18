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
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.user-dashboard-container').height(height -120);
        }
    }
]);