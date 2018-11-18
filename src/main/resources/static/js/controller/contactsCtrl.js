'use strict';

/**
 *
 * @name ContactsCtrl
 * @description
 * # ContactsCtrl
 *
 * Controller for the Contacts view
 */
var dashboardApp = angular.module('dashboardApp');

dashboardApp.controller('ContactsCtrl', ['$scope', '$http', '$location', '$rootScope', '$routeParams',
    function ($scope, $http, $location, $rootScope, $routeParams) {
        calculateViewPortSize();
        $( window ).resize(function () {
            calculateViewPortSize()
        });
        function calculateViewPortSize() {
            var height = window.innerHeight;
            $('.contacts-container').height(height -120);
        }
    }
]);