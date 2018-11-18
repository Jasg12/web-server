'use strict';

/**
 *
 * @name sportBettingApp
 * @description
 * # sport betting
 *
 * Main module of the application.
 */
angular
    .module('dashboardApp', [
        //    'ngAnimate',
        //    'ngCookies',
        'ngResource',
        'ngRoute'
    ])
    .config(function ($routeProvider) {
        $routeProvider
        /*.when('/', {
            templateUrl: 'views/home.html',
            controller: 'HomeCtrl'
        })*/
            .when('/', {
                templateUrl: 'view/home-page.html'
            })
            .when('/dashboard/user', {
                templateUrl: 'view/user-dashboardView.html',
                controller: 'UserDashboardCtrl'
            })
            .when('/dashboard/iot-manager', {
                templateUrl: 'view/iot-managerView.html',
                controller: 'IoTManagerDashboardCtrl'
            })
            .when('/dashboard/data-manager', {
                templateUrl: 'view/data-managerView.html',
                controller: 'DataManagerDashboardCtrl'
            })
            .when('/terms',{
                templateUrl: 'view/terms.html',
                controller: 'TermsCtrl'
            })
            .when('/contacts',{
                templateUrl: 'view/contacts.html',
                controller: 'ContactsCtrl'
            })
            .otherwise({
                redirectTo: '/'
            });
    });