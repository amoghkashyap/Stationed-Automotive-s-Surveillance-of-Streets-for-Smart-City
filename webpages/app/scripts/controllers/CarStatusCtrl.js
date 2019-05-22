'use strict';

/**
 * @ngdoc function
 * @name ovniGuiApp.controller:CarStatusCtrl
 * @description
 * # CarStatusCtrl
 * Controller of the ovniGuiApp
 */
angular.module('ovniGuiApp').controller('CarStatusCtrl', function () {

    var _self = this;

    _init();

    function _init() {
        console.log("CarStatus Ctrl init function");

        _self.markers = [
            
            {
                
                "status": "Detected",
                "plate_number": "KA 05 JX 6451",
                "lat": 13.0526,
                "lng": 77.5666,
                "phNo": "+918904655657"

            },
            {
                "status": "Warning",
                "plate_number": "KA 19 AB 1996",
                "lat": 18.220554,
                "lng": -63.068615,
                "phNo": "+919164192836"
            },
            {
                "status": "penalty",
                "plate_number": "JK 01 DZ 3415",
                "lat": 13.193887,
                "lng": -59.543198,
                "phNo": "+917259825389"
            },
            {
                "status": "tow",
                "plate_number": "KA 09 EF 8464",
                "lat": 37.090240,
                "lng": -95.712891,
                "phNo": "+919164192836"

            }

        ];
console.log("hi");

        mapInitialise();
    }

    function mapInitialise() {
        // See post: http://asmaloney.com/2014/01/code/creating-an-interactive-map-with-leaflet-and-openstreetmap/
console.log("hello");
        _self.map = L.map('map', {
            center: [20.0, 5.0],
            minZoom: 2,
            zoom: 2
        })

        L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>',
            subdomains: ['a', 'b', 'c']
        }).addTo(_self.map)

        // var myURL = jQuery('script[src$="leaf-demo.js"]').attr('src').replace('leaf-demo.js', '')

        var myIcon = L.icon({
            iconUrl: 'images/pin24.png',
            iconRetinaUrl: 'images/pin48.png',
            iconSize: [29, 24],
            iconAnchor: [9, 21],
            popupAnchor: [0, -14]
        })

        for (var i = 0; i < _self.markers.length; ++i) {
            L.marker([_self.markers[i].lat, _self.markers[i].lng], { icon: myIcon })
                .bindPopup('<a href="' + _self.markers[i].url + '" target="_blank">' + _self.markers[i].plate_number + _self.markers[i].status + _self.markers[i].phNo + '</a>')
                .addTo(_self.map);
              
        }


    }
});                                         