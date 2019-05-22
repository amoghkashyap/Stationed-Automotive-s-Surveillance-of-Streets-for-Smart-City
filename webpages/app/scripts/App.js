'use strict';

/**
 * @ngdoc overview
 * @name ovniGuiApp
 * @description
 * # ovniGuiApp
 *
 * Main module of the application.
 */
var app = angular.module('ovniGuiApp',
    [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.router',
        'ngScrollbars'
    ]
);

app.config(function ($stateProvider, $urlRouterProvider) {

   var dashboard = {
        name: 'carStatus',
        url: '/car-status',
        templateUrl: 'views/carStatus.html',
        controller: 'CarStatusCtrl',
        controllerAs: 'carStatus'
    };

    var history = {
        name: 'history',
        url: '/mission-history',
        templateUrl: "views/history.html"
    };
    var example ={
        name:'example',
        url: '/mission-example',
        templateUrl: "views/example.html",
         controller: 'ExampleCtrl'

    };
    $stateProvider.state('dashboard', dashboard);
    $stateProvider.state('history', history);
     $stateProvider.state('example', example);


    $urlRouterProvider.otherwise("/mission-example");
});