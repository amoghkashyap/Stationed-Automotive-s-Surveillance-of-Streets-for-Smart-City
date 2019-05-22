'use strict';

/**
 * @ngdoc function
 * @name ovniGuiApp.controller:ExampleCtrl
 * @description
 * # ExampleCtrl
 * Controller of the ovniGuiApp
 */
angular.module('ovniGuiApp').controller('ExampleCtrl', function () {

    var _self = this;

    _init();

    function _init() {
        console.log("ExampleCtrl Ctrl init function");
    

        _self.carData = [];
        _self.carMarkers = [];
        _self.map = L.map('map', {
            center: [12.95, 77.6],
            minZoom: 2,
            zoom: 11
        })

        L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>',
            subdomains: ['a', 'b', 'c']
        }).addTo(_self.map);

        // _self.myIcon = L.icon({
        //     iconUrl: 'images/pin24.png',
        //     iconRetinaUrl: 'images/pin48.png',
        //     iconSize: [29, 24],
        //     iconAnchor: [9, 21],
        //     popupAnchor: [0, -14]
        // })

        initSocketFunction();
    }

    function initSocketFunction() {
        var exampleSocket = new WebSocket("ws://10.142.228.140:8080/controller/websocketendpoint");
        exampleSocket.onopen = function (event) {

            var msg = {
                action: "hello22",
            };
            exampleSocket.send(JSON.stringify(msg));


        };

        exampleSocket.onmessage = function (event) {
            console.log(event.data);
            var server_message = JSON.parse(event.data);
            server_message.plateNumber = (server_message.plateNumber).replace(/ /g, '');
            var carPlateNumber = server_message.plateNumber;
            console.log("server_message.status"+server_message.status);
            if(server_message.status==='towing'){
                
        _self.myIcon = L.icon({
            iconUrl: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
            iconRetinaUrl: 'http://maps.google.com/mapfiles/ms/icons/red-dot.png',
            iconSize: [29, 24],
            iconAnchor: [9, 21],
            popupAnchor: [0, -14]
        })
            }
    else{         
        _self.myIcon = L.icon({
            iconUrl: 'images/pin24.png',
            iconRetinaUrl: 'images/pin48.png',
            iconSize: [29, 24],
            iconAnchor: [9, 21],
            popupAnchor: [0, -14]
        })

    }
            //  mapInitialise();

            addMarkerToMap(carPlateNumber, server_message);

        };
    }

    function addMarkerToMap(carPlateNumber, carData) {
        if (carData.status == 'left') {
            for (var i = 0; i < _self.carMarkers.length; i++) {
                if (_self.carMarkers[i].plateNumber == carPlateNumber) {
                    _self.map.removeLayer(_self.carMarkers[i].carMarker);
                    _self.carMarkers.splice(i, 1);
                    console.log("marker is removed for this carData");
                }
            }
        } else {
            var presentMarker;
            var found = _self.carMarkers.some(function (el) {
                if (el.plateNumber === carPlateNumber) {
                    presentMarker = el;
                    return true;
                }
                // return el.plateNumber === carPlateNumber;
            });

            if (!found) {
                var carMarker = new L.marker([carData.latitude, carData.longitude], { icon: _self.myIcon });
                _self.map.addLayer(carMarker);
                carMarker.bindPopup('<a href="' + '" target="_blank">' + carData.plateNumber + carData.status + carData.
                    phoneNumber + '</a>')
                _self.carMarkers.push({ "plateNumber": carPlateNumber, "carMarker": carMarker });

            } else {
                _self.map.removeLayer(presentMarker.carMarker);
                var carMarker = new L.marker([carData.latitude, carData.longitude], { icon: _self.myIcon });
                _self.map.addLayer(carMarker);
                carMarker.bindPopup('<a href="' + '" target="_blank">' + carData.plateNumber + carData.status + carData.
                    phoneNumber + '</a>')
                presentMarker.carMarker = carMarker;
                console.log("presentMarker", presentMarker);
            }


        }
    }

    function getSocketData(server_message) {
        console.log("server_message", server_message);
        server_message.plateNumber = (server_message.plateNumber).replace(/ /g, '');
        var carPlateNumber;
        var carPresent = false;
        // if (_self.carData.length <= 0) {
        //     _self.carData.push(server_message);
        //     carDataPushedIndex = 0;
        // } else {

        for (var i = 0; i < _self.carData.length; i++) {
            console.log("i", i);
            console.log(_self.carData[i].plateNumber);
            console.log(server_message.plateNumber);
            if (_self.carData[i].plateNumber == server_message.plateNumber) {
                // _self.carData.splice(i, 1);
                _self.carData[i].status = server_message.status;
                carPlateNumber = server_message.plateNumber;
                carPresent = true;
                break;
            }
        }


        if (carPresent == false) {
            _self.carData.push(server_message);
            carPlateNumber = server_message.plateNumber;
        }
        // }
        console.log("carPresent", carPresent);
        console.log("carData", _self.carData);

        mapInitialise(carPlateNumber, server_message);

    }

    function mapInitialise(carPlateNumber, carData) {
        // See post: http://asmaloney.com/2014/01/code/creating-an-interactive-map-with-leaflet-and-openstreetmap/
        //  var myURL = jQuery('script[src$="leaf-demo.js"]').attr('src').replace('leaf-demo.js', '')
        console.log("carPlateNumber: " + carPlateNumber + "------- ; carData : " + carData);
        console.log("_self.carMarkers", _self.carMarkers);

        if (_self.carMarkers.length > 0) {
            console.log("_self.carMarkers.length", _self.carMarkers.length);
            for (var i = 0; i < _self.carMarkers.length; i++) {

                if (_self.carMarkers[i].carPlateNumber == carPlateNumber) {
                    console.log(_self.carMarkers[i].carPlateNumber + " ----- " + carPlateNumber);
                    if (carData.status == "left") {
                        console.log("left yes");
                        _self.map.removeLayer(_self.carMarkers[i].carMarker);
                        _self.carMarkers.splice(i, 1);
                        console.log("marker is removed for this carData");
                        break;
                    } else {
                        console.log("left no");
                        _self.map.removeLayer(_self.carMarkers[i].carMarker);
                        _self.carMarkers.splice(i, 1);
                        var carMarker = new L.marker([carData.latitude, carData.longitude], { icon: _self.myIcon });
                        _self.map.addLayer(carMarker);
                        // var carMarker = _self.carMarkers[carIndex];
                        console.log("just bind popup carMarker", carMarker);
                        carMarker.bindPopup('<a href="' + '" target="_blank">' + carData.plateNumber + carData.status + carData.
                            phoneNumber + '</a>')
                        var data = { "carPlateNumber": carPlateNumber, "carMarker": carMarker };
                        _self.carMarkers.push({ "carPlateNumber": carPlateNumber, "carMarker": carMarker });
                        console.log("marker is removed and added for this carData");
                        break;
                    }
                } else {
                    var carMarker = new L.marker([carData.latitude, carData.longitude], { icon: _self.myIcon });
                    _self.map.addLayer(carMarker);
                    carMarker.bindPopup('<a href="' + '" target="_blank">' + carData.plateNumber + carData.status + carData.
                        phoneNumber + '</a>')
                    // .addTo(_self.map);
                    _self.carMarkers.push({ "carPlateNumber": carPlateNumber, "carMarker": carMarker });
                    console.log("marker is just added for this carData");
                    break;
                }
            }
        } else {
            var carMarker = new L.marker([carData.latitude, carData.longitude], { icon: _self.myIcon });
            _self.map.addLayer(carMarker);
            carMarker.bindPopup('<a href="' + '" target="_blank">' + carData.plateNumber + carData.status + carData.
                phoneNumber + '</a>')
            // .addTo(_self.map);
            _self.carMarkers.push({ "carPlateNumber": carPlateNumber, "carMarker": carMarker });
            console.log("marker is just added for this carData");
        }




        console.log("carMarkers", _self.carMarkers);


    }
});

