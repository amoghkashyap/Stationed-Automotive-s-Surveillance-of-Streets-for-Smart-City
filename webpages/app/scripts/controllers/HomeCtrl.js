'use strict';

/**
 * @ngdoc function
 * @name ovniGuiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the ovniGuiApp
 */
angular.module('ovniGuiApp').controller('HomeCtrl', function () {
    
    console.log("home ctrl init ");

    var _self = this;

    _self.awesomeThings = [
        'HTML5 Boilerplate',
        'AngularJS',
        'Karma'
    ];
});                                         